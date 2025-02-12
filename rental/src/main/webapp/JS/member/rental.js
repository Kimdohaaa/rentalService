let sno = new URL(location.href).searchParams.get("sno");
let rdate = new URL(location.href).searchParams.get("rdate")  ;  
console.log(sno)
console.log(rdate)
// 예약 현황 조회에서 rno 랑 rdate 가져오기

// [1] 사용자가 선택한 날짜를 백으로 보내 해당 날짜에 이미 완료된 시간 가져오기
function rentalList() {
    // 선택된 날짜 출력
    let rentalbox = document.querySelector(".rentalbox");

    let html = ``;
    console.log("확인", sno, rdate);
    // AJAX 요청을 보내는 코드 (fetch 사용)
    fetch(`/rental/rental/state?sno=${sno}&rdate=${rdate}`)
        .then(response => response.json())
        .then(data => {  // t s
            console.log(data);

            if (data != null && data.length > 0) {  // data가 null이 아니고 길이가 0 이상일 경우
                data.forEach((t) => {  // f s
                    for (let i = 0; i <= 24; i++) {  // f s
						let rtime = "0"
						if(i < 10){
							let str = String (i)
							rtime += str;
							console.log(rtime)
						}else{
							let str = String (i)
							rtime = str;
							console.log(rtime)
						} 
						console.log("확인" , rtime)
                        if (t != i) {  // if s
							console.log("ccc" ,t)
                            html += `<tr>
                                        <td> ${rdate} </td>
                                        <td> ${rtime} </td>
                                        <td> 10,000 </td>
                                        <td>
											<input type='button'
										    onclick='count("minus",${rtime})'
										    value='-'
										    class="btn btn-primary" style="background-color : #212529;"/>
										    <span id="result${rtime}"> 0 </span>
										    <input type='button'
										    onclick='count("plus",${rtime})'
										    value='+'
										    class="btn btn-primary" style="background-color : #212529;"/>
                                        </td>
                                        <td class="rentalbtn">
                                            <button type="button" onclick="addRental(${rtime})" class="btn btn-primary" style="background-color : #212529; border: none;">
                                                예약가능
                                            </button>
                                        </td>
                                    </tr>`;
                        } else {  // e s
                            html += `<tr>
                                        <td> ${rdate} </td>
                                        <td> ${rtime} </td>
                                        <td> 10,000 </td>
                                        <td>
                                            <input type='button' value='X'
                                                class="btn btn-primary" style="background-color: red;"/>
                                            <input id='result${rtime}' />
                                            <input type='button' value='X'
                                                class="btn btn-primary" style="background-color: red;"/>
                                        </td>
                                        <td class="rentalbtn">
                                            <button type="button" class="btn btn-primary" style="background-color: red; border: none;">
                                                예약불가
                                            </button>
                                        </td>
                                    </tr>`;
                        }  // e e
                    }  // f e
                })  // f e
            } else {
                // 데이터가 없을 때
                for (let i = 0; i <= 24; i++) {  // f s
					let rtime = "0"
					if(i < 10){
						let str = String (i)
						rtime += str;
						console.log(rtime)
					}else{
						let str = String (i)
						rtime = str;
						console.log(rtime)
					} 
					console.log("최종확인",rtime)
                    html += `<tr>
                                <td> ${rdate} </td>
                                <td> ${rtime} </td>
                                <td> 10,000 </td>
                                <td>
                                    <input type='button'
                                        onclick='count("minus",${rtime})'
                                        value='-'
                                        class="btn btn-primary" style="background-color : #212529;"/>
                                    <span id="result${rtime}"> 0 </span>
                                    <input type='button'
                                        onclick='count("plus",${rtime})'
                                        value='+'
                                        class="btn btn-primary" style="background-color : #212529;"/>
                                </td>
                                <td class="rentalbtn">
                                    <button type="button" onclick="addRental(${rtime})" class="btn btn-primary" style="background-color : #212529; border: none;">
                                        예약가능
                                    </button>
                                </td>
                            </tr>`;
                }
            }
            // rentalbox.innerHTML은 반복문 밖에서 한 번만 호출
            rentalbox.innerHTML = html;
        })  // t e
        .catch(error => {
            console.error('요청 에러:', error);
        });
}

rentalList()


const addRental = (rtime) => {
	rtime = (rtime < 10 ? "0" +rtime : rtime )
	let rcountin = document.getElementById(`result${rtime < 10 ? "0" + rtime : rtime}`);
	if (rcountin) { // Check if the element exists
	    let rcount = rcountin.value;
	    console.log("인원", rcount);
	} else {
	    console.error(`Element with id result${rtime} not found.`);
	}
	console.log(rtime)

	
	
	let obj = {
		rcount : rcount,
		rdate : rdate,
		sno : sno,
		rtime : rtime
	}
	const option = {
		method :  `POST`,
		headers : {"Content-Type" : "application/json"},
		body : JSON.stringify(obj)
	}
	
	fetch("/rental/member/rental", option)
		.then(r => r.json())
		.then(data => {
			if(data == true){
				alert("예약 성공")
				// location.href = "/rental/member/index.jsp"
			}else{
				alert("예약 불가 : 관리자에게 문의하세요.")
				// location.href = "/rental/member/index.jsp"
			}
		})
		.catch(e => {console.log(e)})
}