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

<!-- �Ϲ�ȸ���� �����̷���ȸ -->

<html>
<head>
<title>���� �����ȸ</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
	function fncGetUserList() {
		document.detailForm.submit();
	}
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

		<!-- ��ü -->
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
								<td width="93%" class="ct_ttl01">���� �����ȸ</td>
							</tr>
						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37"></td>
				</tr>
			</table>


			<!-- table 2 : ���Ÿ�� ���̺� -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">��ü <%=total%> �Ǽ�, ���� <%=currentPage%> ������
					</td>
				</tr>
				<tr>
					<td class="ct_list_b" width="50">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="120">�ֹ�����</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">��ǰ��</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">�����ڸ�</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="200">�����ּ�</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="300">�����Ȳ</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">�������</td>
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
					<td align="left">���ſϷ�</td>
					<td></td>
					<%
					} else if (purchaseVO.getTranCode().equals("2")) {
					%>
					<td align="left"><a
						href="updateTranCode.do?tranNo=<%=purchaseVO.getTranNo()%>&tranCode=3">���ǵ���</a></td>
					<td></td>
					<%
					} else if (purchaseVO.getTranCode().equals("3")) {
					%>
					<td align="left">��ۿϷ�</td>
					<td></td>
					<%
					}
					%>


					<%
					if (purchaseVO.getPaymentOption().equals("1")) {
					%>
					<td align="left">���ݰ���</td>
					<td></td>
					<%
					} else if (purchaseVO.getPaymentOption().equals("2")) {
					%>
					<td align="left">�ſ����</td>
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


			<!-- table 3 : PageNavigation ���̺� -->
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