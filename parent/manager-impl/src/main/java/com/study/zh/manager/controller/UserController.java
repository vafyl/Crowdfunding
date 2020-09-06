package com.study.zh.manager.controller;

import com.alibaba.excel.EasyExcel;
import com.study.zh.bean.TRole;
import com.study.zh.bean.TUser;
import com.study.zh.manager.service.UserService;
import com.study.zh.util.AjaxResult;
import com.study.zh.util.Page;
import com.study.zh.util.StringUtil;
import com.study.zh.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService ;

    List<TUser> UserList = null;


    @RequestMapping("/index")
    public String index(){
        return "user/index";
    }



    @RequestMapping("/assignRole")
    public String assignRole(Integer userid,Map map){

        List<TRole> allRoleList = userService.queryAllRole();
        List<Integer> roleIds = userService.queryRoleByUserid(userid);

        List<TRole> leftRoleList = new ArrayList<TRole>();//未分配的角色
        List<TRole> rigthRoleList = new ArrayList<TRole>();//已分配的角色

        //分配角色
        for (TRole role: allRoleList){
            if(roleIds.contains(role.getId())){
                rigthRoleList.add(role);
            } else{
                leftRoleList.add(role);
            }
        }

        map.put("leftRoleList",leftRoleList);
        map.put("rigthRoleList",rigthRoleList);

        return "user/assignrole";
    }

    @RequestMapping("/toAdd")
    public String toAdd(){
        return "user/add";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id,Map map){

        TUser user = userService.getUserById(id);
        map.put("user", user);

        return "user/update";
    }


    //接收多条数据.
    @ResponseBody
    @RequestMapping("/doDeleteBatch")
    public Object doDeleteBatch(Data data){
        AjaxResult result = new AjaxResult();
        try {

            int count = userService.deleteBatchUserByVO(data);
            result.setSuccess(count==data.getDatas().size());
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败!");
        }
        return result;
    }

    //接收一个参数名带多个值.
	/*@ResponseBody
	@RequestMapping("/doDeleteBatch")
	public Object doDeleteBatch(Integer[] id){
		AjaxResult result = new AjaxResult();
		try {

			int count = userService.deleteBatchUser(id);
			result.setSuccess(count==id.length);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("删除数据失败!");
		}

		return result;
	}*/

    @ResponseBody
    @RequestMapping("/doDelete")
    public Object doDelete(Integer id){
        AjaxResult result = new AjaxResult();
        try {

            int count = userService.deleteUser(id);
            result.setSuccess(count==1);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("删除数据失败!");
        }

        return result;
    }

    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(TUser user){
        AjaxResult result = new AjaxResult();
        try {

            int count = userService.updateUser(user);
            result.setSuccess(count==1);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("修改数据失败!");
        }

        return result;
    }


    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(TUser user){
        AjaxResult result = new AjaxResult();
        try {

            int count = userService.saveUser(user);
            result.setSuccess(count==1);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("保存数据失败!");
        }

        return result;
    }

    //分配角色
    @ResponseBody
    @RequestMapping("/doAssignRole")
    public Object doAssignRole(Integer userid,Data data){
        AjaxResult result = new AjaxResult();
        try {

            userService.saveUserRoleRelationshop(userid,data);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("角色分配失败!");
        }

        return result;
    }

    //取消角色分配
    @ResponseBody
    @RequestMapping("/doUnAssignRole")
    public Object doUnAssignRole(Integer userid,Data data){
        AjaxResult result = new AjaxResult();
        try {

            userService.deleteUserRoleRelationshop(userid,data);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("取消角色分配失败!");
        }

        return result;
    }


    //全部分配

    @ResponseBody
    @RequestMapping("/doAllRightAssignRole")
    public Object doAllRightAssignRole(Integer userid,Data data) {
        AjaxResult result = new AjaxResult();
        try {

            userService.saveAllUserRoleRelationshop(userid, data);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("角色分配失败!");
        }

        return result;
    }

    //全部分配

    @ResponseBody
    @RequestMapping("/doUnAllRightAssignRole")
    public Object doUnAllRightAssignRole(Integer userid,Data data){
        AjaxResult result = new AjaxResult();
        try {

            userService.deleteAllUserRoleRelationshop(userid,data);
            result.setSuccess(true);
        } catch (Exception e) {
            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("角色分配失败!");
        }

        return result;
    }


    //条件查询
    @ResponseBody
    @RequestMapping("/doIndex")
    public Object doIndex(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
                        @RequestParam(value="pagesize",required=false,defaultValue="10") Integer pagesize,
                        String queryText){
        AjaxResult result = new AjaxResult();
        try {

            Map paramMap = new HashMap();
            paramMap.put("pageno", pageno);
            paramMap.put("pagesize", pagesize);

            if(StringUtil.isNotEmpty(queryText)){

                if(queryText.contains("%")){
                    queryText = queryText.replaceAll("%", "\\\\%");
                }

                paramMap.put("queryText", queryText); //   \%
            }
            Page page = userService.queryUserPage(paramMap);
            result.setSuccess(true);
            result.setPage(page);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("查询数据失败!");
        }

        return result; //将对象序列化为JSON字符串,以流的形式返回.
    }


    //导入Excel表格
    @RequestMapping(value = "/doToExcel")
    @ResponseBody
    public Object toExcel(@RequestParam MultipartFile[] file, HttpSession session) {

        int result = 0;

        try {
            result = userService.adduser(file[0]);
        } catch (Exception e) {

            e.printStackTrace();
        }

        if (result > 0) {
            return "{\"result\":\"excel文件数据导入成功！\"}";
        } else {
            return "{\"result\":\"excel文件数据导入失败！\"}";

        }
    }



    //异步请求
	/*@ResponseBody
	@RequestMapping("/index")
	public Object index(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
				@RequestParam(value="pagesize",required=false,defaultValue="10") Integer pagesize){
		AjaxResult result = new AjaxResult();
		try {
			Page page = userService.queryPage(pageno,pagesize);

			result.setSuccess(true);
			result.setPage(page);
		} catch (Exception e) {
			result.setSuccess(false);
			e.printStackTrace();
			result.setMessage("查询数据失败!");
		}

		return result; //将对象序列化为JSON字符串,以流的形式返回.
	}*/


    //同步请求
	/*@RequestMapping("/index")
	public String index(@RequestParam(value="pageno",required=false,defaultValue="1") Integer pageno,
				@RequestParam(value="pagesize",required=false,defaultValue="10") Integer pagesize,Map map){

		Page page = userService.queryPage(pageno,pagesize);

		map.put("page", page);

		return "user/index";
	}*/

}
