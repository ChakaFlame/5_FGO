
package tsys.sales.web;

import tsys.sales.common.*;
import tsys.sales.entity.*;
import tsys.sales.logic.*;
import javax.servlet.http.*;

public class LoginAction {
	public String execute(HttpServletRequest req){
		String page = "/MainMenu/MainMenu.jsp";							//入力画面を戻り値に
		//boolean loginflag = false;							//ログインできるかのフラグ
		String memberCode = req.getParameter("membercode");
		String password = req.getParameter("password");

		if(memberCode != null && password != null && (memberCode.equals("") || password.equals("")) ){
			req.setAttribute("messsage", "メンバーコードとパスワードを入力してください");
			return page;
		}

		try{

			LoginLogic loginLogic = new LoginLogic();
			Member member = loginLogic.login(memberCode,password);

			HttpSession session = req.getSession();
			session.setAttribute("memberCode", member.getMemberCode());
			session.setAttribute("memberName", member.getName());

		} catch (SalesBusinessException e){
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
			page = "/Error/Error.jsp";
		} catch (SalesSystemException e){
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
			page = "Error/Error.jsp";
		}
			return page;
	}
}

