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
<div class="main-container">
<% if (session.getAttribute("memberCode") == null) { %>
	<jsp:include page="header/header1.jsp" />
<% } else {%>
	<jsp:include page="header/header2.jsp" />
<% } %>
	<!-- ショッピングカート -->
	<!-- フォーム -->
	<form action="./tsys" method="POST" name="inform">
			<!-- ボタンID用フィールド -->
			<input type="hidden" name="BUTTON_ID" value="">
	<div align="center" style="padding-top: 1%;">
		<!-- 見出し -->
		<h2>ショッピングカート</h2>
	</div>
	<div align="center">
		<table border="0">
		<tr class="sample1">
			<td class="table1">ホテル名</td>
			<td class="table1">宿泊日</td>
			<td class="table1">宿泊料金</td>
			<td class="table1">部屋数</td>
			<td class="table1">小計</td>
		</tr>
		<c:forEach var="cart" items="${sessionScope.cart}">
		<tr>
			<td><c:out value="${cart.hotel.hotelName}" /></td>
			<td><c:out value="${cart.hotel.hotelDate}" /></td>
			<td class = "number"><c:out value="${cart.hotel.basicPrice}" />円</td>
			<td  align="right"><c:out value="${cart.reservNo}" /></td>
			<td><c:out value="${cart.calcPrice()}" />円</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="3" align="right">合計<td>
			<td class="total">
				<c:out value="${sessionScope.totalPrice}" />円
			</td>
		</tr>
		</table>
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
	</div>
	<jsp:include page="footer/footer.html" />
</body>

</html>