<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title></title>
		 <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Login</title>
        <link href="./css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
		
		<!-- jQuery -->
		<!-- CDN 주소 추가 방식 -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>		
		
		<script>
		
			$(document).ready(function() {
				
				$('#cbEmployee').change(function() {
					$('#checkboxForm').submit();
				});
				
				$('#cbTeacher').change(function() {
					$('#checkboxForm').submit();
				});
				
				$('#cbStudent').change(function() {
					$('#checkboxForm').submit();
				});				
				
				
				console.log($('#cbEmployee').is(':checked'))
				
				if($('#cbEmployee').is(':checked')) {
					$('#employeeForm').show();
					$('#teacherForm').hide();
					$('#studentForm').hide();
				}
				
				if ($('#cbTeacher').is(':checked')){
					$('#employeeForm').hide();
					$('#teacherForm').show();
					$('#studentForm').hide();
				}
				
				if ($('#cbStudent').is(':checked')){
					$('#employeeForm').hide();
					$('#teacherForm').hide();
					$('#studentForm').show();
				}
				
			});
		
		</script>
		
	</head>
	
	 <body class="bg-primary">
        <div id="layoutAuthentication">
            <div id="layoutAuthentication_content">
                <main>
                    <div class="container">
                        <div class="row justify-content-center">
                            <div class="col-lg-5">
                                <div class="card shadow-lg border-0 rounded-lg mt-5">
                                    <div class="card-header"><h3 class="text-center font-weight-light my-4">Login</h3></div>
                                    <div class="card-body">
                                    <c:if test="${loginEmp == null && loginTeacher == null && loginStudent == null }">
										<form id = "checkboxForm" method = "post" action = "${pageContext.request.contextPath }/login">
											<c:if test="${user == 'employee' || user == null }">
												<input type = "radio" name = "user" id = "cbEmployee" value = "employee" checked>사원
												<input type = "radio" name = "user" id = "cbTeacher" value = "teacher">선생님
												<input type = "radio" name = "user" id = "cbStudent" value = "student">학생
											</c:if>
											<c:if test="${user == 'teacher' }">
												<input type = "radio" name = "user" id = "cbEmployee" value = "employee">사원
												<input type = "radio" name = "user" id = "cbTeacher" value = "teacher" checked>선생님
												<input type = "radio" name = "user" id = "cbStudent" value = "student">학생
											</c:if>
											<c:if test="${user == 'student' }">
												<input type = "radio" name = "user" id = "cbEmployee" value = "employee">사원
												<input type = "radio" name = "user" id = "cbTeacher" value = "teacher">선생님
												<input type = "radio" name = "user" id = "cbStudent" value = "student" checked>학생
											</c:if>
										</form>	
										<!-- 사원 -->
                                      	<form id = "employeeForm" action = "${pageContext.request.contextPath }/loginEmp" method = "post">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputEmail" name = "empId" type="text" placeholder="name@example.com" />
                                                <label for="inputEmail">ID</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputPassword" name = "empPw" type="password" placeholder="Password" />
                                                <label for="inputPassword">Password</label>
                                                <span style="color:red"id = "msgs" class="msgs"></span>
                                            </div>                                            
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <button type = "submit">LOGIN</button>
                                            </div>
                                        </form>
                                        
                                        <!-- 강사 -->
                                      	<form id = "teacherForm" action = "${pageContext.request.contextPath }/loginTeacher" method = "post">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputEmail" name = "teacherId" type="text" placeholder="name@example.com" />
                                                <label for="inputEmail">ID</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputPassword" name = "teacherPw" type="password" placeholder="Password" />
                                                <label for="inputPassword">Password</label>
                                                <span style="color:red"id = "msgs" class="msgs"></span>
                                            </div>                                            
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <button type = "submit">LOGIN</button>
                                            </div>
                                        </form>
                                        
                                        <!-- 학생 -->
                                      	<form id = "studentForm" action = "${pageContext.request.contextPath }/loginStudent" method = "post">
                                            <div class="form-floating mb-3" >
                                                <input class="form-control" id="inputEmail" name = "studentId" type="text" placeholder="name@example.com" />
                                                <label for="inputEmail">ID</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputPassword" name = "studentPw" type="password" placeholder="Password" />
                                                <label for="inputPassword">Password</label>
                                                <span style="color:red"id = "msgs" class="msgs"></span>
                                            </div>                                            
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <button type = "submit">LOGIN</button>
                                            </div>
                                        </form>
                                        </c:if>
                                    </div>            
                                </div>
                            </div>
                        </div>
                    </div>
                </main>
            </div>     
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
    </body>
</html>