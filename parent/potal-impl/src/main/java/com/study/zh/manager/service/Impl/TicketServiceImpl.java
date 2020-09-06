package com.study.zh.manager.service.Impl;

import com.study.zh.bean.TMember;
import com.study.zh.bean.TTicket;
import com.study.zh.manager.dao.TTicketMapper;
import com.study.zh.manager.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TTicketMapper ticketMapper;

    @Override
    public TTicket getTicketByMemberId(Integer id) {
        return ticketMapper.getTicketByMemberId(id);
    }

    @Override
    public void saveTicket(TTicket ticket) {
        ticketMapper.saveTicket(ticket);
    }

    @Override
    public void updatePstep(TTicket ticket) {
        ticketMapper.updatePstep(ticket);
    }

    @Override
    public void updatePiidAndPstep(TTicket ticket) {
        ticketMapper.updatePiidAndPstep(ticket);
    }

    @Override
    public TMember getMemberByPiid(String processInstanceId) {
        return ticketMapper.getMemberByPiid(processInstanceId);
    }

    @Override
    public void updateStatus(TMember member) {
        ticketMapper.updateStatus(member);
    }
}
