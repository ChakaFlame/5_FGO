<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文一覧</title>
<link rel="stylesheet" type="text/css" href="/tourSystem/Order/Order.css">
</head>

<body>
	<form name="inform" method="post" action="./tsys">
	<input type="hidden" name="BUTTON_ID" value="">
<% if (session.getAttribute("memberCode") == null) { %>
	<jsp:include page="header/header1.jsp" />
<% } else {%>
	<jsp:include page="header/header2.jsp" />
<% } %>
	<div align="center" style="padding-top: 1%;">
		<h2><c:out value="${sessionScope.memberName}" /> 様の<br>
		注文一覧</h2>

		<table border="0">
			<tr>

				<td class="sample1">注文番号</td>
				<td class="sample1">注文日</td>
				<td class="sample1">金額</td>
			</tr>

			<c:forEach var="order" items="${sessionScope.orderList}" varStatus="status">
				<tr>
					<td class="padding">
						<a href="./tsys?BUTTON_ID=L0802_01_01&orderNo="${requestScope.orderNoList[status.index]}">
						<c:out value="${requestScope.orderNoList[status.index]}" />
						</a>
					</td>
					<td class="padding">
						<c:out value="${order.orderDate}" />
					</td>
					<td class="number">
						<c:out value="${order.orderTotal}" /></td>
				</tr>
			</c:forEach>
		</table>
	</div>
	</form>
	<jsp:include page="footer/footer.html" />
</body>
</html>