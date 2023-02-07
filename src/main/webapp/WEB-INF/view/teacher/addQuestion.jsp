<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Add Question</h1>
	<div>${errorMsg}</div>
	<form method="post" action="${pageContext.request.contextPath}/teacher/addQuestion">
		<table border="1">
			<tr>
				<td>testNo</td>
				<td><input type="number" name="testNo" value="${testNo}" readonly="readonly"></td>
			</tr>
			<tr>
				<td>questionIdx</td>
				<td><input type="number" name="questionIdx" min="1" max="10"></td>
			</tr>
			<tr>
				<td>questionTitle}</td>
				<td><input type="text" name="questionTitle"></td>
			</tr>
		</table>
		<button type="submit">추가</button>
	</form>
	<hr>
	<br>
	<h3>문제리스트</h3>
	<table border="1">
		<tr>			
			<th>questionIdx</th>
			<th>questionTitle</th>
			<th>문제수정</th>		
		</tr>
		<c:forEach var="q" items="${list}">
			<tr>
				<td>${q.questionIdx}</td>
				<td>
					<a href="${pageContext.request.contextPath}/teacher/addExample?questionNo=${q.questionNo}">${q.questionTitle}</a>
				</td>
				<td>
					<a href="${pageContext.request.contextPath}/teacher/modifyQuestion?testNo=${testNo}&questionNo=${q.questionNo}&questionTitle=${q.questionTitle}&questionIdx=${q.questionIdx}">수정</a>
				</td>		
			</tr>
		</c:forEach>
	</table>
</body>
</html>