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
            <label for="formGroupExampleInput2" class="form-label ">사업자등록번호</label>
				<div class="d-flex align-items-center gap-2">
				    <input type="text" class="form-control rtime smno" id="formGroupExampleInput2">
				    <button onclick="번호조회함수()" type="button" class="btn btn-dark" style="width: 20%"> 번호조회 </button>
				</div>
			<label for="formGroupExampleInput2" class="form-label 조회결과" style="color: red"></label>
        </div>
        
        <div class="mb-3">
            <label for="formGroupExampleInput3" class="form-label ">가맹점 주소</label>
            	<div class="d-flex align-items-center gap-2">
				     <input type="text" class="form-control rcount saddr" id="sample5_address" >
				    <button onclick="sample5_execDaumPostcode()" type="button" class="btn btn-dark" style="width: 20%"> 주소검색 </button>
				</div>
				<div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
        </div>
        
        <div class="mb-3">
            <label for="formGroupExampleInput4" class="form-label ">가맹지점</label>
            <input type="text" class="form-control sname" id="formGroupExampleInput4" >
        </div>
        
         <div class="mb-3">
            <label for="formGroupExampleInput4" class="form-label">가맹사진 첨부</label>
            <input type="file" class="form-control simg" id="formGroupExampleInput4" >
        </div>
        
        
        
	<button onclick="onStore()" type="button" class="btn btn-dark">신청</button>
    </div>
    
	
</div>


<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=1ac4a57d8a5927d34020a891fcdbbcbd&libraries=services"></script>
<script src="/rental/JS/admin/kakao.js"></script>

<script src="/rental/JS/admin/admStore.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>


	
	
</body>
</html>