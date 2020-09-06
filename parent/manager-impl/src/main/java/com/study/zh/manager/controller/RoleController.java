package com.study.zh.manager.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.study.zh.bean.TPermission;
import com.study.zh.bean.TRole;
import com.study.zh.manager.service.PermissionService;
import com.study.zh.manager.service.RoleService;
import com.study.zh.util.AjaxResult;
import com.study.zh.util.Page;
import com.study.zh.util.StringUtil;
import com.study.zh.vo.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/index")
	public String index() {
		return "role/index";
	}


	@RequestMapping("/add")
	public String add() {
		return "role/add";
	}




	@RequestMapping("/assignPermission")
	public String assignPermission() {
		return "role/assignPermission";
	}

	@ResponseBody
	@RequestMapping("/loadDataAsync")
	public Object loadDataAsync(Integer roleid){

		List<TPermission> root = new ArrayList<TPermission>();

		List<TPermission> childredPermissons =  permissionService.queryAllPermission();


		//根据角色id查询该角色之前所分配过的许可.
		List<Integer> permissonIdsForRoleid = permissionService.queryPermissionidsByRoleid(roleid);


		Map<Integer,TPermission> map = new HashMap<Integer,TPermission>();//100

		for (TPermission innerpermission : childredPermissons) {
			map.put(innerpermission.getId(), innerpermission);
			if(permissonIdsForRoleid.contains(innerpermission.getId())){
				innerpermission.setChecked(true);
			}
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


		return root ;
	}

	@ResponseBody
	@RequestMapping("/doAssignPermission")
	public Object doAssignPermission(Integer roleid, Data datas){
		AjaxResult result = new AjaxResult();
		try {
			int count = roleService.saveRolePermissionRelationship(roleid,datas);

			result.setSuccess(count==datas.getIds().size());

		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}



	//传递多个对象方式
	@ResponseBody
	@RequestMapping("/batchDelete")
	public Object batchDelete(Data datas){
		AjaxResult result = new AjaxResult();
		try {
			int count = roleService.batchDeleteRole(datas);
			if(count==datas.getDatas().size()){
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}
	/* 传递多个id值方式
	@ResponseBody
	@RequestMapping("/batchDelete")
	public Object batchDelete(Integer[] ids){
		AjaxResult result = new AjaxResult();
		try {
			int count = roleService.batchDeleteRole(ids);
			if(count==ids.length){
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}*/

	@ResponseBody
	@RequestMapping("/delete")
	public Object delete(Integer uid){
		AjaxResult result = new AjaxResult();
		try {
			int count = roleService.deleteRole(uid);
			if(count==1){
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}


	@ResponseBody
	@RequestMapping("/doEdit")
	public Object doEdit(TRole role) {
		AjaxResult result = new AjaxResult();
		try {
			int count = roleService.updateRole(role);// id没传的情况下,更新并不会报错,但是并没有更新成功,所以,需要加以判断
			if(count==1){
				result.setSuccess(true);
			}else{
				result.setSuccess(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	@RequestMapping("/edit")
	public String edit(Integer id,Map<String,Object> map) {
		TRole role = roleService.getRole(id);
		map.put("role", role);
		return "role/edit";
	}


	@ResponseBody
	@RequestMapping("/doAdd")
	public Object doAdd(TRole role) {

		AjaxResult result = new AjaxResult();

		try {

			roleService.saveRole(role);

			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}

		return result;
	}

	/**
	 * 异步分页查询
	 * @param pageno
	 * @param pagesize
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/pageQuery")
	public Object pageQuery(String queryText,@RequestParam(required = false, defaultValue = "1") Integer pageno,
							@RequestParam(required = false, defaultValue = "2") Integer pagesize){
		AjaxResult result = new AjaxResult();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("pageno", pageno); // 空指针异常
			paramMap.put("pagesize", pagesize);

			if(StringUtil.isNotEmpty(queryText)){
				queryText = queryText.replaceAll("%", "\\\\%"); //斜线本身需要转译
				System.out.println("--------------"+queryText);
			}

			paramMap.put("queryText", queryText);

			// 分页查询数据
			Page<TRole> rolePage = roleService.pageQuery(paramMap);

			result.setPage(rolePage);
			result.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess(false);
		}
		return  result;
	}
}
