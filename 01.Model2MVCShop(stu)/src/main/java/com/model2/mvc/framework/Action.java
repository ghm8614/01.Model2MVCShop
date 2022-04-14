package com.model2.mvc.framework;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// �߻�Ŭ���� : Ȯ���� ����Ŭ�������� �߻�޼��� �������̵� ����
public abstract class Action {
	
	//F
	private ServletContext servletContext;
	
	//C
	public Action(){
	}
	
	//M
	
	// �� �߻�Ŭ������ Ȯ���� ����Ŭ�������� ServletContext ��ü�� ����� �� �ְ� �ϴ�
	// getter method 
	public ServletContext getServletContext() {
		return servletContext;
	}

	// setter method
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	// �߻�޼���
	// ��Ʈ������ ����Ʈ��Ʈ�ѷ��� ���� 
	public abstract String execute(HttpServletRequest request, HttpServletResponse response) throws Exception ;
}