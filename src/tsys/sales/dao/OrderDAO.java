/**
 *
 * OrberDAO.java
 *
 */
package tsys.sales.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import tsys.sales.entity.Member;
import tsys.sales.entity.Order;
import tsys.sales.entity.OrderDetaill;

public class OrderDAO {

    private Connection con;  //接続オブジェクト

    /*
     * コンストラクタ
     */
    public OrderDAO(Connection con) {
    	this.con = con;
    }

    /*
     * 注文一覧
     */
    public ArrayList<Order> findOrder(String memberCode){
    	return ;
    }

    /*
     * 注文詳細
     */
    public ArrayList<OrderDetaill> findOrderDetail(int orderNo){
    	return ;
    }

    /*
     * Orderテーブルに追加
     */
    public boolean insertOrder(Order order){
    	return ;
    }

    /*
     * OrderDetailテーブルに追加
     */
    public boolean insertOrderDetail(OrderDetaill orderDetail){
    	return ;
    }

    /*
     * Orderテーブルから削除
     */
    public boolean deleteOrderDetail(int OrderNo){
    	return ;
    }

    /*
     * メンバーの1件検索
     */
    public Member findAddress(int memberCode){
    	String sql = "SELECT * FROM member WHERE MemberCode = ?";
    	PreparedStatement stmt = null;
    	ResultSet res = null;
    	Member member = null;

    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, memberCode)
    		res = stmt.executeQuery();

    		//検索結果がある場合、戻り値に設定する。
    		if(res.next()) {
    			member = new Member(
    					res.getString("memberCode"),
    					res.getString("name"),
    					res.getString("password"),
    					res.getString("mail"),
    					res.getString("zipCode"),
    					res.getString("prefecture"),
    					res.getString("address"),
    					res.getString("tel"));
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    		throw e;
    	} finally {
    		if(res != null) {
    			res.close();
    		}
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	return Member;
    }
}