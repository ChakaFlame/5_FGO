package tsys.sales.entity;

import java.io.Serializable;

public class OrderDetail implements Serializable {
	/** 注文番号 */
	private int orderNo;
	/** 商品コード */
	private String itemCode;
	/** 商品名 */
	private String name;
	/** 原価 */
	private int price;
	/** 予約数 */
	private int quantity;
	/**
	 *
	 */
	public OrderDetail() {
	}
	/**
	 * @param orderNo
	 * @param itemCode
	 * @param name
	 * @param price
	 * @param quantity
	 */
	public OrderDetail(int orderNo, String itemCode, String name, int price, int quantity) {
		this.orderNo = orderNo;
		this.itemCode = itemCode;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	/**
	 * @return totalPrice
	 */
	public int calcPrice(){
		int subTotalPrice;
		subTotalPrice=price*quantity;
		return subTotalPrice;
	}

	/**
	 * @return orderNo
	 */
	public int getOrderNo() {
		return orderNo;
	}
	/**
	 * @param orderNo セットする orderNo
	 */
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
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
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
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
	/**
	 * @return quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @param quantity セットする quantity
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


}
