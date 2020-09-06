package com.study.zh.manager.dao;

import com.study.zh.bean.TMember;

import java.util.List;
import java.util.Map;

public interface TMemberMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TMember record);

    int insertSelective(TMember record);

    TMember selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TMember record);

    int updateByPrimaryKey(TMember record);

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