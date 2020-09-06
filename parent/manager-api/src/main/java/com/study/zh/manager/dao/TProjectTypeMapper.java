package com.study.zh.manager.dao;


import com.study.zh.bean.TProjectType;

public interface TProjectTypeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProjectType record);

    int insertSelective(TProjectType record);

    TProjectType selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TProjectType record);

    int updateByPrimaryKey(TProjectType record);
}