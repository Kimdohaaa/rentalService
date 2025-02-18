console.log("확인");




const findStoreNo = () =>{
	const option = { method : 'GET'}
	console.log(option);
	fetch('/rental/store/info' , option)
		.then(r => r.json())
		.then(data => { console.log(data);
			const smoselect = document.querySelector('.sno')
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
			smoselect.innerHTML = html;
		} )
		.catch( e => { console.log(e); })
}
findStoreNo();






// 운영 상태 변경 및 이유
const onUpdate = (sno) => {
	
	
	const sno = document.querySelector('.sno').value;
	const sstate =  document.querySelector('.sstate').value;

	
	const obj = { sstate : sstate , sno: sno }
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
				alert('수정완료');
				location.href="total.jsp"; //나중에 문제 될시 수정할것
			}
			else{alert('수정 실패');}
			
		})
		.catch(e => {console.log(e);})
		
}//f end
			


