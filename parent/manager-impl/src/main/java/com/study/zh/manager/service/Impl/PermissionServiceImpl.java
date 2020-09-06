package com.study.zh.manager.service.Impl;

import com.study.zh.bean.TPermission;
import com.study.zh.manager.dao.TPermissionMapper;
import com.study.zh.manager.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private TPermissionMapper permissionMapper;


    @Override
    public List<TPermission> queryAllPermission() {
        return permissionMapper.queryAllPermission();
    }

    @Override
    public TPermission getPermissionById(Integer id) {
        return permissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int deletePermission(Integer id) {
        return permissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Integer> queryPermissionidsByRoleid(Integer roleid) {
        return permissionMapper.queryPermissionidsByRoleid(roleid);
    }

    @Override
    public int updatePermission(TPermission permission) {
        return permissionMapper.updateByPrimaryKeySelective(permission);
    }


    @Override
    public int savePermission(TPermission permission) {
        return permissionMapper.insert(permission);
    }
}
