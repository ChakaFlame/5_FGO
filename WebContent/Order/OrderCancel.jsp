<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
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
				<td class="padding"><c:out value="${sessionScope.order.index}" /></td>
				<td class="sample1">注文日</td>
				<td class="padding">2017/<c:out value="${sessionScope.order.orderDate}" /></td>
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
		<tr>

			<td><c:out value="${sessionScope.orderDetail.name}" /></td>
			<td><c:out value="${sessionScope.order}" /></td>
			<td class="number2">15,000円</td>
			<td class="number1">1</td>
			<td class="number2">15,000円</td>
		</tr>
		<tr>
			<td>FKO002</td>
			<td>アサトンタワーホテル　博多</td>
			<td>2020/08/16</td>
			<td class="number2">15,000円</td>
			<td class="number1">1</td>
			<td class="number2">15,000円</td>
		</tr>
		<tr>
			<td colspan="4" align="right">合計（２件）<td>
			<td class="sample2">30,000円</td>
		</tr>
		</table>
		<br>
		<a href="#" class="button">確認</a>
	</div>
</body>
</html>