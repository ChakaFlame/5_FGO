package tsys.sales.web;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tsys.sales.entity.Hotel;
import tsys.sales.entity.Item;

public class ShoppingCartAddAction {
	public String execute(HttpServletRequest req){
		String page = "/HotelSearch/HotelDetail.jsp";		// ホテル検索画面を戻り値に
		ArrayList<Item> cart;
		int totalPrice = 0;

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
		int reservNo = Integer.parseInt(req.getParameter("reservNo"));

		// sessionのホテル情報を削除
		session.removeAttribute("Hotel");

		if(hotel == null){
			req.setAttribute("error", "ホテルが見つかりません。");
			return page;
		}
		if(reservNo > hotel.getStock()){
			req.setAttribute("error", "予約数が空室を超えています。");
			return page;
		}


		// 追加する商品の情報をitemに格納
		Item item = new Item();
		item.setHotel(hotel);
		item.setReservNo(reservNo);
		item.setPrice(hotel.getBasicPrice());

		// カートに商品を追加する。
		cart.add(item);

		//合計値を計算
		for (Item item2 : cart) {
			totalPrice += item2.calcPrice();
		}

		// カートをsessionに格納
		session.setAttribute("cart", cart);
		session.setAttribute("totalPrice", totalPrice);
		page = "/Shoppingcart/ShoppingCart.jsp";

		return page;
	}
}
