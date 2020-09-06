package com.study.zh.manager.service;

import com.study.zh.bean.TMember;
import com.study.zh.bean.TTicket;

public interface TicketService {
    TTicket getTicketByMemberId(Integer id);

    void saveTicket(TTicket ticket);

    void updatePstep(TTicket ticket);

    void updatePiidAndPstep(TTicket ticket);

    TMember getMemberByPiid(String processInstanceId);

    void updateStatus(TMember member);
}
