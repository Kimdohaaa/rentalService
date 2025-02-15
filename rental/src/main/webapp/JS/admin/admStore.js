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
	
	//FormData는 별도의 Content-Type 설정 불필요
	const option = {
		method : 'POST' ,
		body : JSON.stringify(obj)
	}
	
	fetch('/rental/stor/info' , option)
		.then(r => r.json())
		.then( data => {
			console.log(data);
				
				if( data == true){
					alert('가맹점 등록 성공');
					
				}else
					alert('가맹점 등록 실패');
			
			
		})
		.catch( e => {console.log(e);})
	
	
}// f end

// [2] 가맹점 전체출력함수
	const findall = () => {
		
		const option = { method : 'GET'}
		fetch('/rental/stor/info' , option)
			.then(r => r.json())
			.then(data => {
				
				let storeUpdate // 나중에 html에 넣을곳 지정하기
				let html = ``;
				
				data.forEach((store) => {
					html += ``
					
					
				})
				
				
			})
			.catch(e => {console.log(e);})
	}//f end
	

