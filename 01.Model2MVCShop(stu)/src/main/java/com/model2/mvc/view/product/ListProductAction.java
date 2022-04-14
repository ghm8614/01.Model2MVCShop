package com.model2.mvc.view.product;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

//@WebServlet("/ListProductAction")
public class ListProductAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		String menu = request.getParameter("menu");
		System.out.println("menu: " + menu);
		// ==================================================================
		SearchVO searchVO = new SearchVO();

		int page = 1;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));

		String pageUnit = super.getServletContext().getInitParameter("pageUnit");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));

		System.out.println("컨트롤러에서의 " + searchVO);
		// ==================================================================

		ProductService productService = new ProductServiceImpl();
		HashMap<String, Object> map = productService.getProductList(searchVO);

		request.setAttribute("menu", menu);
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("map", map);
		// map1 : (String "count", Integer total) -> 전체 게시물 개수
		// map2 : (String "list", ArrayList<ProductVO> list) -> 게시물 데이터

		return "forward:/product/listProduct.jsp";
	}
}
