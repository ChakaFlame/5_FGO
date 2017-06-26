package tsys.sales.web;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tsys.sales.entity.Hotel;
import tsys.sales.entity.Order;
import tsys.sales.entity.OrderDetail;
import tsys.sales.logic.OrderDetailLogic;
import tsys.sales.logic.OrderListLogic;

public class OrderDetailAction {
	public String execute(HttpServletRequest req) throws SQLException {

		String page = "/Order/OrderCancel.jsp";
		HttpSession session = req.getSession();
		OrderListLogic orderListLogic = new OrderListLogic();
		OrderDetailLogic orderDetailLogic = new OrderDetailLogic();

		session.setAttribute("orderNo", Integer.valueOf(req.getParameter("orderNo")));

		ArrayList<Order> orderList = orderListLogic.orderList(String.valueOf(session.getAttribute("memberCode")));
		session.setAttribute("order", orderList);

		ArrayList<OrderDetail> orderDetailList = OrderDetailLogic
				.orderDetail(Integer.valueOf(req.getParameter("orderNo")));
		session.setAttribute("orderDetail", orderDetailList);

		for (OrderDetail orderDetail : orderDetailList) {
			String itemCodeStr = orderDetail.getItemCode();
			Hotel hotelList = orderDetailLogic.orderHotelDetail(itemCodeStr);
			session.setAttribute("hotel", hotelList);
		}
		return page;
	}
}