console.log("rentallist.js open")

// 로그인된 사용자의 예약 현황 조회
const getRentalList = () => {
	

	fetch("/rental/member/rental")
		.then(r => r.json())
		.then(data => {
			
			let rentallist = document.querySelector(".rentallist");
			let html = ``;
										
			
			data.forEach((rental) => {
				html += `<tr>
							<td>${rental.rno}</td>
							<td>${rental.sname}</td>
							<td>${rental.rdate}</td>
							<td>${rental.rtime}</td>
							<td>${rental.rprice}</td>
							<td>${rental.rcount}</td>
							<td class="rentalbtn">
								<button type="button" onclick="rentalUpdate(${rental.rno})" class="btn btn-primary" style="background-color : #212529; border: none;">
							    	예약 수정
								 </button>
							</td>                                            
							<td class="rentalbtn">
								<button type="button" class="btn btn-primary" style="background-color : #212529; border: none;">
								<a href="/rental/member/rentalDelete.jsp?rno=${rental.rno}"> 예약 취소 </a>
								</button>
							</td>
						</tr>`
			})
			rentallist.innerHTML = html;
		})
		.catch(e => {console.log(e)})
}

getRentalList() // 최초 실행

// 예약 수정
const rentalUpdate = (rno) => {
	console.log(rno);
	
	let rcount = Number(prompt("수정 인원을 입력하세요."))
	
	console.log(rcount)
	
	// 사용자가 입력한 인원이 int 타입인지 확인하기
	if(rcount < 0 || !Number.isInteger(rcount)){
		alert("올바른 인원을 입력하세요.");
		return;
	}
	
	// 인원 제한 추가할거면 추가하기
	
	let obj = {
		rno: rno,
		rcount : rcount
	}
	
	const option = {
		method : 'PUT',
		headers : {"Content-Type" : "application/json"},
		body : JSON.stringify(obj)
	}
	
	fetch("/rental/member/rental", option)
		.then( r => r.json())
		.then(data => {
			if(data == true){
				alert("수정 성공")
				location.href = "/rental/member/rentallist.jsp"
			}else{
				alert("실패 : 관리자에게 문의")
				location.href = "/rental/member/rentallist.jsp"
			}
		})
		.catch(e => {console.log(e)})
	
}

