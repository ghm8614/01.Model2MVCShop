package com.model2.mvc.framework;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 추상클래스 : 확장한 하위클래스에서 추상메서드 오버라이딩 강제
public abstract class Action {
	
	//F
	private ServletContext servletContext;
	
	//C
	public Action(){
	}
	
	//M
	
	// 이 추상클래스를 확장한 하위클래스에서 ServletContext 객체를 사용할 수 있게 하는
	// getter method 
	public ServletContext getServletContext() {
		return servletContext;
	}

	// setter method
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	// 추상메서드
	// 스트링으로 프론트컨트롤러에 리턴 
	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception ;
}