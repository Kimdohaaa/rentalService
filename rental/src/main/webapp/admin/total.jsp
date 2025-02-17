<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang=ko>
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>관리자페이지 사이드 바</title>
        
        <!-- CSS 경로 -->
        <link href="../startbootstrap-sb-admin-gh-pages/css/styles.css" rel="stylesheet" />
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
        <link href= "/rental/css/total.css" rel="stylesheet" />
        <!-- FontAwesome -->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    
    <!-- 상단 바 S -->
<<<<<<< HEAD
<body>
=======
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.html">관리자 페이지</a> 
            
            <!-- 상단 바 숨기기 기능 -->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            
            <!-- 검색창 기능 => 사용할지 말지 결정하기 -->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                <div class="input-group">
                    <input class="form-control" type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" />
                    <button class="btn btn-primary" id="btnNavbarSearch" type="button"><i class="fas fa-search"></i></button>
                </div>
            </form>
            
            <!-- 상단바 드롭다운 -->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <!-- <li><a class="dropdown-item" href="#!">Settings</a></li>  -->
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="adminLogin.jsp">Logout</a></li>
                    </ul>
                </li>
            </ul>
        </nav>
        
        <!-- 상단 바 E -->
    
    
    	<!-- 사이드 바 S -->
        <div id="layoutSidenav">
            <div id="layoutSidenav_nav">
                <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
                    <div class="sb-sidenav-menu">
                        <div class="nav">
                            
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapseLayouts" aria-expanded="false" aria-controls="collapseLayouts">
                                <div class="sb-nav-link-icon"><i class="fas fa-columns"></i></div>
                                관리
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapseLayouts" aria-labelledby="headingOne" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav">
                                	
                                    <a class="nav-link" >대여등록</a>
                                    	<nav class="sb-sidenav-menu-nested nav">
		                                    <a class="nav-link" href="rentalAdd.jsp">대여등록</a>
		                                    <a class="nav-link" href="rentalRead.jsp">대여현황조회</a>
		                                    <a class="nav-link" href="rentalUpdate.jsp">대여수정</a>
		                                    <a class="nav-link" href="rentalCancel.jsp">대여취소</a>
		                                </nav>
		                                
		                                <!-- jsp , js 등록 만드는중  한-->
                                    <a class="nav-link" >가맹관리</a>
                                    	<nav class="sb-sidenav-menu-nested nav">
		                                    <a class="nav-link" href="storeAdd.jsp">가맹등록</a>
		                                    <a class="nav-link" href="storeRead.jsp">가맹조회</a>
		                                    <a class="nav-link" href="storeUpdate.jsp">가맹수정</a>
		                                    <a class="nav-link" href="storeDelete.jsp">가맹삭제</a>
		                                </nav>
		                                
		                            
                                    <a class="nav-link" >매출관리</a>
                                    	<nav class="sb-sidenav-menu-nested nav">
		                                    <a class="nav-link" href="annual.jsp">년 별 매출</a>
		                                    <a class="nav-link" href="month.jsp">월 별 매출</a>
		                                    <a class="nav-link" href="day.jsp">일 별 매출</a>
		                                </nav>
		                                
		                             <a class="nav-link" href="cancelAll.jsp" >취소통계</a>
                                    	   
                                </nav>
                            </div>
                            
                            
                            <a class="nav-link collapsed" href="#" data-bs-toggle="collapse" data-bs-target="#collapsePages" aria-expanded="false" aria-controls="collapsePages">
                                <div class="sb-nav-link-icon"><i class="fas fa-book-open"></i></div>
                                setting
                                <div class="sb-sidenav-collapse-arrow"><i class="fas fa-angle-down"></i></div>
                            </a>
                            <div class="collapse" id="collapsePages" aria-labelledby="headingTwo" data-bs-parent="#sidenavAccordion">
                                <nav class="sb-sidenav-menu-nested nav accordion" id="sidenavAccordionPages">
                                    	<a class="nav-link" href="adminRead.jsp">관리자 정보 조회</a>
		                                <a class="nav-link" href="adminUpdate.jsp">관리자 정보 수정</a> <!-- 정보 조회에서 수정 페이지로 이동할 시 삭제 -->
		                                <a class="nav-link" href="adminLogin.jsp">로그아웃</a>
		                        </nav>
                            </div>
                            
                        </div>
                    </div>


    <jsp:include page="header.jsp"></jsp:include>

    <section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="table-container">
<div class="container mt-4 total" >
    <h3>총매출</h3>
    <p id="totalSalesBox" ></p> 
</div>
                <!-- 드롭다운 메뉴 (테이블 헤더 바로 위에 위치) -->
                <div class="dropdown mb-2">
                    <button class="btn btn-dark dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                        가맹점 선택
                    </button>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="#">강남점(본점)</a></li>
                        <li><a class="dropdown-item" href="#">종로점(2호점)</a></li>
                        <li><a class="dropdown-item" href="#">서초점(3호점)</a></li>
                        <li><a class="dropdown-item" href="#">홍대점(4호점)</a></li>
                        <li><a class="dropdown-item" href="#">해운대점(5호점)</a></li>
                        <li><a class="dropdown-item" href="#">부산남구점(6호점)</a></li>
                        <li><a class="dropdown-item" href="#">대구점(7호점)</a></li>
                        <li><a class="dropdown-item" href="#">대전점(8호점)</a></li>
                        <li><a class="dropdown-item" href="#">광주점(9호점)</a></li>
                        <li><a class="dropdown-item" href="#">인천점(10호점)</a></li>
                    </ul>
                </div>

                <!-- 테이블 -->
                <table class="table">
                    <thead class="table-light">

                    </thead>
                    <tbody class="rentalbox">
                    <div class="annualTotal">
                    <canvas id="annualChart"></canvas>
					</div>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

    <!-- Bootstrap JavaScript 및 Popper.js 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-QFODSHaEtUrx9nUd7HNd1GhXHV4+JXv45myi6mPSUeVqYUXo3U8+frqZRxkaBtJj" crossorigin="anonymous"></script>
	<script src="/rental/JS/admin/total.js"></script>
</body>
</html>
