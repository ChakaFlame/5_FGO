<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<head>
<meta charset="UTF-8">
<title>注文取消確定</title>
<link rel="stylesheet" type="text/css" href="Order.css">
</head>
<body>
	<div align="center">
		<h2>注文取消</h2>
		<h3>以下の注文を取消しました。</h3>

		<c:forEach var="s" items="${sessionScope}">
		<table border="0">
			<tr>
				<td class="sample1">メンバーコード</td>
				<td class="padding"><c:out value="${s.memberCode}"/></td>
				<td class="sample1">メンバー名</td>
				<td class="padding"><c:out value="${s.memberName}"/></td>
			</tr>
			<tr>
				<td class="sample1">注文番号</td>
				<td class="padding"><c:out value="${s.orderNo}"/></td>
				<td class="sample1">注文日</td>
				<td class="padding"><c:out value="${s.orderDate}"/></td>
			</tr>
		</table>
		</c:forEach>
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
			<td>FKO001</td>
			<td>福岡プリンセスホテル</td>
			<td>2020/08/15</td>
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
		<br>
		<a href="#" class="button">メニューへ</a>
	</div>
</body>
</html>