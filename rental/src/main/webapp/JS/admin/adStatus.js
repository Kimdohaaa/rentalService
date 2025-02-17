console.log("확인");


//가맹점 수정 출력함수
const findAll = () => {
	
	const option = { method : 'GET'}
	console.log(option);
	fetch('/rental/store/info/status' , option)
		.then(r => r.json())
		.then(data => {
			
			let storeStatus = document.querySelector(".status");
			console.log(storeStatus);
			let html = ``;
			
			data.forEach((store) => {
				
				if(store.sstate == 1){
					store.sstate = "운영중";
					
				}else if(store.sstate == 0){
					store.sstate = "휴점";
				}else{
					store.sstate = "미정";
				}
				
			html += `
						<thead class="table-light"> 
									<tr>
										
										<th> ${store.smno} </th>
										<th> ${store.saddr} </th>
										<th> ${store.sname} </th>
										<th> ${store.sstate}</th>
										<th> ${store.reson}</th>										
										<th><button onclick="queryString(${store.sno})" type="button" class="btn btn-dark">수정</button></th>			
									</tr>
												
							</thead>		
						`
				
				
			})//each end
			storeStatus.innerHTML = html;
			
			
			
		})//then end
		.catch(e => {console.log(e);})
	
	
}// f end
	findAll();


	
	
	
function queryString(sno){
	
	const query = `?sno = ${sno}`;
	location.href = `${query}`;
	
}//f end





// 운영 상태 변경 및 이유
const onUpdate = (sno) => {
	
	
	const sstate = document.querySelector('.saddr').value;
	const reson =  document.querySelector('.reson').value;

	
	const obj = { sstate : sstate , reson : reson , sno: sno }
	console.log(obj);
	
	const option = {
		method : 'PUT' ,
		headers : {'Content-Type' : 'application/json'},
		body : JSON.stringify(obj)
	}
	
	fetch('/rental/store/info/status' , option)
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
			


