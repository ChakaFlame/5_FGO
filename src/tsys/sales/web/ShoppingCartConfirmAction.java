package tsys.sales.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;

import tsys.sales.common.SalesBusinessException;
import tsys.sales.common.SalesSystemException;
import tsys.sales.logic.ShoppingCartConfirmLogic;
import tsys.sales.entity.Member;
import tsys.sales.entity.Item;
import tsys.sales.entity.Order;
import tsys.sales.entity.OrderDetail;

public class ShoppingCartConfirmAction {
	/**
	 * 注文確認画面の注文確定ボタンが押された場合の処理を実行する。
	 * @param request リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		String page = "/Order/OrderConfirmation.jsp";
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
		ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");
		int totalPrice = (int)session.getAttribute("totalPrice");
		String zipCode = (String)session.getAttribute("zipCode");
		String prefecture = (String)session.getAttribute("prefecture");
		String address = (String)session.getAttribute("address");

		//クライアントの入力値を取得する。
		String payment = req.getParameter("PAYMENT");

		try {
			//ShoppingCartConfirmLogicを生成し、メソッドを呼び出す。
			ShoppingCartConfirmLogic shoppingCartConfirmLogic = new ShoppingCartConfirmLogic();
			orderNo = shoppingCartConfirmLogic.orderConfirm(payment, memberCode, cart);
			if(orderNo == 0) {
				req.setAttribute("message", "エラーが発生しました。");
				page = "/Error/Error.jsp";
				return page;
			} else {
				page = "/Order/OrderConfirmed.jsp";
			}
		} catch(SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
		} catch(SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			//エラー画面に戻り値を設定
			page = "/Error/Error.jsp";
		}

		//決済方法の判定
				if (payment.equals("01")) {
					payment = "代金引き換え";
				} else {
					payment = "コンビニエンスストア決済";
				}


		//必要な項目をリクエストに格納
				req.setAttribute("orderNo", orderNo);
				req.setAttribute("cart", cart);
				req.setAttribute("payment", payment);
				req.setAttribute("totalPrice", totalPrice);
				req.setAttribute("zipCode", zipCode);
				req.setAttribute("prefecture", prefecture);
				req.setAttribute("address", address);

		//セッションに保存したカート情報（他決済に必要な情報）の削除
		session.removeAttribute("cart");
		session.removeAttribute("payment");
		session.removeAttribute("totalPrice");
		session.removeAttribute("zipCode");
		session.removeAttribute("prefecture");
		session.removeAttribute("address");

		return page;
	}
}
