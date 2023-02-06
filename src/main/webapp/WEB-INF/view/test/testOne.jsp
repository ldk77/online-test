<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:set var="idx" value="${t.questionIdx}"/>
		<c:forEach var="t" items="${list}">
			<table>			
				<c:set var="curdate" value="${t.questionIdx}"/>
				<c:if test="${curdate != idx}">
				<tr>
					<td>${t.questionIdx}. ${t.questionTitle}</td>
				</tr>
				</c:if>	
				<tr>
					<td>${t.exampleIdx}.${t.exampleTitle}</td>
				</tr>				
			</table>		
		</c:forEach>
	<c:set var="idx" target="idx" value="${t.questionIdx}"/>
</body>
</html>