package tsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.HotelDAO;
import tsys.sales.entity.Hotel;

public class TestHotelDAO_01 {

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
			Hotel hotel = hotelDAO.findHotelDetail("HTL000002");

			if (hotel == null) {
				System.out.println("戻り値：" + hotel);
			}else{
				System.out.println("ホテルコード：" + hotel.getHotelCode());
				System.out.println("ホテル名　　：" + hotel.getHotelName());
				System.out.println("都市コード　：" + hotel.getCityCode());
				System.out.println("グレード　　：" + hotel.getGrade());
				System.out.println("基本料金　　：" + hotel.getBasicPrice());
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
}