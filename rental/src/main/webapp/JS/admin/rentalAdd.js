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

const findMemberPhone = () => {
    const option = { method: 'GET' };
    fetch('/rental/admin/memberinfo', option)
        .then(r => r.json())
        .then(data => {
            const phoneSelect = document.querySelector('.mphone');
            let html = ``;

            data.forEach(member => {
                html += `<option value="${member.mphone}">${member.mid} / ${member.mphone}</option>`;
            });

            phoneSelect.innerHTML = html;
        })
        .catch(e => {
            console.log(e);
        });
};

findMemberPhone();