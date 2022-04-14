package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;

public class ProductDAO {

	// C
	public ProductDAO() {

	}

	public ProductVO findProduct(int prodNo) throws Exception {

		System.out.println("======================================");
		System.out.println("findProduct()");
		System.out.println("prodNo: " + prodNo);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS ����̹� ���� ���� �� ���� ���� ����");

		String sql = "SELECT * FROM product WHERE prod_no = ?";

		System.out.println("2. ������ü ����");
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, prodNo);

		System.out.println("3. ���� ���� �� data ��������");
		ResultSet rs = pstmt.executeQuery();

		ProductVO productVO = null;
		while (rs.next()) {
			productVO = new ProductVO();
			System.out.println("rs.next = true");

			productVO.setProdNo(rs.getInt("PROD_NO"));
			productVO.setProdName(rs.getString("PROD_NAME"));
			productVO.setProdDetail(rs.getString("PROD_DETAIL"));
			productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
			productVO.setPrice(rs.getInt("PRICE"));
			productVO.setFileName(rs.getString("IMAGE_FILE"));
			productVO.setRegDate(rs.getDate("REG_DATE"));

			System.out.println("4. data �������� ����");
		}
		System.out.println("dao������ " + productVO);
		System.out.println("======================================");

		pstmt.close();
		con.close();

		return productVO;
	}

	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception {

		System.out.println("======================================");
		System.out.println("getProductList(SearchVo searchVO)");
		System.out.println("dao������ " + searchVO);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS ����̹� ���� ���� �� ���� ���� ����");

		String sql = "SELECT * FROM product P, transaction T WHERE P.prod_no = T.prod_no(+) ";

		// �˻� ��ư Ŭ����, listProduct.do
		if (searchVO.getSearchCondition() != null) {
			if (searchVO.getSearchCondition().equals("0")) {
				sql += "AND P.prod_no = '" + searchVO.getSearchKeyword() + "'";
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += "AND P.prod_name LIKE '%" + searchVO.getSearchKeyword() + "%'";
			} else if (searchVO.getSearchCondition().equals("2")) {
				sql += "AND P.price = '" + searchVO.getSearchKeyword() + "'";
			}
		}

		// �˻�â null �̸�, �ܼ� ��� ��ǰ ��� ��ȸ
		sql += " ORDER BY P.prod_no";

		System.out.println("2. ������ü ����");
		PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_UPDATABLE);

		// ������ db�� �����ϰ� ���� ResultSet ��ü�� ���޹���
		System.out.println("3. ���� ���� �� data ��������");
		ResultSet rs = pstmt.executeQuery();

		rs.last(); // Ŀ���� �� ������ row�� �̵�
		int total = rs.getRow(); // ���� Ŀ���� ����Ű�� row ��ȣ get
		System.out.println("�ο��� ��:" + total);

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("count", new Integer(total));

		// ResultSet Ŀ���� ���ϴ� ��ġ(index)�� row�� �̵��ϴ� �޼���
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);

		ArrayList<ProductVO> list = new ArrayList<ProductVO>();

		
		if (total > 0) {

			System.out.println("4. data �������� ����");

			for (int i = 0; i < searchVO.getPageUnit(); i++) {

				ProductVO productVO = new ProductVO();

				productVO.setProdNo(rs.getInt("PROD_NO"));
				productVO.setProdName(rs.getString("PROD_NAME"));
				productVO.setProdDetail(rs.getString("PROD_DETAIL"));
				productVO.setManuDate(rs.getString("MANUFACTURE_DAY"));
				productVO.setPrice(rs.getInt("PRICE"));
				productVO.setFileName(rs.getString("IMAGE_FILE"));
				productVO.setRegDate(rs.getDate("REG_DATE"));
				productVO.setProTranCode(rs.getString("tran_status_code")); // null : �Ǹ���, 1 : ���ſϷ�
				// System.out.println(rs.getString("tran_status_code" + "A"));

				System.out.println(productVO);

				list.add(productVO);
				if (!rs.next()) {
					break;
				}
			}

			System.out.println("list�� ����� productVO ���� : " + list.size());
			map.put("list", list);
			System.out.println("map()�� ����� (key,value)�� ���� : " + map.size());

			System.out.println("======================================");

			pstmt.close();
			con.close();
		}

		return map;
	}

	public void insertProduct(ProductVO productVO) throws Exception {

		System.out.println("======================================");
		System.out.println("insertProduct");
		System.out.println("dao������ " + productVO);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS ����̹� ���� ���� �� ���� ���� ����");

		String sql = "INSERT INTO product VALUES(seq_product_prod_no.nextval,?,?,?,?,?,sysdate)";

		System.out.println("2. ������ü ����");
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, productVO.getProdName());
		pstmt.setString(2, productVO.getProdDetail());
		pstmt.setString(3, productVO.getManuDate().replace("-", ""));
		pstmt.setInt(4, productVO.getPrice());
		pstmt.setString(5, productVO.getFileName());

		System.out.println("3. ���� ����");
		int result = pstmt.executeUpdate();

		if (result == 1) {
			System.out.println("4. ���� ���� ����");
		}

		System.out.println("======================================");

		pstmt.close();
		con.close();
	}

	public void updateProduct(ProductVO productVO) throws Exception {

		System.out.println("======================================");
		System.out.println("updateProduct()");
		System.out.println(productVO);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS ����̹� ���� ���� �� ���� ���� ����");

		System.out.println("2. ������ü ����");
		String sql = "UPDATE product SET PROD_NAME = ?, PROD_DETAIL=?, MANUFACTURE_DAY=?, PRICE=?, IMAGE_FILE=? WHERE PROD_NO = ?";

		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, productVO.getProdName());
		pstmt.setString(2, productVO.getProdDetail());
		pstmt.setString(3, productVO.getManuDate());
		pstmt.setInt(4, productVO.getPrice());
		pstmt.setString(5, productVO.getFileName());
		pstmt.setInt(6, productVO.getProdNo());

		System.out.println("3. ���� ����");
		int result = pstmt.executeUpdate();

		if (result == 1) {
			System.out.println("4. ���� ���� ����");
		}

		pstmt.close();
		con.close();
	}

}
