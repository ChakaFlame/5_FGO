package tsys.sales.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;



@WebServlet("/tsys")
public class FrontController extends HttpServlet {
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res)
											throws ServletException, IOException {
		doPost(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request,
	 * 										HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res)
											throws ServletException, IOException {
		String page = null;

		// 画面のボタンIDを取得する。
		req.setCharacterEncoding("UTF-8");
		String buttonID = req.getParameter("BUTTON_ID");

		// ボタンIDがnullの場合、デフォルト値を設定する。[メニュー画面]
		if (buttonID == null) {
			buttonID = "M_03";
		}

		// どのボタンが押されたかによって処理を振り分ける。			<! 正常系のみ記述済み !>
		switch(buttonID) {

			//メインメニューへ戻る
			case "M_01":
				MenuAction menuAction = new MenuAction();
				page = menuAction.execute(req);
				break;
			//ヘッダ ログイン
			case "M_03":
				ToLoginAction toLoginAction = new ToLoginAction();
				page = toLoginAction.execute(req);
				break;
			//ヘッダ メンバー名(商品一覧)
			case "M_04":
				OrderListAction orderListAction = new OrderListAction();
				page = orderListAction.execute(req);
				break;
			//ヘッダ カート
			case "M_05":
				ToShoppingcartAction toShoppingcartAction = new ToShoppingcartAction();
				page = toShoppingcartAction.execute(req);
				break;
			//ヘッダ ログアウト
			case "M_06":
				ToLogoutAction toLogoutAction = new ToLogoutAction();
				page = toLogoutAction.execute(req);
				break;

			// メニュー画面のホテルボタンが押された場合
			case "0100_01_01":
			case "0201_01_01"://ユースケース図100,200間でボタンIDに齟齬有り
				ToHotelSearchAction toHotelSearchAction = new ToHotelSearchAction();
				page = toHotelSearchAction.execute(req);	//page = "/tourSystem/HotelSearch.jsp";
				break;
			// ホテル検索画面の検索ボタンが押された場合
			case "0802_01_01":
				HotelSearchAction hotelSearchAction = new HotelSearchAction();
				page = hotelSearchAction.execute(req);
				break;
			//ホテル検索画面の商品コードが押された場合
			case "L0802_01_01":
				HotelDetailAction hotelDetailAction = new HotelDetailAction();
				page = hotelDetailAction.execute(req);
				break;

			//ログイン画面 ログインボタン
			case "0101_01_01":
				LoginAction loginAction = new LoginAction();
				page = loginAction.execute(req);
				break;
			//ホテル商品詳細 カートへボタン
			case "0802_02_01"://ユースケース図800,200間で齟齬有り
			case "0801_01_01":
			case "0801_01":
				ShoppingCartAddAction shoppingCartAddAction = new ShoppingCartAddAction();
				page = shoppingCartAddAction.execute(req);
				break;
			//ショッピングカート画面 購入ボタン
			case "0201_01_02":
				ShoppingCartBuyAction shoppingCartBuyAction = new ShoppingCartBuyAction();
				page = shoppingCartBuyAction.execute(req);
				break;
		}

		RequestDispatcher rd = req.getRequestDispatcher(page);
		rd.forward(req, res);
	}
}

