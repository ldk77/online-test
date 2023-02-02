<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>학생로그인</h1>
	<form method="post" action="${pageContext.request.contextPath}/loginStudent">
		<table border="1">
			<tr>
				<td>studentId</td>
				<td><input type="text" name="studentId"></td>
			</tr>
			<tr>
				<td>studentPw</td>
				<td><input type="password" name="studentPw"></td>
			</tr>	
		</table>
		<button type="submit">로그인</button>
	</form>
</body>
</html>