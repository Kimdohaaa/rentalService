<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
        
        <!-- FontAwesome -->
        <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
    </head>
    
    <!-- 상단 바 S -->
    <body class="sb-nav-fixed">
        <nav class="sb-topnav navbar navbar-expand navbar-dark bg-dark">
            <!-- Navbar Brand-->
            <a class="navbar-brand ps-3" href="index.html">관리자 페이지</a> 
            
            <!-- 상단 바 숨기기 기능 -->
            <button class="btn btn-link btn-sm order-1 order-lg-0 me-4 me-lg-0" id="sidebarToggle" href="#!"><i class="fas fa-bars"></i></button>
            
            <!-- 검색창 기능 => 사용할지 말지 결정하기 -->
            <form class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0">
                
            </form>
            
             <!-- 상단바 드롭다운 -->
            <ul class="navbar-nav ms-auto ms-md-0 me-3 me-lg-4">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbarDropdown" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false"><i class="fas fa-user fa-fw"></i></a>
                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdown">
                        <!-- <li><a class="dropdown-item" href="#!">Settings</a></li>  -->
                        <li><hr class="dropdown-divider" /></li>
                        <li><a class="dropdown-item" href="/rental/login/login.jsp">Logout</a></li>
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
                                        
                                    <a class="nav-link" href="rentalRead.jsp">대여등록</a>
                                            <nav class="sb-sidenav-menu-nested nav">
                                                    <a class="nav-link" href="rentalAdd.jsp">대여등록</a>
                                                    <a class="nav-link" href="rentalRead.jsp">대여현황조회</a>
                                                    <a class="nav-link" href="rentalUpdate.jsp">대여수정</a>
                                                    <a class="nav-link" href="rentalCancel.jsp">대여취소</a>
                                                </nav>
                                                
                                    <a class="nav-link" href="storeRead.jsp">가맹관리</a>
                                            <nav class="sb-sidenav-menu-nested nav">
                                                    <a class="nav-link" href="storeAdd.jsp">가맹등록</a>
                                                    <a class="nav-link" href="storeRead.jsp">가맹조회</a>
                                                    <a class="nav-link" href="storeUpdate.jsp">가맹수정</a>
                                                    <a class="nav-link" href="storeDelete.jsp">가맹상태수정</a>
                                                </nav>
                                                
                                            
                                    <a class="nav-link" >매출관리</a>
                                            <nav class="sb-sidenav-menu-nested nav">
                                                    <a class="nav-link" href="total.jsp">년 별 매출</a>
                                                    <a class="nav-link" href="month.jsp">월 별 매출</a>
                                                    <a class="nav-link" href="day.jsp">일 별 매출</a>
                                                </nav>
                                                
                                             <a class="nav-link" href="cancelAll.jsp" >취소통계</a>
                                             <nav class="sb-sidenav-menu-nested nav">
                                                    <a class="nav-link" href="etcCancel.jsp">기타 취소 사유</a>
                                                </nav>
                                               
                                </nav>
                            </div>
                            
                            
                            
                            
                        </div>
                    </div>

                </nav>
            </div>
        </div>
        
        <!-- 사이드 바 E -->
        
       <!-- 여기에 jsp 작성 -->
        
        <!-- JS 경로 -->
        <script src="/js/admin/total.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="../startbootstrap-sb-admin-gh-pages/js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="../startbootstrap-sb-admin-gh-pages/assets/demo/chart-area-demo.js"></script>
        <script src="../startbootstrap-sb-admin-gh-pages/assets/demo/chart-bar-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js" crossorigin="anonymous"></script>
        <script src="../startbootstrap-sb-admin-gh-pages/js/datatables-simple-demo.js"></script>
    </body>
</html>
