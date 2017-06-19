package tsys.sales.test;
/*
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.OrderDAO;
import tsys.sales.entity.Order;
import tsys.sales.entity.OrderDetail;

public class TestOrderDAO_02 {
	public static void main(){
		Connection con = null;

		// テストのための準備としてデータベースに接続する。
		try{
			con = ConnectionManager.getConnection();
		}catch (Exception e) {
			e.printStackTrace();
		}

		// ここからテストを行う。
		int orderNo = 2;
		try{
			OrderDAO orderDAO = new OrderDAO(con);
			ArrayList<OrderDetail> orderDetailList = OrderDAO.findOrderDetail(orderNo);
			for (OrderDetail orderDetail : orderDetailList) {
				System.out.println("受注番号：" + orderDetail.getOrderNo());
				System.out.println("商品コード：" + orderDetail.getItemCode());
				System.out.println("商品名：" + orderDetail.getName());
				System.out.println("料金：" + orderDetail.getPrice());
				System.out.println("購入数：" + orderDetail.getQuantity());
			}



		}catch (NullPointerException e) {
			System.out.println("NullPointerExceptionがスローされました。");
			e.printStackTrace();
		}catch (SQLException e) {
			System.out.println("SQLExceptionがスローされました。");
			e.printStackTrace();
		}finally {
			try{
				if(con != null){
					con.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
