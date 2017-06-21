<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ショッピングカート</title>
	<link rel="stylesheet" type="text/css" href="/tourSystem/Shoppingcart/cart.css">
</head>
<body>
	<!-- ショッピングカート -->
	<!-- フォーム -->
	<form action="/tourSystem/tsys" method="POST" name="inform">
			<!-- ボタンID用フィールド -->
			<input type="hidden" name="BUTTON_ID" value="">
	<div align="center">
		<!-- 見出し -->
		<h2>ショッピングカート</h2>
	</div>
	<br>
	<br>
	<br>
	<div align="center">
		<table border="0">
		<tr class="sample1">
			<td class="padding">ホテルコード</td>
			<td class="padding">ホテル名</td>
			<td class="padding">宿泊日</td>
			<td class="padding">宿泊料金</td>
			<td class="padding">部屋数</td>
			<td class="padding">小計</td>
		</tr>
		<c:forEach var="hotel" items="${sessionScope.cart}">
		<tr>
			<td><c:out value="${hotel.hotelcode}" /></td>
			<td><c:out value="${hotel.hotelName}" /></td>
			<td><c:out value="${hotel.hotelDate}" /></td>
			<td><c:out value="${hotel.price}" />円</td>
			<td  align="right"><c:out value="${hotel.reservNo}" /></td>
			<td><c:out value="${hotel.calcPrice()}" />円</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="4" align="right">合計<td>
			<td class="sample2">
				<c:forEach var="hotel" items="${sessionScope.cart}">
				${totalPrice += hotel.price}$
				</c:forEach>
				<c:out value="${totalPrice}" />円
			</td>
		</tr>
		</table>
		<br>
		<br>
		<br>
		<br>
		<table>
		<tr>
			<td onclick="document.inform.BUTTON_ID.value='0100_01_01';document.inform.submit()" class="button">買物を続ける</td>
			<td onclick="document.inform.BUTTON_ID.value='0201_01_02';document.inform.submit()" class="button">購入</td>
		</tr>
		</table>
	</div>

	<!-- エラーメッセージ -->
	<div align ="center" style="color: red;">
		<c:out value="${requestScope.message}" />
	</div>
	</form>
</body>
</html>