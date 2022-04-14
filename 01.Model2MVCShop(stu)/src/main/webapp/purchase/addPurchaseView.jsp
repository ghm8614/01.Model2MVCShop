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
<!-- 구매 버튼 누른 후, 렌더링되는 구매완료 동적 페이지 -->

다음과 같이 구매가 되었습니다.

<table border=1>

<!-- purchaseVO 에 User buyer, Product purchaseProd 존재 -->
	<tr>
		<td>물품번호</td>
		<td><%= purchaseVO.getPurchaseProd().getProdNo()%></td>
		<td></td>
	</tr>
	
	<!-- 구매자 아이디 가져올시, 
		1. requestScope에 저장된 purchase.buyer.userId
		2. sessionScope에 저장된 user.userId 
		모두 가능  -->
	<tr>
		<td>구매자아이디</td>
		<td><%= userVO.getUserId() %></td>	
		<!--<td>${requestScope.purchase.buyer.userId}</td> -->	
		<td></td>
	</tr>
	
	
<!--  -->	
	<tr>
		<td>구매방법</td>
		<td><%= purchaseVO.getPaymentOption()%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td><%= purchaseVO.getReceiverName()%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td><%= purchaseVO.getReceiverPhone()%></td>
		<td></td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td><%= purchaseVO.getDivyAddr()%></td>
		<td></td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td><%= purchaseVO.getDivyRequest()%></td>
		<td></td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td><%= purchaseVO.getDivyDate()%></td>
		<td></td>
	</tr>
</table>
</form>

</body>
</html>