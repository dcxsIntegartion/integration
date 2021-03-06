package team.union.basic_data.materialType.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import team.union.sys_sp.com.cfg.BaseConfig;
import team.union.sys_sp.com.rs.BsgridVo;
import team.union.sys_sp.com.rs.ResultVo;
import team.union.basic_data.materialType.dao.MaterialTypeDao;
import team.union.basic_data.materialType.model.MaterialType;
import team.union.basic_data.materialType.service.IMaterialTypeService;
import team.union.basic_data.materialType.vo.MaterialTypeVo;

@Repository
@Transactional(rollbackFor = Exception.class)
public class MaterialTypeService implements IMaterialTypeService {
	@Autowired
	private MaterialTypeDao materialTypeDao;

	public MaterialTypeVo findAllTreeNode() {
		List<MaterialType> list = this.materialTypeDao.findAll();
		List<MaterialTypeVo> voList = new ArrayList<MaterialTypeVo>();
		for (MaterialType mt : list) {
			if (mt.getLevel() == 1) {
				MaterialTypeVo vo = buildVo(mt);
				vo = buildChildrenNode(vo, list);
				voList.add(vo);
			}
		}
		MaterialTypeVo root = new MaterialTypeVo();
		root.setMaterialName("物资类型");
		root.setChildren(voList);
		root.setIsParent(true);
		return root;
	}

	
	public MaterialTypeVo findAllImportantAndUsingTreeNode() {
		List<MaterialType> list = this.materialTypeDao.findAllImportantAndUsing();
		List<MaterialTypeVo> voList = new ArrayList<MaterialTypeVo>();
		for (MaterialType mt : list) {
			if (mt.getLevel() == 1) {
				MaterialTypeVo vo = buildVo(mt);
				vo = buildChildrenNode(vo, list);
				voList.add(vo);
			}
		}
		MaterialTypeVo root = new MaterialTypeVo();
		root.setMaterialName("物资类型");
		root.setChildren(voList);
		root.setIsParent(true);
		return root;
	}
	
	private MaterialTypeVo buildVo(MaterialType mt) {
		MaterialTypeVo vo = new MaterialTypeVo(mt.getMaterialTypeId(), mt.getMaterialType(), mt.getMaterialNum(), mt.getMaterialName(), mt.getMaterialState(), mt.getSuperiorMaterialId(), mt.getWorkProcedureId(), mt.getLevel(), null, mt.getLevel()<3);
		return vo;
	}
	
	private MaterialType extractVo(MaterialTypeVo vo) {
		MaterialType mt = new MaterialType();
		mt.setMaterialTypeId(vo.getMaterialTypeId());
		mt.setLevel(vo.getLevel());
		mt.setMaterialName(vo.getMaterialName());
		mt.setMaterialNum(vo.getMaterialNum());
		mt.setMaterialState(vo.getMaterialState());
		mt.setMaterialType(vo.getMaterialType());
		mt.setSuperiorMaterialId(vo.getSuperiorMaterialId());
		mt.setWorkProcedureId(vo.getWorkProcedureId());
		return mt;
	}
	
	private MaterialTypeVo buildChildrenNode(MaterialTypeVo vo, List<MaterialType> list) {
		for(MaterialType mt : list) {
			if(vo.getMaterialTypeId().equals(mt.getSuperiorMaterialId())) {
				MaterialTypeVo cvo = buildVo(mt);
				buildChildrenNode(cvo, list);
				if(vo.getChildren() == null) {
					vo.setChildren(new ArrayList<MaterialTypeVo>());
				}
				vo.getChildren().add(cvo);
			}
		}
		return vo;
	}

	
	public ResultVo saveOrUpdateMaterialType(MaterialTypeVo vo) {
		ResultVo resultVo = new ResultVo();
		if(vo.getMaterialTypeId() != null && vo.getMaterialTypeId() > 0) {
			this.materialTypeDao.updateByPrimaryKeySelective(extractVo(vo));
		} else {
			MaterialType pvo = this.materialTypeDao.selectByPrimaryKey(vo.getSuperiorMaterialId());
			Map<String, Object> params = new HashMap<String, Object>();
			if(pvo != null) {
				params.put("pid", pvo.getMaterialTypeId());
			} else {
				params.put("pid", null);
			}
			params.put("level", vo.getLevel());
			String childMaxNum = this.materialTypeDao.getMaxChildNum(params);
			String currNumStr = "";
			if(childMaxNum == null) {
				if(pvo != null) {
					currNumStr = pvo.getMaterialNum() + "01";
				} else {
					currNumStr = "01";
				}
			} else { 
				String maxNumStr = childMaxNum.substring(childMaxNum.length() - 2);
				int currNum = Integer.parseInt(maxNumStr)+1;
				currNumStr = childMaxNum.substring(0, childMaxNum.length() - 2) + (currNum<10?"0"+currNum:currNum);
			}
			vo.setMaterialNum(currNumStr);
			this.materialTypeDao.insert(extractVo(vo));
		}
		resultVo.setData(vo);
		resultVo.setInfo("保存成功");
		resultVo.setStatus(BaseConfig.SUCCESS_STATUS);
		return resultVo;
	}
	
