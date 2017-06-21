<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>注文一覧</title>
<link rel="stylesheet" type="text/css" href="Order.css">
</head>
	<div align="center">
		<c:out value="${sessionScope.memberName}" /><h2>様の注文一覧</h2>
		<table border="0">
			<tr>
				<td class="sample1">注文番号</td>
				<td class="sample1">注文日</td>
				<td class="sample1">金額</td>
			</tr>
				<c:forEach var="order"items="requestScope.orderList">
				<tr>
					<td class="padding"><a href=""><c:out
								value="${order.orderNum}" /></a></td>
					<td class="padding"><c:out
							value="${order.orderDate}" /></td>
					<td class="number"><c:out
							value="${order.price}" /></td>
				</tr>
				</c:forEach>
		</table>
	</div>
</body>
</html>