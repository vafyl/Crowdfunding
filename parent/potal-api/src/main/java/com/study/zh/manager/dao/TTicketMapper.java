package com.study.zh.manager.dao;


import com.study.zh.bean.TMember;
import com.study.zh.bean.TTicket;

public interface TTicketMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TTicket record);

    int insertSelective(TTicket record);

    TTicket selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TTicket record);

    int updateByPrimaryKey(TTicket record);

    TTicket getTicketByMemberId(Integer memberid);

    void saveTicket(TTicket ticket);

    void updatePstep(TTicket ticket);

    void updatePiidAndPstep(TTicket ticket);

    TMember getMemberByPiid(String processInstanceId);

    void updateStatus(TMember member);
}