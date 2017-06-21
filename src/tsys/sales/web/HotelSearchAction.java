package tsys.sales.web;//tsys.sales.web;

import tsys.sales.common.*;
import tsys.sales.entity.*;
import tsys.sales.logic.*;
import javax.servlet.http.*;
import java.util.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HotelSearchAction {
	public String execute(HttpServletRequest req) {
		String page = "/HotelSearch/HotelSearchView.jsp"; // 入力画面を戻り値に設定
		DateFormat format = new SimpleDateFormat("yyyy-M-d");

		String hotelDateSr = "";
		hotelDateSr = req.getParameter("year") + "-" + req.getParameter("month") + "-" + req.getParameter("date");

		System.out.println(hotelDateSr);

		if (hotelDateSr == null || hotelDateSr.equals("")) {
			req.setAttribute("message","宿泊日を選択してください");
			return page;
		}
		Date hotelDate=null;
		try {
			hotelDate = format.parse(hotelDateSr);
		} catch (ParseException e1) { // TODO 自動生成 parse使う際にtrycatch必要らしいので追加
			e1.printStackTrace();
		}

		String cityCode = req.getParameter("city");

		if (cityCode != null && cityCode.equals("")) {
			req.setAttribute("messsage", "宿泊都市を選択してください");
			return page;
		}

		try {
			HotelSearchLogic hotelSearchLogic = new HotelSearchLogic();
			ArrayList<Hotel> hotelList = hotelSearchLogic.searchHotel(cityCode, hotelDate);

			req.setAttribute("HotelList", hotelList);
			page = "/HotelSearch/HotelSearchView.jsp"; // ホテル検索画面内に結果表示させるため同画面に戻る
		} catch (SalesBusinessException e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		} catch (SalesSystemException e) {
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
			page = "/SalesErrorView.jsp";
		}
		return page;
	}
}
