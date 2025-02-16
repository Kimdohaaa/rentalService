
const rentalCancel = () => {
	const rnoin = document.querySelector('.rno');
	const rreasonin = document.querySelector('.rreason:checked');
	
	if(rreasonin){
		let rno = rnoin.value;
		let rreason = rreasonin.value;
		
		if(rreason == "reason"){
			rreson = prompt('취소 사유를 입력하세요. ')
		}
		console.log(rreason);
		
		let obj = {
			rno : rno,
			rreason : rreason
		}
		const option = {
			method : 'PUT',
			headers : {'Content-Type' : 'application/json'},
			body : JSON.stringify(obj)
		}
		fetch('/rental/admin/cancel', option)
			.then(response => response.json())
			.then(data =>{
				if(data == true){
					alert('예약 취소 완료');
					location.href="rentalCancel.jsp";
				}else{
					alert('예약 취소 실패');
				}
			})
			.catch(e => {console.log(e);})
	} else{
		alert('예약 취소 사유를 선택하세요.');
	}
}