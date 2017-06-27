<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/tourSystem/MainMenu/Menu.css">
<title>メインメニュー</title>
</head>

<!-- <IFRAME src="Header.jsp" name="header" width="100%" height="110px"></IFRAME> -->

<body class="background" topmargin="0" leftmargin ="0" rightmargin="0" marginwidth="0" marginheight="0">
<section class="header12">
<% if (session.getAttribute("memberCode") == null) { %>
	<jsp:include page="header/header1.jsp" />
<% } else {%>
	<jsp:include page="header/header2.jsp" />
<% } %>
</section>
	<!-- メニュー -->
	<!-- フォーム -->
	<form action="/tourSystem/tsys" method="POST" name="inform">
			<!-- ボタンID用フィールド -->
	<input type="hidden" name="BUTTON_ID" value="">
	<section class="wrapper">
		<div class="card" style="background-color: #fece3e;">
			<span onclick="document.inform.BUTTON_ID.value='0100_01_01';document.inform.submit()">
		  	<div class="header">
	  			<img alt="globe" src="img/hotel.png" style="padding-top:40px; width:90px">
				<h3>ホテル</h3>
	  		</div>
	  		</span>
		</div>

		<div class="card" style="background-color: #ffa517;">
	  		<div class="header">
	  			<img alt="globe" src="img/plane.png" style="padding-top:40px; width:90px">
	    		<h3>フライト</h3>
	  		</div>
		</div>
		<div class="card" style=" background-color: #f57650;">
	 		<div class="header" >
	  			<img alt="globe" src="img/globe.png" style="padding-top:40px; width:90px">
	    		<h3>ツアー</h3>
	  		</div>
		</div>
	</section>
	</form>
	<jsp:include page="footer/footer.html" />
</body>
<!--<IFRAME src="Footer.jsp" name="footer" width="100%" height="60px"></IFRAME>-->
</html>
