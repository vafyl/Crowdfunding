package com.study.zh.manager.service;

import java.util.List;
import java.util.Map;


import com.study.zh.bean.TRole;
import com.study.zh.util.Page;
import com.study.zh.vo.Data;


public interface RoleService {

	public Page<TRole> pageQuery(Map<String, Object> paramMap);

	public int queryCount(Map<String, Object> paramMap);

	public void saveRole(TRole user);

	public TRole getRole(Integer id);

	public int updateRole(TRole user);

	public int deleteRole(Integer uid);

	public int batchDeleteRole(Integer[] uid);
	
	public int batchDeleteRole(Data datas);

	public List<TRole> queryAllRole();

	public List<Integer> queryRoleidByUserid(Integer id);

	/*public void doAssignRoleByUserid(Integer userid, Integer[] ids);

	public void doUnAssignRoleByUserid(Integer userid, Integer[] ids);*/


	public void doAssignRoleByUserid(Integer userid, List<Integer> ids);

	public void doUnAssignRoleByUserid(Integer userid, List<Integer> ids);

	public int saveRolePermissionRelationship(Integer roleid, Data datas);
}
