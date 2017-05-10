package team.union.basic_data.materialType.dao;

import java.util.List;
import java.util.Map;

import team.union.basic_data.materialType.model.MaterialType;
import team.union.nonbusiness.com.interf.Mapper;

public interface MaterialTypeDao extends Mapper<MaterialType>{
	public List<MaterialType> findAll();
	public List<MaterialType> findAllImportantAndUsing();
	public String getMaxChildNum(Map<String, Object> params);
	public List<MaterialType> list(Map<String, Object> args);
	public int updateWorkProcedureIdById(Map<String, Object> params);
	public Long getMaxWorkProcedureId();
	
}
