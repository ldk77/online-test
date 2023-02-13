<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		 <link rel="apple-touch-icon" sizes="76x76" href="./assets/img/apple-icon.png">
		 <link rel="icon" type="image/png" href="./assets/img/favicon.png">
		 <title>
		  online test
		 </title>
		 <!--     Fonts and icons     -->
		<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet" />
		<!-- Nucleo Icons -->
		<link href="./assets/css/nucleo-icons.css" rel="stylesheet" />
		<link href="./assets/css/nucleo-svg.css" rel="stylesheet" />
		<!-- Font Awesome Icons -->
		<script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
		<link href="./assets/css/nucleo-svg.css" rel="stylesheet" />
		<!-- CSS Files -->
		<link id="pagestyle" href="./assets/css/soft-ui-dashboard.css?v=1.0.7" rel="stylesheet" />
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
	
	 <body class="">
         <main class="main-content  mt-0">
    <section>
      <div class="page-header min-vh-75">
        <div class="container">
          <div class="row">
            <div class="col-xl-4 col-lg-5 col-md-6 d-flex flex-column mx-auto">
              <div class="card card-plain mt-8">
                <div class="card-header pb-0 text-left bg-transparent">
                  <h3 class="font-weight-bolder text-info text-gradient">Welcome back</h3>
                  <p class="mb-0">Enter your id and password to login</p>
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
                                                <button type = "submit"  class="btn bg-gradient-info w-100 mt-4 mb-0">LOGIN</button>
                                            </div>
                                        </form>
                                        
                                        <!-- 강사 -->
                                      	<form id = "teacherForm" action = "${pageContext.request.contextPath }/loginTeacher" method = "post">
                                            <div class="form-floating mb-3">
                                                <input class="form-control" id="inputEmail" name = "teacherId" type="text" placeholder="name@example.com" />
                                                <label for="inputEmail">ID</label>
                                            </div>
                                            <div class="form-floating mb-3">
                                                <input class="form-control" aria-label="Password" aria-describedby="password-addon" id="inputPassword" name = "teacherPw" type="password" placeholder="Password" />
                                                <label for="inputPassword">Password</label>
                                                <span style="color:red"id = "msgs" class="msgs"></span>
                                            </div>                                            
                                            <div class="d-flex align-items-center justify-content-between mt-4 mb-0">
                                                <button type = "submit"  class="btn bg-gradient-info w-100 mt-4 mb-0">LOGIN</button>
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
                                                <button type = "submit"  class="btn bg-gradient-info w-100 mt-4 mb-0">LOGIN</button>
                                            </div>
                                        </form>
                                        </c:if>
                                    </div>            
                                </div>
                            </div>
                        </div>
                    </div>
				</div>
			</div>
		</section>
          </main> 
		<!-- -------- START FOOTER 3 w/ COMPANY DESCRIPTION WITH LINKS & SOCIAL ICONS & COPYRIGHT ------- -->
		  <footer class="footer py-5">
		    <div class="container">
		      <div class="row">
		        <div class="col-lg-8 mb-4 mx-auto text-center">
		          <a href="javascript:;" target="_blank" class="text-secondary me-xl-5 me-3 mb-sm-0 mb-2">
		            Company
		          </a>
		          <a href="javascript:;" target="_blank" class="text-secondary me-xl-5 me-3 mb-sm-0 mb-2">
		            About Us
		          </a>
		          <a href="javascript:;" target="_blank" class="text-secondary me-xl-5 me-3 mb-sm-0 mb-2">
		            Team
		          </a>
		          <a href="javascript:;" target="_blank" class="text-secondary me-xl-5 me-3 mb-sm-0 mb-2">
		            Products
		          </a>
		          <a href="javascript:;" target="_blank" class="text-secondary me-xl-5 me-3 mb-sm-0 mb-2">
		            Blog
		          </a>
		          <a href="javascript:;" target="_blank" class="text-secondary me-xl-5 me-3 mb-sm-0 mb-2">
		            Pricing
		          </a>
		        </div>
		        <div class="col-lg-8 mx-auto text-center mb-4 mt-2">
		          <a href="javascript:;" target="_blank" class="text-secondary me-xl-4 me-4">
		            <span class="text-lg fab fa-dribbble"></span>
		          </a>
		          <a href="javascript:;" target="_blank" class="text-secondary me-xl-4 me-4">
		            <span class="text-lg fab fa-twitter"></span>
		          </a>
		          <a href="javascript:;" target="_blank" class="text-secondary me-xl-4 me-4">
		            <span class="text-lg fab fa-instagram"></span>
		          </a>
		          <a href="javascript:;" target="_blank" class="text-secondary me-xl-4 me-4">
		            <span class="text-lg fab fa-pinterest"></span>
		          </a>
		          <a href="javascript:;" target="_blank" class="text-secondary me-xl-4 me-4">
		            <span class="text-lg fab fa-github"></span>
		          </a>
		        </div>
		      </div>
		      <div class="row">
		        <div class="col-8 mx-auto text-center mt-1">
		          <p class="mb-0 text-secondary">
		            Copyright © <script>
		              document.write(new Date().getFullYear())
		            </script> Soft by Creative Tim.
		          </p>
		        </div>
		      </div>
		    </div>
		  </footer>           
           <!--   Core JS Files   -->
			  <script src="./assets/js/core/popper.min.js"></script>
			  <script src="./assets/js/core/bootstrap.min.js"></script>
			  <script src="./assets/js/plugins/perfect-scrollbar.min.js"></script>
			  <script src="./assets/js/plugins/smooth-scrollbar.min.js"></script>
			  <script>
			    var win = navigator.platform.indexOf('Win') > -1;
			    if (win && document.querySelector('#sidenav-scrollbar')) {
			      var options = {
			        damping: '0.5'
			      }
			      Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
			    }
			  </script>
			  <!-- Github buttons -->
			  <script async defer src="https://buttons.github.io/buttons.js"></script>
			  <!-- Control Center for Soft Dashboard: parallax effects, scripts for the example pages etc -->
			  <script src="./assets/js/soft-ui-dashboard.min.js?v=1.0.7"></script> 
    </body>
</html>