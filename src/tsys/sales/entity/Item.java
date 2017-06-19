package tsys.sales.entity;

public class Item {
	/** ホテルインスタンス */
	private Hotel hotel;
	/** 予約数 */
	private int reservNo;
	/** 値段 */
	private int price;
	/**
	 *
	 */
	public Item() {
	}
	/**
	 * @param hotel
	 * @param reservNo
	 * @param price
	 * @return
	 */
	public Item(Hotel hotel, int reservNo, int price) {
		this.hotel = hotel;
		this.reservNo = reservNo;
		this.price = price;
	}

	public int calcPrice(){
		int totalPrice;
		totalPrice=reservNo*price;
		return totalPrice;
	}
	/**
	 * @return hotel
	 */
	public Hotel getHotel() {
		return hotel;
	}
	/**
	 * @param hotel セットする hotel
	 */
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	/**
	 * @return reservNo
	 */
	public int getReservNo() {
		return reservNo;
	}
	/**
	 * @param reservNo セットする reservNo
	 */
	public void setReservNo(int reservNo) {
		this.reservNo = reservNo;
	}
	/**
	 * @return price
	 */
	public int getPrice() {
		return price;
	}
	/**
	 * @param price セットする price
	 */
	public void setPrice(int price) {
		this.price = price;
	}




}
