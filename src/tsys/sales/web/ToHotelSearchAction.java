package tsys.sales.web;

import javax.servlet.http.HttpServletRequest;

import tsys.sales.common.SalesBusinessException;
import tsys.sales.common.SalesSystemException;
import tsys.sales.entity.Hotel;
import tsys.sales.logic.HotelDetailLogic;

public class ToHotelSearchAction {
	public String execute(HttpServletRequest req){
		String page = "/HotelSearch/HotelSearch.jsp";							//入力画面を戻り値に設定

			return page;
	}
}
