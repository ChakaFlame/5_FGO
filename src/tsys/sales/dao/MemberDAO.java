package tsys.sales.dao;

/**
 *
 * MemberDAO.java
 *
 */


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
    public Member findMember(String memberCode, String password) throws SQLException{
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
    			member = new Member();
    			member.setMemberCode(res.getString("memberCode"));
    			member.setName(res.getString("name"));
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
    public boolean checkAddress(String address) throws SQLException{
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
    public Member insertMember(Member member) throws SQLException{
    	// 作成

    	PreparedStatement stmt = null;
    	ResultSet res = null; 				//結果セット
    	Member immember= null;
    	String sql = "INSERT INTO Member VALUES (?,?,?,?,?,?,?,?)";


    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, member.getMemberCode());	//→自動採番に要変更
    		stmt.setString(2, member.getName());
    		stmt.setString(3, member.getPassword());
    		stmt.setString(4, member.getMail());
    		stmt.setString(5, member.getZipCode());
    		stmt.setString(6, member.getPrefecture());
    		stmt.setString(6, member.getAddress());
    		stmt.setString(7, member.getTel());

    		res = stmt.executeQuery();

    		//DBに登録した情報をエンティティにも設定

    								//↓自動採番と同じ番号に要変更
    		immember = new Member(member.getMemberCode(), member.getName(), member.getPassword(),
    							member.getMail(), member.getZipCode(), member.getPrefecture(), member.getAddress(), member.getTel());


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

    	return immember;//メンバー;
    }
}