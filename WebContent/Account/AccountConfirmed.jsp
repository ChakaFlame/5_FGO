<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>登録確定</title>
<link rel="stylesheet" type="text/css" href="/tourSystem/Account/Account.css">
</head>
<body>
	<div align="center">
	<h2>メンバー登録確定</h2>
	<h3>以下の内容で登録完了しました。</h3>
	<!-- フォーム -->
	<form action="/tourSystem/tsys" method="POST" name="fm">
		<input type="hidden" name="BUTTON_ID" value="">
		<br>
		<br>
		<table>
			<tr>
				<td class="sample1">メンバーコード</td>
				<td><c:out value="${sessionScope.member.memberCode}" /></td>
			</tr>
			<tr>
				<td class="sample1">メンバー名</td>
			<td><c:out value="${sessionScope.member.name}" /></td>
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
		<input class="button" type="button"  value="メニューへ" onclick="document.fm.BUTTON_ID.value='M_01'; document.fm.submit()">
	</form>
	</div>
	<jsp:include page="footer/footer.html" />
</body>
</html>