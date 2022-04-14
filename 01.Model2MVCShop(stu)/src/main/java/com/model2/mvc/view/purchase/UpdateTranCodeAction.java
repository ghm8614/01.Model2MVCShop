package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeAction extends Action {

	// user ���� �����̷���ȸ(listPurchase), ���ǵ��� ��ư
	// ���ǵ��� ��ư -> listPurchase �����Ȳ : ���� ��ۿϷ� �����Դϴ�.
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// ���ǵ��� ��ư Ŭ����, �Ѿ���� ������
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		String tranCode = request.getParameter("tranCode"); // ������ : 3
		System.out.println("tranNo: " + tranNo);
		System.out.println("tranCode: " + tranCode);

		PurchaseVO purchaseVO = new PurchaseVO();
		purchaseVO.setTranNo(tranNo);
		purchaseVO.setTranCode(tranCode);
		System.out.println("��Ʈ�ѷ������� " + purchaseVO);

		PurchaseService purchaseService = new PurchaseServiceImpl();
		purchaseService.updateTranCode(purchaseVO);

		// ������������ �ӹ��� �����ϱ�..
		return "forward:/listPurchase.do";
	}

}
