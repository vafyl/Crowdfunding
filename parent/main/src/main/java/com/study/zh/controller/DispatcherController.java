package com.study.zh.controller;

import com.study.zh.bean.*;
import com.study.zh.manager.listener.PassListener;
import com.study.zh.manager.listener.RefuseListener;
import com.study.zh.manager.service.CheckcodeService;
import com.study.zh.manager.service.MemberService;
import com.study.zh.manager.service.TicketService;
import com.study.zh.manager.service.UserService;
import com.study.zh.util.AjaxResult;
import com.study.zh.util.Const;
import com.study.zh.util.MD5Util;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class DispatcherController {


    @Autowired
    private UserService userService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private CheckcodeService checkcodeService;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/login")
    public String login(HttpServletRequest request,HttpSession session){

        //判断是否需要自动登录
        //如果之前登录过，cookie中存放了用户信息，需要获取cookie中的信息，并进行数据库的验证

        boolean needLogin = true;
        String logintype = null ;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){ //如果客户端禁用了Cookie，那么无法获取Cookie信息

            for (Cookie cookie : cookies) {
                if("logincode".equals(cookie.getName())){
                    String logincode = cookie.getValue();
                    String[] split = logincode.split("&");
                    if(split.length == 3){
                        String loginacct = split[0].split("=")[1];
                        String userpwd = split[1].split("=")[1];
                        logintype = split[2].split("=")[1];
                        Map<String,Object> paramMap = new HashMap<String,Object>();
                        paramMap.put("loginacct", loginacct);
                        paramMap.put("userpswd", userpwd);
                        paramMap.put("type", logintype);

                        if("user".equals(logintype)){


                            TUser dbLogin = userService.queryUserLogin(paramMap);

                            if(dbLogin!=null){
                                session.setAttribute(Const.Const_LOGIN_USER, dbLogin);
                                needLogin = false ;
                            }


                            //加载当前登录用户的所拥有的许可权限.

                            //User user = (User)session.getAttribute(Const.LOGIN_USER);

                            List<TPermission> myPermissions = userService.queryPermissionByUserid(dbLogin.getId()); //当前用户所拥有的许可权限

                            TPermission permissionRoot = null;

                            Map<Integer,TPermission> map = new HashMap<Integer,TPermission>();

                            Set<String> myUris = new HashSet<String>(); //用于拦截器拦截许可权限

                            for (TPermission innerpermission : myPermissions) {
                                map.put(innerpermission.getId(), innerpermission);

                                myUris.add("/"+innerpermission.getUrl());
                            }

                            session.setAttribute(Const.MY_URIS, myUris);


                            for (TPermission permission : myPermissions) {
                                //通过子查找父
                                //子菜单
                                TPermission child = permission ; //假设为子菜单
                                if(child.getPid() == null ){
                                    permissionRoot = permission;
                                }else{
                                    //父节点
                                    TPermission parent = map.get(child.getPid());
                                    parent.getChildren().add(child);
                                }
                            }


                            session.setAttribute("permissionRoot", permissionRoot);


                        }else if("member".equals(logintype)){


                            TMember dbLogin = memberService.queryMemberLogin(paramMap);

                            if(dbLogin!=null){
                                session.setAttribute(Const.Const_LOGIN_Member, dbLogin);
                                needLogin = false ;
                            }
                        }

                    }
                }
            }
        }

        if(needLogin){
            return "login";
        }else{
            if("user".equals(logintype)){
                return "redirect:/main.htm";
            }else if("member".equals(logintype)){
                return "redirect:/member.htm";
            }
        }
        return "login";
    }

    @RequestMapping("/reg")
    public String reg(){
        return "reg";
    }

    @RequestMapping("/member")
    public String member(){
        return "member/member";
    }

    @RequestMapping("/main")
    public String main(HttpSession session){
        //加载当前登录用户所拥有的权限

        TUser user = (TUser) session.getAttribute(Const.Const_LOGIN_USER);
        List<TPermission> myPermissions = userService.queryPermissionByUserid(user.getId());

        TPermission permissionRoot = null;


        Map<Integer,TPermission> map = new HashMap<Integer,TPermission>();//100

        for (TPermission innerpermission : myPermissions) {
            map.put(innerpermission.getId(), innerpermission);
        }


        for (TPermission permission : myPermissions) { //100
            //通过子查找父
            //子菜单
            TPermission child = permission ; //假设为子菜单
            if(child.getPid() == null ){
                permissionRoot = permission;
            }else{
                //父节点
                TPermission parent = map.get(child.getPid());
                parent.getChildren().add(child);
            }
        }

        session.setAttribute("permissionRoot",permissionRoot);
        return "main";
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){

        //销毁session
        session.invalidate();
        //重定向
        return "redirect:/index.htm";
    }


    @RequestMapping("/cancellation")
    public String cancellation(HttpSession session,HttpServletRequest request){

        Cookie[] cookies=request.getCookies();
        for (int i=0;i < cookies.length;i++){
            Cookie cookieClear = cookies[i];
            cookieClear.setMaxAge(0);
            cookieClear.setValue(null);
//            response.addCookie(cookie);
        }
        //销毁session,cookie
        session.invalidate();
//        cookie.setMaxAge(-1);
        //重定向
        return "redirect:/index.htm";
    }

    @RequestMapping("/forget")
    private String forget(){
        return "forget";
    }

    @RequestMapping("/reset")
    private String reset(){
        return "reset";
    }

    @RequestMapping("/minecrowdfunding")
    public String minecrowdfunding(){
        return "minecrowdfunding";
    }

    //异步请求
    //HttpMessageConverter
    //@ResponseBody 结合Jackson组件,将返回结果转换为字符串.将JSON串以流的形式返回给客户端.
    @ResponseBody
    @RequestMapping("/doLogin")
    public Object doLogin(String loginacct,String userpswd,String type,
                          String rememberme,HttpSession session,HttpServletResponse response){

        AjaxResult result = new AjaxResult();

        try {
            Map<String,Object> paramMap = new HashMap<String,Object>();
            paramMap.put("loginacct", loginacct);
            paramMap.put("userpswd", MD5Util.digest(userpswd));
            paramMap.put("type", type);

            if("member".equals(type)){

                TMember member = memberService.queryMemberLogin(paramMap);

                session.setAttribute(Const.Const_LOGIN_Member, member);

                if("1".equals(rememberme)){
                    String logincode = "\"loginacct="+member.getLoginacct()+"&userpwd="+member.getUserpswd()+"&logintype=member\"";

                    Cookie c = new Cookie("logincode",logincode);

                    c.setMaxAge(60*60*24*14); //2周时间Cookie过期     单位秒
                    c.setPath("/"); //表示任何请求路径都可以访问Cookie

                    response.addCookie(c);
                }

            }else if("user".equals(type)){
                TUser user = userService.queryUserLogin(paramMap);

                session.setAttribute(Const.Const_LOGIN_USER, user);


                if("1".equals(rememberme)){
                    String logincode = "\"loginacct="+user.getLoginacct()+"&userpwd="+user.getUserpswd()+"&logintype=user\"";
                    Cookie c = new Cookie("logincode",logincode);

                    c.setMaxAge(60*60*24*14); //2周时间Cookie过期     单位秒
                    c.setPath("/"); //表示任何请求路径都可以访问Cookie

                    response.addCookie(c);
                }

                //---------------------

                //加载当前登录用户的所拥有的许可权限.

                //User user = (User)session.getAttribute(Const.LOGIN_USER);

                List<TPermission> myPermissions = userService.queryPermissionByUserid(user.getId()); //当前用户所拥有的许可权限

                TPermission permissionRoot = null;

                Map<Integer,TPermission> map = new HashMap<Integer,TPermission>();

                Set<String> myUris = new HashSet<String>(); //用于拦截器拦截许可权限

                for (TPermission innerpermission : myPermissions) {
                    map.put(innerpermission.getId(), innerpermission);

                    myUris.add("/"+innerpermission.getUrl());
                }

                session.setAttribute(Const.MY_URIS, myUris);


                for (TPermission permission : myPermissions) {
                    //通过子查找父
                    //子菜单
                    TPermission child = permission ; //假设为子菜单
                    if(child.getPid() == null ){
                        permissionRoot = permission;
                    }else{
                        //父节点
                        TPermission parent = map.get(child.getPid());
                        parent.getChildren().add(child);
                    }
                }

                session.setAttribute("permissionRoot", permissionRoot);
            }else{

            }

            result.setData(type);
            result.setSuccess(true);
            // {"success":true}
        } catch (Exception e) {
            result.setMessage("登录失败!");
            e.printStackTrace();
            result.setSuccess(false);
            // {"success":false,"message":"登录失败!"}
            //throw e ;
        }

        return result ;
    }


    @ResponseBody
    @RequestMapping("/doSendEmail")
    public Object doSendEmail(String email){
        AjaxResult result = new AjaxResult();


        try {
            ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionKey("register").singleResult();

            StringBuilder checkcode = new StringBuilder();
            for (int i = 1; i <= 4; i++) {
                checkcode.append(new Random().nextInt(10));
            }

            checkcodeService.saveCheckcode(checkcode.toString());


            Map<String,Object> variables= new HashMap<String,Object>();
            variables.put("toEmail", email);
            variables.put("authcode", checkcode);
            variables.put("loginacct", "789");
            variables.put("passListener", new PassListener());
            variables.put("refuseListener", new RefuseListener());

            runtimeService.startProcessInstanceById(processDefinition.getId(), variables);



            result.setSuccess(true);
            result.setMessage("发送成功！请注意邮箱！！");
        }catch (Exception e){
            result.setMessage("发送失败！！");
            e.printStackTrace();
            result.setSuccess(false);
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/doRegister")
    public Object doRegister(String loginacct,String userpswd,String email,
                             String type,String emailcheck,String name){
        TMember member = new TMember();
        member.setLoginacct(loginacct);
        member.setUserpswd(MD5Util.digest(userpswd));
        member.setUsername(name);
        member.setEmail(email);
        member.setAuthstatus("0");
        member.setUsertype(type);
        AjaxResult result = new AjaxResult();
        try {

            TMember memberCount = memberService.queryMemberByLoginacct(loginacct);
            TCheckcode checkcodecount = checkcodeService.queryCheckcode(Integer.valueOf(emailcheck));
            if (memberCount != null){
                result.setSuccess(false);
                result.setMessage("用户已经存在！！请修改账号！！");
            }else if (checkcodecount != null){
                memberService.saveMember(member);
                result.setSuccess(true);
                result.setMessage("注册成功！！");
            }else{
                result.setSuccess(false);
                result.setMessage("验证码错误！！");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("注册失败！！");
        }

        return result;
    }



    @ResponseBody
    @RequestMapping("/doFindPwd")
    public Object doFindPwd(String loginacct,String emailcheck,
                            HttpSession session){
        AjaxResult result = new AjaxResult();
        try {

            TMember memberCount = memberService.queryMemberByLoginacct(loginacct);
            TCheckcode checkcodecount = checkcodeService.queryCheckcode(Integer.valueOf(emailcheck));
            if (memberCount == null){
                result.setSuccess(false);
                result.setMessage("账号不存在！！请修改账号！！");
            }else if (checkcodecount != null){
                session.setAttribute(Const.Const_FORGET_LOGINACCT, loginacct);
                result.setSuccess(true);
            }else{
                result.setSuccess(false);
                result.setMessage("验证码错误！！");
            }
        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("找回密码失败！！");
        }

        return result;
    }



    @ResponseBody
    @RequestMapping("/doReset")
    public Object doReset(String againuserpswd,HttpSession session){


        AjaxResult result = new AjaxResult();
        TMember member = new TMember();
        String loginacct = (String) session.getAttribute(Const.Const_FORGET_LOGINACCT);
        member.setLoginacct(loginacct);
        member.setUserpswd(MD5Util.digest(againuserpswd));

        try {

            int count = memberService.updateMembePwdrByLoginacct(member);
            result.setSuccess(count == 1);

            result.setMessage("修改成功！！");


        }catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            result.setMessage("修改失败！！");
        }

        return result;
    }






    //同步请求 做重定向：刷新页面时不会再提交表单，而是刷新main。否则会刷新doLogin，重复登录。
    /*@RequestMapping("/doLogin")
    public String doLogin(String loginacct, String userpswd, String type, HttpSession session){
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("loginacct",loginacct);
        paramMap.put("userpswd",userpswd);
        paramMap.put("type",type);
        TUser user = userService.queryUserLogin(paramMap);

        session.setAttribute(Const.Const_LOGIN_USER,user);
        return "redirect:/main.htm";
    }*/


}
