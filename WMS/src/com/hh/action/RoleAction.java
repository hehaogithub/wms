package com.hh.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.hh.actionform.RoleForm;
import com.hh.domain.PageBean;
import com.hh.domain.Resource;
import com.hh.domain.Role;
import com.hh.service.ResourceService;
import com.hh.service.RoleService;

public class RoleAction extends BaseAction {
	
	@Autowired
	@Qualifier("roleServiceImpl")
	private RoleService roleService;
	@Autowired
	@Qualifier("resourceServiceImpl")
	private ResourceService resourceService;

	
	/*
	 * 获取所有角色信息
	 */
	public ActionForward getRoleList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		int start = Integer.valueOf(request.getParameter("start"));// 开始位置
		int pageSize = Integer.valueOf(request.getParameter("limit"));// 每页记录数
        PageBean<Role> pageBean=roleService.findByPage(start, pageSize);
		StringBuilder json = new StringBuilder("{\"total\":");
		// root:需要展示的数据json格式，fastjson转化数据为要求的json格式
		json.append(pageBean.getTotalCount()).append(",\"role\":");
		json.append(JSON.toJSONString(pageBean.getList()));
		json.append("}");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		return null;
	}
	public ActionForward editRoleResource(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		int roleid=Integer.parseInt(request.getParameter("roleid"));
		System.out.println("----"+roleid);
		//角色编号
		Role role=roleService.queryById(roleid);//获得角色
		Set<Resource> rset=new HashSet<Resource>();
		
		role.setResources(rset);
		roleService.update(role);
	   
		
		String ids=request.getParameter("nodeids");
		
		  String[] temp;
	      String delimeter = " ";  // 指定分割字符
	      temp = ids.split(delimeter);
		  for(int i=0;i<temp.length;i++){
			  System.out.println(temp[i]);
		  }
		  int [] num=new int[temp.length];
		  for(int i=0;i<num.length;i++){
	            num[i]=Integer.parseInt(temp[i]);
	       Resource resource1=resourceService.queryById(num[i]);
	       rset.add(resource1);    
	           
	        }
		    role.setResources(rset);
			roleService.update(role);
		  
		return null;
	}
	public ActionForward getRole(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    response.setCharacterEncoding("UTF-8");
		    List<Role> role=roleService.queryAll();
		    List<RoleForm> roleList=new ArrayList<RoleForm>();
		    for(Role r:role){
		    	RoleForm re=new RoleForm();
		    	re.setRole_id(r.getRole_id());
		    	re.setRole_name(r.getRole_name());
		    	roleList.add(re);
		    }
		    
		    
			StringBuilder json = new StringBuilder("{\"data\":");
			json.append(JSON.toJSONString(roleList));
			json.append("}");
		    PrintWriter out = response.getWriter();
			out.write(json.toString());
		    return null;
	}
	
	
	


}
