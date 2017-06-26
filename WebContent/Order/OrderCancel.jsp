<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文取消</title>
<link rel="stylesheet" type="text/css"
	href="/tourSystem/Order/Order.css">
</head>
<body>
	<%
		if (session.getAttribute("memberCode") == null) {
	%>
	<jsp:include page="header/header1.jsp" />
	<%
		} else {
	%>
	<jsp:include page="header/header2.jsp" />
	<%
		}
	%>
	<!-- 注文取消 -->
	<!-- フォーム -->
	<form action="/tourSystem/tsys" method="POST" name="inform">
		<!-- ボタンID用フィールド -->
		<input type="hidden" name="BUTTON_ID" value="">

		<div align="center">
			<h2>注文取消</h2>
			<table border="0">
				<tr>
					<td class="sample1">メンバーコード</td>
					<td class="padding"><c:out value="${sessionScope.memberCode}" /></td>
					<td class="sample1">メンバー名</td>
					<td class="padding"><c:out value="${sessionScope.memberName}" /></td>
				</tr>
				<tr>
					<td class="sample1">注文番号</td>
					<td class="padding"><c:out value="${requestScope.orderNo}" /></td>
					<td class="sample1">注文日</td>
					<td class="padding"><c:out value="${requestScope.orderDate}" /></td>

				</tr>
			</table>
			<br>
			<table border="0">
				<tr class="sample1">
					<td class="padding">ホテル名</td>
					<td class="padding">宿泊日</td>
					<td class="padding">宿泊料金</td>
					<td class="padding">部屋数</td>
					<td class="padding">小計</td>
				</tr>

				<%
					int orderCount = 0;
					int sum = 0;
				%>
				<c:forEach var="hotel" items="${requestScope.hotelList}">
					<td><c:out value="${hotel.hotelName}" /></td>
					<td><c:out value="${hotel.hotelDate}" /></td>
					<td class="number2"><c:out value="${hotel.price}" /></td>
					<td class="number1"><c:out value="${hotel.reservNo}" /></td>
			</c:forEach>
				<tr>

					<td colspan="4" align="right">合計（<%=orderCount%>件）
					<td>
					<td class="sample2"><c:out value="${requestScope.totalprice}" />円</td>
				</tr>

			</table>
			<br> <span
				onclick="document.inform.BUTTON_ID.value='0202_01_01';document.inform.submit()"
				class="button">確認</span>
		</div>
	</form>
	<jsp:include page="footer/footer.html" />
</body>
</html>