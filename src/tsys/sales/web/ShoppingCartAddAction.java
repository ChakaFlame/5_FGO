package tsys.sales.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tsys.sales.entity.Hotel;
import tsys.sales.entity.Item;

public class ShoppingCartAddAction {
	public String execute(HttpServletRequest req){
		String page = "/HotelSearch.jsp";		// ホテル検索画面を戻り値に
		ArrayList<Item> cart;

		// セッションからカートを獲得する。
		HttpSession session = req.getSession();

		// カートがない場合はカートを新規作成し、カートがある場合はカートをセットする。
		if (session.getAttribute("cart") == null) {
			cart = new ArrayList<Item>();
		}else{
			cart = (ArrayList<Item>) session.getAttribute("cart");
		}

		// ホテルの情報、予約数を獲得する。
		Hotel hotel = (Hotel) session.getAttribute("Hotel");
//		Hotel hotel = (Hotel) req.getAttribute("Hotel");
		int reservNo = Integer.parseInt(req.getParameter("reservNo"));

		if(hotel == null){
			req.setAttribute("error", "ホテルが見つかりません。");
			return page;
		}
		if(reservNo <= 0){
			req.setAttribute("error", "予約室数が異常です。");
			return page;
		}


		// 追加する商品の情報をitemに格納
		Item item = new Item();
		item.setHotel(hotel);
		item.setReservNo(reservNo);

		// カートに商品を追加する。
		cart.add(item);

		// カートをsessionに格納
		session.setAttribute("cart", cart);
		page = "/Shoppingcart/ShoppingCart.jsp";

		return page;
	}
}
