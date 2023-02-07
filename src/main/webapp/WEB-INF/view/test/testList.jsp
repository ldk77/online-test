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
	<!-- Menu include -->
	<div>
		<c:import url="/WEB-INF/view/test/inc/testMenu.jsp"></c:import>
	</div>
	<h1>test List</h1>
	<a href="${pageContext.request.contextPath}/teacher/addTest">시험등록</a>
		<form method="get" action="${pageContext.request.contextPath}/test/testList">
		<input type="text" name="searchWord">
		<button type="submit">시험검색</button>
	</form>
	<table border="1">
		<tr>			
			<th>testTitle</th>
			<th>testDate</th>
			<th>수정/응시</th>	
			<th>문제등록</th>		
		</tr>
		<c:forEach var="t" items="${list}">
			<tr>
				<td><a href="${pageContext.request.contextPath}/test/testOne?testNo=${t.testNo}">${t.testTitle}</a></td>
				<td>${t.testDate}</td>
				<td><a href="${pageContext.request.contextPath}/teacher/modifyTest?testNo=${t.testNo}&testTitle=${t.testTitle}&testDate=${t.testDate}">수정</a>
					/ <a href="${pageContext.request.contextPath}/">응시</a>
				</td>
				<td><a href="${pageContext.request.contextPath}/teacher/addQuestion?testNo=${t.testNo}">등록</a></td>		
			</tr>
		</c:forEach>
	</table>
	<div>
		<a href="${pageContext.request.contextPath}/test/testList?currentPage=1&searchWord=${searchWord}">처음으로</a>
		<c:if test="${currentPage > 1}">
		<a href="${pageContext.request.contextPath}/test/testList?currentPage=${currentPage-1}&searchWord=${searchWord}">이전</a>
		</c:if>
		<c:forEach var="i" begin="${startPage}" end="${endPage}" step="1">
		<a href="${pageContext.request.contextPath}/test/testList?currentPage=${i}&searchWord=${searchWord}">${i}</a>
		</c:forEach>
		<c:if test="${currentPage < lastPage}">
		<a href="${pageContext.request.contextPath}/test/testList?currentPage=${currentPage+1}&searchWord=${searchWord}">다음</a>
		</c:if>
		<a href="${pageContext.request.contextPath}/test/testList?currentPage=${lastPage}&searchWord=${searchWord}">끝으로</a>
	</div>	
</body>
</html>