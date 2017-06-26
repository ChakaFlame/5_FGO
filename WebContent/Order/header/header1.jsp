<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>

</style>

<title>Header</title>
</head>
<body>
	<div style="webkit-box-shadow: 0px 3px 5px rgba(100, 100, 100, 0.49);
 		-moz-box-shadow:    0px 3px 5px rgba(100, 100, 100, 0.49);
	 	box-shadow:         0px 3px 5px rgba(100, 100, 100, 0.49);">
		<table style="width: 100%;  padding:2%; color:#5A5050;">
		<tr>
			<td class="element" style="width: 25%; font-family: 'Verdana'; font-size:30px;">
				<a href="MainMenu/MainMenu.jsp" style="text-decoration:none; color:#5A5050;">FLM tours</a>
			</td>
			<td class="element" style="width: 50%; ">  </td>
			<td class="element" style="width: 10%; font-size:20px;">新規登録</td>
			<td class="element" style="width: 10%; font-size:20px;">
			<input type="button" class="button1"  name="login" value="ログイン" onClick="document.inform.BUTTON_ID.value='M_03';document.inform.submit();">
			</td>

		</tr>
		</table>
		</div>
</body>
</html>