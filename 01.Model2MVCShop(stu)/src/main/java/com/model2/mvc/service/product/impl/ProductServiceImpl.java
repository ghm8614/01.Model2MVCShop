package com.model2.mvc.service.product.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.dao.ProductDAO;
import com.model2.mvc.service.product.vo.ProductVO;


public class ProductServiceImpl implements ProductService {

	private ProductDAO productDAO;
	
	//C
	public ProductServiceImpl() {
		productDAO = new ProductDAO();		
	}
	
	@Override
	public ProductVO findProduct(int prodNo) throws Exception {
		return productDAO.findProduct(prodNo);
	}

	@Override
	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception{
		return productDAO.getProductList(searchVO);
	}

	@Override
	public void insertProduct(ProductVO productVO) throws Exception {
		productDAO.insertProduct(productVO);
	}

	@Override
	public void updateProduct(ProductVO productVO) throws Exception{
		productDAO.updateProduct(productVO);
	}

}
