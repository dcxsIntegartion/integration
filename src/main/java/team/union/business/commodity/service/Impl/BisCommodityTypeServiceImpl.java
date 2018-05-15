package team.union.business.commodity.service.Impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import team.union.basic_data.com.rs.Result;
import team.union.business.commodity.dao.BisCommodityTypeDao;
import team.union.business.commodity.model.BisCommodityType;
import team.union.business.commodity.service.IBisCommodityTypeService;
import team.union.sys_sp.com.cfg.PromptMsgConfig.PROMPT;

@Repository
@Transactional(rollbackFor = Exception.class)
public class BisCommodityTypeServiceImpl implements IBisCommodityTypeService {

	@Autowired
	private BisCommodityTypeDao typeDao;
	
	@Override
	public Result typeTree() {
		Result result = new Result();
		try {
			List<BisCommodityType> list = typeDao.queryAll();
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setData(toTree(list));
		} catch (Exception e) {
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg("查询商品分类树失败");
		}
		return result;
	}

	@Override
	public Result listAll() {
		Result result = new Result();
		try {
			List<BisCommodityType> list = typeDao.queryAll();
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setData(list);
		} catch (Exception e) {
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg("查询商品分类失败");
		}
		return result;
	}

	@Override
	public Result update(BisCommodityType type) {
		Result result = new Result();
		try {
			typeDao.update(type);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		} catch (Exception e) {
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg("更新商品分类失败");
		}
		return result;
	}

	@Override
	public Result insert(BisCommodityType type) {
		Result result = new Result();
		try {
			typeDao.insert(type);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		} catch (Exception e) {
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg("新增商品分类失败");
		}
		return result;
	}

	@Override
	public Result delete(List<BigDecimal> ids) {
		Result result = new Result();
		try {
			typeDao.deleteByIds(ids);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
		} catch (Exception e) {
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg("删除商品分类失败");
		}
		return result;
	}
	
	@Override
	public Result findOne(BigDecimal id) {
		Result result = new Result();
		try {
			BisCommodityType type = typeDao.findById(id);
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setData(type);
		} catch (Exception e) {
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg("查找分类失败");
		}
		return result;
	}
	
	@Override
	public Result listSelect() {
		Result result = new Result();
		try {
			List<BisCommodityType> list = typeDao.querySelect();
			result.setState(PROMPT.SUCCESS.getNo());
			result.setMsg(PROMPT.SUCCESS.getMsg());
			result.setData(list);
		} catch (Exception e) {
			result.setState(PROMPT.FAIL.getNo());
			result.setMsg("查找下拉框失败");
		}
		return result;
	}

	/**
	 * 
	* 方法名:          toTree
	* 方法功能描述:    将list转化为所需要的树结构
	* @param list
	* @return  
	* @return:        List<BisCommodityType>
	* @Author:        zh
	* @Create Date:   2017年5月10日
	 */
	private List<BisCommodityType> toTree(List<BisCommodityType> list){
		List<BisCommodityType> tree = new ArrayList<>();
		for(BisCommodityType type:list){
			if (type.getParTypeId() == null) {
				tree.add(type);
			}
		}
		for(BisCommodityType p:tree){
			p.setChildren(children(list,p.getTypeId()));
		}
		return tree;
	}
	
	private List<BisCommodityType> children(List<BisCommodityType> list,BigDecimal parId){
		List<BisCommodityType> chList = new ArrayList<>();
		for(BisCommodityType type:list){
			if (type.getParTypeId() == parId || parId.equals(type.getParTypeId())) {
			children(list, type.getTypeId());
				chList.add(type);
			}
		}
		return chList;
	}

}
