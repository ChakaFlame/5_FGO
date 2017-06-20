<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
<link rel="stylesheet" type="text/css" href="login.css">
<script type="text/javascript">
	// 検索ボタンが押された場合、未入力チェックを行う。
	function PushSearchButton() {
		var memberCode = document.inform.MemberCode.value;
		if (memberCode == "") {
			alert("メンバーコードを入力してください。");
			return false;
		}

		document.inform.BUTTON_ID.value='0101_01_01';
		document.inform.submit();
	}
</script>
</head>
<body>
	<div align="center">
		<h2>ログイン</h2>
		<br>
		<br>
		<!-- フォーム -->
		<form action="/5_FGO/tsys" method="POST" name="inform">
			<table border="0">
				<tr>
					<td class="sample1">メンバーコード</td>
					<td><input type="text" name="membercode" value = "${param.MemberCode}" maxLength="6"></td>
				</tr>
				<tr>
					<td class="sample1">パスワード</td>
					<td><input type="password" name="password" value = "${param.Password}" maxLength=15"></td>
				</tr>
			</table>
			<br>
			<a href="#" class="button" onclick="document.fm.BUTTON_ID.value='0101_01_01'; document.fm.submit()">ログイン</a>
			<br>
			<br>
			<a href="#" class="button" onclick="document.fm.BUTTON_ID.value='L0101_01_01'; document.fm.submit()">アカウントをお持ちでない方はコチラ</a>
		</form>
	</div>
</body>
</html>