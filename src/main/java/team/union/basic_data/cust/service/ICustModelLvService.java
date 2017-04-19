package team.union.basic_data.cust.service;

import team.union.basic_data.com.interf.IService;
import team.union.basic_data.cust.model.CustModelLv;

public interface ICustModelLvService extends IService<CustModelLv>{

	/** 判断邀请人电话是否存在  **/
	public CustModelLv selInviter(String inviterMp);
	/** 
	 * 根据邀请人层级设置客户层级关系
	 * @param Inviter  邀请人model
	 * @param cust     客户model
	 * @return
	 */
	public CustModelLv setCustModelLv(CustModelLv Inviter,CustModelLv cust);
	 
}
