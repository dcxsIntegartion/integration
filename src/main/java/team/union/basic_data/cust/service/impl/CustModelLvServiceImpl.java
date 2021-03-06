package team.union.basic_data.cust.service.impl;

import java.util.ArrayList;
import java.util.Date;
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
import team.union.basic_data.cust.dao.CustModelLvDao;
import team.union.basic_data.cust.model.CustModelLv;
import team.union.basic_data.cust.service.ICustModelLvService;
import team.union.sys_sp.com.cfg.PromptMsgConfig.PROMPT;
import team.union.sys_sp.util.ToolsUtil;
/**
 * Title: 客户分层级
 * @author chenS
 * @date 2017-3-25
 * @version 1.0
 */
@Repository
@Transactional(rollbackFor = Exception.class)
public class CustModelLvServiceImpl implements ICustModelLvService{

	@Autowired
	private CustModelLvDao custModelLvDao;
	
	
	public BsgridVo<HashMap<String, Object>> paging(Map<String, Object> parm,
			int curPage, int pageSize) {
		PageHelper.startPage(curPage, pageSize);
		List<HashMap<String, Object>> data =  custModelLvDao.selMap(parm);
		Page<HashMap<String, Object>> pageData = (Page<HashMap<String, Object>>) data;
		BsgridVo<HashMap<String, Object>> bsgridVo = new BsgridVo<HashMap<String, Object>>();
		bsgridVo.setCurPage(curPage);
		bsgridVo.setData(pageData);
		bsgridVo.setSuccess(true);
		bsgridVo.setTotalRows(pageData.getTotal());
		return bsgridVo;
	}

	
	public List<HashMap<String, Object>> selMapLst(Map<String, Object> parm) {
		List<HashMap<String, Object>> data =  custModelLvDao.selMap(parm);
		return data;
	}

	
	public List<CustModelLv> selVoLst(Map<String, Object> parm) {
		List<CustModelLv> data =  custModelLvDao.selVo(parm);
		return data;
	}

	
	public Result selById(Long id) {
		Result result = new Result();
		CustModelLv vo = custModelLvDao.selectByPrimaryKey(id);
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
	
	public Result add(CustModelLv vo) {
		Result result = new Result();
		if(null!=vo && null == vo.getId()){
			vo.setJoinTime(new Date());
			//设置客户层级
			if(ToolsUtil.isNotEmpty(vo.getInviterMp())){
				vo = setCustModelLv(selInviter(vo.getInviterMp()),vo);
			}
			custModelLvDao.insert(vo);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		}else{
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg(PROMPT.FAIL.getMsg());
		}
		return result;
	}
	
	public Result update(CustModelLv vo) {
		Result result = new Result();
		if(null!=vo && null!=vo.getId() &&
			null != custModelLvDao.selectByPrimaryKey(vo.getId())){
			//邀请电话不能被修改
			vo.setInviterMp(null);
			custModelLvDao.updateByPrimaryKeySelective(vo);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		}else{
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg(PROMPT.FAIL.getMsg());
		}
		return result;
	}

	
	public Result updateBySelective(CustModelLv vo) {
		Result result = new Result();
		if(null!=vo && null!=vo.getId() &&
			null != custModelLvDao.selectByPrimaryKey(vo.getId())){
			custModelLvDao.updateByPrimaryKeySelective(vo);
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
		CustModelLv vo = custModelLvDao.selectByPrimaryKey(id);
		if(null!=vo){
			custModelLvDao.deleteByPrimaryKey(id);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		}else{
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg(PROMPT.FAIL.getMsg());
		}
		return result;
	}

	
	public CustModelLv selInviter(String inviterMp) {
		List<CustModelLv> lst = new ArrayList<CustModelLv>();
		if(ToolsUtil.isNotEmpty(inviterMp)){
			Map<String, Object> parm = new HashMap<String,Object>();
			parm.put("inviterMp", inviterMp);
			lst = custModelLvDao.selVo(parm);
		}
		if(lst.size()==1){
			return lst.get(0);
		}
		return null;
	}

	
	public CustModelLv setCustModelLv(CustModelLv Inviter, CustModelLv cust) {
		if(null!=Inviter && Inviter.getId()!=null && null!=cust){
			cust.setOneNo(Inviter.getId());
			cust.setTwoNo(Inviter.getOneNo());
			cust.setThreeNo(Inviter.getTwoNo());
			cust.setFourNo(Inviter.getThreeNo());
			cust.setFiveNo(Inviter.getFourNo());
			cust.setSixNo(Inviter.getFiveNo());
		}else{
			return cust;
		}
		return cust;
	}
}
