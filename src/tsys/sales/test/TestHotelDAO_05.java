/**
 * クラス名：
 * クラスカテゴリテスト用ドライバークラス
 *
 * テスト対象クラス：HotelDAO
 * テスト対象メソッド：findCityName(cityCode)
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

public class TestHotelDAO_05 {
	public static void main(String[] args){

		/*
		 * テストケース番号：PT002_05_001
		 * 存在する都市コードを引数に指定し、
		 * 戻り値として都市名が返される場合
		 */
		System.out.println("---------------PT002_05_001---------------");
		test01("01");

		/*
		 * テストケース番号：PT002_05_002
		 * 存在しな都市コードを引数に指定し、
		 * 戻り値としてnullが返される場合
		 */
		System.out.println("---------------PT002_05_002--------------");
		test01("00");

		/*
		 * テストケース番号：PT002_05_003
		 * データベース接続が無効な状態で、
		 * 存在するホテルコード（cityCode）を引数に指定し、
		 * NullPointerExceptionが発生する場合。
		 */
		System.out.println("---------------PT002_05_003--------------");
		test02();
	}

	public static void test01(String cityCode){
		Connection con = null;

		try{
			con = ConnectionManager.getConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}

		try{
			HotelDAO hotelDAO = new HotelDAO(con);
			String cityName = null;
			cityName = hotelDAO.findCityName(cityCode);
			if (cityName == null){
				System.out.println(cityCode + "は該当するデータがありません。");
			} else {
				System.out.println(cityName);
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

	public static void test02(){
		Connection con = null;

		try{
			HotelDAO hotelDAO = new HotelDAO(con);
			String cityName = null;
			cityName = hotelDAO.findCityName("01");
			if (cityName == null){
				System.out.println("01" + "は該当するデータがありません。");
			} else {
				System.out.println(cityName);
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
