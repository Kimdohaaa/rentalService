console.log("확인");



// 운영 상태 변경 및 이유
const onUpdate = () => {
	
	const smno = document.querySelector('.smno').value;
	const sname = document.querySelector('.sname').value;
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
			


