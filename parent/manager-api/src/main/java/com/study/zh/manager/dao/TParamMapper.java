package com.study.zh.manager.dao;


import com.study.zh.bean.TParam;

public interface TParamMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TParam record);

    int insertSelective(TParam record);

    TParam selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TParam record);

    int updateByPrimaryKey(TParam record);
}