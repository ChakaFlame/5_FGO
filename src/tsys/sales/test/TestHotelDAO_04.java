package tsys.sales.test;

/*
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.HotelDAO;

public class TestHotelDAO_04 {
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
			String itemCode = "TOR000001";
			int quantity = 1;

			boolean updated = hotelDAO.updateStock(itemCode, quantity);

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