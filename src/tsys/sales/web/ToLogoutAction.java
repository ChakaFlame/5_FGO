package tsys.sales.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tsys.sales.common.SalesBusinessException;
import tsys.sales.common.SalesSystemException;
import tsys.sales.entity.Hotel;

public class ToLogoutAction {
	public String execute(HttpServletRequest req){

		HttpSession session = req.getSession(true);				//セッションの破棄
		session.invalidate();

		String page = "/Login/Logout.jsp";							//入力画面を戻り値に設定

			return page;
	}
}
