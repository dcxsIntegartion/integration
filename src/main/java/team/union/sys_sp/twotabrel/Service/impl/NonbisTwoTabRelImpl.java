package team.union.sys_sp.twotabrel.Service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import team.union.basic_data.com.rs.BsgridVo;
import team.union.basic_data.com.rs.Result;
import team.union.sys_sp.com.cfg.PromptMsgConfig.PROMPT;
import team.union.sys_sp.twotabrel.Service.INonbisTwoTabRelService;
import team.union.sys_sp.twotabrel.dao.NonbisTwoTabRelDao;
import team.union.sys_sp.twotabrel.model.NonbisTwoTabRel;
import team.union.sys_sp.twotabrel.vo.TwoTabRelWeb;
import team.union.sys_sp.util.ToolsUtil;
/**
 * Title: 机构下属关联关系
 * @author chenS
 * @date 2017-4-1
 * @version 1.0
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class NonbisTwoTabRelImpl implements INonbisTwoTabRelService{

	@Autowired
	private NonbisTwoTabRelDao nonbisTwoTabRelDao;
	
	public BsgridVo<HashMap<String, Object>> paging(Map<String, Object> parm,
			int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  nonbisTwoTabRelDao.selMap(parm);
		Page<HashMap<String, Object>> pageData = (Page<HashMap<String, Object>>) data;
		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<HashMap<String, Object>>();
		bsgridVo.setCurPage(curPage);
		bsgridVo.setData(pageData);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(pageData.getTotal());
		return bsgridVo;
	}

	public List<HashMap<String, Object>> selMapLst(Map<String, Object> parm) {
		List<HashMap<String, Object>> data =  nonbisTwoTabRelDao.selMap(parm);
		return data;
	}

	public List<NonbisTwoTabRel> selVoLst(Map<String, Object> parm) {
		List<NonbisTwoTabRel> data =  nonbisTwoTabRelDao.selVo(parm);
		return data;
	}

	public Result selById(Long id) {
		Result result = new Result();
		NonbisTwoTabRel vo = nonbisTwoTabRelDao.selectByPrimaryKey(id);
		if(vo!=null){
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setData(vo);
		}else{
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg(PROMPT.FAIL.getMsg());
		}
		return result;
	}

	public Result add(NonbisTwoTabRel vo) {
		Result result = new Result();
		if(null!=vo && null == vo.getId()){
			nonbisTwoTabRelDao.insert(vo);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		}else{
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg(PROMPT.FAIL.getMsg());
		}
		return result;
	}

	public Result update(NonbisTwoTabRel vo) {
		Result result = new Result();
		if(null!=vo && null!=vo.getId() &&
			null != nonbisTwoTabRelDao.selectByPrimaryKey(vo.getId())){
			nonbisTwoTabRelDao.updateByPrimaryKey(vo);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		}else{
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg(PROMPT.FAIL.getMsg());
		}
		return result;
	}

	public Result updateBySelective(NonbisTwoTabRel vo) {
		Result result = new Result();
		if(null!=vo && null!=vo.getId() &&
			null != nonbisTwoTabRelDao.selectByPrimaryKey(vo.getId())){
			nonbisTwoTabRelDao.updateByPrimaryKeySelective(vo);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		}else{
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg(PROMPT.FAIL.getMsg());
		}
		return result;
	}

	public Result delById(Long id) {
		Result result = new Result();
		NonbisTwoTabRel vo = nonbisTwoTabRelDao.selectByPrimaryKey(id);
		if(null!=vo){
			nonbisTwoTabRelDao.deleteByPrimaryKey(id);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		}else{
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg(PROMPT.FAIL.getMsg());
		}
		return result;
	}

	public TwoTabRelWeb selColer(String bisId) {
		TwoTabRelWeb tabRel = new TwoTabRelWeb();
		if(ToolsUtil.isNotEmpty(bisId)){
			Map<String, Object> parm = new HashMap<String, Object>();
			parm.put("bisId", bisId);
			List<NonbisTwoTabRel> lst = nonbisTwoTabRelDao.selColer(parm);
			if(lst.size()>0){
				String[] colers = new String[lst.size()];
				String[] mainIds = new String[lst.size()];
				for(int i=0;i<lst.size();i++){
					colers[i] = lst.get(i).getColer();
					mainIds[i] = lst.get(i).getMainId().toString();
				}
				tabRel.setColers(colers);
				tabRel.setMainIds(mainIds);
			}
		}
		return tabRel;
	}

}
