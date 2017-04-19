package team.union.nonbusiness.sys.dao;

import java.util.HashMap;

import team.union.nonbusiness.com.interf.Mapper;
import team.union.nonbusiness.sys.model.Roles;

public interface RolesDao  extends Mapper<Roles>{
    	public Roles getRoleByTypeName(HashMap<Object, String> map);
}