console.log("rental.js open")
// 예약 현황 조회에서 rno 랑 rdate 가져오기


const rentalbtn = () =>{
	let rentalbox = document.querySelector(".rentalbox");
	let rentalbtn = document.querySelector(".rentalbtn");
	
	let html  = ``;
	let btnhtml = ``;
	
	const option = {
		method : 'POST',
		headers : {"Content-Type" :"application/json"},
		// 사용자가 선택한 날짜 , 가맹점 번호 가져오기
	}
	
	
	fetch("/rental/rental/state")
		.then(r => r.json())
		.then(data => {
			for(let i = 0; i < 24 ; i++){
				html += `<tr>
							<td> 캘린더에서 누른값 받아오기 </td>
							<td> ${i} </td>
							<td> 10,000 </td>
						<td>
						<input type='button'
					    onclick='count("minus")'
						     value='-'
						     class="btn btn-primary" style="background-color : #212529;"/>
						<span id='result'>0</span>
						<input type='button'
						onclick='count("plus")'
						     value='+'
						class="btn btn-primary" style="background-color : #212529;"/>

						</td>`			
			}
			rentalbox.innerHTML = html;
		})
				

}

const rstate = () => {
	let rentalbox = document.querySelector(".rentalbox");
	let html = ``;
			for(let i = 0; i < 24 ; i++){
				html += `<tr>
								<td> ${data.rno}</td>
								<td> ${data.rdate} </td>
								<td> ${i} </td>
								<td> 10,000 </td>
								<td>
									<input type='button'
									       onclick='count("minus")'
									       value='-'
									       class="btn btn-primary" style="background-color : #212529;"/>
									<span id='result'>0</span>
									<input type='button'
									       onclick='count("plus")'
									       value='+'
									       class="btn btn-primary" style="background-color : #212529;"/>

								</td>`			
			}

		}