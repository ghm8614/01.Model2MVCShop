package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeAction extends Action {

	// user 계정 구매이력조회(listPurchase), 물건도착 버튼
	// 물건도착 버튼 -> listPurchase 배송현황 : 현재 배송완료 상태입니다.
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 물건도착 버튼 클릭시, 넘어오는 데이터
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		String tranCode = request.getParameter("tranCode"); // 고정값 : 3
		System.out.println("tranNo: " + tranNo);
		System.out.println("tranCode: " + tranCode);

		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setTranNo(tranNo);
		purchaseVO.setTranCode(tranCode);
		System.out.println("컨트롤러에서의 " + purchaseVO);

		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.updateTranCode(purchaseVO);

		// 현재페이지에 머물게 구현하기..
		return "forward:/listPurchase.do";
	}

}
