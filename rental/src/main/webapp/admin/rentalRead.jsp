<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>슬로건짐 관리자 페이지</title>

    <!-- Bootstrap CSS 추가 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- 추가 CSS -->
    <style>
        /* 드롭다운과 테이블을 시각적으로 밀착 */
        .table-container {
            position: relative;
            display: inline-block;
            width: 100%;
        }

        .dropdown {
            display: inline-block;
            margin-bottom: 0; /* 드롭다운과 테이블 헤더 간격을 제거 */
        }
    </style>
</head>
<body>

    <jsp:include page="header.jsp"></jsp:include>

    <section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="table-container">
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
                        <tr>
                            <th> 날짜 </th>
                            <th> 시간 </th>
                            <th> 금액 </th>
                            <th> 예약 인원 </th>
                            <th> 예약상태 </th>
                            <th> 회원아이디 </th>
                        </tr>
                    </thead>
                    <tbody class="rentalbox">
                    </tbody>
                </table>
            </div>
        </div>
    </section>

    <!-- Bootstrap JavaScript 및 Popper.js 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-QFODSHaEtUrx9nUd7HNd1GhXHV4+JXv45myi6mPSUeVqYUXo3U8+frqZRxkaBtJj" crossorigin="anonymous"></script>
	
</body>
</html>
