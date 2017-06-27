<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文取消確定</title>
<link rel="stylesheet" type="text/css" href="Order.css">

</head>
<body>
<% if (session.getAttribute("memberCode") == null) { %>
	<jsp:include page="header/header1.jsp" />
<% } else {%>
	<jsp:include page="header/header2.jsp" />
<% } %>
<!-- 注文取消確定 -->
	<!-- フォーム -->
	<form action="/tourSystem/tsys" method="POST" name="inform">
	<!-- ボタンID用フィールド -->
	<input type="hidden" name="BUTTON_ID" value="">

	<div align="center">
		<h2>注文取消</h2>
		<h3>以下の注文を取消しました。</h3>


		<table border="0">
			<tr>
				<td class="sample1">メンバーコード</td>
				<td class="padding"><c:out value="${sessionScope.memberCode}"/></td>
				<td class="sample1">メンバー名</td>
				<td class="padding"><c:out value="${sessionScope.memberName}"/></d>
			</tr>
			<tr>
				<td class="sample1">注文番号</td>
				<td class="padding"><c:out value="${requestScope.order.orderNo}"/></td>
				<td class="sample1">注文日</td>
				<td class="padding"><c:out value="${requestScope.order.orderDate}"/></td>
			</tr>
		</table>
		<br>
		<table border="0">
				<tr class="sample1">
				<td class="padding">ホテルコード</td>
				<td class="padding">ホテル名</td>
				<td class="padding">宿泊日</td>
				<td class="padding">宿泊料金</td>
				<td class="padding">部屋数</td>
				<td class="padding">小計</td>
			</tr>
			<c:forEach var="hotel" items="${requestScope.hotelList}">
					<tr>
						<td><c:out value="${hotel.hotelName}" /></td>
						<td><c:out value="${hotel.hotelDate}" /></td>
						<td class="number"><c:out value="${hotel.price}" />円</td>
						<td class="number"><c:out value="${hotel.reservNo}" /></td>
						<td class="number"><c:out value="${hotel.price*hotel.reservNo}"/>円</td>
					</tr>
				</c:forEach>
			<tr>
				<td colspan="4" align="right">合計（<c:out
							value="${requestScope.count}" />件）<td>
				<td class="sample2"><c:out value="${requestScope.totalprice}" />円</td>
			</tr>
			</table>
		<br>
		<br>
			<span onclick="document.inform.BUTTON_ID.value='M_01';document.inform.submit()"
              class="button">メニューへ</span>
	</div>
	</form>
	<jsp:include page="footer/footer.html" />
</body>
</html>