package com.study.zh.manager.service;

import com.study.zh.bean.TMember;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface MemberService {
    TMember queryMemberLogin(Map<String, Object> paramMap);

    void updateAcctType(TMember loginMember);

    void updateBasicinfo(TMember loginMember);

    void updateEmail(TMember loginMember);

    void updateAuthstatus(TMember loginMember);

    TMember getMemberById(Integer memberid);

    List<Map<String, Object>> queryCertByMemberid(Integer memberid);

    int saveMember(TMember member);

    TMember queryMemberByLoginacct(String loginacct);

    int updateMembePwdrByLoginacct(TMember member);
}