	public String generateMaterialNumByPid(Long pid) {
		MaterialType pvo = this.materialTypeDao.selectByPrimaryKey(pid);
		Map<String, Object> params = new HashMap<String, Object>();
		if(pvo != null) {
			params.put("pid", pvo.getMaterialTypeId());
			params.put("level", pvo.getLevel()-1);
		} else {
			params.put("pid", null);
			params.put("level", 1);
		}
	
		String childMaxNum = this.materialTypeDao.getMaxChildNum(params);
		String currNumStr = "";
		if(childMaxNum == null) {
			if(pvo != null) {
				currNumStr = pvo.getMaterialNum() + "01";
			} else {
				currNumStr = "01";
			}
		} else { 
			String maxNumStr = childMaxNum.substring(childMaxNum.length() - 2);
			int currNum = Integer.parseInt(maxNumStr)+1;
			currNumStr = childMaxNum.substring(0, childMaxNum.length() - 2) + (currNum<10?"0"+currNum:currNum);
		}
		return currNumStr;
	}

	
	public BsgridVo<MaterialType> list(Map<String, Object> args, int page, int rows) {
		PageHelper.startPage(page, rows);		
		List<MaterialType> content = materialTypeDao.list(args);
		Page<MaterialType> result = (Page<MaterialType>) content;
		BsgridVo<MaterialType> bsgridVo = new BsgridVo<MaterialType>();
		bsgridVo.setCurPage(page);
		bsgridVo.setData(result);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(result.getTotal());
		return bsgridVo;
	}

	@SuppressWarnings("unchecked")
	public ResultVo addToGroup(Map<String, Object> params) {
		ResultVo resultVo = new ResultVo();
		Long workProcedureId = Long.valueOf(params.get("workProcedureId").toString());
		List<Long> ids = (List<Long>) params.get("checkedIds");
		Long currWorkProcedureId = -1L;
		if(workProcedureId == null || workProcedureId.equals(-1L)) {
			currWorkProcedureId = this.materialTypeDao.getMaxWorkProcedureId() + 1;
		} else {
			currWorkProcedureId = workProcedureId;
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("workProcedureId", currWorkProcedureId);
		map.put("ids", ids);
		this.materialTypeDao.updateWorkProcedureIdById(map);
		//操作记录
		resultVo.setInfo("保存成功");
		resultVo.setData(currWorkProcedureId);
		resultVo.setStatus(BaseConfig.SUCCESS_STATUS);
		return resultVo;
	}

	
	public ResultVo removeFromGroup(Map<String, Object> params) {
		ResultVo resultVo = new ResultVo();
		Long materialTypeId = Long.valueOf(params.get("materialTypeId").toString());
		List<Long> ids = new ArrayList<Long>();
		ids.add(materialTypeId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ids", ids);
		map.put("workProcedureId", null);
		this.materialTypeDao.updateWorkProcedureIdById(map);
		//操作记录
		resultVo.setInfo("移出成功");
		resultVo.setData(materialTypeId);
		resultVo.setStatus(BaseConfig.SUCCESS_STATUS);
		return resultVo;
	}

}