<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" type="text/css" href="/tourSystem/Account/Account.css">
<script type="text/javascript">
	// 検索ボタンが押された場合、未入力チェックを行う。
	function PushSearchButton() {
		var memberName = document.fm.membername.value;
		var password   = document.fm.password.value;
		var zipCode    = document.fm.zipcode.value;
		var prefecture = document.fm.prefecture.value;
		var address    = document.fm.address.value;
		var tel        = document.fm.tel.value;
		var mail       = document.fm.mail.value;

		if (memberName == "" || password == "" || zipCode == "" || prefecture == "" || address == "" || tel == "" || mail == "")  {
			alert("情報を入力してください。");
			return false;
		}
		return true;
	}
</script>
</head>
<body>
	<div align="center">
	<h2>メンバー登録</h2>
	<br>
	<br>
	<!-- フォーム -->
	<form action="/tourSystem/tsys" method="POST" name="fm">
		<input type="hidden" name="BUTTON_ID" value="">
		<table>
			<tr>
				<td class="sample1">メンバー名</td>
				<td><input type="text" name="Name" value = "${param.Name}" maxLength="40"></td>
			</tr>
			<tr>
				<td class="sample1">パスワード</td>
				<td><input type="password" name="Password" value = "${param.Password}" maxLength="15"></td>
			</tr>
			<tr>
				<td class="sample1">郵便番号</td>
				<td><input type="text" name="ZipCode" value = "${param.ZipCode}" maxLength="8"></td>
			</tr>
			<tr>
				<td class="sample1">都道府県</td>
				<td>
					<select id="prefecture" name="Prefecture">
						<option value="北海道">北海道</option>
						<option value="青森">青森</option>
						<option value="秋田">秋田</option>
						<option value="岩手">岩手</option>
						<option value="宮城">宮城</option>
						<option value="山形">山形</option>
						<option value="福島">福島</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="sample1">住所</td>
				<td><input type="text" name="Address" value = "${param.Address}" maxLength="100"></td>
			</tr>
			<tr>
				<td class="sample1">電話番号</td>
				<td><input type="text" name="Tel" value = "${param.Tel}" maxLength="13"></td>
			</tr>
			<tr>
				<td class="sample1">メールアドレス</td>
				<td><input type="text" name="Mail" value = "${param.Mail}" maxLength="100"></td>
			</tr>
		</table>
		<br>
		<br>
		<input class="button" type="button"  value="確認" onclick="document.fm.BUTTON_ID.value='0901_01_01'; document.fm.submit()">
	</form>
	</div>
	<div align="center" style="color:red; font-weight:bold;">
    	<%-- エラーメッセージがある場合、出力 --%>
    	<c:out value="${requestScope.message}" />
	</div>
</body>
</html>