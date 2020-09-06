package com.study.zh.manager.dao;


import com.study.zh.bean.TAdvertisement;

public interface TAdvertisementMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAdvertisement record);

    int insertSelective(TAdvertisement record);

    TAdvertisement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAdvertisement record);

    int updateByPrimaryKey(TAdvertisement record);
}