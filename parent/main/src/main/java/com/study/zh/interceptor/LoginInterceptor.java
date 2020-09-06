package com.study.zh.interceptor;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.study.zh.bean.TMember;
import com.study.zh.bean.TUser;
import com.study.zh.util.Const;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;



public class LoginInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		//1.定义哪些路径是不需要拦截(将这些路径称为白名单)
		Set<String> uri = new HashSet<String>();
		uri.add("/user/reg.do");
		uri.add("/user/reg.htm");
		uri.add("/login.htm");
		uri.add("/doLogin.do");
		uri.add("/logout.do");
		uri.add("/index.htm");
		uri.add("/cancellation.do");
		uri.add("/reg.htm");
		uri.add("/doSendEmail.do");
		uri.add("/doRegister.do");
		uri.add("/forget.htm");
		uri.add("/doFindPwd.do");
		uri.add("/reset.htm");
		uri.add("/doReset.do");

		
		//获取请求路径.
		String servletPath = request.getServletPath();
		
		if(uri.contains(servletPath)){
			return true ;
		}
		
		
		//2.判断用户是否登录,如果登录就放行
		HttpSession session = request.getSession();
		TUser user = (TUser)session.getAttribute(Const.Const_LOGIN_USER);
		TMember member = (TMember)session.getAttribute(Const.Const_LOGIN_Member);


		if(user!=null || member!=null){
			return true ;
		}else{
			response.sendRedirect(request.getContextPath()+"/login.htm");
			return false;
		}
		
	}
	
}
