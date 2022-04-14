package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class UpdatePurchaseAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		// String buyerId = request.getParameter("buyerId");
		// hidden data.. session getAttri로 userId=buyerId 사용하면 되는데, 파라미터로 받아올 필요가 있나?

		System.out.println("tranNo : " + tranNo);
		System.out.println(request.getParameter("paymentOption"));
		System.out.println(request.getParameter("receiverName"));
		System.out.println(request.getParameter("receiverPhone"));
		System.out.println(request.getParameter("receiverAddr"));
		System.out.println(request.getParameter("receiverRequest"));
		System.out.println(request.getParameter("divyDate"));

		PurchaseVO purchaseVO = new PurchaseVO();

		HttpSession session = request.getSession();
		UserVO userVO = (UserVO) session.getAttribute("user");
		purchaseVO.setBuyer(userVO);

		purchaseVO.setTranNo(tranNo);
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setDivyDate(request.getParameter("divyDate"));

		System.out.println("컨트롤러에서의 " + purchaseVO);

		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.updatePurchase(purchaseVO);

		return "forward:/getPurchase.do?tranNo=" + tranNo;
	}

}
