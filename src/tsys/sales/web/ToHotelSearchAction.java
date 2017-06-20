package tsys.sales.web;

import javax.servlet.http.HttpServletRequest;

import tsys.sales.common.SalesBusinessException;
import tsys.sales.common.SalesSystemException;
import tsys.sales.entity.Hotel;
import tsys.sales.logic.HotelDetailLogic;

public class ToHotelSearchAction {
	public String execute(HttpServletRequest req){
		String page = "/HotelSearch.jsp";							//入力画面を戻り値に設定

		String hotelCode = req.getParameter("hotelCode");

		try{
			HotelDetailLogic hotelDetailLogic = new HotelDetailLogic();
			Hotel hotel = hotelDetailLogic.getDetail(hotelCode);

			req.setAttribute("Hotel", hotel);
			page = "/HotelSearch.jsp";								//ホテル商品
		} catch (SalesBusinessException e){
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		} catch (SalesSystemException e){
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
			page = "/SalesErrorView.jsp";
		}
			return page;
	}
}
