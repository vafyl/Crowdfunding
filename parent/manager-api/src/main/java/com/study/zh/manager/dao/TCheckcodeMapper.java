package com.study.zh.manager.dao;


import com.study.zh.bean.TCheckcode;

public interface TCheckcodeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TCheckcode record);

    int insertSelective(TCheckcode record);

    TCheckcode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TCheckcode record);

    int updateByPrimaryKey(TCheckcode record);

    void saveCheckcode(String checkcode);

    TCheckcode selectByCheckcode(Integer emailcheck);
}