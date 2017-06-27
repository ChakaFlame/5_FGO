<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文確認</title>
<link rel="stylesheet" type="text/css" href="/tourSystem/Order/Order.css">
</head>
<body>
	<!-- 注文確認 -->
	<!-- フォーム -->
	<form action="/tourSystem/tsys" method="POST" name="inform">
		<!-- ボタンID用フィールド -->
		<input type="hidden" name="BUTTON_ID" value="">
	<div align="center">
		<h2>注文確認</h2>
	<br>
	<br>
	<br>
		<h4>購入商品情報</h4>
		<table border="0">
		<tr class="sample1">
			<td class="padding">ホテル名</td>
			<td class="padding">宿泊日</td>
			<td class="padding">宿泊料金</td>
			<td class="padding">部屋数</td>
			<td class="padding">小計</td>
		</tr>
		<c:forEach var="cart" items="${sessionScope.cart}">
		<tr>
			<td><c:out value="${cart.hotel.hotelName}" /></td>
			<td><c:out value="${cart.hotel.hotelDate}" /></td>
			<td class="number"><c:out value="${cart.hotel.basicPrice}" />円</td>
			<td class="number"><c:out value="${cart.reservNo}" /></td>
			<td class="number"><c:out value="${cart.calcPrice()}" />円</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="3" align="right">合計<td>
			<td class="sample2">
				<c:out value="${sessionScope.totalPrice}" />円
			</td>
		</tr>
	</table>
	<br>
		<h4>メンバー情報</h4>
		<table border="0">
		<tr>
			<td class="sample1">メンバーコード</td>
			<td class="padding"><c:out value="${sessionScope.memberCode}" /></td>
		</tr>
		<tr>
			<td class="sample1">メンバー名</td>
			<td class="padding"><c:out value="${sessionScope.memberName}" /></td>
		</tr>
		<tr>
			<td class="sample1">送付先</td>
			<td class="padding">
				〒<c:out value="${sessionScope.zipCode}" />　　
				<c:out value="${sessionScope.prefecture}" />
				<c:out value="${sessionScope.address}" />
			</td>
		</tr>
		<tr>
			<td class="sample1">決済方法<td>
				<select name="PAYMENT">
					<option value="01">代金引換</option>
					<option value="02">コンビニ決済</option>
				</select>
		</tr>
	</table>
			<span onclick="document.inform.BUTTON_ID.value='0201_02_01';document.inform.submit()"
              class="button">注文確定</span>
	</div>
	</form>
</body>
</html>