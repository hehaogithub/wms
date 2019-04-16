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
import org.hibernate.SQLQuery;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;
import com.hh.actionform.UserForm;
import com.hh.domain.Repertory;
import com.hh.domain.Resource;
import com.hh.domain.Role;
import com.hh.domain.Tree;
import com.hh.domain.User;
import com.hh.service.ResourceService;
import com.hh.service.RoleService;
import com.hh.service.UserService;
/**
 * 登录页面Action
 * 
 * @author HeHao
 * @since 2019-02-09
 * @see BaseAction
 */
public class LoginAction extends BaseAction{
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	@Autowired
	@Qualifier("roleServiceImpl")
	private RoleService roleService;
	@Autowired
	@Qualifier("resourceServiceImpl")
	private ResourceService resourceService;
	
	
	
	/**
	 * 跳转到登录页面
	 * 
	 * @param
	 * @return
	 */
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		return mapping.findForward("loginView");
	}
	
	/**
	 * 登陆身份验证
	 * 
	 * @param
	 * @return
	 */
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 response.setCharacterEncoding("UTF-8");
    	 PrintWriter out = response.getWriter(); 
		 UserForm user = (UserForm) request.getAttribute("userForm");
		 User existUser=userService.login(user);
		 if(existUser==null){
			  out.write(JSONObject.toJSONString("用户名或密码填写错误"));
		 }else{
			 request.getSession().setAttribute("loginaccount",existUser); //用Session保存账户名
		 }
		 return null;
		 
	 }
	/**
	 * 退出登录
	 * 
	 * 
	 *
	 */
	public ActionForward logout(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
            request.getSession().invalidate();
            request.getRequestDispatcher("/login.jsp").forward(request, response);  
		    return null;
		 
	 }
	 public ActionForward listMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request,
	            HttpServletResponse response) throws Exception {
		   String node=request.getParameter("node");//获取菜单节点
		   User user= (User) request.getSession().getAttribute("loginaccount");//获取用户信息
		   
		   if(request.getSession().getAttribute(user.getUser_loginaccount())==null){//判断用户权限有没有保存在session中，避免多次查询数据库
		   Set<Resource> resource=user.getRole().getResources();//获取用户权限
		   request.getSession().setAttribute(user.getUser_loginaccount(),resource);//将用户权限放入session中
		   }
		   Set<Resource> re=(Set<Resource>) request.getSession().getAttribute(user.getUser_loginaccount());
		   List<Tree> list=new ArrayList<Tree>();
		   if(node==null||node.equals("0")){//点的是根节点，获取一级菜单
			 
			   for(Resource r:re){
				  
				   if(Integer.toString(r.getId()).length()==1){
                       Tree tr=new Tree();
			           tr.setId(r.getId());
					   tr.setLeaf(r.getLeaf());
					   tr.setPid(r.getPid());
					   tr.setText(r.getText());
					   tr.setUrl(r.getUrl());
					   list.add(tr);
				   }
			   }
			   
		   }else if(node.length()==1){//点的是一级菜单，获取二级菜单
			   for(Resource r:re){
					  
				   if(Integer.toString(r.getId()).length()==2){
                       Tree tr=new Tree();
			           tr.setId(r.getId());
					   tr.setLeaf(r.getLeaf());
					   tr.setPid(r.getPid());
					   tr.setText(r.getText());
					   tr.setUrl(r.getUrl());
					   list.add(tr);
				   }
			   }
			   
		   }else if(node.length()==2){//点的是一级菜单，获取二级菜单
			 
			   for(Resource r:re){
				  
				   if(Integer.toString(r.getId()).length()==4&&Integer.toString(r.getId()).substring(0,2).equals(node)){
					   Tree tr=new Tree();
					   tr.setId(r.getId());
					   tr.setLeaf(r.getLeaf());
					   tr.setPid(r.getPid());
					   tr.setText(r.getText());
					   tr.setUrl(r.getUrl());
					   list.add(tr);
				   }
			   }
			   
		   }
		   response.setCharacterEncoding("UTF-8");
	       PrintWriter out = response.getWriter();  
		   out.write(JSONObject.toJSONString(list));
		   System.out.println(JSONObject.toJSONString(list));
		   return null;
		   
	    }
}
