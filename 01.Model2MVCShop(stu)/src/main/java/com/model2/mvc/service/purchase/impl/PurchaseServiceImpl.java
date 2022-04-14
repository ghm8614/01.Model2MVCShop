package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.SearchVO;

import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;


public class PurchaseServiceImpl implements PurchaseService {

	PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {
		purchaseDAO = new PurchaseDAO();
	}
	
	//add
	@Override
	public void addPurchase(PurchaseVO purchaseVO) throws Exception {
		purchaseDAO.insertPurchase(purchaseVO);
	}
	
	//get
	@Override
	public PurchaseVO getPurchase(int tranNo) throws Exception{
		return purchaseDAO.findPurchase(tranNo);
	}

	@Override
	public Map<String, Object> getPurchaseList(SearchVO searchVO, String userId) throws Exception{
		return purchaseDAO.getPurchaseList(searchVO, userId);
	}

	@Override
	public HashMap<String, Object> getSaleList(SearchVO searchVO) {
		return null;
	}
	
	//update
	@Override
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception{
		purchaseDAO.updatePurchase(purchaseVO);
	}
	
	@Override
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception{
		purchaseDAO.updateTranCode(purchaseVO);
	}

}
