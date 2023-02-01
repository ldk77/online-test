<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>강사로그인</h1>
	<form method="post" action="${pageContext.request.contextPath}/teacher/loginTeacher">
		<table border="1">
			<tr>
				<td>teacherId</td>
				<td><input type="text" name="teacherId"></td>
			</tr>
			<tr>
				<td>teacherPw</td>
				<td><input type="password" name="teacherPw"></td>
			</tr>	
		</table>
		<button type="submit">로그인</button>
	</form>
</body>
</html>