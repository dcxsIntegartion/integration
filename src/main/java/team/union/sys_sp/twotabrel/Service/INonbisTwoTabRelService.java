package team.union.sys_sp.twotabrel.Service;
import team.union.business.com.interf.IService;
import team.union.sys_sp.twotabrel.model.NonbisTwoTabRel;
import team.union.sys_sp.twotabrel.vo.TwoTabRelWeb;

public interface INonbisTwoTabRelService extends IService<NonbisTwoTabRel>{
	/** 根据参数查询业务关系  **/
	public TwoTabRelWeb selColer(String bisId);
	
	
	
}
