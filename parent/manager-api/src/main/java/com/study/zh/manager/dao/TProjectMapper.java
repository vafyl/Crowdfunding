package com.study.zh.manager.dao;


import com.study.zh.bean.TProject;

public interface TProjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TProject record);

    int insertSelective(TProject record);

    TProject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TProject record);

    int updateByPrimaryKey(TProject record);
}