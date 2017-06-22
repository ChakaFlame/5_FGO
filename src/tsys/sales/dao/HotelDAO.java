/**
 *
 * HotelDAO.java
 *
 */
package tsys.sales.dao;

import java.sql.Connection;

import java.util.Date;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
     * 都市コードで該当都市を検索し、都市名を返す。
     */
    public String findCityName(String cityCode) throws SQLException{
		String sql = "SELECT * FROM City WHERE CityCode = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		String city = null;
		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, cityCode);
			res =  stmt.executeQuery();

			/**
			 *  検索結果がある場合、戻り値に設定する。
			 */
			if(res.next()) {
				city = res.getString("name");
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
    	return city;
    }

    /**
     * ホテルインスタンスに該当する詳細情報を追加し、ホテルインスタンスを返す。
     */
    public Hotel findHotel(Hotel hotel) throws SQLException{
		String sql = "SELECT * FROM HotelMaster WHERE HotelCode = ?";
		PreparedStatement stmt = null;

		ResultSet res = null;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setString(1, hotel.getHotelCode());
			res =  stmt.executeQuery();

			/**
			 *  検索結果がある場合、戻り値に設定する。
			 */
			if(res.next()) {
				hotel.setHotelName(res.getString("name"));
				hotel.setCityCode(res.getString("cityCode"));
				hotel.setCityName(this.findCityName(hotel.getCityCode()));
				hotel.setGrade(res.getString("grade"));
				hotel.setBasicPrice(res.getInt("basicPrice"));
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
     * 商品コードでホテル商品を検索し、ホテル商品詳細を返す。
     */
    public Hotel findHotelDetail(String itemCode) throws SQLException{
		String sql = "SELECT * FROM Hotel WHERE ItemCode = ?";

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
					res.getString("date"),
					res.getInt("stock")
				);
				hotel = this.findHotel(hotel);
				hotel.setCityName(this.findCityName(hotel.getCityCode()));
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
     * 都市コードと宿泊日で条件と一致するホテル検索し、ホテルリストを返す。
     */
    public ArrayList<Hotel> searchHotel(String cityCode, Date hotelDate) throws SQLException{
		String sql = "SELECT * FROM Hotel INNER JOIN HotelMaster ON Hotel.HotelCode = HotelMaster.HotelCode WHERE Date = ? AND CityCode = ?";
		PreparedStatement stmt = null;
		ResultSet res = null;
		Hotel hotel = null;
		ArrayList<Hotel> hotelList = new ArrayList<Hotel>();

		try {
			stmt = con.prepareStatement(sql);
			DateFormat fm = new SimpleDateFormat("yyyy-M-d");
			String date = fm.format(hotelDate);
			stmt.setString(1, date);
			stmt.setString(2, cityCode);
			res =  stmt.executeQuery();

			// 検索結果をhotelListに追加する。
			while(res.next()) {
				hotel = new Hotel(
						res.getString("itemCode"),
						res.getString("hotelCode"),
						res.getString("date"),
						res.getInt("stock")
					);
					hotel = this.findHotel(hotel);
					hotel.setCityName(this.findCityName(hotel.getCityCode()));
				hotelList.add(hotel);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
    	return hotelList;
    }

    /**
     * 注文取消後、注文詳細リストで残室数の更新(取り消し時に増やす)し、更新結果（true-成功, false-失敗）を返す。
     */
    public boolean updateStock(ArrayList<OrderDetail> OrderDetailList){
    	boolean result = false;
		String sql = "update Hotel set Stock = Stock + ? where ItemCode = ?";
		PreparedStatement stmt = null;
		int updated = 0;

		try {
			for (OrderDetail orderDetail : OrderDetailList) {
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, orderDetail.getQuantity());
				stmt.setString(2, orderDetail.getItemCode());
				updated = stmt.executeUpdate();
			}
			if (updated > 0){
				result = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
    }

    /**
     * 商品コードと購入数で残室数を更新(注文時に減らす)し、更新結果（true-成功, false-失敗）を返す。
     */
    public boolean updateStock(String itemCode, int quantity){
    	boolean result = false;
		String sql = "update Hotel set Stock = Stock - ? where ItemCode = ?";
		PreparedStatement stmt = null;
		int updated = 0;

		try {
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, quantity);
			stmt.setString(2, itemCode);
			updated = stmt.executeUpdate();
			if (updated > 0){
				result = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
    	return result;
    }
}