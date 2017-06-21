<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- pageディレクティブの使用 --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- taglibディレクティブタグで、使用するタグライブラリを宣言 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ホテル商品詳細</title>
<link rel="stylesheet" type="text/css" href="Hotel.css">

</head>
<body>
	<div align="center">
	<form action="/tourSystem/tsys" method="POST" name="fm">
	<!-- ボタンID用フィールド -->
			<input type="hidden" name="BUTTON_ID" value="">
		<h2>
			<c:out value="${requestScope.Hotel.hotelName}" />
		</h2>
		<br> <br>
		<table>
			<tr>
				<td class="sample1">ホテル名</td>
				<td><c:out value="${requestScope.Hotel.hotelName}" /></td>
			</tr>
			<tr>
				<td class="sample1">宿泊日</td>
				<td><c:out value="${requestScope.Hotel.hotelDate}" /></td>
			</tr>
			<tr>
				<td class="sample1">グレード</td>
				<td><c:out value="${requestScope.Hotel.grade}" /></td>
			</tr>
			<tr>
				<td class="sample1">料金</td>
				<td><c:out value="${requestScope.Hotel.price}" />円</td>
			</tr>
			<tr>
				<td class="sample1">空室</td>
				<td><c:out value="${requestScope.Hotel.stock}" /></td>
			</tr>
			<tr>
				<td class="sample1">予約数</td>
				<td><select name="部屋数">
						<option value="1">--</option>
						<option value="2">1</option>
						<option value="3">2</option>
						<option value="4">3</option>
						<option value="5">4</option>
						<option value="6">5</option>
				</select></td>
			</tr>
		</table>

		<br> <br>

			<span class="button" onclick="document.fm.BUTTON_ID.value='0801_01_01'; document.fm.submit()">カートへ</span>
	</form>
	</div>
</body>
</html>