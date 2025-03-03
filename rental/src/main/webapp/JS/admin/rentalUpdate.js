const rentalUpdate = () => {
	const rnoin = document.querySelector('.rno');
	const rdatein = document.querySelector('.rdate');
	const rtimein = document.querySelector('.rtime');
	const rcountin = document.querySelector('.rcount');
	const snoin = document.querySelector('.sno');
	
	const rno = rnoin.value;
	const rdate = rdatein.value;
	const rtime = rtimein.value;
	const rcount = rcountin.value;
	const sno = snoin.value;
	
	const obj = {
		rno : rno,
		rdate : rdate,
		rtime : rtime,
		rcount : rcount,
		sno : sno
	}
	
	const option = {
		method : 'PUT',
		headers : {'Content-Type' : 'application/json'},
		body : JSON.stringify(obj)
	} 
	fetch('/rental/admin/rental', option)
		.then(response => response.json())
		.then(data => {
			if(data == true){
				alert('수정 성공');
				location.href="rentalUpdate.jsp"
			}else{
				alert('수정 실패')
			}
		})
		.catch(e => {console.log(e);})
}

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
