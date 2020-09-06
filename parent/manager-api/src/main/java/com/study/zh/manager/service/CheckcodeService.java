package com.study.zh.manager.service;

import com.study.zh.bean.TCheckcode;

public interface CheckcodeService {
    void saveCheckcode(String checkcode);

    TCheckcode queryCheckcode(Integer emailcheck);
}
