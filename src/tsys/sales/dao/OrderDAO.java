/**
 *
 * OrderDAO.java
 *
 */
package tsys.sales.dao;

import java.sql.Connection;

import java.sql.Date;
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
    public ArrayList<Order> findOrder(String memberCode) throws SQLException{
    	String sql = "SELECT * FROM OrderMaster WHERE MemberCode = ?";
    	PreparedStatement stmt = null;
    	ResultSet res = null;
    	ArrayList<Order> orderList = new ArrayList<Order>();

    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, memberCode);
    		res = stmt.executeQuery();

    		//検索結果がある場合、検索結果の数だけOrderListに格納する。
    		if(res.next()) {
    			orderList.add( new Order(
    					res.getInt("orderNo"),
    					res.getDate("orderDate"),
    					res.getInt("orderTotal"),
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
    public ArrayList<OrderDetail> findOrderDetail(int orderNo) throws SQLException{
    	String sql = "SELECT * FROM OrderDetail WHERE OrderNo = ?";
    	PreparedStatement stmt = null;
    	ResultSet res = null;
    	ArrayList<OrderDetail> orderDetailList = new ArrayList<OrderDetail>();

    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setInt(1, orderNo);
    		res = stmt.executeQuery();

    		//検索結果がある場合、検索結果の数だけOrderDetailListに格納する。
    		if(res.next()) {
    			orderDetailList.add( new OrderDetail(
    					res.getInt("orderNo"),
    					res.getString("itemCode"),
    					res.getString("name"),
    					res.getInt("price"),
    					res.getInt("quantity")));
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
    public String insertOrder(Order order) throws SQLException{
    	String orderNo = null;
//    	boolean insertFlag = false;
    	//OrderMasterテーブルに該当の受注情報を追加

    	String sql1 = "INSERT INTO OrderMaster(OrderDate, OrderTotal, MemberCode, Payment) VALUES ('?','?','?','?')";
    	String sql2 = "SELECT * FROM OrderMaster order by OrderNo desc limit 1;";
    	ResultSet res;
    	PreparedStatement stmt1 = null;
    	PreparedStatement stmt2 = null;
    	int insertCount;

    	String sql = "INSERT INTO OrderMaster VALUES ('?','?','?','?')";
    	PreparedStatement stmt = null;


    	try {
    		stmt1 = con.prepareStatement(sql1);
    		stmt1.setDate(1,(Date) order.getOrderDate());
    		stmt1.setInt(2, order.getOrderTotal());
    		stmt1.setString(3, order.getMemberCode());
    		stmt1.setString(4, order.getPayment());
    		insertCount = stmt1.executeUpdate();
    		stmt2 = con.prepareStatement(sql2);
    		res =  stmt2.executeQuery();
    		orderNo = res.getString(1);
    	} catch (SQLException e) {
    		return orderNo;
    	} finally {
    		if (stmt1 != null) {
    			stmt1.close();
    		}
    		if (stmt2 != null) {
    			stmt2.close();
    		}
    	}
//    	insertFlag = true;
    	return orderNo;
    }
     public boolean insertOrderDetail(OrderDetail order) throws SQLException{
    	boolean insertFlag = false;
    	//OrderDetailテーブルに該当の受注情報を追加
    	String sql = "INSERT INTO OrderDetail VALUES ('?','?','?','?')";
    	PreparedStatement stmt = null;
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setInt(1, order.getOrderNo());
    		stmt.setString(2, order.getItemCode());
    		stmt.setInt(3, order.getPrice());
    		stmt.setInt(4, order.getQuantity());
    		stmt.executeUpdate();
    	} catch (SQLException e) {
    		return insertFlag;
    	} finally {
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	insertFlag = true;
    	return insertFlag;
    }

    /*
     * Orderテーブルから削除
     */

    public boolean deleteOrder(int OrderNo) throws SQLException{
    	boolean deleteFlag = false;
    	//OrderMasterテーブルから該当の受注情報を削除
    	String sql = "DELETE FROM OrderMaster WHERE OrderNo = ?";
    	PreparedStatement stmt = null;
    	con.setAutoCommit(false);
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setInt(1, OrderNo);
    		stmt.executeUpdate();
    	} catch (SQLException e) {
    		return deleteFlag;
    	}
    	//OrderDetailテーブルから該当の受注情報を削除
    	sql = "DELETE FROM OrderDetail WHERE OrderNo = ?";
    	stmt = null;
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setInt(1, OrderNo);
    		stmt.executeUpdate();
    	} catch (SQLException e) {
    		return deleteFlag;
    	} finally {
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	con.commit();
    	deleteFlag = true;
    	return deleteFlag;
    }

    /*
     * メンバーの1件検索
     */
    public Member findAddress(String memberCode) throws SQLException{
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
    	return member;
    }
}