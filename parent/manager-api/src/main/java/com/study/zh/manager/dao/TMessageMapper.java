package com.study.zh.manager.dao;


import com.study.zh.bean.TMessage;

public interface TMessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMessage record);

    int insertSelective(TMessage record);

    TMessage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMessage record);

    int updateByPrimaryKey(TMessage record);
}