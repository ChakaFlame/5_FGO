package tsys.sales.dao;

/**
 *
 * MemberDAO.java
 *
 */


import java.sql.Connection;
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


    /**
     * 登録されているメンバーの検索を行う。
     * 引数としてメンバーコード、パスワードを受け取り、登録済みの場合はMember型で該当のメンバー情報を返す。
     */
    public Member findMember(String memberCode, String password) throws SQLException{
    	// 作成

    	PreparedStatement stmt = null;
    	ResultSet res = null; 				//結果セット
    	Member member = null;
    	String sql = "SELECT MemberCode,Name FROM Member WHERE MemberCode = ? AND Password = ?";


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

    /**
     * 既に登録されているメールアドレスかどうかの確認を行う。
     * メールアドレスを引数として受け取り、登録済みであればfalse、未登録であればtrueを返す。
     */
    public boolean checkAddress(String address) throws SQLException{
    	// 作成
    	PreparedStatement stmt = null;
    	ResultSet res = null; 				//結果セット
    	String sql = "SELECT Mail" + " FROM Member WHERE Mail = ?";
    	boolean caflag = true;

    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, address);
    		res = stmt.executeQuery();

    		//検索結果がある場合(すでに登録されているアドレスの場合)、フラグを折る。
    		if (res.next()) {
    			caflag = false;
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

    /**
     * メンバーの新規登録を行う。
     * Membar型の引数を受け取り、データベースに登録を行った後、再度Member型でメンバー情報を返す。
     */
    public Member insertMember(Member member) throws SQLException{
    	// 作成
    	PreparedStatement stmt = null;
    	PreparedStatement stmt2 = null;

    	ResultSet res = null; 				//結果セット

    	String sql = "INSERT INTO Member VALUES (CONCAT(\"CM\",LPAD(NEXTVAL(\"CM\"), 4, '0')),"
    			+ "?,?,?,?,?,?,?,?)";
		String sql2 = "SELECT * FROM MEMBER WHERE MAIL = ?";

    	int updated = 0;

    	try {
    		stmt = con.prepareStatement(sql);
    		stmt.setString(1, member.getName());
    		stmt.setString(2, member.getPassword());
    		stmt.setString(3, member.getRole());
    		stmt.setString(4, member.getMail());
    		stmt.setString(5, member.getZipCode());
    		stmt.setString(6, member.getPrefecture());
    		stmt.setString(7, member.getAddress());
    		stmt.setString(8, member.getTel());
    		updated = stmt.executeUpdate();

    		if (updated > 0){
        		//DBに登録した情報をエンティティにも設定
    			stmt2 = con.prepareStatement(sql2);
    			stmt2.setString(1,member.getMail());
    			res = stmt2.executeQuery();
    			if (res.next()){
    				member.setMemberCode(res.getString("MemberCode"));
    			}
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
}