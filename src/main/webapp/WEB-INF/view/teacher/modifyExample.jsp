<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/teacher/modifyExample">
		<table border="1">
			<tr>
				<td>exampleTitle</td>
				<td><input type="text" name="exampleTitle" value="${exampleTitle}"></td>
			</tr>
			<tr>
				<td>exampleIdx</td>
				<td><input type="number" name="exampleIdx" value="${exampleIdx}" min="1" max="4"></td>
			</tr>			
		</table>
		<input type="hidden" name="exampleNo" value="${exampleNo}">
		<button type="submit">수정</button>		
	</form>
</body>
</html>