package tsys.sales.web;//tsys.sales.web;

import tsys.sales.common.*;
import tsys.sales.entity.*;
import tsys.sales.logic.*;
import javax.servlet.http.*;
import java.util.*;


public class HotelDetailAction {
	public String execute(HttpServletRequest req){
		String page = "/HotelSearch.jsp";							//入力画面を戻り値に設定

		HttpSession session=req.getSession();

		String hotelCode = req.getParameter("itemCode");

		try{
			HotelDetailLogic hotelDetailLogic = new HotelDetailLogic();
			Hotel hotel = hotelDetailLogic.getDetail(hotelCode);

			session.setAttribute("Hotel", hotel);
//			req.setAttribute("Hotel", hotel);
			page = "/HotelSearch/HotelDetail.jsp";								//ホテル商品詳細へ
		} catch (SalesBusinessException e){
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
		} catch (SalesSystemException e){
			e.printStackTrace();
			req.setAttribute("error", e.getMessage());
			page = "/Error.jsp";
		}
			return page;
	}

}
