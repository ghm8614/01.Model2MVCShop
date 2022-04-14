package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeByProdAction extends Action {

	// admin 계정 상품관리(listProduct)에서 배송하기 버튼
	// 배송하기 버튼 클릭 -> 상품관리 listProduct 현재상태 : 배송중
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 배송하기 버튼 클릭시, 넘어오는 데이터
		String prodNo = request.getParameter("prodNo");
		String tranCode = request.getParameter("tranCode"); // 고정값 : 2

		ProductVO productVO = new ProductVO();
		productVO.setProdNo(Integer.parseInt(prodNo));

		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setTranCode(tranCode);
		System.out.println("컨트롤러에서의 " + purchaseVO);

		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.updateTranCode(purchaseVO);
		
		// listProduct.jsp
		return "forward:/listProduct.do?menu=manage";
	}

}
