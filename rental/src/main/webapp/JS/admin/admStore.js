console.log("admStroe 확인");

// <script src="/rental/JS/admin/admstore.js"></script>


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
						}else{
							store.sstate = "휴점";
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
	
	

	

