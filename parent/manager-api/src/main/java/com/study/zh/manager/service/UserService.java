package com.study.zh.manager.service;

import com.study.zh.bean.TPermission;
import com.study.zh.bean.TRole;
import com.study.zh.bean.TUser;
import com.study.zh.util.Page;
import com.study.zh.vo.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {

    TUser queryUserLogin(Map<String, Object> paramMap);

//    Page queryUserPage(Integer pageno, Integer pagesize);

    int saveUser(TUser user);

    int insertUser(TUser user);
    Page queryUserPage(Map<String, Object> paramMap);

    TUser getUserById(Integer id);

    int updateUser(TUser user);

    int deleteUser(Integer id);

//    int deletBatchUser(Integer[] ids);

    int deleteBatchUserByVO(Data data);

    List<TUser> queryAllUser();

    List<TRole> queryAllRole();

    List<Integer> queryRoleByUserid(Integer id);

    void saveUserRoleRelationshop(Integer userid,Data data);

    void deleteUserRoleRelationshop(Integer userid,Data data);

    void saveAllUserRoleRelationshop(Integer userid, Data data);

    void deleteAllUserRoleRelationshop(Integer userid, Data data);

    List<TPermission> queryPermissionByUserid(Integer id);

    int adduser(MultipartFile multipartFile) throws IOException;
}
