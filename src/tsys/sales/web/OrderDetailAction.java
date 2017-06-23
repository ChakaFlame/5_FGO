package tsys.sales.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tsys.sales.entity.Hotel;
import tsys.sales.entity.Order;
import tsys.sales.entity.OrderDetail;
import tsys.sales.logic.HotelDetailLogic;
import tsys.sales.logic.HotelSearchLogic;
import tsys.sales.logic.OrderDetailLogic;

public class OrderDetailAction {
	public String execute(HttpServletRequest req) {

		HttpSession session = req.getSession();

		String page = null;
		String orderindex = (String) req.getParameter("ORDER_INDEX");
		Order order = null;
		ArrayList<Order> orderList = (ArrayList<Order>) session.getAttribute("orderList");

		session.setAttribute("order", req.getParameter("index"));
		session.removeAttribute("orderList");

		try {
			order = (Order) session.getAttribute(orderindex);

			ArrayList<OrderDetail> orderDetailList= OrderDetailLogic.orderDetail(order.getOrderNo());
			ArrayList<Hotel> hotelList=HotelSearchLogic.searchHotel(orderDetailList.get(2));


			for (OrderDetail orderDetail2 : orderDetailList) {
				session.setAttribute("orderDetail", orderDetail2);
			}

			if (orderList == null) {
				req.setAttribute("message", "注文履歴なし。");
				return page;
			}
		} catch (Exception e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		}
		return page;
	}
}
