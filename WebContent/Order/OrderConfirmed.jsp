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
<% if (session.getAttribute("memberCode") == null) { %>
	<jsp:include page="header/header1.jsp" />
<% } else {%>
	<jsp:include page="header/header2.jsp" />
<% } %>
	<!-- 注文確定 -->
	<!-- フォーム -->
	<form action="/tourSystem/tsys" method="POST" name="inform">
		<!-- ボタンID用フィールド -->
		<input type="hidden" name="BUTTON_ID" value="">
	<div align="center">
		<h2>注文確定</h2>
		<h3>ありがとうございました！</h3>
	<br>
	<table>
		<tr>
			<td><h4>注文番号：</h4></td>
			<td><c:out value="${requestScope.orderNo}" /></td>
		</tr>
	</table>
		<h4>購入商品情報</h4>
		<table border="0">
		<tr class="sample1">
			<td class="padding">ホテル名</td>
			<td class="padding">宿泊日</td>
			<td class="padding">宿泊料金</td>
			<td class="padding">部屋数</td>
			<td class="padding">小計</td>
		</tr>
		<c:forEach var="cart" items="${requestScope.cart}">
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
				<c:out value="${requestScope.totalPrice}" />円
			</td>
		</tr>
		</table>
	</div>
	<br>
	<div align="center">
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
				〒<c:out value="${requestScope.zipCode}" />　　
				<c:out value="${requestScope.prefecture}" />
				<c:out value="${requestScope.address}" />
			</td>
		</tr>
		<tr>
			<td class="sample1">決済方法<td>
			<td class="padding"><c:out value="${requestScope.payment}" /></td>
		</tr>
	</table>
	</div>
	<div align="center">
			<span onclick="document.inform.BUTTON_ID.value='M_01';document.inform.submit()"
              class="button">メニューへ</span>
	</div>
	<br><br>
	</form>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="footer/footer.html" />
</body>
</html>