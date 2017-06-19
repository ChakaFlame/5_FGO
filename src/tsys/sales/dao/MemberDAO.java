/**
 *
 * MemberDAO.java
 *
 */
package tsys.sales.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tsys.sales.entity.Member;

public class MemberDAO {

    private Connection con;  //接続オブジェクト

    /*
     * コンストラクタ
     */
    public MemberDAO(Connection con) {
    	this.con = con;
    }

    /*
     * 接続のクローズ
     */
    public void close() throws SQLException {

        if (con != null) {
            con.close();
        }

    }

    /*
     * メンバーの検索
     */
    public Member findMember(String memberCode){
    	// 作成
    	return //メンバー;
    }

    /*
     * アドレスの重複をチェック
     */
    public boolean checkAddress(String address){
    	// 作成
    	return //ture false;
    }

    /*
     *  メンバーをDBに追加
     */
    public Member insertMember(Member member){
    	// 作成
    	return //メンバー;
    }
}