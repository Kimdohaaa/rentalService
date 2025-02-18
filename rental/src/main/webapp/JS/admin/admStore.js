console.log("admStroe 확인");

// <script src="/rental/JS/admin/admstore.js"></script>

const findStoreNo = () =>{
	const option = { method : 'GET'}
	console.log(option);
	fetch('/rental/store/info' , option)
		.then(r => r.json())
		.then(data => { console.log(data);
			const snoselect = document.querySelector('.sno')
			let html = ``;
			
			data.forEach( store =>{ 

				if(store.sstate == 1){
						store.sstate = "운영중";
					}else if( store.sstate == 2) {
						store.sstate = "휴점";
					}else if( store.sstate == 0) {
						store.sstate = "폐점";
					}
					
				html += `<option value="${ store.sno }"> ${ store.smno} / ${ store.sname } ( ${ store.sstate } ) </option>`
			})
			snoselect.innerHTML = html;
		} )
		.catch( e => { console.log(e); })
}
findStoreNo();


const 번호조회함수 = () => {
	
	  // 사용자가 입력한 사업자번호를 가져옴
	  const 사업자번호 = document.querySelector('.smno').value;

	  // 서버로 보낼 데이터 객체 생성 (사업자번호를 배열로 감싸서 전송)
	  let data = { "b_no": [ 사업자번호.replaceAll('-','') ] }  // 테스트 사업자번호: 6408101354 (더조은 사업자번호)

	  // fetch 요청 옵션 설정
	  const option = {
	    method: 'POST',  // 요청 방식: POST
	    headers: {
	      'Content-Type': 'application/json'  // 요청 헤더에 Content-Type을 'application/json'으로 설정
	    },
	    body: JSON.stringify(data)  // 데이터 객체를 JSON 문자열로 변환하여 전송
	  }

	  // 공공 API URL 및 인증키 설정 (https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15081808)
	  const appKey = 'nwPZ%2F9Z3sVtcxGNXxOZfOXwnivybRXYmyoIDyvU%2BVDssxywHNMU2tA55Xa8zvHWK0bninVkiuZAA4550BDqIbQ%3D%3D' // 일반 인증키 (Encoding) - API 호출을 위한 인증키를 입력해야 함
	  fetch(`https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=${appKey}`, option)  // 공공 API 호출
	    .then(response => response.json())  // 응답을 JSON 형식으로 변환
	    .then(data => {
	      console.log(data);  // 콘솔에 반환된 데이터를 출력
	      document.querySelector('.조회결과').innerText = data.data[0].tax_type  // tax_type 값을 화면에 표시
	    })
	    .catch(error => console.error('Error:', error));  // 에러가 발생하면 콘솔에 에러 메시지 출력
	
}






// [1] 가맹점 등록함수
const onStore = () => {
	
	const smnoselect = document.querySelector('.smno');
	const saddrselect = document.querySelector('.saddr');
	const snameselect = document.querySelector('.sname');
	const simgselect = document.querySelector('.simg');
	
	console.log(snameselect);
	
	const smno = smnoselect.value;
	const saddr = saddrselect.value;
	const sname = snameselect.value;
	const simg = simgselect.value;
	
	console.log(sname);
	
	let obj = { smno : smno , saddr : saddr , sname : sname , simg : simg }
	
	console.log(obj);
	//FormData는 별도의 Content-Type 설정 불필요
	
	//formData로 변경
	const formData = new FormData();
	for(const key in obj){
		formData.append(key, obj[key]);
	}
	
	
	const option = {
		method : 'POST' ,
		body : formData
	
	}
	
	console.log(option);
	fetch('/rental/store/info' , option)
		.then(r => r.json())
		.then( data => {
			console.log(data);
				
			alert('등록성공');
			
			
		})
		.catch( e => {console.log(e);})
	
	
}// f end

// [2] 가맹점 전체출력함수
	const findAll = () => {
		
		const option = { method : 'GET'}
		console.log(option);
		fetch('/rental/store/info' , option)
			.then(r => r.json())
			.then(data => {
				
				let storeUpdate = document.querySelector(".store");
				
				
				
				console.log(storeUpdate);
				let html = ``;
				
				data.forEach((store) => {
						if(store.sstate == 1){
							store.sstate = "운영중";
						}else if( store.sstate == 2) {
							store.sstate = "휴점";
						}else if( store.sstate == 0) {
							store.sstate = "폐점";
						}
					
					html += `	
							     <thead class="table-light"> 
									<tr>
										<th> ${store.sno}</th>
										<th> ${store.smno} </th>
										<th> ${store.saddr} </th>
										<th> ${store.sname} </th>
										<th> ${store.sstate}</th>
										
									</tr>
									
								</thead>	
								`
					
					
				})
				storeUpdate.innerHTML = html;
				console.log(storeUpdate);
			})
			.catch(e => {console.log(e);})
	}//f end
	findAll();
	
// [3] 수정 함수

const onUpdate = () => {
	
	const sno = document.querySelector('.sno').value;
	const smno = document.querySelector('.smno').value;
	const saddr = document.querySelector('.saddr').value;
	const sname = document.querySelector('.sname').value;

	
	const obj = { smno :smno , saddr : saddr , sname : sname , sno: sno }
	console.log(obj);
	
	const option = {
		method : 'PUT' ,
		headers : {'Content-Type' : 'application/json'},
		body : JSON.stringify(obj)
	}
	
	fetch('/rental/store/info' , option)
		.then(r => r.json())
		.then(data => {
			if( data == true){
				alert('회원정보수정완료');
				location.href="total.jsp"; //나중에 문제 될시 수정할것
			}
			else{alert('회원정보수정 실패');}
			
		})
		.catch(e => {console.log(e);})
	
}//f end
	
	

	

