package com.study.zh.manager.dao;


import com.study.zh.bean.TProjectTag;

public interface TProjectTagMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProjectTag record);

    int insertSelective(TProjectTag record);

    TProjectTag selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TProjectTag record);

    int updateByPrimaryKey(TProjectTag record);
}