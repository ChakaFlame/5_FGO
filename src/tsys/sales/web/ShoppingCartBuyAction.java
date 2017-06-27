package tsys.sales.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.*;

import tsys.sales.common.SalesBusinessException;
import tsys.sales.common.SalesSystemException;
import tsys.sales.logic.ShoppingCartBuyLogic;
import tsys.sales.entity.Member;
import tsys.sales.entity.Item;

public class ShoppingCartBuyAction {
	/**
	 * ショッピングカート画面の購入ボタンが押された場合の処理を実行する。
	 * @param request リクエスト情報
	 * @return 次画面名
	 */
	public String execute(HttpServletRequest req) {
		String page = "/Shoppingcart/ShoppingCart.jsp";

		//セッションを繋ぐ。
		HttpSession session = req.getSession(false);
		if (session == null) {
			req.setAttribute("message", "エラーが発生しました。");
			page = "/Error/Error.jsp";
			return page;
		}

		//カートが空の場合はショッピングカート画面でメッセージ表示
		if (session.getAttribute("cart") == null) {
			req.setAttribute("message", "ショッピングカートが空です。");
			return page;
		}

		//ログインしていない場合はログイン画面に遷移
		if (session.getAttribute("memberCode") == null) {
			page = "/Login/Login.jsp";
			return page;
		}

		try {
			//ShoppingCartBuyLogicを生成し、メソッドを呼び出す。
			ShoppingCartBuyLogic shoppingCartBuyLogic = new ShoppingCartBuyLogic();
			Member member = shoppingCartBuyLogic.findAddress((String)session.getAttribute("memberCode"));

			//合計金額を取得
			int totalPrice = (int)session.getAttribute("totalPrice");

			//検索結果をリクエストスコープに格納
			session.setAttribute("zipCode", member.getZipCode());
			session.setAttribute("prefecture", member.getPrefecture());
			session.setAttribute("address", member.getAddress());

			//結果画面を戻り値に設定する。
			page = "/Order/OrderConfirmation.jsp";
		} catch(SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
		} catch(SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("message", e.getMessage());
			//エラー画面に戻り値を設定
			page = "/Error/Error.jsp";
		}
		return page;
	}
}
