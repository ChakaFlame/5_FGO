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
<link rel="stylesheet" type="text/css" href="/tourSystem/HotelSearch/Hotel.css">

</head>
<body>
<% if (session.getAttribute("memberCode") == null) { %>
	<jsp:include page="header/header1.jsp" />
<% } else {%>
	<jsp:include page="header/header2.jsp" />
<% } %>
	<div align="center" style="padding-top: 1%;">
		<h2>ホテル商品詳細</h2>
	</div>
	<div align="center">
	<form action="/tourSystem/tsys" method="POST" name="inform">
	<!-- ボタンID用フィールド -->
			<input type="hidden" name="BUTTON_ID" value="">
		<h2>
			<c:out value="${requestScope.Hotel.hotelName}" />
		</h2>
		<br> <br>
		<table>
			<tr>
				<td class="sample1">ホテル名</td>
				<!-- <td><c:out value="${requestScope.Hotel.hotelName}" /></td> -->
				<td><c:out value="${sessionScope.Hotel.hotelName}" /></td>
			</tr>
			<tr>
				<td class="sample1">宿泊日</td>
				<!-- <td><c:out value="${requestScope.Hotel.hotelDate}" /></td> -->
				<td><c:out value="${sessionScope.Hotel.hotelDate}" /></td>
			</tr>
			<tr>
				<td class="sample1">グレード</td>
				<!-- <td><c:out value="${requestScope.Hotel.grade}" /></td> -->
				<td><c:out value="${sessionScope.Hotel.grade}" /></td>
			</tr>
			<tr>
				<td class="sample1">料金</td>
				<!-- <td><c:out value="${requestScope.Hotel.basicPrice}" />円</td> -->
				<td><c:out value="${sessionScope.Hotel.basicPrice}" />円</td>
			</tr>
			<tr>
				<td class="sample1">空室</td>
				<!-- <td><c:out value="${requestScope.Hotel.stock}" /></td> -->
				<td><c:out value="${sessionScope.Hotel.stock}" /></td>
			</tr>
			<tr>
				<td class="sample1">予約数</td>
				<td><select name="reservNo">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
						<option value="11">11</option>
						<option value="12">12</option>
						<option value="13">13</option>
						<option value="14">14</option>
						<option value="15">15</option>
				</select></td>
			</tr>
		</table>
		<br> <br>
			<span class="button" onclick="document.inform.BUTTON_ID.value='0801_01_01'; document.inform.submit()">カートへ</span>
	</form>
	</div>
	<jsp:include page="footer/footer.html" />
</body>
</html>