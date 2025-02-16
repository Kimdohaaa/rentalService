<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="table-container">
<div class="container mt-4 total" >
    <h3>취소 사유 통계</h3>
</div>
                <!-- 테이블 -->
                <table class="table">
                    <thead class="table-light">

                    </thead>
                    <tbody class="rentalbox">
                    <div class="cancelAll">
                    <canvas id="cancelChart"></canvas>
					</div>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

    <!-- Bootstrap JavaScript 및 Popper.js 추가 -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-QFODSHaEtUrx9nUd7HNd1GhXHV4+JXv45myi6mPSUeVqYUXo3U8+frqZRxkaBtJj" crossorigin="anonymous"></script>
	<script src="/rental/JS/admin/cancelAll.js"></script>
</body>
</html>