package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeByProdAction extends Action {

	// admin ���� ��ǰ����(listProduct)���� ����ϱ� ��ư
	// ����ϱ� ��ư Ŭ�� -> ��ǰ���� listProduct ������� : �����
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// ����ϱ� ��ư Ŭ����, �Ѿ���� ������
		String prodNo = request.getParameter("prodNo");
		String tranCode = request.getParameter("tranCode"); // ������ : 2

		ProductVO productVO = new ProductVO();
		productVO.setProdNo(Integer.parseInt(prodNo));

		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setTranCode(tranCode);
		System.out.println("��Ʈ�ѷ������� " + purchaseVO);

		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.updateTranCode(purchaseVO);
		
		// listProduct.jsp
		return "forward:/listProduct.do?menu=manage";
	}

}
