<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
 <meta name="viewport" content="width=device-width, initial-scale=1">

 <style>
        .center-box {
            width: 50%;
            margin: 0 auto;
        }
    </style>
 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<script src="/rental/JS/admin/adStatus.js"></script>

	<section class="py-5">
        <div class="container px-4 px-lg-5 mt-5">
            <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">


<div class="container d-flex justify-content-center align-items-center vh-100">
    <div class="center-box">
    
      
      	
      
       
        <div class="mb-3">
            <label for="formGroupExampleInput2" class="form-label ">사업자등록번호</label>
            <input type="text" class="form-control rtime smno" id="formGroupExampleInput2" >
        </div>
        
        <div class="mb-3">
            <label for="formGroupExampleInput3" class="form-label ">가맹점 주소</label>
            <input type="text" class="form-control rcount saddr" id="formGroupExampleInput3" >
        </div>
        
	     	<div class="dropdown">
	  <button class="btn btn-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
	    Dropdown button
	  </button>
	  <ul class="dropdown-menu">
	    <li><a class="dropdown-item" href="#">Action</a></li>
	    <li><a class="dropdown-item" href="#">Another action</a></li>
	    <li><a class="dropdown-item" href="#">Something else here</a></li>
	  </ul>
	</div>
								        
        
        
         <!--  <div class="mb-3">
            <label for="formGroupExampleInput4" class="form-label">가맹사진 첨부</label>
            <input type="file" class="form-control simg" id="formGroupExampleInput4" >
        </div>  나중에 가맹 사진 첨부 필요할시 사용 -->
        
        
        
	<button onclick="onUpdate()" type="button" class="btn btn-dark">수정 신청</button>
    </div>
    
	
</div>

<script src="/rental/JS/admin/admStore.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


	
	
</body>
</html>
</html>