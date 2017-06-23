<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文取消確認</title>
<link rel="stylesheet" type="text/css" href="/tourSystem/Order/Order.css">
</head>
<body>
	<!-- 注文確認 -->
	<!-- フォーム -->
	<form action="/tourSystem/tsys" method="POST" name="inform">
		<!-- ボタンID用フィールド -->
		<input type="hidden" name="BUTTON_ID" value="">
	<div align="center">
		<h2>注文取消確認</h2>
	</div>
	<br>
	<br>
	<br>
	<div align="left">
		<h4>注文商品情報</h4>
	</div>
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
		<%
			int reservCount = 0;
			int sum = 0;
		%>
		<c:forEach var="order" items="${reqestScope}">
			<tr>
				<td><td class="padding"><td><c:out value="${order.hotelCode}" /></td>
				<td><c:out value="${order.hotelName}"/></td>
				<td><c:out value="${order.hotelDate}"/></td>
				<td class="number2"><c:out value="${order.price}"/></td>
				<td class="number1"><c:out value="${order.reservNo}"/></td>
				<td class="number2"><c:out value="${order.calcPrice()}" /></td>
				<%
					reservCount += (int)session.getAttribute("order.reservNo");
					sum += (int)session.getAttribute("order.calcPrice()");
				%>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="4" align="right">合計（<%= reservCount %>件）<td>
			<td class="sample2"><%= sum %>円</td>
		</tr>
		</table>

	</div>
	<div align="center">
			<span onclick="document.inform.BUTTON_ID.value='0202_03_01';document.inform.submit()"
              class="button">注文取消</span>
	</div>
	</form>
</body>
</html>