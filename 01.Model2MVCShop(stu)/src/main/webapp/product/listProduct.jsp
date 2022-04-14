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
<title>상품 관리</title>

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

	<!-- 전체 -->
		<form name="detailForm" action="/listProduct.do?menu=<%=menu%>" method="post">
		
			
		<!-- table 1 : 상품관리 OR 상품목록조회 title -->
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
								<td width="93%" class="ct_ttl01">상품 목록 조회</td>
							</tr>
							<%
							} else if (menu != null && menu.equals("manage")) {
							%>
							<tr>
								<td width="93%" class="ct_ttl01">상품 관리</td>
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


		<!-- table 2 : searchCondition, searchKeyword, 검색 버튼 테이블-->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
				
				<!-- condition, keyword -->
					<td align="right">
					<select name="searchCondition" class="ct_input_g" style="width: 80px">
							<option value="0">상품번호</option>
							<option value="1">상품명</option>
							<option value="2">상품가격</option>
					</select> 
					<input type="text" name="searchKeyword" class="ct_input_g" style="width: 200px; height: 19px" /></td>

				<!-- 검색버튼 -->
					<td align="right" width="70">
						<table border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td width="17" height="23"><img
									src="/images/ct_btnbg01.gif" width="17" height="23"></td>
								<td background="/images/ct_btnbg02.gif" class="ct_btn01"
									style="padding-top: 3px;"><a
									href="javascript:fncGetProductList();">검색</a></td>
								<td width="14" height="23"><img
									src="/images/ct_btnbg03.gif" width="14" height="23"></td>
							</tr>
						</table>
					</td>
				</tr>
			</table>



		<!-- table 3 : 상품관리 OR 상품목록조회 data 테이블 -->
			<table width="100%" border="0" cellspacing="0" cellpadding="0"
				style="margin-top: 10px;">
				<tr>
					<td colspan="11">전체 <%=total%> 건수, 현재 <%=currentPage%> 페이지
					</td>
				</tr>

				<tr>
					<td class="ct_list_b" width="100">No</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">상품명</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b" width="150">가격</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">등록일</td>
					<td class="ct_line02"></td>
					<td class="ct_list_b">현재상태</td>
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
					// 관리자 일반유저 모두 가능..
					// 검색버튼으로 request시 listProductAction 에서 menu = null -> 널포인트Exception 
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
							<td align="left">판매중</td>
						<%} else {%>
							<td align="left">재고없음</td>
						<%}
					
					// 	관리자(admin)만
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
							<td align="left">판매중</td>
						<%} else if(productVO.getProTranCode().startsWith("1")) {%>
							<td align="left">구매완료 <a href="/updateTranCodeByProd.do?prodNo=<%=productVO.getProdNo()%>&tranCode=2">배송하기</a></td>
						<%} else if(productVO.getProTranCode().startsWith("2")) {%>
							<td align="left">배송중</td>
					<% }else if(productVO.getProTranCode().startsWith("3")){%>
						<td align="left">배송완료</td>
					<%} 
					}
					}%>	
						
				</tr>
				<tr>
					<td colspan="11" bgcolor="D6D7D6" height="1"></td>
				</tr>
			</table>



		<!-- table 3 : 페이지 네비게이션 -->
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
			<!--  페이지 Navigator 끝 -->

		</form>

	</div>
</body>
</html>