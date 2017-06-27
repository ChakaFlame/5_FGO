package tsys.sales.web;

import javax.servlet.http.HttpServletRequest;

public class ToOrderCancelAction {
	public String execute(HttpServletRequest req) {
		String page = "/Order/CancelConfirmation.jsp"; // 入力画面を戻り値に設定

		return page;
	}
}
