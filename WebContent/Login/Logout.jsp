<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログアウト</title>
<link rel="stylesheet" type="text/css" href="/tourSystem/Login/login.css">
</head>
<body>
	<form action="/tourSystem/tsys" method="POST" name="fm">
		<input type="hidden" name="BUTTON_ID" value="">
		<div align="center">
		<h2>ログアウト</h2>
		<h3>ログアウトしました。</h3>
		<br>
		<br>
		<br>
		<input class="button" type="button"  value="メニューへ" onclick="document.fm.BUTTON_ID.value='M_01'; document.fm.submit()">
		</div>
	</form>
</body>
</html>