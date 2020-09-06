package com.study.zh.manager.service;


import com.study.zh.bean.TPermission;

import java.util.List;

public interface PermissionService {
    int savePermission(TPermission permission);

    List<TPermission> queryAllPermission();

    int updatePermission(TPermission permission);

    TPermission getPermissionById(Integer id);

    int deletePermission(Integer id);

    List<Integer> queryPermissionidsByRoleid(Integer roleid);
}
