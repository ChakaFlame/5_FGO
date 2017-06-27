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

		req.setAttribute("orderNo", Integer.valueOf(req.getParameter("orderNo")));
		String orderDate = req.getParameter("orderDate");


		ArrayList<Order> orderList = orderListLogic.orderList(String.valueOf(session.getAttribute("memberCode")));
		req.setAttribute("order", orderList);

		ArrayList<OrderDetail> orderDetailList = OrderDetailLogic
				.orderDetail(Integer.valueOf(req.getParameter("orderNo")));
		req.setAttribute("orderDetail", orderDetailList);

		ArrayList<Hotel> hotelList = new ArrayList();
		int totalprice=0;
		int count=0;
		Hotel hotel = null;
		for (OrderDetail orderDetail : orderDetailList) {
			String itemCodeStr = orderDetail.getItemCode();
			hotel = orderDetailLogic.orderHotelDetail(itemCodeStr);
			hotel.setReservNo(orderDetail.getQuantity());
			hotel.setHotelName(orderDetail.getName());
			hotel.setPrice(orderDetail.getPrice());
			hotelList.add(hotel);
			totalprice+=orderDetail.getPrice()*orderDetail.getQuantity();
			count++;
		}
		req.getAttribute(String.valueOf(hotel.getPrice()));
		req.setAttribute("count",count);
		req.setAttribute("totalprice", totalprice);
		req.setAttribute("hotelList", hotelList);
		req.setAttribute("orderDate", orderDate);
		return page;
	}
}