package com.study.zh.manager.dao;


import com.study.zh.bean.TType;

public interface TTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TType record);

    int insertSelective(TType record);

    TType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TType record);

    int updateByPrimaryKey(TType record);
}