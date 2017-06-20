package tsys.sales.logic;

import java.sql.Connection;
import java.sql.SQLException;

import tsys.sales.common.SalesBusinessException;
import tsys.sales.common.SalesSystemException;
import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.HotelDAO;
import tsys.sales.entity.Hotel;

public class HotelDetailLogic {
	Connection con = null;

	Hotel getDetail(String hotelCode)throws SalesBusinessException, SalesSystemException {
		Hotel hotel = null;
		try {
			// データベースの接続を取得する
			con = ConnectionManager.getConnection();

			// ホテルテーブルアクセス用のDAOを生成し、メソッドを呼び出す。
			HotelDAO hotelDao = new HotelDAO(con);
			hotel = hotelDao.findHotelDetail(hotelCode);

			// 結果一覧がない場合、エラーを発生させる。
			if(hotel == null) {
				throw new SalesSystemException("エラーが発生しました。");
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

		return hotel;
	}

}
