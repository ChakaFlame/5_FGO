package tsys.sales.test;

import java.sql.Connection;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.HotelDAO;
import tsys.sales.entity.Hotel;

public class TestHotelDAO_02 {
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
			ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

			int cityCode = 4;
			String strCityCode = String.format("%02d", cityCode);
			String date = "2017-01-01";
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date testDate = format.parse(date);

			hotelList = hotelDAO.searchHotel(strCityCode, testDate);

			if (hotelList.isEmpty()) {
				System.out.println("検索結果なし。");
			}else{
				for (Hotel hotel : hotelList){
					System.out.println("ホテルコード：" + hotel.getHotelCode());
					System.out.println("ホテル名　　：" + hotel.getHotelName());
					System.out.println("料金　　　　：" + hotel	.getBasicPrice());
					System.out.println("空室　　　　：" + hotel	.getStock());
					System.out.println("------------------------------------------");
				}
			}
		} catch (NullPointerException e) {
			System.out.println("NullPointerExceptionがスローされました。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLExceptionがスローされました。");
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
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

