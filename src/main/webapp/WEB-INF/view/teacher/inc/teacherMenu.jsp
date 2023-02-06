<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>	
	<a href="${pageContext.request.contextPath}/teacher/modifyTeacherPw">강사비밀번호수정</a>
	
	<!-- 
		시험회차(table :test) 관리(CRUD)UD는 외래키에 물려있으므로 생각해서
		개별시험회차를 클릭하면 그 회차의 문제 리스스트를 출력(문제관련 CRUD)
		개별문제 클릭하면 그 문제의 보기 리스트를 출력(보기 CRUD)
	  -->
	<a href="${pageContext.request.contextPath}/test/testList">시험관리</a>
	
	<a href="${pageContext.request.contextPath}/teacher/logout">강사로그아웃</a>
</div>
