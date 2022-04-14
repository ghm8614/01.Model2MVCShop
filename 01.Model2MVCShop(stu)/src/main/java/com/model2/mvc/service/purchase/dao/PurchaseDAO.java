package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.user.vo.UserVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseDAO {

	// C
	public PurchaseDAO() {

	}

	// M
	//
	public void insertPurchase(PurchaseVO purchase) throws Exception {

		System.out.println("======================================");
		System.out.println("insertPurchase");
		System.out.println(purchase);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS 드라이버 연결 성공 및 계정 접속 성공");

		String sql = "INSERT INTO transaction VALUES(seq_transaction_tran_no.nextval, ?, ?, ?, ?, ?, ?, ?, ?, sysdate,?)";

		System.out.println("2. 쿼리객체 생성");
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, purchase.getPurchaseProd().getProdNo());
		pstmt.setString(2, purchase.getBuyer().getUserId());
		pstmt.setString(3, purchase.getPaymentOption());
		pstmt.setString(4, purchase.getReceiverName());
		pstmt.setString(5, purchase.getReceiverPhone());
		pstmt.setString(6, purchase.getDivyAddr());
		pstmt.setString(7, purchase.getDivyRequest());
		pstmt.setString(8, purchase.getTranCode());
		pstmt.setString(9, purchase.getDivyDate());

		System.out.println("3. 쿼리 전송");
		int result = pstmt.executeUpdate();

		if (result == 1) {
			System.out.println("4. 쿼리 전송 성공");
		}
		System.out.println("======================================");

		pstmt.close();
		con.close();

	}

	public PurchaseVO findPurchase(int tranNo) throws Exception {

		System.out.println("====================================");
		System.out.println("findPurchase");
		System.out.println("tranNo: " + tranNo);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS 드라이버 연결 성공 및 계정 접속 성공");

		String sql = "SELECT * FROM transaction WHERE tran_no = ?";
		System.out.println("2. 쿼리객체 생성");
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, tranNo);

		ResultSet rs = pstmt.executeQuery();
		System.out.println("3. 쿼리 db로 보내고 받아오기");

		PurchaseVO purchaseVO = null;
		while (rs.next()) {
			/*
			 * System.out.println(rs.getInt("tran_no"));
			 * System.out.println(rs.getInt("prod_no"));
			 * System.out.println(rs.getString("buyer_id"));
			 * System.out.println(rs.getString("payment_option") + "A");
			 * System.out.println(rs.getString("receiver_name"));
			 * System.out.println(rs.getString("receiver_phone"));
			 * System.out.println(rs.getString("demailaddr"));
			 * System.out.println(rs.getString("dlvy_request"));
			 * System.out.println(rs.getString("tran_status_code") + "A");
			 * System.out.println(rs.getString("order_data"));
			 * System.out.println(rs.getString("dlvy_date"));
			 */

			purchaseVO = new PurchaseVO();

			ProductVO product = new ProductVO();
			product.setProdNo(rs.getInt("prod_no"));

			UserVO user = new UserVO();
			user.setUserId(rs.getString("buyer_id"));

			purchaseVO.setPurchaseProd(product);
			purchaseVO.setBuyer(user);

			purchaseVO.setTranNo(rs.getInt("tran_no"));
			purchaseVO.setPaymentOption(rs.getString("payment_option").trim());
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
			purchaseVO.setDivyAddr(rs.getString("demailaddr"));
			purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
			purchaseVO.setTranCode(rs.getString("tran_status_code").trim());
			purchaseVO.setOrderDate(rs.getDate("order_data"));
			purchaseVO.setDivyDate(rs.getString("dlvy_date"));

			System.out.println(("4. data 받아오기 성공"));
		}
		System.out.println("dao에서의 " + purchaseVO);

		pstmt.close();
		con.close();

		return purchaseVO;
	}

	public Map<String, Object> getPurchaseList(SearchVO searchVO, String userId) throws Exception {

		System.out.println("======================================");
		System.out.println("getPurchaseList(SearchVo searchVO)");
		System.out.println("dao에서의 " + searchVO);
		System.out.println("userId:  " + searchVO);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS 드라이버 연결 성공 및 계정 접속 성공");

		String sql = "SELECT T.tran_no, T.order_data, P.prod_no, P.prod_name, T.receiver_name, T.demailaddr, T.tran_status_code, T.payment_option\r\n"
				+ "FROM transaction T, product P WHERE P.prod_no = T.prod_no AND buyer_id = ? ";

		System.out.println("2. 쿼리객체 생성");
		PreparedStatement pstmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		pstmt.setString(1, userId);

		ResultSet rs = pstmt.executeQuery();
		System.out.println("3. 쿼리 db로 보내고 받아오기");

		rs.last(); // 커서를 맨 마지막 row로 이동
		int total = rs.getRow(); // 현재 커서가 가리키는 row 번호 get
		System.out.println("로우의 수:" + total);

		Map<String, Object> map = new HashMap<>();
		map.put("count", new Integer(total));

		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit() + 1);

		List<PurchaseVO> list = new ArrayList<>();

		if (total > 0) {

			for (int i = 0; i < searchVO.getPageUnit(); i++) {

				System.out.println("주문번호:" + rs.getString("tran_no"));
				System.out.println("주문일자:" + rs.getString("order_data"));
				System.out.println("상품번호:" + rs.getString("prod_no"));
				System.out.println("상품명:" + rs.getString("prod_name"));
				System.out.println("수령인:" + rs.getString("receiver_name"));
				System.out.println("배송주소:" + rs.getString("demailaddr"));
				System.out.println("상태코드:" + rs.getString("tran_status_code"));
				System.out.println("결제방법:" + rs.getString("payment_option"));

				PurchaseVO purchaseVO = new PurchaseVO();
				ProductVO productVO = new ProductVO();

				productVO.setProdNo(rs.getInt("prod_no"));
				productVO.setProdName(rs.getString("prod_name"));

				purchaseVO.setPurchaseProd(productVO);

				purchaseVO.setTranNo(rs.getInt("tran_no"));
				purchaseVO.setOrderDate(rs.getDate("order_data"));
				purchaseVO.setReceiverName(rs.getString("receiver_name"));
				purchaseVO.setDivyAddr(rs.getString("demailaddr"));
				purchaseVO.setTranCode(rs.getString("tran_status_code").trim());
				purchaseVO.setPaymentOption(rs.getString("payment_option").trim());
				System.out.println(("4. data 받아오기 성공"));

				list.add(purchaseVO);

				if (!rs.next()) {
					break;
				}
			}

			map.put("list", list);

			pstmt.close();
			con.close();
		}

		return map;
	}

	public HashMap<String, Object> getSaleList(SearchVO searchVO) {

		return null;
	}

	// update
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception {

		System.out.println("====================================");
		System.out.println("updatePurchase");
		System.out.println("dao에서의 " + purchaseVO);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS 드라이버 연결 성공 및 계정 접속 성공");

		String sql = "UPDATE transaction \r\n"
				+ "SET payment_option=?, receiver_name=?, receiver_phone=?, demailaddr=?, dlvy_request=?, dlvy_date=? \r\n"
				+ "WHERE tran_no = ?";

		System.out.println("2. 쿼리객체 생성");
		PreparedStatement pstmt = con.prepareStatement(sql);

		pstmt.setString(1, purchaseVO.getPaymentOption());
		pstmt.setString(2, purchaseVO.getReceiverName());
		pstmt.setString(3, purchaseVO.getReceiverPhone());
		pstmt.setString(4, purchaseVO.getDivyAddr());
		pstmt.setString(5, purchaseVO.getDivyRequest());
		pstmt.setString(6, purchaseVO.getDivyDate());
		pstmt.setInt(7, purchaseVO.getTranNo());

		System.out.println("3. 쿼리 전송");
		int result = pstmt.executeUpdate();

		if (result == 1) {
			System.out.println("4. 쿼리 전송 성공");
		}

		pstmt.close();
		con.close();

	}

	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {

		System.out.println("====================================");
		System.out.println("updateTranCode");
		System.out.println("dao에서의 " + purchaseVO);

		Connection con = DBUtil.getConnection();
		System.out.println("1. RDBMS 드라이버 연결 성공 및 계정 접속 성공");

//		String sql = "UPDATE transaction SET tran_status_code = ? ";

		System.out.println("2. 쿼리객체 생성");

		if (purchaseVO.getTranCode().equals("2")) {

			String sql = "UPDATE transaction SET tran_status_code = ? WHERE prod_no = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);

			pstmt.setString(1, purchaseVO.getTranCode());
			pstmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());

			System.out.println("3. 쿼리 전송");
			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("4. 쿼리 전송 성공");
			}

			pstmt.close();
			con.close();

		} else if (purchaseVO.getTranCode().equals("3")) {

			String sql2 = "UPDATE transaction SET tran_status_code = ? WHERE tran_no = ?";

			PreparedStatement pstmt = con.prepareStatement(sql2);

			pstmt.setString(1, purchaseVO.getTranCode());
			pstmt.setInt(2, purchaseVO.getTranNo());

			System.out.println("3. 쿼리 전송");
			int result = pstmt.executeUpdate();

			if (result == 1) {
				System.out.println("4. 쿼리 전송 성공");
			}

			pstmt.close();
			con.close();
		}

	}
}
