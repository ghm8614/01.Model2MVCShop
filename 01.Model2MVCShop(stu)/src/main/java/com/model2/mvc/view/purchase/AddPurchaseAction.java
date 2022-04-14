package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.impl.UserServiceImpl;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// addPurchase.jsp hidden data : 1.prodNo 2.userId
		String prodNo = request.getParameter("prodNo");
		String buyerId = request.getParameter("buyerId");

		System.out.println("prodNo : " + prodNo);
		System.out.println("buyerId :" + buyerId);

		ProductService productService = new ProductServiceImpl();
		ProductVO productVO = productService.findProduct(Integer.parseInt(prodNo));
		System.out.println("컨트롤러에서의 " + productVO);

//		UserService userService = new UserServiceImpl();
//		User user = userService.getUser(buyerId);

		// 세션 사용
		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("user");

		PurchaseVO purchaseVO = new PurchaseVO();

		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setBuyer(userVO);

		// Transaction Table에 insert
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setTranCode("1"); // 구매완료 코드 : 1
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));

		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.addPurchase(purchaseVO);

		// updatePurchase.jsp 로 data 전달
		request.setAttribute("purchaseVO", purchaseVO); // prodNo, buyerId 포함

		// 판매 중인 상품이 판매완료로 바뀌므로, updatePurchase.jsp
		return "forward:/purchase/addPurchaseView.jsp";
	}

}
