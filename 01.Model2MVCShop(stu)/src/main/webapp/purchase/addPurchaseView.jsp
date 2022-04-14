<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>


<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO" %>
<%@ page import="com.model2.mvc.service.user.vo.UserVO" %>

<% PurchaseVO purchaseVO = (PurchaseVO)request.getAttribute("purchaseVO"); %>
<% UserVO userVO = (UserVO)session.getAttribute("user"); %>
 
<html>
<head>
<title>Insert title here</title>
</head>

<body>
<!-- ���� ��ư ���� ��, �������Ǵ� ���ſϷ� ���� ������ -->

������ ���� ���Ű� �Ǿ����ϴ�.

<table border=1>

<!-- purchaseVO �� User buyer, Product purchaseProd ���� -->
	<tr>
		<td>��ǰ��ȣ</td>
		<td><%= purchaseVO.getPurchaseProd().getProdNo()%></td>
		<td></td>
	</tr>
	
	<!-- ������ ���̵� �����ý�, 
		1. requestScope�� ����� purchase.buyer.userId
		2. sessionScope�� ����� user.userId 
		��� ����  -->
	<tr>
		<td>�����ھ��̵�</td>
		<td><%= userVO.getUserId() %></td>	
		<!--<td>${requestScope.purchase.buyer.userId}</td> -->	
		<td></td>
	</tr>
	
	
<!--  -->	
	<tr>
		<td>���Ź��</td>
		<td><%= purchaseVO.getPaymentOption()%></td>
		<td></td>
	</tr>
	<tr>
		<td>�������̸�</td>
		<td><%= purchaseVO.getReceiverName()%></td>
		<td></td>
	</tr>
	<tr>
		<td>�����ڿ���ó</td>
		<td><%= purchaseVO.getReceiverPhone()%></td>
		<td></td>
	</tr>
	<tr>
		<td>�������ּ�</td>
		<td><%= purchaseVO.getDivyAddr()%></td>
		<td></td>
	</tr>
		<tr>
		<td>���ſ�û����</td>
		<td><%= purchaseVO.getDivyRequest()%></td>
		<td></td>
	</tr>
	<tr>
		<td>����������</td>
		<td><%= purchaseVO.getDivyDate()%></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>