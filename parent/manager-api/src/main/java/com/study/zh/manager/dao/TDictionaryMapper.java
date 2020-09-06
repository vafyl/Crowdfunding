package com.study.zh.manager.dao;


import com.study.zh.bean.TDictionary;

public interface TDictionaryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TDictionary record);

    int insertSelective(TDictionary record);

    TDictionary selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TDictionary record);

    int updateByPrimaryKey(TDictionary record);
}