<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
<c:forEach var="q" items="${list}">
	<c:if test="${q.exampleIdx == 1}">
		<script>
			$(document).ready(function(){
				$('#examBtn').click(function() {
					if($('input[name=answer${q.questionIdx}]').is(':checked')) {
						let exampleAnswer = $('input[name=answer${q.questionIdx}]:checked').val();
						$('#answer${q.questionIdx}').val(exampleAnswer);
						
					}
					$('#examForm').submit();
				});
			});
		</script>
	</c:if>
</c:forEach>
</head>
<body>
	<h1>${thisTest.testNo}회차 ${thisTest.testTitle}</h1>
	<p>시험일자 : ${thisTest.testDate}</p>
	<hr>
	<!-- 문제 -->
	<form id="examForm" action="${pageContext.request.contextPath}/student/addPaper" method="post">
		<div>
			<input type="hidden" name="testNo" value="${thisTest.testNo}"> <!-- 응시시험번호 -->
			<input type="hidden" name="studentNo" value="${loginStudent.studentNo}"> <!-- 응시학생번호 -->
			<c:forEach var="q" items="${list}">
				<div>
					<!-- 문제 -->
					<c:if test="${q.exampleIdx == 1}">
						<div style="font-weight:bold;">
							${q.questionIdx}. ${q.questionTitle}
						</div>
						<!-- 문제 고유번호(questionNo) + 선택한 답안(answer) 히든처리하여 컨트롤러로 넘겨주기  -->
						<input type="hidden" name="questionNo" value="${q.questionNo}">
						<!-- 정답 미선택 제출시, value 0 오답처리 -->
						<input type="hidden" id="answer${q.questionIdx}" name="answer" value="0">
					</c:if>
					<br>
					<!-- 선택지 -->
					<c:if test="${q.exampleIdx == 1}">
						<div>
						<label class="self_check">
							<input type="radio" class="answer${q.questionIdx}" name="answer${q.questionIdx}" value="1">
							<span>1</span>
							&nbsp; ${q.exampleTitle}
						</label>
						</div>
					</c:if>
					<c:if test="${q.exampleIdx == 2}">
						<div>
						<label class="self_check">
							<input type="radio" class="answer${q.questionIdx}" name="answer${q.questionIdx}" value="2">
							<span>2</span>
							&nbsp; ${q.exampleTitle}
						</label>
						</div>
						
					</c:if>
					<c:if test="${q.exampleIdx == 3}">
						<div>
						<label class="self_check">
							<input type="radio" class="answer${q.questionIdx}" name="answer${q.questionIdx}" value="3">
							<span>3</span>
							&nbsp; ${q.exampleTitle}
						</label>
						</div>
					</c:if>
					<c:if test="${q.exampleIdx == 4}">
						<div>
						<label class="self_check">
							<input type="radio" class="answer${q.questionIdx}" name="answer${q.questionIdx}" value="4">
							<span>4</span>
							&nbsp; ${q.exampleTitle}
						</label>
						</div>
						<br><br>
					</c:if>
				</div>
				
			</c:forEach>
		</div>
		<div>
			<button type="button" id="examBtn">제출하기</button>
		</div>
	</form>
</body>
</html>