package team.union.business.mach.servie.Impl;

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
import team.union.business.mach.dao.BisMachDao;
import team.union.business.mach.model.BisMach;
import team.union.business.mach.servie.IBisMachService;
import team.union.sys_sp.com.cfg.PromptMsgConfig.PROMPT;
/**
 * Title: 机构管理
 * @author chenS
 * @date 2017-4-1
 * @version 1.0
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class BisMachServieImpl implements IBisMachService{

	@Autowired
	private  BisMachDao bisMachDao;
	
	
	public BsgridVo<HashMap<String, Object>> paging(Map<String, Object> parm,
			int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  bisMachDao.selMap(parm);
		Page<HashMap<String, Object>> pageData = (Page<HashMap<String, Object>>) data;
		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<HashMap<String, Object>>();
		bsgridVo.setCurPage(curPage);
		bsgridVo.setData(pageData);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(pageData.getTotal());
		return bsgridVo;
	}

	
	public List<HashMap<String, Object>> selMapLst(Map<String, Object> parm) {
		List<HashMap<String, Object>> data =  bisMachDao.selMap(parm);
		return data;
	}

	
	public List<BisMach> selVoLst(Map<String, Object> parm) {
		List<BisMach> data =  bisMachDao.selVo(parm);
		return data;
	}

	
	public Result selById(Long id) {
		Result result = new Result();
		BisMach vo = bisMachDao.selectByPrimaryKey(id);
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
	
	public Result add(BisMach vo) {
		Result result = new Result();
		if(null!=vo && null == vo.getId()){
			bisMachDao.insert(vo);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		}else{
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg(PROMPT.FAIL.getMsg());
		}
		return result;
	}
	
	public Result update(BisMach vo) {
		Result result = new Result();
		if(null!=vo && null!=vo.getId() &&
			null != bisMachDao.selectByPrimaryKey(vo.getId())){
			bisMachDao.updateByPrimaryKeySelective(vo);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		}else{
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg(PROMPT.FAIL.getMsg());
		}
		return result;
	}

	
	public Result updateBySelective(BisMach vo) {
		Result result = new Result();
		if(null!=vo && null!=vo.getId() &&
			null != bisMachDao.selectByPrimaryKey(vo.getId())){
			bisMachDao.updateByPrimaryKeySelective(vo);
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
		BisMach vo = bisMachDao.selectByPrimaryKey(id);
		if(null!=vo){
			bisMachDao.deleteByPrimaryKey(id);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		}else{
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg(PROMPT.FAIL.getMsg());
		}
		return result;
	}

}
