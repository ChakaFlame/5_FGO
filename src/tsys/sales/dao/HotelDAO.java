/**
 *
 * HotelDAO.java
 *
 */
package tsys.sales.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import tsys.sales.entity.Hotel;
import tsys.sales.entity.OrderDetail;


public class HotelDAO {

    private Connection con;  //接続オブジェクト

    /**
     * コンストラクタ
     */
    public HotelDAO(Connection con) {
    	this.con = con;
    }

    /**
     * ホテル1件検索
     */
    public Hotel findHotelDetail(String hotelCode) throws SQLException{
		String sql = "SELECT * FROM Hotel WHERE HotelCode = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Hotel hotel = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hotelCode);
			res =  stmt.executeQuery();

			/**
			 *  検索結果がある場合、戻り値に設定する。
			 */
			if(res.next()) {
				hotel = new Hotel(
						res.getString("itemCode"),
						res.getString("hotelCode"),
						res.getDate("hotelDate"),
						res.getString("hotelName"),
						res.getString("cityCode"),
						res.getString("cityName"),
						res.getString("grade"),
						res.getInt("stock"),
						res.getInt("basePrice"));
			}else {
				System.out.println("error");
			}
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally{
			if(res != null) {
				res.close();
			}
			if(stmt != null) {
				stmt.close();
			}
		}
    	return hotel;
    }

    /**
     * ホテル条件一致検索
     */
    public ArrayList<Hotel> searchHotel(int cityCode, Date date) throws SQLException{
		String sql = "SELECT * FROM Hotel INNER JOIN HotelMaster ON Hotel.HotelCode = HotelMAster.HotelCode WHERE Date = ? AND CityCode = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Hotel hotel = null;
		ArrayList<Hotel> hotelList = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, cityCode);
			res =  stmt.executeQuery();

			// 検索結果をhotelListに追加する。
			while(res.next()) {
				hotel = new Hotel(
					res.getString("itemCode"),
					res.getString("hotelCode"),
					res.getDate("hotelDate"),
					res.getString("hotelName"),
					res.getString("cityCode"),
					res.getString("cityName"),
					res.getString("grade"),
					res.getInt("stock"),
					res.getInt("basePrice"));
				hotelList.add(hotel);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
    	return hotelList;
    }

    /*
     * 残室数の更新(取り消し時に増やす)
     */
    public boolean updateStock(ArrayList<OrderDetail> OrderDetailList){
    	boolean result = false;
		String sql = "update Hotel set Stock = Stock + ? where ItemCode = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		ArrayList<Hotel> hotelList = null;

		try {
			for (OrderDetail orderDetail : OrderDetailList) {
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, orderDetail.getQuantity());
				stmt.setString(2, orderDetail.getItemCode());
				stmt.executeQuery();
			}
			result = true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
    }

    /*
     * 残室数の更新(注文時に減らす)
     */
    public boolean updateStock(String itemCode, int quantity){
    	boolean result = false;
		String sql = "update Hotel set Stock = Stock - ? where ItemCode = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, quantity);
			stmt.setString(2, itemCode);
			stmt.executeQuery();
			result = true;
		} catch(SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }
}