package team.union.nonbusiness.twotabrel.Service;
import team.union.business.com.interf.IService;
import team.union.nonbusiness.twotabrel.model.NonbisTwoTabRel;
import team.union.nonbusiness.twotabrel.vo.TwoTabRelWeb;

public interface INonbisTwoTabRelService extends IService<NonbisTwoTabRel>{
	/** 根据参数查询业务关系  **/
	public TwoTabRelWeb selColer(String bisId);
	
	
	
}
