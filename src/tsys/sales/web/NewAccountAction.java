
package tsys.sales.web;

import tsys.sales.common.*;
import tsys.sales.entity.*;
import tsys.sales.logic.*;
import javax.servlet.http.*;

public class NewAccountAction {
	public String execute(HttpServletRequest req){
		String page = "//tourSystem/Account/NewAccount.jsp";						//入力画面を戻り値に
		boolean caflag = false;										//メールアドレスの重複があったかのフラグ

		//String memberCode = req.getParameter("membercode");	//■自動採番について確認  大文字小文字の確認
		String name = req.getParameter("Name");
		String password = req.getParameter("Password");
		String zipCode = req.getParameter("ZipCode");
		String prefecture = req.getParameter("Prefecture");
		String address = req.getParameter("Address");
		String tel = req.getParameter("Tel");
		String mail = req.getParameter("Mail");

		if(																						//項目全てがnull出なく空文字文字のとき
				name != null && password != null
				&& zipCode != null && prefecture != null && tel != null && address != null && mail != null
				&& (name.equals("") || password.equals("")
				|| zipCode.equals("") || prefecture.equals("") || tel.equals("") || address.equals("") || mail.equals("")) ){
			req.setAttribute("message", "情報を入力してください");
			return page;
		}


		try{

			NewAccountLogic newAccountLogic = new NewAccountLogic();
			caflag = newAccountLogic.checkAddress(mail);

			if(!caflag){
				req.setAttribute("message", "既に登録済みのメールアドレスです。");
			}

			if(caflag){									//メールアドレスの重複が無かった場合 登録情報をmemberに設定 caflag == false

				Member member = new Member();			//情報の設定
				//member.setMemberCode(memberCode);
				member.setName(name);
				member.setPassword(password);
				member.setZipCode(zipCode);
				member.setPrefecture(prefecture);
				member.setTel(tel);
				member.setAddress(address);
				member.setMail(mail);

				HttpSession session = req.getSession();//request→session
				session.setAttribute("member", member);
				//req.setAttribute("member", member);
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

