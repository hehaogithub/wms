package com.hh.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.actions.DispatchAction;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


public class BaseAction extends DispatchAction {
	
	/**
     * 从服务容器中获取服务组件
     * 
     * @param pBeanId
     * @return
     */
	public Object getService(String BeanId,HttpServletRequest request) {
		 ApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
		Object obj=ctx.getBean(BeanId);
		 return obj;
	   }

}
