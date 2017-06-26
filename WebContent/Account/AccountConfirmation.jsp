<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録確認</title>
<link rel="stylesheet" type="text/css" href="/tourSystem/Account/Account.css">
</head>
<body>
	<!-- フォーム -->
	<form action="/tourSystem/tsys" method="POST" name="fm">
		<!-- ボタンID用フィールド -->
		<input type="hidden" name="BUTTON_ID" value="">
		<div align="center">
		<h2>メンバー登録確認</h2>
		<h3>以下の内容でよろしいですか？</h3>
		<br>
		<br>
		<table>
			<tr>
				<td class="sample1">メンバー名</td>
				<td><c:out value="${sessionScope.member.name}" /></td>
				<!-- <td><c:out value="${sessionScope.Hotel.hotelName}" /></td> -->
			</tr>
			<tr>
				<td class="sample1">パスワード</td>
				<td><c:out value="${sessionScope.member.password}" /></td>
			</tr>
			<tr>
				<td class="sample1">郵便番号</td>
				<td><c:out value="${sessionScope.member.zipCode}" /></td>
			</tr>
			<tr>
				<td class="sample1">都道府県</td>
				<td><c:out value="${sessionScope.member.prefecture}" /></td>
			</tr>
			<tr>
				<td class="sample1">住所</td>
				<td><c:out value="${sessionScope.member.address}" /></td>
			</tr>
			<tr>
				<td class="sample1">電話番号</td>
				<td><c:out value="${sessionScope.member.tel}" /></td>
			</tr>
			<tr>
				<td class="sample1">メールアドレス</td>
				<td><c:out value="${sessionScope.member.mail}" /></td>
			</tr>
		</table>
		<br>
		<br>
		<input class="button" type="button"  value="登録" onclick="document.fm.BUTTON_ID.value='0901_03_01'; document.fm.submit()">
		</div>
	</form>
	<jsp:include page="footer/footer.html" />
</body>
</html>