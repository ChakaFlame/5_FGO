package tsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import tsys.sales.common.SalesBusinessException;
import tsys.sales.common.SalesSystemException;
import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.HotelDAO;
import tsys.sales.entity.Hotel;

public class HotelSearchLogic {
	Connection con = null;

	public ArrayList<Hotel> searchHotel(String cityCode, Date hotelDate)throws SalesBusinessException, SalesSystemException {
		ArrayList<Hotel> hotelList = null;
		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// 商品テーブルアクセス用のDAOを生成し、メソッドを呼び出す。
			HotelDAO hotelDao = new HotelDAO(con);
			hotelList = hotelDao.searchHotel(cityCode, hotelDate);

			// 結果一覧がない場合、エラーを発生させる。
			if(hotelList.isEmpty()) {
				throw new SalesBusinessException("エラーが発生しました。");
			}
		}catch(SQLException e) {
			e.printStackTrace();
			throw new SalesSystemException("エラーが発生しました。");
		}finally {
			try {
				if(con != null){
					con.close();
				}
			} catch(SQLException e) {
				e.printStackTrace();
				throw new SalesSystemException("エラーが発生しました。");
			}
		}

		return hotelList;
	}
}
