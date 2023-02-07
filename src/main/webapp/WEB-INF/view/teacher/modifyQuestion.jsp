<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/teacher/modifyQuestion">
		<table border="1">
			<tr>
				<td>questionTitle</td>
				<td><input type="text" name="questionTitle" value="${questionTitle}"></td>
			</tr>
			<tr>
				<td>questionIdx</td>
				<td><input type="number" name="questionIdx" value="${questionIdx}" min="1" max="10"></td>
			</tr>			
		</table>
		<input type="hidden" name="questionNo" value="${questionNo}">
		<button type="submit">수정</button>		
	</form>
</body>
</html>