package com.model2.mvc.view.purchase;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class ListPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		SearchVO searchVO = new SearchVO();

		int page = 1;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		searchVO.setPageUnit(Integer.parseInt(getServletContext().getInitParameter("pageUnit")));
		searchVO.setPage(page);
		System.out.println("컨트롤러에서의 " + searchVO);

		
		PurchaseService purchaseSerivce = new PurchaseServiceImpl();

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("user");

		Map<String, Object> map = purchaseSerivce.getPurchaseList(searchVO, userVO.getUserId());
		System.out.println("map: " + map);

		
		request.setAttribute("searchVO", searchVO);
		request.setAttribute("count", map.get("count"));
		request.setAttribute("list", map.get("list"));

		return "forward:/purchase/listPurchase.jsp";
	}

}
