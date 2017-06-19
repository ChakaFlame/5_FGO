/**
 *
<<<<<<< HEAD
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
    	boolean insertFlag = false;

    	sql = "INSERT INTO OrderDetail VALUES ('?','?','?','?')";
    	PreparedStatement stmt = null;
    	ResultSet res = null;
    	Member member = null;

    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, OrderDetail.OrderNo);
    		stmt.setString(2, OrderDetail.ItemCode);
    		stmt.setString(3, OrderDetail.Price);
    		stmt.setString(4, OrderDetail.Quantity);
    		res = stmt.executeUpdate();
    	} catch (SQLException e) {
    		throw e;
    	} finally {
    		if(res != null) {
    			res.close();
    		}
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	con.commit();
    	insertFlag = true;
    	return insertFlag;
    }

    /*
     * Orderテーブルから削除
     */
    public boolean deleteOrderDetail(int OrderNo){
    	boolean deleteFlag = false;
    	//OrderMasterテーブルから該当の受注情報を削除
    	String sql = "DELETE FROM OrderMaster WHERE OrderNo = ?";
    	PreparedStatement stmt = null;
    	ResultSet res = null;
    	con.setAutoCommit(false);
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, OrderNo);
    		res = stmt.executeUpdate();
    	} catch (SQLException e) {
    		return deleteFlag;
    	} finally {
    		if(res != null) {
    			res.close();
    		}
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	//OrderDetailテーブルから該当の受注情報を削除
    	sql = "DELETE FROM OrderDetail WHERE OrderNo = ?";
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, OrderNo);
    		res = stmt.executeUpdate();
    	} catch (SQLException e) {
    		return deleteFlag;
    	} finally {
    		if(res != null) {
    			res.close();
    		}
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	con.commit();
    	deleteFlag = true;
    	return deleteFlag;
=======
 * OrderDAO.java
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
import tsys.sales.entity.OrderDetail;

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
    	String sql = "SELECT * FROM OrderMaster WHERE MemberCode = ?";
    	PreparedStatement stmt = null;
    	ResultSet res = null;
    	Member member = null;

    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, memberCode);
    		res = stmt.executeQuery();

    		//検索結果がある場合、検索結果の数だけOrderListに格納する。
    		ArrayList<Order> orderList = new ArrayList<Order>();
    		if(res.next()) {
    			orderList.add( new Order(
    					res.getString("orderNo"),
    					res.getDate("orderDate"),
    					res.getint("orderTotal"),
    					res.getString("memberCode"),
    					res.getString("payment")));
    		}
    	} catch (SQLException e) {
    		throw e;
    	} finally {
    		if(res != null) {
    			res.close();
    		}
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	return orderList;
    }

    /*
     * 注文詳細
     */
    public ArrayList<OrderDetaill> findOrderDetail(int orderNo){
    	String sql = "SELECT * FROM OrderDetail WHERE OrderNo = ?";
    	PreparedStatement stmt = null;
    	ResultSet res = null;
    	Member member = null;

    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, orderNo);
    		res = stmt.executeQuery();

    		//検索結果がある場合、検索結果の数だけOrderDetailListに格納する。
    		ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();
    		if(res.next()) {
    			orderDetailList.add( new OrderDetail(
    					res.getString("orderNo"),
    					res.getString("itemCode"),
    					res.getString("name"),
    					res.getString("price"),
    					res.getString("quantity")));
    		}
    	} catch (SQLException e) {
    		throw e;
    	} finally {
    		if(res != null) {
    			res.close();
    		}
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	return orderDetailList;
    }

    /*
     * Orderテーブルに追加
     */
    public boolean insertOrder(Order order){
    	boolean insertFlag = false;
    	//OrderMasterテーブルに該当の受注情報を追加
    	String sql = "INSERT INTO OrderMaster VALUES ('?','?','?','?')";
    	PreparedStatement stmt = null;
    	ResultSet res = null;
    	con.setAutoCommit(false);
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, OrderDetail.OrderNo);
    		stmt.setDate(2, OrderDetail.Date);
    		stmt.setint(3, OrderDetail.Total);
    		stmt.setString(4, OrderDetail.MemberCode);
    		stmt.setString(5, OrderDetail.Payment);
    		res = stmt.executeUpdate();
    	} catch (SQLException e) {
    		return insertFlag;
    	} finally {
    		if(res != null) {
    			res.close();
    		}
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	//OrderMasterテーブルに該当の受注情報を追加
    	sql = "INSERT INTO OrderDetail VALUES ('?','?','?','?')";
    	stmt = null;
    	res = null;

    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, OrderDetail.OrderNo);
    		stmt.setString(2, OrderDetail.ItemCode);
    		stmt.setString(3, OrderDetail.Price);
    		stmt.setString(4, OrderDetail.Quantity);
    		res = stmt.executeUpdate();
    	} catch (SQLException e) {
    		return insertFlag;
    	} finally {
    		if(res != null) {
    			res.close();
    		}
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	con.commit();
    	insertFlag = true;
    	return insertFlag;
    }

    /*
     * Orderテーブルから削除
     */

    public boolean deleteOrder(int OrderNo){
    	return ;
    }
    public boolean deleteOrderDetail(int OrderNo){
    	boolean deleteFlag = false;
    	//OrderMasterテーブルから該当の受注情報を削除
    	String sql = "DELETE FROM OrderMaster WHERE OrderNo = ?";
    	PreparedStatement stmt = null;
    	ResultSet res = null;
    	con.setAutoCommit(false);
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, OrderNo);
    		res = stmt.executeUpdate();
    	} catch (SQLException e) {
    		return deleteFlag;
    	} finally {
    		if(res != null) {
    			res.close();
    		}
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	//OrderDetailテーブルから該当の受注情報を削除
    	sql = "DELETE FROM OrderDetail WHERE OrderNo = ?";
    	stmt = null;
    	res = null;
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, OrderNo);
    		res = stmt.executeUpdate();
    	} catch (SQLException e) {
    		return deleteFlag;
    	} finally {
    		if(res != null) {
    			res.close();
    		}
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	con.commit();
    	deleteFlag = true;
    	return deleteFlag;
>>>>>>> branch 'master' of https://github.com/moon-ahhyun/5_FGO.git
    }

    /*
     * メンバーの1件検索
     */
    public Member findAddress(int memberCode){
    	String sql = "SELECT * FROM Member WHERE MemberCode = ?";
    	PreparedStatement stmt = null;
    	ResultSet res = null;
    	Member member = null;

    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, memberCode);
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