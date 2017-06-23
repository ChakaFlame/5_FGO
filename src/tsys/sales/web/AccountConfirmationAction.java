
package tsys.sales.web;

import tsys.sales.common.*;
import tsys.sales.entity.*;
import tsys.sales.logic.*;
import javax.servlet.http.*;

public class AccountConfirmationAction {
	public String execute(HttpServletRequest req){
		String page = "/Account/AccountConfirmation.jsp";						//入力画面を戻り値に

		Member member = new Member();
		member = (Member) req.getAttribute("member");							//Member型にキャストしてgetAttribute

		try{

			AccountConfirmationLogic accountConfirmationLogic = new AccountConfirmationLogic();
			Member member_IM = accountConfirmationLogic.memberConfirmed(member);

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

		return page;
	}
}

