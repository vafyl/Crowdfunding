package com.study.zh.manager.dao;


import com.study.zh.bean.TAccountTypeCert;

import java.util.List;
import java.util.Map;

public interface TAccountTypeCertMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TAccountTypeCert record);

    int insertSelective(TAccountTypeCert record);

    TAccountTypeCert selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TAccountTypeCert record);

    int updateByPrimaryKey(TAccountTypeCert record);


    int insertAcctTypeCert(Map<String, Object> paramMap);

    int deleteAcctTypeCert(Map<String, Object> paramMap);

    List<Map<String, Object>> queryCertAccttype();
}