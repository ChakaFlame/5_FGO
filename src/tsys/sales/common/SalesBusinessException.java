package tsys.sales.common;

public class SalesBusinessException extends Exception {
	/**
	 * コンストラクタ
	 * @param message エラーメッセージ
	 */
	public SalesBusinessException(String message) {
		super(message);
	}
}
