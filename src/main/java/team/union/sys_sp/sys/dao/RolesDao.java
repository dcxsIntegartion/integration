package team.union.sys_sp.sys.dao;

import java.util.HashMap;

import team.union.sys_sp.com.interf.Mapper;
import team.union.sys_sp.sys.model.Roles;

public interface RolesDao  extends Mapper<Roles>{
    	public Roles getRoleByTypeName(HashMap<Object, String> map);
}