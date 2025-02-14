<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
            <label for="formGroupExampleInput2" class="form-label">가맹점</label>
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
        <div class="mb-3">
            <label for="formGroupExampleInput5" class="form-label">회원 핸드폰 번호</label>
            <input type="text" class="form-control mphone" id="formGroupExampleInput5" placeholder="예시 : 010-0000-0000">
        </div>
	<button onclick="rentalAdd()" type="button" class="btn btn-dark">신청</button>
    </div>
    
	
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
<script src="/rental/JS/admin/rentalAdd.js"></script>

	<jsp:include page="header.jsp"></jsp:include>
	
</body>
</html>