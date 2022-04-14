<%--@page import="com.model2.mvc.common.util.CommonUtil"--%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>

<%@ page import="com.model2.mvc.service.user.vo.UserVO"%>
<%@ page import="com.model2.mvc.service.purchase.vo.PurchaseVO"%>
<%@ page import="java.util.List"%>
<%@ page import="com.model2.mvc.common.SearchVO"%>


<%
UserVO userVO = (UserVO) session.getAttribute("user");
%>
<%
List<PurchaseVO> list = (List<PurchaseVO>) request.getAttribute("list");
int total = (Integer) request.getAttribute("count");
%>
<%
SearchVO searchVO = (SearchVO) request.getAttribute("searchVO");
%>

<%
int totalPage = 0;

int pageUnit = searchVO.getPageUnit();

int currentPage = searchVO.getPage();

if (total > 0) {
	totalPage = total / pageUnit;
	if (total % pageUnit > 0) {
		totalPage++;
	}
}
%>

<!-- 일반회원의 구매이력조회 -->

<html>
<head>
<title>구매 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList() {
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<!-- 전체 -->
		<form name="detailForm" action="/listUser.do" method="post">

			<!-- table 1 :  -->
			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37"></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="93%" class="ct_ttl01">구매 목록조회</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37"></td>
				</tr>
			</table>


			<!-- table 2 : 구매목록 테이블 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">전체 <%=total%> 건수, 현재 <%=currentPage%> 페이지
					</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="50">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="120">주문일자</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">수령자명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="200">수령주소</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="300">배송현황</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">결제방법</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>

				<%
				int count = 1;
				for (PurchaseVO purchaseVO : list) {
				%>
				<tr class="ct_list_pop">
					<td align="center"><a
						href="/getPurchase.do?tranNo=<%=purchaseVO.getTranNo()%>"><%=count++%></a></td>
					<td></td>
					<td align="center"><%=purchaseVO.getOrderDate()%></td>
					<td></td>
					<td align="left"><a
						href="/getProduct.do?prodNo=<%=purchaseVO.getPurchaseProd().getProdNo()%>"><%=purchaseVO.getPurchaseProd().getProdName()%></a>
					</td>
					<td></td>
					<td align="left"><%=purchaseVO.getReceiverName()%></td>
					<td></td>
					<td align="left"><%=purchaseVO.getDivyAddr()%></td>
					<td></td>

					<%
					if (purchaseVO.getTranCode().equals("1")) {
					%>
					<td align="left">구매완료</td>
					<td></td>
					<%
					} else if (purchaseVO.getTranCode().equals("2")) {
					%>
					<td align="left"><a
						href="updateTranCode.do?tranNo=<%=purchaseVO.getTranNo()%>&tranCode=3">물건도착</a></td>
					<td></td>
					<%
					} else if (purchaseVO.getTranCode().equals("3")) {
					%>
					<td align="left">배송완료</td>
					<td></td>
					<%
					}
					%>


					<%
					if (purchaseVO.getPaymentOption().equals("1")) {
					%>
					<td align="left">현금결제</td>
					<td></td>
					<%
					} else if (purchaseVO.getPaymentOption().equals("2")) {
					%>
					<td align="left">신용결제</td>
					<td></td>
					<%
					}
					}
					%>
				</tr>
				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>


			</table>


			<!-- table 3 : PageNavigation 테이블 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">

				<tr>
					<td align="center">
						<%
						for (int i = 1; i <= totalPage; i++) {
						%> <a href="/listPurchase.do?page=<%=i%>"><%=i%></a> <%
 }
 %>
					</td>
				</tr>
			</table>


		</form>

	</div>

</body>
</html>