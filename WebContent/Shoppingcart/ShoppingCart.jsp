<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>ショッピングカート</title>
	<link rel="stylesheet" type="text/css" href="cart.css">
</head>
<body>
	<!-- ショッピングカート -->
	<!-- フォーム -->
	<form action="/tsys" method="POST" name="inform">
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
		<c:forEach items="${sessionScope.cart}">
		<tr>
			<td><c:out value="${sessionScope.cart.hotel.hotelcode}" /></td>
			<td><c:out value="${sessionScope.cart.hotel.hotelName}" /></td>
			<td><c:out value="${sessionScope.cart.hotel.hotelDate}" /></td>
			<td><c:out value="${sessionScope.cart.hotel.price}" />円</td>
			<td  align="right"><c:out value="${sessionScope.cart.reservNo}" /></td>
			<td><c:out value="${sessionScope.cart.calcPrice()}" />円</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="4" align="right">合計<td>
			<td class="sample2"><c:out value="${requestScope.totalPrice}" /></td>
		</tr>
		</table>
		<br>
		<br>
		<br>
		<br>
		<table>
		<tr>
			<td onclick="document.inform.BUTTON_ID.value='0201_01_01';document.inform.submit()" class="button">買物を続ける</td>
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