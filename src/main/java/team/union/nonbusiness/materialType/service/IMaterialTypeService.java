package team.union.nonbusiness.materialType.service;

import java.util.Map;

import team.union.nonbusiness.com.rs.BsgridVo;
import team.union.nonbusiness.com.rs.ResultVo;
import team.union.nonbusiness.materialType.model.MaterialType;
import team.union.nonbusiness.materialType.vo.MaterialTypeVo;


public interface IMaterialTypeService {
	public MaterialTypeVo findAllTreeNode();
	public MaterialTypeVo findAllImportantAndUsingTreeNode();
	public ResultVo saveOrUpdateMaterialType(MaterialTypeVo vo);
	public BsgridVo<MaterialType> list(Map<String, Object> args, int page, int rows);
	public ResultVo addToGroup(Map<String, Object> params);
	public ResultVo removeFromGroup(Map<String, Object> params);
	
}
