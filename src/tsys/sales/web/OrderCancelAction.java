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
	 * @param request リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		String page = "/Order/CancelConfirmation.jsp";
		int orderNo = 0;

		//セッションを繋ぐ
		HttpSession session = req.getSession(false);
		if (session == null) {
			req.setAttribute("message", "エラーが発生しました。");
			page = "/Error/Error.jsp";
			return page;
		}

		//セッションから必要事項をゲット
		String memberCode = (String)session.getAttribute("memberCode");
		String memberName = (String)session.getAttribute("memberName");

		//リクエストから必要事項をゲット
		Order order = (Order)req.getAttribute("order");
		orderNo = order.getOrderNo();
		ArrayList<OrderDetail> orderDetailList = (ArrayList<OrderDetail>)req.getAttribute("orderList");

		try {
			//OrderCancelLogicを生成し、メソッドを呼び出す。
			OrderCancelLogic orderCancelLogic = new OrderCancelLogic();
			orderCancelLogic.orderCancel(orderNo,orderDetailList);
			page = "/Order/CancelConfirmed.jsp";
		} catch(SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
		} catch(SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			//エラー画面に戻り値を設定
			page = "/Error/Error.jsp";
		}

		//必要な項目をリクエストに格納
		req.setAttribute("order", order);
		req.setAttribute("orderDetailList", orderDetailList);

		return page;
	}
}
