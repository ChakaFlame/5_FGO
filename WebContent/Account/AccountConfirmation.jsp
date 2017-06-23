<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録確認</title>
<link rel="stylesheet" type="text/css" href="Account.css">
</head>
<body>
	<div align="center">
	<h2>メンバー登録確認</h2>
	<h3>以下の内容でよろしいですか？</h3>
	<br>
	<br>
	<table>
		<tr>
			<td class="sample1">メンバー名</td>
			<td>XXXXXX</td>
			<td><c:out value="${requestScope.member.membername}" /></td>
			<!-- <td><c:out value="${sessionScope.Hotel.hotelName}" /></td> -->
		</tr>
		<tr>
			<td class="sample1">パスワード</td>
			<td>XXXXXX</td>
		</tr>
		<tr>
			<td class="sample1">郵便番号</td>
			<td>XXX-XXXX</td>
		</tr>
		<tr>
			<td class="sample1">都道府県</td>
			<td>XXXXXX</td>
		</tr>
		<tr>
			<td class="sample1">住所</td>
			<td>XXXXXXXXXXX</td>
		</tr>
		<tr>
			<td class="sample1">電話番号</td>
			<td>XXX-XXXX-XXXX</td>
		</tr>
		<tr>
			<td class="sample1">メールアドレス</td>
			<td>XXXXXX@XXXX.XX.XX</td>
		</tr>
	</table>
	<br>
	<br>
	<input class="button" type="button"  value="登録" onclick="document.fm.BUTTON_ID.value='0901_03_01'; document.fm.submit()">
	</div>
</body>
</html>