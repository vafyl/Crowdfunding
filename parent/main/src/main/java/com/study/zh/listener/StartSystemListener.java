package com.study.zh.listener;

import com.study.zh.bean.TPermission;
import com.study.zh.manager.service.PermissionService;
import com.study.zh.util.Const;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StartSystemListener implements ServletContextListener {
    
    //在服务器启动时，创建application对象时需要执行的方法
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //1.将项目上下文路径(request.getContextPath())放置到application域中.
        ServletContext application = servletContextEvent.getServletContext();
        String contextPath = application.getContextPath();
        application.setAttribute("APP_PATH", contextPath);
        System.out.println("APP_PATH...");

        //2.加载所有许可路径
        ApplicationContext ioc = WebApplicationContextUtils.getWebApplicationContext(application);
        PermissionService permissionService = ioc.getBean(PermissionService.class);
        List<TPermission> queryAllPermission = permissionService.queryAllPermission();

        Set<String> allURIs = new HashSet<String>();

        for (TPermission permission : queryAllPermission) {
            allURIs.add("/"+permission.getUrl());
        }

        application.setAttribute(Const.ALL_PERMISSION_URI, allURIs);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
