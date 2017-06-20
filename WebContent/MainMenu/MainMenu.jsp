<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/Menu.css">
<title>メインメニュー</title>
</head>

<!-- <IFRAME src="Header.jsp" name="header" width="100%" height="110px"></IFRAME> -->
<body topmargin="0" leftmargin="0" marginwidth="0" marginheight="0">
<SCRIPT src="Header.jsp"></SCRIPT>
	<!-- メニュー -->
	<!-- フォーム -->
	<form action="/tourSystem/tsys" method="POST" name="inform">
			<!-- ボタンID用フィールド -->
			<input type="hidden" name="BUTTON_ID" value="">

	<section class="wrapper">
		<span onclick="document.inform.BUTTON_ID.value='0100_01_01';document.inform.submit()">
		<div class="card" style="background-color: #547A82;">
		  	<div class="header">
	  			<img alt="globe" src="hotel.png" style="padding-top:40px; width:90px">
				<h3>ホテル</h3>
	  		</div>
		</div>
		</span>

		<div class="card" style="background-color: #3EACA8;">
	  		<div class="header">
	  			<img alt="globe" src="plane.png" style="padding-top:40px; width:90px">
	    		<h3>フライト</h3>
	  		</div>
		</div>

		<div class="card" style=" background-color: #A2D4AB;">
	 		<div class="header" >
	  			<img alt="globe" src="globe.png" style="padding-top:40px; width:90px">
	    		<h3>ツアー</h3>
	  		</div>
		</div>
	</section>
	</form>
</body>
<!--<IFRAME src="Footer.jsp" name="footer" width="100%" height="60px"></IFRAME>-->
</html>
