package tsys.sales.web;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
//import jsys.sales.web.*;


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
			buttonID = "M_01";
		}

		// どのボタンが押されたかによって処理を振り分ける。
		switch(buttonID) {
			// メニュー画面のホテルボタンが押された場合
			case "0100_01_01":
				ToHotelSearchAction toHotelSearchAction = new ToHotelSearchAction();
				page = toHotelSearchAction.execute(req);
				//page = "/tourSystem/HotelSearch.jsp";
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
		}

		RequestDispatcher rd = req.getRequestDispatcher(page);
		rd.forward(req, res);
	}
}

