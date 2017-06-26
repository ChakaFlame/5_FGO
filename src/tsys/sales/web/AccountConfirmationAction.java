
package tsys.sales.web;

import tsys.sales.common.*;
import tsys.sales.entity.*;
import tsys.sales.logic.*;
import javax.servlet.http.*;

public class AccountConfirmationAction {
	public String execute(HttpServletRequest req){
		String page = "/Account/AccountConfirmation.jsp";						//入力画面を戻り値に
		HttpSession session = req.getSession();								//session獲得用
		Member member = new Member();
		member = (Member) session.getAttribute("member");						//Member型にキャストしてgetAttribute
		System.out.println(member.getName());	//デバッグ用


		try{

			AccountConfirmationLogic accountConfirmationLogic = new AccountConfirmationLogic();
			Member member_IM = accountConfirmationLogic.memberConfirmed(member);
			System.out.println(member_IM.getName());
			req.setAttribute("member", member_IM);
			page = "/Account/AccountConfirmed.jsp";

		} catch (SalesBusinessException e){
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
			page = "/Error/Error.jsp";
		} catch (SalesSystemException e){
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
			page = "Error/Error.jsp";
		}

		//session.invalidate();		//新規登録時のmemberオブジェクト受け渡し用セッションの破棄

		return page;
	}
}