<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>시험추가</h1>
	<div>${errorMsg}</div>
	<form method="post" action="${pageContext.request.contextPath}/teacher/addTest">
		<table border="1">
			<tr>
				<td>testTitle</td>
				<td><input type="text" name="testTitle"></td>
			</tr>
			<tr>
				<td>testDate</td>
				<td><input type="date" name="testDate"></td>
			</tr>
		</table>
		<button type="submit">시험추가</button>
	</form>
</body>
</html>