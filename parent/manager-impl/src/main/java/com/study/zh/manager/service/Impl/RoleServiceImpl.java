package com.study.zh.manager.service.Impl;

import java.util.List;
import java.util.Map;

import com.study.zh.bean.TRole;
import com.study.zh.bean.TRolePermission;
import com.study.zh.manager.dao.TRoleMapper;
import com.study.zh.manager.service.RoleService;
import com.study.zh.util.Page;
import com.study.zh.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private TRoleMapper roleDao;


	public Page<TRole> pageQuery(Map<String, Object> paramMap) {
		Page<TRole> rolePage = new Page<TRole>((Integer)paramMap.get("pageno"),(Integer)paramMap.get("pagesize"));
		
		paramMap.put("startIndex", rolePage.getStartIndex());
		
		List<TRole> roles = roleDao.pageQuery(paramMap);
		
		// 获取数据的总条数
		int count = roleDao.queryCount(paramMap);
		rolePage.setData(roles);
		rolePage.setTotalsize(count);
		return rolePage;
	}

	public int queryCount(Map<String, Object> paramMap) {
		return roleDao.queryCount(paramMap);
	}

	public void saveRole(TRole user) {
		roleDao.insert(user);
	}

	public TRole getRole(Integer id) {
		return roleDao.getRole(id);
	}

	public int updateRole(TRole user) {
		return roleDao.update(user);
	}

	public int deleteRole(Integer uid) {
		return roleDao.delete(uid);
	}

	public int batchDeleteRole(Integer[] uid) {
		return roleDao.batchDelete(uid);
	}

	public int batchDeleteRole(Data datas) {
		return roleDao.batchDeleteObj(datas);
	}

	public List<TRole> queryAllRole() {
		return roleDao.queryAllRole();
	}

	@Override
	public List<Integer> queryRoleidByUserid(Integer id) {
		return roleDao.queryRoleidByUserid(id);
	}

/*	@Override
	public void doAssignRoleByUserid(Integer userid, Integer[] ids) {
		roleDao.saveUserRole(userid,ids);
	}

	@Override
	public void doUnAssignRoleByUserid(Integer userid, Integer[] ids) {
		roleDao.deleteUserRole(userid,ids);
	}*/

	@Override
	public void doAssignRoleByUserid(Integer userid, List<Integer> ids) {
		roleDao.saveUserRole(userid,ids);
	}

	@Override
	public void doUnAssignRoleByUserid(Integer userid, List<Integer> ids) {
		roleDao.deleteUserRole(userid,ids);
	}

	@Override
	public int saveRolePermissionRelationship(Integer roleid, Data datas) {
		
		roleDao.deleteRolePermissionRelationship(roleid);
		
		int totalCount = 0 ;
		List<Integer> ids = datas.getIds();
		for (Integer permissionid : ids) {
			TRolePermission rp = new TRolePermission();
			rp.setRoleid(roleid);
			rp.setPermissionid(permissionid);
			int count = roleDao.insertRolePermission(rp);
			totalCount += count ;
		}
		
		return totalCount;
	}

}
