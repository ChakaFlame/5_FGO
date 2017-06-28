/**
 * All Rights Reserved, Copyright(c) Fujitsu Learning Media Limited
 *
 * ConnectionManager.java
 *
 */
package tsys.sales.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
	/** データベース接続URL */
	private static final String URL = "jdbc:mysql://localhost:3306/tourdb";
	/** ユーザー名 */
	private static final String USER = "mysql";
	/** パスワード */
	private static final String PASSWORD = "mysql";

	/**
	 * データベースの接続を取得する。
	 * @return データベースの接続
	 */
	public static synchronized Connection getConnection() throws SQLException {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}

		return con;
	}
}
