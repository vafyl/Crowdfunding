package com.study.zh.manager.dao;


import com.study.zh.bean.TCert;
import com.study.zh.bean.TMemberCert;
import com.study.zh.vo.Data;

import java.util.List;
import java.util.Map;

public interface TCertMapper {

    TCert queryCert(Map<String, Object> paramMap);

    List<TCert> pageQuery(Map<String, Object> paramMap);

    int queryCount(Map<String, Object> paramMap);

    void insertCert(TCert cert);

    TCert queryById(Integer id);

    int updateCert(TCert cert);

    int deleteCert(Integer id);

    int deleteCerts(Data ds);

    List<TCert> queryCertByAccttype(String accttype);

    List<TCert> queryAllCert();

    List<Map<String, Object>> queryAllAccttypeCert();

    int insertAccttypeCert(Map<String, Object> map);

    int deleteAccttypeCert(Map<String, Object> map);

    void insertMemberCert(TMemberCert memberCert);
}