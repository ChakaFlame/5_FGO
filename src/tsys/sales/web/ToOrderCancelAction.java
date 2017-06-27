package tsys.sales.web;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import tsys.sales.entity.Hotel;

public class ToOrderCancelAction {
	public String execute(HttpServletRequest req) {
		String page = "/Order/CancelConfirmation.jsp"; // 入力画面を戻り値に設定

		Calendar oneWeek = Calendar.getInstance(); // 今日の日付を取得
		oneWeek.add(Calendar.MONTH, 1);
		oneWeek.add(Calendar.DATE, 8); // 今日から７日後に設定
		boolean flag = true; // 取消可能かどうかの判断
		String hotelDateStr; // StringのhotelDate
		Calendar hotelDateCal = Calendar.getInstance(); // CalendarのhotelDate
		Date date;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Date型変換用のformat

		HttpSession session = req.getSession();
		ArrayList<Hotel> hotelList = (ArrayList<Hotel>) session.getAttribute("hotelList");

		for (Hotel hotel : hotelList) {
			hotelDateStr = hotel.getHotelDate();
			try {
				date = new java.util.Date( sdf.parse(hotelDateStr).getTime());
				hotelDateCal.setTime(date);
				hotelDateCal.add(Calendar.MONTH, 1);
			} catch (ParseException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			if(hotelDateCal.before(oneWeek)){
				flag = false;
			}
		}

		if(!flag){
			req.setAttribute("message", "取消不可能な注文です。");
			page = "/Order/OrderCancel.jsp";
			return page;
		}

		return page;
	}
}
