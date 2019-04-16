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
import com.hh.domain.ComboxTree;
import com.hh.domain.PageBean;
import com.hh.domain.Resource;
import com.hh.domain.Role;
import com.hh.domain.Tree;
import com.hh.domain.User;
import com.hh.service.ResourceService;
import com.hh.service.RoleService;
import com.hh.utils.TreeBuilder;

public class ResourceAction extends BaseAction {
	@Autowired
	@Qualifier("resourceServiceImpl")
	private ResourceService resourceService;
	@Autowired
	@Qualifier("roleServiceImpl")
	private RoleService roleService;
	public ActionForward getResourceTree(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		int rid=Integer.parseInt(request.getParameter("rid"));//获取角色id
		Role role=roleService.queryById(rid);//查询角色
		Set<Resource> re=role.getResources();//获得该角色对应的所有资源
		List<ComboxTree> resourceTree=new ArrayList<ComboxTree>();//返回给前端的带有√的树
		List<Resource> rList=resourceService.queryAll();//获取系统所有资源
	
		for(Resource r:rList){
			  ComboxTree tr=new ComboxTree();
			   tr.setId(r.getId());
			   tr.setLeaf(r.getLeaf());
			   tr.setPid(r.getPid());
			   tr.setText(r.getText());
			   tr.setUrl(r.getUrl());
			   for(Resource rs:re){
					 if(rs.getId()==r.getId()){
						 tr.setChecked(true);
						 break;
					 }
					
				}
			   resourceTree.add(tr);
			
		}
		
		List<ComboxTree> list=TreeBuilder.bulid(resourceTree);//寻找节点的孩子
		PrintWriter out = response.getWriter();  
		out.write(JSONObject.toJSONString(list));
		System.out.println(JSONObject.toJSONString(list));
		return null;
	}

}
