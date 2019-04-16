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
import com.hh.domain.PageBean;
import com.hh.domain.Repertory;
import com.hh.domain.Role;
import com.hh.domain.User;
import com.hh.service.RepertoryService;
public class RepertoryAction extends BaseAction {

	@Autowired
	@Qualifier("repertoryServiceImpl")
	private RepertoryService repertoryService;

	/*
	 * 获取仓库所有信息
	 */
	public ActionForward getRepertoryList(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		int start = Integer.valueOf(request.getParameter("start"));// 开始位置
		int pageSize = Integer.valueOf(request.getParameter("limit"));// 每页记录数
        PageBean<Repertory> pageBean=repertoryService.findByPage(start, pageSize);
		StringBuilder json = new StringBuilder("{\"total\":");
		// root:需要展示的数据json格式，fastjson转化数据为要求的json格式
		json.append(pageBean.getTotalCount()).append(",\"repertory\":");
		json.append(JSON.toJSONString(pageBean.getList()));
		json.append("}");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		return null;
	}

	/*
	 * 添加仓库信息
	 */
	public ActionForward addRepertory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Repertory repertory = (Repertory) request.getAttribute("repertoryForm");
		response.setCharacterEncoding("UTF-8");
		if(repertoryService.queryByName(repertory)!=null){
			response.getWriter().write(JSONObject.toJSONString("仓库名已存在"));
		}else{
			repertoryService.insert(repertory);
		}
		return null;
		
	}

	/*
	 * 修改仓库信息
	 */
	public ActionForward updateRepertory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Repertory repertory = (Repertory) request.getAttribute("repertoryForm");
		Repertory re=repertoryService.queryById(repertory.getRepertory_id());
		re.setRemark(repertory.getRemark());
		re.setRepertory_name(repertory.getRepertory_name());
		repertoryService.update(re);
		return null;

	}

	/*
	 * 根据条件查询仓库信息
	 */
	public ActionForward getRepertoryListByKey(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		Repertory repertory = (Repertory) request.getAttribute("repertoryForm");
		int start = Integer.valueOf(request.getParameter("start"));// 开始位置
		int pageSize = Integer.valueOf(request.getParameter("limit"));// 每页记录数
		String name = new String(request.getParameter("repertory_name").getBytes("iso-8859-1"),"utf-8");
        repertory.setRepertory_name(name);
        PageBean<Repertory> pageBean= repertoryService.queryByPageAndKey(start,pageSize,repertory);
		StringBuilder json = new StringBuilder("{\"total\":");
		// root:需要展示的数据json格式，fastjson转化数据为要求的json格式
		json.append(pageBean.getTotalCount()).append(",\"repertory\":");
		json.append(JSON.toJSONString(pageBean.getList()));
		json.append("}");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		return null;

	}
	public ActionForward deleteRepertory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		  int repertory_id=Integer.valueOf(request.getParameter("repertory_id"));
		 Repertory re=new Repertory();
		 re.setRepertory_id(repertory_id);
		 repertoryService.delete(re);
		 return null;
	
	};
	public ActionForward findByPage(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		  int repertory_id=Integer.valueOf(request.getParameter("repertory_id"));
		 Repertory re=new Repertory();
		 re.setRepertory_id(repertory_id);
		 repertoryService.delete(re);
		 return null;
	
	};
	public ActionForward getRepertory(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setCharacterEncoding("UTF-8");
		List<Repertory> repertoryList=repertoryService.queryAll();
		StringBuilder json = new StringBuilder("{\"data\":");
		json.append(JSON.toJSONString(repertoryList));
		json.append("}");
	    PrintWriter out = response.getWriter();
		out.write(json.toString());
				return null;
		
	}
	
	

}
