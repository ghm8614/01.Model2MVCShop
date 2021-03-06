<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.model2.mvc.common.SearchVO"%>
<%@ page import="com.model2.mvc.service.product.vo.ProductVO"%>

<%
	String menu = (String)request.getAttribute("menu");
	SearchVO searchVO = (SearchVO) request.getAttribute("searchVO");
	HashMap<String, Object> map = (HashMap<String, Object>) request.getAttribute("map");
	
	int total = 0;
	ArrayList<ProductVO> list = null;
	if (map != null) {
		total = ((Integer) map.get("count")).intValue();
		list = (ArrayList<ProductVO>) map.get("list");
	}
	
	int currentPage = searchVO.getPage();
	
	int totalPage = 0;
	if (total > 0) {
		totalPage = total / searchVO.getPageUnit();
		if (total % searchVO.getPageUnit() > 0)
			totalPage += 1;
	}
%>


<html>
<head>
<title>惑前 包府</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">
<!--
	function fncGetProductList() {
		document.detailForm.submit();
	}
	-->
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

	<div style="width: 98%; margin-left: 10px;">

	<!-- 傈眉 -->
		<form name="detailForm" action="/listProduct.do?menu=<%=menu%>" method="post">
		
			
		<!-- table 1 : 惑前包府 OR 惑前格废炼雀 title -->
			<table width="100%" height="37" border="0" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"
						width="15" height="37" /></td>
					<td background="/images/ct_ttl_img02.gif" width="100%"
						style="padding-left: 10px;">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">

							<%
							if (menu != null && menu.equals("search")) {
							%>
							<tr>
								<td width="93%" class="ct_ttl01">惑前 格废 炼雀</td>
							</tr>
							<%
							} else if (menu != null && menu.equals("manage")) {
							%>
							<tr>
								<td width="93%" class="ct_ttl01">惑前 包府</td>
							</tr>
							<%
							}
							%>

						</table>
					</td>
					<td width="12" height="37"><img src="/images/ct_ttl_img03.gif"
						width="12" height="37" /></td>
				</tr>
			</table>


		<!-- table 2 : searchCondition, searchKeyword, 八祸 滚瓢 抛捞喉-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
				
				<!-- condition, keyword -->
					<td align="right">
					<select name="searchCondition" class="ct_input_g" style="width: 80px">
							<option value="0">惑前锅龋</option>
							<option value="1">惑前疙</option>
							<option value="2">惑前啊拜</option>
					</select> 
					<input type="text" name="searchKeyword" class="ct_input_g" style="width: 200px; height: 19px" /></td>

				<!-- 八祸滚瓢 -->
					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;"><a
									href="javascript:fncGetProductList();">八祸</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>



		<!-- table 3 : 惑前包府 OR 惑前格废炼雀 data 抛捞喉 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">傈眉 <%=total%> 扒荐, 泅犁 <%=currentPage%> 其捞瘤
					</td>
				</tr>

				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">惑前疙</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">啊拜</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">殿废老</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">泅犁惑怕</td>
				</tr>
				<tr>
					<td colspan="11" bgcolor="808285" height="1"></td>
				</tr>
				
				<%
				int no = 1;
				for (ProductVO productVO : list) {
				%>
				<tr class="ct_list_pop">
					<td align="center"><%=no++%></td>
					<td></td>

					<%
					// 包府磊 老馆蜡历 葛滴 啊瓷..
					// 八祸滚瓢栏肺 request矫 listProductAction 俊辑 menu = null -> 澄器牢飘Exception 
					if (menu != null && menu.equals("search")) {
					%>
						<td align="left"><a
							href="/getProduct.do?prodNo=<%=productVO.getProdNo()%>&menu=search"><%=productVO.getProdName()%></a></td>
						<td></td>
						<td align="left"><%=productVO.getPrice()%></td>
						<td></td>
						<td align="left"><%=productVO.getManuDate()%></td>
						<td></td>
						
						<% if(productVO.getProTranCode()==null) {%>
							<td align="left">魄概吝</td>
						<%} else {%>
							<td align="left">犁绊绝澜</td>
						<%}
					
					// 	包府磊(admin)父
					} else if (menu != null && menu.equals("manage")) {
					%>
						<td align="left"><a
							href="/updateProductView.do?prodNo=<%=productVO.getProdNo()%>&menu=manage"><%=productVO.getProdName()%></a></td>
						<td></td>
						<td align="left"><%=productVO.getPrice()%></td>
						<td></td>
						<td align="left"><%=productVO.getManuDate()%></td>
						<td></td>
						
						<% if(productVO.getProTranCode()==null) {%>
							<td align="left">魄概吝</td>
						<%} else if(productVO.getProTranCode().startsWith("1")) {%>
							<td align="left">备概肯丰 <a href="/updateTranCodeByProd.do?prodNo=<%=productVO.getProdNo()%>&tranCode=2">硅价窍扁</a></td>
						<%} else if(productVO.getProTranCode().startsWith("2")) {%>
							<td align="left">硅价吝</td>
					<% }else if(productVO.getProTranCode().startsWith("3")){%>
						<td align="left">硅价肯丰</td>
					<%} 
					}
					}%>	
						
				</tr>
				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>
			</table>



		<!-- table 3 : 其捞瘤 匙厚霸捞记 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td align="center">
						<%
						for (int i = 1; i <= totalPage; i++) {
						%> <a
							href="/listProduct.do?page=<%=i%>&menu=<%=menu%>&searchCondition=<%=searchVO.getSearchCondition()%>&searchKeyword=<%=searchVO.getSearchKeyword()%>"><%=i%></a>
						<%
						}
						%>
					</td>
				</tr>
			</table>
			<!--  其捞瘤 Navigator 场 -->

		</form>

	</div>
</body>
</html>