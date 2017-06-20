package tsys.sales.web;

import tsys.sales.common.*;
import tsys.sales.entity.*;
import tsys.sales.logic.*;
import javax.servlet.http.*;

public class LoginAction {
	public String execute(HttpServletRequest req){
		String page = "/MainMenu.jsp";							//入力画面を戻り値に
		//boolean loginflag = false;							//ログインできるかのフラグ
		String memberCode = req.getParameter("MemberCode");
		String password = req.getParameter("Password");

		if(memberCode != null && password != null && (memberCode.equals("") || password.equals("")) ){
			req.setAttribute("messsage", "メンバーコードとパスワードを入力してください");
			return page;
		}

		try{
			LoginLogic loginLogic = new LoginLogic();
			Member member = loginLogic.login(memberCode,password);

			req.setAttribute("memberCode", member.getMemberCode());
			req.setAttribute("password", member.getPassword());

		} catch (SalesBusinessException e){
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		} catch (SalesSystemException e){
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
			page = "/SalesErrorView.jsp";
		}
			return page;
	}

}
