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

    public Hotel findHotelDetail(String itemCode) throws SQLException{
		String sql = "SELECT * FROM Hotel WHERE ItemCode = ?";
		String sql2 = "SELECT * FROM HotelMaster WHERE HotelCode = ?";
		String sql3 = "SELECT * FROM City WHERE CityCode = ?";

		PreparedStatement stmt = null;

		ResultSet res = null;

		Hotel hotel = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, itemCode);
			res =  stmt.executeQuery();

			/**
			 *  検索結果がある場合、戻り値に設定する。
			 */
			if(res.next()) {
				hotel = new Hotel(
					res.getString("itemCode"),
					res.getString("hotelCode"),
					res.getDate("date"),
					res.getInt("stock"));
				stmt = con.prepareStatement(sql2);
				stmt.setString(1, hotel.getHotelCode());
				res =  stmt.executeQuery();
				if(res.next()) {
					hotel.setHotelName(res.getString("name"));
					hotel.setCityCode(res.getString("cityCode"));
					hotel.setGrade(res.getString("grade"));
					hotel.setBasicPrice(res.getInt("basicPrice"));
					stmt = con.prepareStatement(sql3);
					stmt.setString(1, hotel.getCityCode());
					res=  stmt.executeQuery();
					if(res.next()) {
						hotel.setCityName(res.getString("name"));
					}else {
					System.out.println("error");
					}
				}
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