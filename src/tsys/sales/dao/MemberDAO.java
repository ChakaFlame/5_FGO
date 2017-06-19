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

import jsys.sales.entity.Customer;
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
//     * 接続のクローズ
//     */
//    public void close() throws SQLException {
//
//        if (con != null) {
//            con.close();
//        }
//
//    }

    /*
     * メンバーの検索
     */
    public Member findMember(String memberCode, String password){
    	// 作成

    	PreparedStatement stmt = null;
    	ResultSet res = null; 				//結果セット
    	Member member = null;
    	String sql = "SELECT MemberCode,Password" + " FROM Member WHERE MemberCode = ? AND Password = ?";


    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, memberCode);
    		stmt.setString(2, password);
    		res = stmt.executeQuery();

    		//検索結果がある場合、戻り値に設定する。
    		if (res.next()) {
    			member = new Member(
    					res.getString("memberCode"),
    					res.getString("password"));
    		}

    	}catch(SQLException e){
    		e.printStackTrace();
    		throw e;

    	} finally {

    		if (res != null) {
    			res.close();
    		}

    		if (stmt != null) {
    			stmt.close();
    		}
    	}

    return member;//メンバー;

    }

    /*
     * アドレスの重複をチェック
     */
    public boolean checkAddress(String address){
    	// 作成

    	PreparedStatement stmt = null;
    	ResultSet res = null; 				//結果セット
    	Member member = null;
    	String sql = "SELECT Address" + " FROM Member WHERE Address = ?";
    	boolean caflag = true;

    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, address);
    		res = stmt.executeQuery();

    		//検索結果がある場合(すでに登録されているアドレスの場合)、フラグを折る。
    		if (res.next()) {
    			caflag = false;
//    			member = new Member(
//    					res.getString("Address"));
    		}

    	}catch(SQLException e){
    		e.printStackTrace();
    		throw e;

    	} finally {

    		if (res != null) {
    			res.close();
    		}

    		if (stmt != null) {
    			stmt.close();
    		}
    	}

    	return caflag;//ture false;
    }

    /*
     *  メンバーをDBに追加
     */
    public Member insertMember(Member member){
    	// 作成

    	PreparedStatement stmt = null;
    	ResultSet res = null; 				//結果セット
    	Member member = null;
    	String sql = "INSERT INTO Member VALUES (?,?,?,?,?,?,?)";


    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, member.memberCode);
    		stmt.setString(2, member.name);
    		stmt.setString(3, member.password);
    		stmt.setString(4, member.mail);
    		stmt.setString(5, member.zipcode);
    		stmt.setString(6, member.prefecture);
    		stmt.setString(7, member.rel);

    		res = stmt.executeQuery();

    		//DBに登録した情報をエンティティにも設定

    		member = new Member(member.memberCode, member.name, member.password,
    							member.mail, member.zipcode, member.prefecture, member.rel);


    	}catch(SQLException e){
    		e.printStackTrace();
    		throw e;

    	} finally {

    		if (res != null) {
    			res.close();
    		}

    		if (stmt != null) {
    			stmt.close();
    		}
    	}

    	return member;//メンバー;
    }
}