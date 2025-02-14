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
<body>

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
