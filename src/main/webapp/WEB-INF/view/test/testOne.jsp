<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>


</head>
<body>
	<h1>${thisTest.testNo}회차 ${thisTest.testTitle}</h1>
	<p>시험일자 : ${thisTest.testDate}</p>
	<hr>
	<div>
		<c:forEach var="q" items="${list}">
			<div>
				<!-- 문제 -->
				<c:if test="${q.exampleIdx == 1}">
					<div style="font-weight:bold;">
						${q.questionIdx}. ${q.questionTitle}
					</div>	
				</c:if>
				<br>
				<!-- 선택지 -->
				<c:if test="${q.exampleIdx == 1}">
					<div>
						&nbsp;1. ${q.exampleTitle}
					</div>
				</c:if>
				<c:if test="${q.exampleIdx == 2}">
					<div>
						&nbsp;2. ${q.exampleTitle}
					</div>
				</c:if>
				<c:if test="${q.exampleIdx == 3}">
					<div>
						&nbsp;3. ${q.exampleTitle}
					</div>
				</c:if>
				<c:if test="${q.exampleIdx == 4}">
					<div>
						&nbsp;4. ${q.exampleTitle}
					</div>
					<br>
				</c:if>
			</div>
		</c:forEach>
	</div>
</body>
</html>