<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<form method="post" action="${pageContext.request.contextPath}/teacher/modifyTest">
		<table border="1">
			<tr>
				<td>testTitle</td>
				<td><input type="text" name="testTitle" value="${testTitle}"></td>
			</tr>
			<tr>
				<td>testDate</td>
				<td><input type="date" name="testDate" value="${testDate}"></td>
			</tr>			
		</table>
		<input type="hidden" name="testNo" value="${testNo}">
		<button type="submit">수정</button>		
	</form>
</body>
</html>