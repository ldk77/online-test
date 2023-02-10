<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<meta charset="utf-8" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
	<meta name="description" content="" />
	<meta name="author" content="" />
	<title>Login</title>
	<link href="css/styles.css" rel="stylesheet" />
	<script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
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




