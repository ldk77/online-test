<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- empMenu include -->
	<div>
		<c:import url="/WEB-INF/view/employee/inc/empMenu.jsp"></c:import>
	</div>
	<h1>student List</h1>
	<a href="${pageContext.request.contextPath}/employee/addStudent">학생등록</a>
	<a href="${pageContext.request.contextPath}/student/modifyStudentPw">비밀번호수정</a>
	<table border="1">
		<tr>
			<th>studentId</th>
			<th>studentName</th>
			<th>삭제</th>
		</tr>
		<c:forEach var="s" items="${list}">
			<tr>
				<td>${s.studentId}</td>
				<td>${s.studentName}</td>
				<td><a href="${pageContext.request.contextPath}/employee/removeStudent?studentNo=${s.studentNo}">삭제</a></td>
			</tr>
		</c:forEach>
	</table>
	<form method="get" action="${pageContext.request.contextPath}/employee/studentList">
		<input type="text" name="searchWord">
		<button type="submit">이름검색</button>
	</form>
	<div>
		<a href="${pageContext.request.contextPath}/employee/studentList?currentPage=1&searchWord=${searchWord}">처음으로</a>
		<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/employee/studentList?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<a href="${pageContext.request.contextPath}/employee/studentList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
		</c:forEach>
		<c:if test="${currentPage < lastPage}">
		<a href="${pageContext.request.contextPath}/employee/studentList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a>
		</c:if>
		<a href="${pageContext.request.contextPath}/employee/studentList?currentPage=${lastPage}&searchWord=${searchWord}">끝으로</a>
	</div>	
</body>
</html>