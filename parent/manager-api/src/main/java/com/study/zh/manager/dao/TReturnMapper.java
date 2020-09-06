package com.study.zh.manager.dao;


import com.study.zh.bean.TReturn;

public interface TReturnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TReturn record);

    int insertSelective(TReturn record);

    TReturn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TReturn record);

    int updateByPrimaryKey(TReturn record);
}