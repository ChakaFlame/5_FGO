package tsys.sales.test;

/*
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.HotelDAO;
import tsys.sales.entity.OrderDetail;

public class TestHotelDAO_03 {
	public static void main(String[] args) {

		Connection con = null;

		// テストのための準備としてデータベースに接続する。

		try {
			con = ConnectionManager.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ここからテストを行う。
		try {
			HotelDAO hotelDAO = new HotelDAO(con);
			ArrayList<OrderDetail> orderDetailList1 = new ArrayList<OrderDetail>();
			ArrayList<OrderDetail> orderDetailList2 = new ArrayList<OrderDetail>();
			OrderDetail orderDetail = new OrderDetail();

			orderDetail.setOrderNo(1);
			orderDetail.setItemCode("TOR000001");
			orderDetail.setPrice(25000);
			orderDetail.setQuantity(2);

			orderDetailList1.add(orderDetail);

			boolean updated = hotelDAO.updateStock(orderDetailList1);

			if (updated) {
				System.out.println("更新完了。");
			}else{
				System.out.println("更新失敗。");
			}

		} catch (NullPointerException e) {
			System.out.println("NullPointerExceptionがスローされました。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLExceptionがスローされました。");
			e.printStackTrace();
		} finally {
			try { //データベースへの接続を切断する
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
}
*/
