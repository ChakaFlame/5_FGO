/**
 *
 * OrderDAO.java
 *
 */
package tsys.sales.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import tsys.sales.entity.Member;
import tsys.sales.entity.Order;
import tsys.sales.entity.OrderDetail;
import tsys.sales.entity.Item;

public class OrderDAO {

private Connection con;  //接続オブジェクト

    /*
     * コンストラクタ
     */
    public OrderDAO(Connection con) {
    	this.con = con;
    }

    /**
     * @param memberCode
     * @return
     * @throws SQLException
     *
     * 注文番号と一致するものを検索する。
     * memberCodeを引数とし、DBからmemberCodeが一致した行の全カラムを抜き出す
     * 複数ある場合もOrderList ArrayList<Order>に格納する
     * ArrayList<Order>を返す。
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

    		// 検索結果がある場合、検索結果の数だけOrderListに格納する。

    		while(res.next()) {
    			orderList.add( new Order(
    					res.getInt("orderNo"),
    					res.getString("orderDate"),
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

   /**
    *
    * @param orderNo
    * @return
    * @throws SQLException
    *
    * 注文詳細を表示する。
    * orderNoを引数に、OrderDetailテーブルより一致する行の全カラムを抜き出し
    * orderDetailListに格納する。
    * ArrayList<OrderDetail>を返す。
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
    		while (res.next()) {
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


    /**
     *
     * @param cart
     * @param orderDate
     * @param orderTotal
     * @param memberCode
     * @param payment
     * @return
     * @throws SQLException
     *
     * Orderテーブルに注文情報を挿入する。
     * 引数をSQLのINSERT文で挿入する。
     * OrderNoを返す。
     */
    public int insertOrder(String orderDate,int orderTotal,String memberCode,String payment)
    			throws SQLException{
    	int orderNo = 0;
//    	boolean insertFlag = false;
    	//OrderMasterテーブルに該当の受注情報を追加

    	String sql1 = "INSERT INTO OrderMaster(OrderDate, OrderTotal, MemberCode, Payment) VALUES (?,?,?,?)";
    	String sql2 = "SELECT * FROM OrderMaster order by OrderNo desc limit 1";
    	ResultSet res = null;
    	PreparedStatement stmt1 = null;
    	PreparedStatement stmt2 = null;
    	try {
    		stmt1 = con.prepareStatement(sql1);
    		stmt1.setString(1,orderDate);
    		stmt1.setInt(2, orderTotal);
    		stmt1.setString(3, memberCode);
    		stmt1.setString(4, payment);
    		stmt2 = con.prepareStatement(sql2);
    		res =  stmt2.executeQuery();
    		if(res.next()){
	    		orderNo = res.getInt(1);
    		}
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
    /**
     *
     * @param orderNo
     * @param cart
     * @return
     * @throws SQLException
     *
     * OrderDetailテーブルに注文詳細情報を挿入する。
     * 引数をSQLのINSERT文で挿入する。
     * 成功であればtrue,失敗であればfalseを返す。
     */
     public boolean insertOrderDetail(int orderNo,ArrayList<Item> cart) throws SQLException{
    	boolean insertFlag = false;
    	//OrderDetailテーブルに該当の受注情報を追加
    	String sql = "INSERT INTO OrderDetail VALUES (?,?,?,?,?)";
    	PreparedStatement stmt = null;
    	for (Item item : cart) {
    		try {
    			stmt = con.prepareStatement(sql);
    			stmt.setInt(1, orderNo);
    			stmt.setString(2, item.getHotel().getItemCode());
    			stmt.setString(3, item.getHotel().getHotelName());
    			stmt.setInt(4, item.getHotel().getBasicPrice());
    			stmt.setInt(5, item.getReservNo());
    			stmt.executeUpdate();
    		} catch (SQLException e) {
    			return insertFlag;
    		} finally {
    			if (stmt != null) {
    				stmt.close();
    			}
    		}
    	}
    	insertFlag = true;
    	return insertFlag;
    }

     /**
      *
      * @param OrderNo
      * @return
      * @throws SQLException
      *
      * Orderテーブル,OrderDetailテーブルから該当情報を削除
      * 引数から受け取った注文番号で検索し、該当情報を両テーブル
      * （Order,OrderDetail）から削除する。
      * 成功すればtrue,失敗であればfalseが返す。
      */
    public boolean deleteOrder(int OrderNo, ArrayList<OrderDetail>orderDetailList) throws SQLException{
    	boolean deleteFlag = false;
    	//OrderDetailテーブルから該当の受注情報を削除
    	String sql = "DELETE FROM OrderDetail WHERE OrderNo = ?";
    	PreparedStatement stmt = null;
    	try {
    		for (OrderDetail orderDetail : orderDetailList) {
    			stmt = con.prepareStatement(sql);
    			stmt.setInt(1, OrderNo);
    			stmt.executeUpdate();
    		}
    	} catch (SQLException e) {
    		return deleteFlag;
    	} finally {
    		if (stmt != null) {
    			stmt.close();
    		}
    	}
    	//OrderMasterテーブルから該当の受注情報を削除
    	sql = "DELETE FROM OrderMaster WHERE OrderNo = ?";
    	stmt = null;
    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setInt(1, OrderNo);
    		stmt.executeUpdate();
    	} catch (SQLException e) {
    		return deleteFlag;
    	}
    	deleteFlag = true;
    	return deleteFlag;
    }
    /**
     *
     * @param memberCode
     * @return
     * @throws SQLException
     *
     * memberCodeより、１件検索を行う。
     * 引数のmemberCodeより、MemberテーブルのmemberCodeと一致するものを
     * Memberインスタンスに格納、そのオブジェクトを返す。
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