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
		String page = "/OrderConfirmation.jsp";


		//セッションを繋ぐ
		HttpSession session = req.getSession(false);
		if (session == null) {
			req.setAttribute("message", "エラーが発生しました。");
			page = "Error.jsp";
			return page;
		}
		//セッションから必要事項をゲット
		String memberCode = (String)session.getAttribute("memberCode");
		ArrayList<Item> cart = (ArrayList<Item>)session.getAttribute("cart");

		//クライアントの入力値を取得する。
		String payment = req.getParameter("PAYMENT");

		try {
			//ShoppingCartConfirmLogicを生成し、メソッドを呼び出す。
			ShoppingCartConfirmLogic shoppingCartConfirmLogic = new ShoppingCartConfirmLogic();
			if(!shoppingCartConfirmLogic.orderConfirm(payment, memberCode, cart)) {
				req.setAttribute("message", "エラーが発生しました。");
				page = "Error.jsp";
				return page;
			} else {
				page = "/OrderConfirmed.jsp";
			}
		} catch(SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
		} catch(SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			//エラー画面に戻り値を設定
			page = "/Error.jsp";
		}
		return page;
	}
}
