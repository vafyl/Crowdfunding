package com.study.zh.manager.controller;

import com.study.zh.bean.TPermission;
import com.study.zh.manager.service.PermissionService;
import com.study.zh.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("/index")
    public String index(){
        return "/permission/index";
    }
    @RequestMapping("/toAdd")
    public String toAdd(){
        return "/permission/add";
    }

    @ResponseBody
    @RequestMapping("/doAdd")
    public Object doAdd(TPermission permission){
        AjaxResult result = new AjaxResult();

        try {

            int count = permissionService.savePermission(permission);

            result.setSuccess(count == 1);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("保存许可树数据失败!");
        }

        return result ;
    }


    @RequestMapping("/toUpdate")
    public String toUpdate(Integer id,Map map){
        TPermission permission = permissionService.getPermissionById(id);
        map.put("permission", permission);
        return "permission/update";
    }


    @ResponseBody
    @RequestMapping("/doUpdate")
    public Object doUpdate(TPermission permission){
        AjaxResult result = new AjaxResult();

        try {

            int count = permissionService.updatePermission(permission);
            result.setSuccess(count == 1);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("修改许可树数据失败!");
        }
        return result ;
    }


	@ResponseBody
	@RequestMapping("/deletePermission")
	public Object deletePermission(Integer id){
		AjaxResult result = new AjaxResult();

		try {

			int count = permissionService.deletePermission(id);

			result.setSuccess(count == 1);
		} catch (Exception e) {
			result.setSuccess(false);
			result.setMessage("删除许可树数据失败!");
		}

		return result ;
	}


    //用Map集合来查找父,来组合父子关系.减少循环的次数 ,提高性能.
    @ResponseBody
    @RequestMapping("/loadData")
    public Object loadData(){
        AjaxResult result = new AjaxResult();

        try {

            List<TPermission> root = new ArrayList<TPermission>();

            List<TPermission> childredPermissons =  permissionService.queryAllPermission();

            Map<Integer,TPermission> map = new HashMap<Integer,TPermission>();//100

            for (TPermission innerpermission : childredPermissons) {
                map.put(innerpermission.getId(), innerpermission);
            }


            for (TPermission permission : childredPermissons) { //100
                //通过子查找父
                //子菜单
                TPermission child = permission ; //假设为子菜单
                if(child.getPid() == null ){
                    root.add(permission);
                }else{
                    //父节点
                    TPermission parent = map.get(child.getPid());
                    parent.getChildren().add(child);
                }
            }

            result.setSuccess(true);
            result.setData(root);

        } catch (Exception e) {

            result.setSuccess(false);
            e.printStackTrace();
            result.setMessage("加载许可树数据失败!");
        }


        return result ;
    }
}
