package tsys.sales.web;

import javax.servlet.http.HttpServletRequest;

public class ToOrderCancelAction {
	public String execute(HttpServletRequest req) {
		String page = "/Order/OrderConfirmation.jsp"; // 入力画面を戻り値に設定

		return page;
	}
}
