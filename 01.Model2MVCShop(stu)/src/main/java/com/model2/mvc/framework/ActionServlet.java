package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;

// DispatcherServlet = FrontController
public class ActionServlet extends HttpServlet {
	
	//F
	private RequestMapping mapper;

	//M
	@Override
	public void init() throws ServletException {
		super.init();
		//resources = actionmapping.properties ������ ��ü ���ڿ�
		String resources=getServletConfig().getInitParameter("resources");
		mapper=RequestMapping.getInstance(resources);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																									throws ServletException, IOException {
		String url = request.getRequestURI();	
		//System.out.println("url: "+ url);
		String contextPath = request.getContextPath(); 
		//System.out.println("contextPath: "+ contextPath);
		String path = url.substring(contextPath.length());
		System.out.println(path);	// /addUser.do
		
		try{
			Action action = mapper.getAction(path);
			action.setServletContext(getServletContext());
			
			String resultPage=action.execute(request, response);
			String result=resultPage.substring(resultPage.indexOf(":")+1);
			
			if(resultPage.startsWith("forward:"))
				HttpUtil.forward(request, response, result);
			else
				HttpUtil.redirect(response, result);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}