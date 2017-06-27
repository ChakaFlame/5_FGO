package tsys.sales.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;

import tsys.sales.common.SalesBusinessException;
import tsys.sales.common.SalesSystemException;
import tsys.sales.logic.OrderCancelLogic;
import tsys.sales.entity.*;

public class OrderCancelAction {
	/**
	 * 取消確認画面の取消確定ボタンが押された場合の処理を実行する。
	 *
	 * @param request
	 *            リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		String page = "/Order/CancelConfirmaition.jsp";
		int orderNo = 0;

		// セッションを繋ぐ
		HttpSession session = req.getSession(false);
		if (session == null) {
			req.setAttribute("message", "エラーが発生しました。");
			page = "/Error/Error.jsp";
			return page;
		}

		//必要な値をセッションから確保
		orderNo = (int)session.getAttribute("orderNo");
		ArrayList<OrderDetail> orderDetailList = (ArrayList<OrderDetail>) session.getAttribute("orderDetail");
		String orderDate = (String)session.getAttribute("orderDate");
		ArrayList<Hotel> hotelList = (ArrayList<Hotel>)session.getAttribute("hotelList");
		int count = (int)session.getAttribute("count");
		int totalPrice = (int)session.getAttribute("totalprice");

		try {
			// OrderCancelLogicを生成し、メソッドを呼び出す。
			OrderCancelLogic orderCancelLogic = new OrderCancelLogic();
			orderCancelLogic.orderCancel(orderNo, orderDetailList);
			page = "/Order/CancelConfirmed.jsp";
		} catch (SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
		} catch (SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			// エラー画面に戻り値を設定
			page = "/Error/Error.jsp";
		}

		// 必要な項目をリクエストに格納
		req.setAttribute("orderNo", orderNo);
		req.setAttribute("orderDetail", orderDetailList);
		req.setAttribute("orderDate", orderDate);
		req.setAttribute("hotelList", hotelList);
		req.setAttribute("count", count);
		req.setAttribute("totalprice", totalPrice);

		//セッションを切る
		session.removeAttribute("orderNo");
		session.removeAttribute("orderDetail");
		session.removeAttribute("orderDate");
		session.removeAttribute("count");
		session.removeAttribute("totalprice");
		session.removeAttribute("hotelList");
		session.removeAttribute("orderList");

		return page;
	}
}
