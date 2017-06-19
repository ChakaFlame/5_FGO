/**
 *
 * HotelDAO.java
 *
 */
package tsys.sales.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import tsys.sales.entity.Hotel;


public class HotelDAO {

    private Connection con;  //接続オブジェクト

    /*
     * コンストラクタ
     */
    public HotelDAO(Connection con) {
    	this.con = con;
    }

//    /*
//     * 接続
//     */
//    public void connect() throws SQLException {
//        con = ConnectionManager.getConnection();
//    }

    /*
     * 接続のクローズ
     */
    public void close() throws SQLException {

        if (con != null) {
            con.close();
        }

    }

    /*
     * ホテル1件検索
     */
    public Hotel findHotelDetail(String hotelCode){
    	return ;
    }

    /*
     * ホテル条件一致検索
     */
    public ArrayList<Hotel> searchHotel(int cityCode, Date date){
    	return ;
    }

    /*
     * 残室数の更新(取り消し時に増やす)
     */
    public boolean updateStock(ArrayList<OrderDetail>){
    	return ;
    }

    /*
     * 残室数の更新(注文時に減らす)
     */
    public boolean updateStock(String itemCode, int quantity){
    	return ;
    }
}