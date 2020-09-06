package com.study.zh.manager.listener;

import com.study.zh.bean.TMember;
import com.study.zh.manager.service.MemberService;
import com.study.zh.manager.service.TicketService;
import com.study.zh.util.ApplicationContextUtils;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.context.ApplicationContext;

//实名审核拒绝监听器
public class RefuseListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) throws Exception {


        // 审批失败
        Integer memberid = (Integer)execution.getVariable("memberid");

        // 获取Spring容器

        ApplicationContext context = ApplicationContextUtils.applicationContext;

        // 获取service

        TicketService ticketService = context.getBean(TicketService.class);
        MemberService memberService = context.getBean(MemberService.class);

        // 改变会员的状态
        TMember member= memberService.getMemberById(memberid);
        member.setAuthstatus("0");
        memberService.updateAuthstatus(member);

        // 改变流程审批单的状态

        ticketService.updateStatus(member);


    }
}
