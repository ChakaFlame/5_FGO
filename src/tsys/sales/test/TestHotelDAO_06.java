/**
 * クラス名：
 * クラスカテゴリテスト用ドライバークラス
 *
 * テスト対象クラス：HotelDAO
 * テスト対象メソッド：findHotel(hotel)
 *
 * クラス名			：上級
 * グループ名		：5_FGO
 * 作成者			：ムン
 * 作成日			：2017/6/23
 * テスト実施日		：2017/6/23
 * テスト実施者		：ムン
 */
package tsys.sales.test;

import java.sql.Connection;
import java.sql.SQLException;

import tsys.sales.dao.ConnectionManager;
import tsys.sales.dao.HotelDAO;
import tsys.sales.entity.Hotel;

public class TestHotelDAO_06 {
	public static void main(String[] args){

		/*
		 * テストケース番号：PT002_06_001
		 * 存在するホテルインスタンスを引数に指定し、
		 * 戻り値として詳細情報が追加されたホテルインスタンスが返される場合
		 */
		Hotel hotel1 = new Hotel("HTL000001","FKO001", "2017-01-01",15);
		System.out.println("---------------PT002_06_001---------------");
		test01(hotel1);

		/*
		 * テストケース番号：PT002_06_002
		 * 存在しないホテルインスタンスを引数に指定し、
		 * 戻り値としてnullが返される場合
		 */
		Hotel hotel2 = null;
		System.out.println("---------------PT002_06_002--------------");
		test01(hotel2);

		/*
		 * テストケース番号：PT002_06_003
		 * データベース接続が無効な状態で、
		 * 存在するホテルインスタンスを引数に指定し、
		 * NullPointerExceptionが発生する場合。
		 */
		System.out.println("---------------PT002_06_003--------------");
		test02(hotel1);
	}

	public static void test01(Hotel hotel){
		Connection con = null;

		try{
			con = ConnectionManager.getConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}

		try{
			HotelDAO hotelDAO = new HotelDAO(con);
			Hotel res = null;
			res = hotelDAO.findHotel(hotel);
			if (res == null){
				System.out.println("該当するデータがありません。");
			} else {
				System.out.println(res.getCityName());
			}

		} catch (NullPointerException e) {
			System.out.println("NullPointerExceptionがスローされました。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLExceptionがスローされました。");
			e.printStackTrace();
		} finally {
			try { //データベースへの接続を切断する
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public static void test02(Hotel hotel){
		Connection con = null;

		try{
			HotelDAO hotelDAO = new HotelDAO(con);
			Hotel res = null;
			res = hotelDAO.findHotel(hotel);
			if (res == null){
				System.out.println("該当するデータがありません。");
			} else {
				System.out.println(res.getCityName());
			}
		} catch (NullPointerException e) {
			System.out.println("NullPointerExceptionがスローされました。");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("SQLExceptionがスローされました。");
			e.printStackTrace();
		} finally {
			try { //データベースへの接続を切断する
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
