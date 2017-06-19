package tsys.sales.entity;
import java.util.Date;

public class Order {
	/** 注文番号 */
	private int orderNo;
	/** 注文日時 */
	private Date orderDate;
	/** 注文合計金額 */
	private int orderTotal;
	/** 顧客コード */
	private String memberCode;
	/** 決済方法 */
	private String payment;
	/**
	 *
	 */
	public Order() {
	}
	/**
	 * @param orderNo
	 * @param orderDate
	 * @param orderTotal
	 * @param memberCode
	 * @param payment
	 */
	public Order(int orderNo, Date orderDate, int orderTotal, String memberCode, String payment) {
		super();
		this.orderNo = orderNo;
		this.orderDate = orderDate;
		this.orderTotal = orderTotal;
		this.memberCode = memberCode;
		this.payment = payment;
	}
	/**
	 * @return orderNo
	 */
	public int getOrderNo() {
		return orderNo;
	}
	/**
	 * @return orderDate
	 */
	public Date getOrderDate() {
		return orderDate;
	}
	/**
	 * @return orderTotal
	 */
	public int getOrderTotal() {
		return orderTotal;
	}
	/**
	 * @return memberCode
	 */
	public String getMemberCode() {
		return memberCode;
	}
	/**
	 * @return payment
	 */
	public String getPayment() {
		return payment;
	}
	/**
	 * @param orderNo セットする orderNo
	 */
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * @param orderDate セットする orderDate
	 */
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	/**
	 * @param orderTotal セットする orderTotal
	 */
	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}
	/**
	 * @param memberCode セットする memberCode
	 */
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	/**
	 * @param payment セットする payment
	 */
	public void setPayment(String payment) {
		this.payment = payment;
	}


}
