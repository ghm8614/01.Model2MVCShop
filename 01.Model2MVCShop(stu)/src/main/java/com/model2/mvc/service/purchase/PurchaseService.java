package com.model2.mvc.service.purchase;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public interface PurchaseService {
	
	// add
	public void addPurchase(PurchaseVO purchaseVO) throws Exception; 
	
	// get
	public PurchaseVO getPurchase(int tranNo) throws Exception;
	
	// 구매목록 
	public Map<String, Object> getPurchaseList(SearchVO searchVO, String userId) throws Exception;
	
	// 판매목록
	public HashMap<String, Object> getSaleList(SearchVO searchVO);
	
	
	// update
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception;
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception;
}
