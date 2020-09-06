package com.study.zh.manager.service.Impl;


import com.study.zh.bean.TMember;
import com.study.zh.manager.dao.TMemberMapper;
import com.study.zh.manager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class MerberServiceImpl implements MemberService {

    @Autowired
    private TMemberMapper memberMapper;

    @Override
    public TMember queryMemberLogin(Map<String, Object> paramMap) {
        return memberMapper.queryMemberLogin(paramMap);
    }

    @Override
    public void updateAcctType(TMember loginMember) {
        memberMapper.updateAcctType(loginMember);
    }

    @Override
    public void updateBasicinfo(TMember loginMember) {
        memberMapper.updateBasicinfo(loginMember);
    }

    @Override
    public void updateEmail(TMember loginMember) {
        memberMapper.updateEmail(loginMember);
    }

    @Override
    public void updateAuthstatus(TMember loginMember) {
        memberMapper.updateAuthstatus(loginMember);
    }

    @Override
    public TMember getMemberById(Integer memberid) {
        return memberMapper.getMemberById(memberid);
    }

    @Override
    public List<Map<String, Object>> queryCertByMemberid(Integer memberid) {
        return memberMapper.queryCertByMemberid(memberid);
    }

    @Override
    public int saveMember(TMember member) {
        return memberMapper.saveMember(member);
    }

    @Override
    public TMember queryMemberByLoginacct(String loginacct) {
        return memberMapper.queryMemberByLoginacct(loginacct);
    }

    @Override
    public int updateMembePwdrByLoginacct(TMember member) {
        return memberMapper.updateMembePwdrByLoginacct(member);
    }
}
