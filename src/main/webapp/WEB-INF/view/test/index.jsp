<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Dashboard - SB Admin</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="../css/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="${pageContext.request.contextPath}/test/index">ONLINE TEST</a>
            <!-- Navbar-->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/student/modifyStudentPw">비밀번호수정</a></li>                     
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="${pageContext.request.contextPath}/student/logout">로그아웃</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">                      
                            <div class="sb-sidenav-menu-heading">TEST</div>
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                              	TEST
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/test/testList">TEST LIST</a>
                                    <a class="nav-link" href="${pageContext.request.contextPath}/student/studentTestList">TEST SCORE</a>
                                </nav>
                            </div>                    
                       
                       
                        </div>
                    </div>      
                </nav>
            </div>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">환영합니다</h1>
                        
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">님이 접속 하였습니다.</li>
                        </ol>
                        <div class="row">
                            <div class="col-xl-12">
                                <div class="card mb-4">
                                    <div class="card-header">                                      
                                     	 test List
                                    </div>
                                    <div class="card-body">  
                                    <div style="float: right; padding-bottom: 10px;"> 
                                    <c:if test="${loginTeacher != null}">                               
										<a href="${pageContext.request.contextPath}/teacher/addTest">시험등록</a>
									</c:if>		
											<form method="get" action="${pageContext.request.contextPath}/test/testList">
											<input type="text" name="searchWord">
											<button type="submit">시험검색</button>
									</div>	
										</form>
										<table class = "table table-hover w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
											<tr>			
												<th>testTitle</th>
												<th>testDate</th>
												<th>수정/응시/보기</th>	
												<th>문제등록</th>		
											</tr>
											<c:forEach var="t" items="${list}">
												<tr>
													<td>${t.testTitle}</td>
													<td>${t.testDate}</td>
													<td><a href="${pageContext.request.contextPath}/teacher/modifyTest?testNo=${t.testNo}&testTitle=${t.testTitle}&testDate=${t.testDate}">수정</a>
														/ <a href="${pageContext.request.contextPath}/student/addPaper?testNo=${t.testNo}">응시</a>
														/ <a href="${pageContext.request.contextPath}/test/testOne?testNo=${t.testNo}">보기</a>
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
                                    <canvas id="myAreaChart" width="100%" height="40"></canvas></div>
                                </div>
                            </div>                  
                        </div>
                        <div class="card mb-4">
    
                            </div>                  
                </main>
                <footer class="py-4 bg-light mt-auto">
                    <div class="container-fluid px-4">
                        <div class="d-flex align-items-center justify-content-between small">
                            <div class="text-muted">Copyright &copy; Your Website 2022</div>
                            <div>
                                <a href="#">Privacy Policy</a>
                                &middot;
                                <a href="#">Terms &amp; Conditions</a>
                            </div>
                        </div>
                    </div>
                </footer>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="assets/demo/chart-area-demo.js"></script>
        <script src="assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
