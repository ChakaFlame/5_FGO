import java.util.Date;

public class Hotel {
	/** 商品コード */
	private String itemCode;
	/** ホテルコード */
	private String hotelCode;
	/** 宿泊日 */
	private Date hotelDate;
	/** ホテル名 */
	private String hotelName;
	/** 都市コード */
	private String cityCode;
	/** 都市名 */
	private String cityName;
	/** グレード */
	private String grade;
	/** 残数 */
	private int stock;
	/** 原価 */
	private int basicPrice;
	/**
	 *
	 */
	public Hotel() {
	}
	/**
	 * @param itemCode
	 * @param hotelCode
	 * @param hotelDate
	 * @param hotelName
	 * @param cityCode
	 * @param cityName
	 * @param grade
	 * @param stock
	 * @param basicPrice
	 */
	public Hotel(String itemCode, String hotelCode, Date hotelDate, String hotelName, String cityCode, String cityName,
			String grade, int stock, int basicPrice) {
		this.itemCode = itemCode;
		this.hotelCode = hotelCode;
		this.hotelDate = hotelDate;
		this.hotelName = hotelName;
		this.cityCode = cityCode;
		this.cityName = cityName;
		this.grade = grade;
		this.stock = stock;
		this.basicPrice = basicPrice;
	}

	/**
	 * 原価から合計金額を算出(未実装)
	 */
	public int calcPrice(){
		int totalPrice=0;

		return totalPrice;
	}


	/**
	 * @return itemCode
	 */
	public String getItemCode() {
		return itemCode;
	}
	/**
	 * @param itemCode セットする itemCode
	 */
	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	/**
	 * @return hotelCode
	 */
	public String getHotelCode() {
		return hotelCode;
	}
	/**
	 * @param hotelCode セットする hotelCode
	 */
	public void setHotelCode(String hotelCode) {
		this.hotelCode = hotelCode;
	}
	/**
	 * @return hotelDate
	 */
	public Date getHotelDate() {
		return hotelDate;
	}
	/**
	 * @param hotelDate セットする hotelDate
	 */
	public void setHotelDate(Date hotelDate) {
		this.hotelDate = hotelDate;
	}
	/**
	 * @return hotelName
	 */
	public String getHotelName() {
		return hotelName;
	}
	/**
	 * @param hotelName セットする hotelName
	 */
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	/**
	 * @return cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode セットする cityCode
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	/**
	 * @return cityName
	 */
	public String getCityName() {
		return cityName;
	}
	/**
	 * @param cityName セットする cityName
	 */
	public void setCityName(String cityName) {
		this.cityName = cityName;
	}
	/**
	 * @return grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade セットする grade
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return stock
	 */
	public int getStock() {
		return stock;
	}
	/**
	 * @param stock セットする stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}
	/**
	 * @return basicPrice
	 */
	public int getBasicPrice() {
		return basicPrice;
	}
	/**
	 * @param basicPrice セットする basicPrice
	 */
	public void setBasicPrice(int basicPrice) {
		this.basicPrice = basicPrice;
	}


}
