package com.hh.action;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import com.hh.actionform.UserForm;
import com.hh.domain.PageBean;
import com.hh.domain.Repertory;
import com.hh.domain.Role;
import com.hh.domain.Tree;
import com.hh.domain.User;
import com.hh.service.RepertoryService;
import com.hh.service.RoleService;
import com.hh.service.UserService;

public class UserAction extends BaseAction{
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	@Autowired
	@Qualifier("repertoryServiceImpl")
	private RepertoryService repertoryService;
	@Autowired
	@Qualifier("roleServiceImpl")
	private RoleService roleService;
	
	/*
	 * 获取所有系统用户信息
	 */
	public ActionForward getUserList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		int start = Integer.valueOf(request.getParameter("start"));// 起始
		int pageSize = Integer.valueOf(request.getParameter("limit"));// 每页记录数
		int repertoryid=Integer.valueOf(request.getParameter("repertoryId"));
		PageBean<User> pageBean=userService.findByPage(start, pageSize);//获取用户集合
		List<UserForm> userList=new ArrayList<UserForm>();
		for(User u:pageBean.getList()){
			UserForm user=new UserForm();
			user.setUser_id(u.getUser_id());
			user.setUser_name(u.getUser_name());
			user.setUser_loginaccount(u.getUser_loginaccount());
			user.setUser_password(u.getUser_password());
			user.setUser_phone(u.getUser_phone());
			user.setRole_name(u.getRole().getRole_name());
			user.setRepertory_name(u.getRepertory().getRepertory_name());
			userList.add(user);
		}
		

		
		StringBuilder json = new StringBuilder("{\"total\":");
		// root:需要展示的数据json格式，fastjson转化数据为要求的json格式
		json.append(pageBean.getTotalCount()).append(",\"user\":");
		json.append(JSON.toJSONString(userList));
		json.append("}");
		
		
		
		
		
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		return null;
	}
	/*
	 * 获取仓库
	 */
	
	public ActionForward getRepertoryTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 response.setCharacterEncoding("UTF-8");
		List<Repertory> list = null;
		List<Tree> treeList=new ArrayList<Tree>();
		list = repertoryService.queryAll();
		for (int i = 0; i < list.size(); i++) {
			
			Repertory r = (Repertory) list.get(i);
			Tree tr=new Tree();
			tr.setId(r.getRepertory_id());
			tr.setLeaf(1);
			tr.setText(r.getRepertory_name());
			tr.setPid(10);
			
			treeList.add(tr);
		}
		PrintWriter out = response.getWriter();  
		 System.out.println("->>>>"+JSONObject.toJSONString(treeList));
	    out.write(JSONObject.toJSONString(treeList));
	   
				
		return null;
		
		
	}
	/*
	 * 根据仓库编号查询
	 * 1.获取仓库编号
	 */
	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		/*
		 * 获取前端传递的一些参数
		 * 
		 */
		response.setCharacterEncoding("UTF-8");
		String name = new String(request.getParameter("username").getBytes("iso-8859-1"),"utf-8");
		User us=new User();
	    if(!name.equals("0")){
	    
	    	us.setUser_name(name);
	    }
		int repetoryid=Integer.parseInt(request.getParameter("repertoryId"));
		int begin=Integer.parseInt(request.getParameter("start"));//开始位置
		int pageSize=Integer.parseInt(request.getParameter("limit"));
	    PageBean<User> pageBean=userService.query(us,begin, pageSize, repetoryid);
	    List<UserForm> userList=new ArrayList<UserForm>();
	    for(User u:pageBean.getList()){
			UserForm user=new UserForm();
			user.setUser_id(u.getUser_id());
			user.setUser_name(u.getUser_name());
			user.setUser_loginaccount(u.getUser_loginaccount());
			user.setUser_password(u.getUser_password());
			user.setUser_phone(u.getUser_phone());
			user.setRole_name(u.getRole().getRole_name());
			user.setRepertory_name(u.getRepertory().getRepertory_name());
			userList.add(user);
		}
		StringBuilder json = new StringBuilder("{\"total\":");
	    json.append(pageBean.getTotalCount()).append(",\"user\":");
		json.append(JSON.toJSONString(userList));
		json.append("}");
	    
       PrintWriter out = response.getWriter();
		out.write(json.toString());
		return null;
		
		    }
	/*
	 * 给仓库添加用户或者移除仓库用户
	 */
	public ActionForward changgeUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		    UserForm uf=(UserForm)form;//获取前端封装的数据
		   
		    User user=new User();
		    user.setUser_name(uf.getUser_name());
		    user.setUser_loginaccount(uf.getUser_loginaccount());
		    user.setUser_password(uf.getUser_password());
		    user.setUser_phone(uf.getUser_phone());
		    user.setUser_sex(uf.getUser_sex());
		   
		    Repertory repertory=repertoryService.queryById(uf.getRepertory_id());//获取该用户关联的仓库
		    Role role=roleService.queryById(uf.getRole_id());//获取用户关联的角色
		   
		    user.setRepertory(repertory);//给用户关联仓库
		    user.setRole(role);//用户关联角色
		    //repertory.getUsers().add(user);//set发生改变,仓库关联用户
		   //role.getUsers().add(user);//set发生改变,角色关联用户
		    userService.insert(user);//或者 repertoryService.update(repertory);
		    return null;
		
	}
	/*
	 * 删除用户
	 */
	public ActionForward deleteUser(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		   int user_id=Integer.valueOf(request.getParameter("user_id"));
		   User user=new User();
		   user.setUser_id(user_id);
		   userService.delete(user);
		   return null;
		
	}
	}
	
	
	

	
	
	


