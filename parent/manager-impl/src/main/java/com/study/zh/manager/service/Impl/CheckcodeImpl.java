package com.study.zh.manager.service.Impl;

import com.study.zh.bean.TCheckcode;
import com.study.zh.manager.dao.TCheckcodeMapper;
import com.study.zh.manager.service.CheckcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckcodeImpl implements CheckcodeService {

    @Autowired
    TCheckcodeMapper checkcodeMapper;

    @Override
    public void saveCheckcode(String checkcode) {
        checkcodeMapper.saveCheckcode(checkcode);
    }

    @Override
    public TCheckcode queryCheckcode(Integer emailcheck) {
        return checkcodeMapper.selectByCheckcode(emailcheck);
    }
}
