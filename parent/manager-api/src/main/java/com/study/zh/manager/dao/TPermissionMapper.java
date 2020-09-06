package com.study.zh.manager.dao;


import com.study.zh.bean.TPermission;

import java.util.List;

public interface TPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TPermission record);

    int insertSelective(TPermission record);

    TPermission selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TPermission record);

    int updateByPrimaryKey(TPermission record);

    List<TPermission> queryAllPermission();

    List<Integer> queryPermissionidsByRoleid(Integer roleid);
}