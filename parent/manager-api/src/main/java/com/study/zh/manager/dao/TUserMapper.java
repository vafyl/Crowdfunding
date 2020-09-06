package com.study.zh.manager.dao;


import com.study.zh.bean.TPermission;
import com.study.zh.bean.TRole;
import com.study.zh.bean.TUser;
import com.study.zh.vo.Data;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface TUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TUser record);

    int insertSelective(TUser record);

    TUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TUser record);

    int updateByPrimaryKey(TUser record);

    TUser queryUserLogin(Map<String, Object> paramMap);

//    List<TUser> queryList(@Param(value = "startIndex") Integer startIndex,
//                          @Param(value = "pagesize") Integer pagesize);

    Integer queryCount(Map<String, Object> paramMap);

    List<TUser> queryList(Map<String, Object> paramMap);

    int deleteBatchUserByVO(@Param(value = "userList") List<TUser> datas);

    List<Integer> queryRoleByUserid(Integer id);

    List<TRole> queryAllRole();

    //查询所有用户
    List<TUser> queryAllUser();

    void saveUserRoleRelationshop(@Param("userid") Integer userid,@Param("data")  Data data);

    void deleteUserRoleRelationshop(@Param("userid") Integer userid,@Param("data")  Data data);

    void saveAllUserRoleRelationshop(@Param("userid") Integer userid,@Param("data") Data data);

    void deleteAllUserRoleRelationshop(@Param("userid") Integer userid,@Param("data") Data data);

    List<TPermission> queryPermissionByUserid(Integer id);

    int selectCount( @Param("loginacct") String loginacct);


    //int deleteBatchUserByVO(Data data);

    //int deleteBatchUserByVO(List<User> userList);

    //int deleteBatchUserByVO(User[] userList);1

}