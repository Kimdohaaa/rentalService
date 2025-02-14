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
    
       
        <div>
        
        	가계사진 출력
        	
        </div>
       
       
       
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label smno">사업자등록번호</label>
            <input type="text" class="form-control rtime " id="formGroupExampleInput2" >
        </div>
        
        <div class="mb-3">
            <label for="formGroupExampleInput3" class="form-label saddr">가맹점 주소</label>
            <input type="text" class="form-control rcount" id="formGroupExampleInput3" >
        </div>
        
        <div class="mb-3">
            <label for="formGroupExampleInput4" class="form-label sname">가맹지점</label>
            <input type="text" class="form-control sno" id="formGroupExampleInput4" >
        </div>
        
        
        
	<button onclick="#" type="button" class="btn btn-dark">신청</button>
    </div>
    
	
</div>

<scripr src="/rental/js/admin/admStore.js"></scripr>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


	
	
</body>
</html>