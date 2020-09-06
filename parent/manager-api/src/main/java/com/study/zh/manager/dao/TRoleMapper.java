package com.study.zh.manager.dao;


import com.study.zh.bean.TRole;
import com.study.zh.bean.TRolePermission;
import com.study.zh.vo.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TRoleMapper {
    List<TRole> pageQuery(Map<String, Object> paramMap);

    int queryCount(Map<String, Object> paramMap);

    void insert(TRole user);

    TRole getRole(Integer id);

    int update(TRole role);

    int delete(Integer uid);

    int batchDelete(@Param("ids") Integer[] uid);

    int batchDeleteObj(Data datas);

    List<TRole> queryAllRole();

    List<Integer> queryRoleidByUserid(Integer id);

    /*void saveUserRole(@Param("userid") Integer userid, @Param("roleids") Integer[] ids);

    void deleteUserRole(@Param("userid") Integer userid,@Param("roleids")  Integer[] ids);*/
    void saveUserRole(@Param("userid") Integer userid, @Param("roleids") List<Integer> ids);

    void deleteUserRole(@Param("userid") Integer userid,@Param("roleids")  List<Integer> ids);

    int insertRolePermission(TRolePermission rp);

    void deleteRolePermissionRelationship(Integer roleid);

}