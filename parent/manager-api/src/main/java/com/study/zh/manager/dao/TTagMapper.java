package com.study.zh.manager.dao;


import com.study.zh.bean.TTag;

public interface TTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTag record);

    int insertSelective(TTag record);

    TTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTag record);

    int updateByPrimaryKey(TTag record);
}