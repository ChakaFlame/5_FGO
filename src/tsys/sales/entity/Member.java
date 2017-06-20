package tsys.sales.entity;

import java.io.Serializable;

public class Member implements Serializable {

	/** 顧客コード */
	private String memberCode;
	/** 顧客名 */
	private String name;
	/** パスワード */
	private String password;
	/** メールアドレス */
	private String mail;
	/** 郵便番号 */
	private String zipCode;
	/** 都道府県 */
	private String prefecture;
	/** 住所 */
	private String address;
	/** 電話番号 */
	private String tel;
	/** 権限 */
	private String role = "Customer";

	/**
	 * 引数のないコンストラクタ:何もしない。
	 */
	public Member() {
	}

	/**
	 * @param memberCode
	 * @param name
	 * @param password
	 * @param mail
	 * @param zipCode
	 * @param prefecture
	 * @param address
	 * @param tel
	 */
	public Member(String memberCode, String name, String password, String mail, String zipCode, String prefecture,
			String address, String tel) {
		super();
		this.memberCode = memberCode;
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.zipCode = zipCode;
		this.prefecture = prefecture;
		this.address = address;
		this.tel = tel;
	}

	/**
	 * @param memberCode セットする memberCode
	 */
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param mail セットする mail
	 */
	public void setMail(String mail) {
		this.mail = mail;
	}

	/**
	 * @param zipCode セットする zipCode
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @param prefecture セットする prefecture
	 */
	public void setPrefecture(String prefecture) {
		this.prefecture = prefecture;
	}

	/**
	 * @param address セットする address
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return memberCode
	 */
	public String getMemberCode() {
		return memberCode;
	}

	/**
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @return mail
	 */
	public String getMail() {
		return mail;
	}

	/**
	 * @return zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @return prefecture
	 */
	public String getPrefecture() {
		return prefecture;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @return tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @return role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role セットする role
	 */

	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @param tel セットする tel
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}


}
