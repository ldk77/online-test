<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Add Example</h1>
	<div>${errorMsg}</div>
	<form method="post" action="${pageContext.request.contextPath}/teacher/addExample">
		<table border="1">
			<tr>
				<td>questionNo</td>
				<td><input type="number" name="questionNo" value="${questionNo}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>exampleIdx</td>
				<td><input type="number" name="exampleIdx" min="1" max="4"></td>
			</tr>
			<tr>
				<td>exampleTitle</td>
				<td><input type="text" name="exampleTitle"></td>
			</tr>
		</table>
		<button type="submit">추가</button>
	</form>
	<hr>
	<br>
	<h3>보기리스트</h3>
	<table border="1">
		<tr>			
			<th>exampleIdx</th>
			<th>exampleTitle</th>
			<th>보기수정</th>		
		</tr>
		<c:forEach var="e" items="${list}">
			<tr>
				<td>${e.exampleIdx}</td>
				<td>${e.exampleTitle}</td>
				<td>
					<a href="${pageContext.request.contextPath}/teacher/modifyExample?exampleNo=${e.exampleNo}&exampleTitle=${e.exampleTitle}&exampleIdx=${e.exampleIdx}">수정</a>
				</td>		
			</tr>
		</c:forEach>
	</table>
</body>
</html>