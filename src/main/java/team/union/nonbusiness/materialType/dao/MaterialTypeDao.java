package team.union.nonbusiness.materialType.dao;

import java.util.List;
import java.util.Map;

import team.union.nonbusiness.com.interf.Mapper;
import team.union.nonbusiness.materialType.model.MaterialType;

public interface MaterialTypeDao extends Mapper<MaterialType> {
	public List<MaterialType> findAll();
	public List<MaterialType> findAllImportantAndUsing();
	public String getMaxChildNum(Map<String, Object> params);
	public List<MaterialType> list(Map<String, Object> args);
	public int updateWorkProcedureIdById(Map<String, Object> params);
	public Long getMaxWorkProcedureId();
	
}
