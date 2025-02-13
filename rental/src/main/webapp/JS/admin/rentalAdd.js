console.log("rental.js open");

const rentalAdd = () => {
	let rdatein = document.querySelector('.rdate');
	let rtimein = document.querySelector('.rtime');
	let rcountin = document.querySelector('.rcount');
	let snoin = document.querySelector('.sno');
	let mphonein = document.querySelector('.mphone');
	
	const rdate = rdatein.value;
	const rtime = rtimein.value;
	const rcount = rcountin.value;
	const sno = snoin.value;
	const mphone = mphonein.value;
	
	const obj = {
		rdate : rdate,
		rtime : rtime,
		rcount : rcount,
		sno : sno, 
		mphone : mphone
	}
	console.log(obj);
	
	const option = {
		method : 'POST',
		headers : {'Content-Type' : 'application/json'},
		body : JSON.stringify(obj)
	}
	
	fetch('/rental/admin/rental', option)
		.then(response => response.json())
		.then(data => {
			if(data == true){
				alert('신청 성공');
				location.href = "rentalAdd.jsp"
			}else{
				alert('신청 실패')
			}
		})
		.catch(e => {console.log(e)})
}