package com.study.zh.manager.service.Impl;

import com.study.zh.manager.dao.TAccountTypeCertMapper;
import com.study.zh.manager.service.CerttypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CerttypeServiceImpl implements CerttypeService {
    @Autowired
    private TAccountTypeCertMapper accountTypeCertMapper ;

    @Override
    public List<Map<String, Object>> queryCertAccttype() {
        return accountTypeCertMapper.queryCertAccttype();
    }

    @Override
    public int insertAcctTypeCert(Map<String, Object> paramMap) {
        return accountTypeCertMapper.insertAcctTypeCert(paramMap);
    }

    @Override
    public int deleteAcctTypeCert(Map<String, Object> paramMap) {
        return accountTypeCertMapper.deleteAcctTypeCert(paramMap);
    }
}
