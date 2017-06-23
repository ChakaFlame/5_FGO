
package tsys.sales.web;

import tsys.sales.common.*;
import tsys.sales.entity.*;
import tsys.sales.logic.*;
import javax.servlet.http.*;

public class NewAccountAction {
	public String execute(HttpServletRequest req){
		String page = "/Account/NewAccount.jsp";						//入力画面を戻り値に
		boolean caflag = false;										//メールアドレスの重複があったかのフラグ

		String memberCode = req.getParameter("membercode");	//■自動採番について確認  大文字小文字の確認
		String name = req.getParameter("name");
		String password = req.getParameter("password");
		String zipCode = req.getParameter("zipcode");
		String prefecture = req.getParameter("prefecture");
		String tel = req.getParameter("tel");
		String mail = req.getParameter("mail");


		if(																						//項目全てがnull出なく空文字文字のとき
				memberCode != null && name != null && password != null
				&& zipCode != null && prefecture != null && tel != null && mail != null
				&& (memberCode.equals("") || name.equals("") || password.equals("")
				|| zipCode.equals("") || prefecture.equals("") || tel.equals("") || mail.equals("")) ){
			req.setAttribute("messsage", "情報を入力してください");
			return page;
		}


		try{

			NewAccountLogic newAccountLogic = new NewAccountLogic();
			boolean caflag = newAccountLogic.checkAddress(mail);

			if(!caflag){									//メールアドレスの重複が無かった場合 登録情報をmemberに設定 caflag == false

				Member member = new Member();			//情報の設定
				member.setMemberCode(memberCode);
				member.setName(name);
				member.setPassword(password);
				member.setZipCode(zipCode);
				member.setPrefecture(prefecture);
				member.setTel(tel);
				member.setMail(mail);

				req.setAttribute("member", member);
				page = "/Account/AccountConfirmation.jsp";
			}

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

