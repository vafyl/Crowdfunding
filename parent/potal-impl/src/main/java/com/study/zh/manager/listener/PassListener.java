package com.study.zh.manager.listener;

import com.study.zh.bean.TMember;
import com.study.zh.manager.service.MemberService;
import com.study.zh.manager.service.TicketService;
import com.study.zh.util.ApplicationContextUtils;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;
import sun.security.krb5.internal.Ticket;


//实名审核通过监听器
public class PassListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {


        // 审批通过
        Integer memberid = (Integer)execution.getVariable("memberid");

        // 获取Spring容器

        ApplicationContext context = ApplicationContextUtils.applicationContext;

        // 获取service

        TicketService ticketService = context.getBean(TicketService.class);
        MemberService memberService = context.getBean(MemberService.class);

        // 改变会员的状态
        TMember member= memberService.getMemberById(memberid);
        member.setAuthstatus("2");
        memberService.updateAuthstatus(member);

        // 改变流程审批单的状态

        ticketService.updateStatus(member);


    }
}
