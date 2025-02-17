<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>슬로건짐 관리자 페이지</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .center-box {
            width: 50%;
            margin: 0 auto;
        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="center-box">
    	<div class="mb-3">
            <label for="formGroupExampleInput1" class="form-label">대여번호</label>
            <input type="text" class="form-control rno" id="formGroupExampleInput1" placeholder="예시 : 0">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput1" class="form-label">날짜</label>
            <input type="date" class="form-control rdate" id="formGroupExampleInput1" placeholder="예시 : 0000-00-00">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label">시간</label>
            <input type="text" class="form-control rtime" id="formGroupExampleInput2" placeholder="예시 : 00">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput3" class="form-label">인원수</label>
            <input type="text" class="form-control rcount" id="formGroupExampleInput3" placeholder="예시 : 0">
        </div>
        <div class="mb-3">
            <label for="formGroupExampleInput4" class="form-label">가맹점 번호</label>
            <input type="text" class="form-control sno" id="formGroupExampleInput4" placeholder="예시 : 0">
        </div>
        
	<button onclick="rentalUpdate()" type="button" class="btn btn-dark">수정</button>
    </div>
    
	
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="/rental/JS/admin/rentalUpdate.js"></script>
</body>
</html>
