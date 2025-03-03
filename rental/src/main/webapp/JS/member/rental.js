/*
let sno = new URL(location.href).searchParams.get("sno");
let rdate = new URL(location.href).searchParams.get("rdate")  ;  
console.log(sno)
console.log(rdate)
// 예약 현황 조회에서 rno 랑 rdate 가져오기


// [1] 사용자가 선택한 날짜를 백으로 보내 해당 날짜에 이미 완료된 시간 가져오기
function rentalList() {
  // Select the rental box element
  let rentbox = document.querySelector(".rentalbox");
  let html = ``;

  console.log("확인", sno, rdate); 
  
  fetch(`/rental/rental/state?sno=${sno}&rdate=${rdate}`)
    .then(response => response.json())
    .then(data => {
      console.log(data);
     
	  // Set 으로 중복 방지 배열 생성
	  let timeList = new Set();
      if (data && data.length > 0) {
        
		 // response 값 순회
		  data.forEach((t) => {
          // 순회 값 Set 에 대입
		  timeList.add(t);
        });
      }
      // 예약 가능 시간 생성
      for (let i = 0; i <= 24; i++) {
        let rtime = i < 10 ? `0${i}` : `${i}`; // 문자열 2글자 타입으로 변환

		// response 된 값에 예약 시간이 있으면 예약불가
        if (timeList.has(rtime)) {
          html += `
            <tr>
              <td>${rdate}</td>
              <td>${rtime}</td>
              <td>10,000</td>
			  <td>예약불가합니다</td>
              <td><button class="btn btn-danger" disabled>예약불가</button></td>
            </tr>
          `;
        } else {
          html += `
            <tr>
              <td>${rdate}</td>
              <td>${rtime}</td>
              <td>10,000</td>
              <td>
                <input type='button' onclick='count("minus", ${rtime})' value='-' class="btn btn-primary" style="background-color: #212529;"/>
                <span id="result${rtime}">0</span>
                <input type='button' onclick='count("plus", ${rtime})' value='+' class="btn btn-primary" style="background-color: #212529;"/>
              </td>
              <td class="rentalbtn">
                <button type="button" onclick="addRental(${rtime})" class="btn btn-primary" style="background-color: #212529; border: none;">예약 가능</button>
              </td>
            </tr>
          `;
        }
      }

      // HTML 출력
	  rentbox.innerHTML = html;
    })
    .catch(error => {
      console.error('요청 처리 오류:', error); // 예외처리
    });
}

rentalList()

const addRental = (rtime) => {
	rtime = (rtime < 10 ? "0" +rtime : rtime )
	let rcountin = document.getElementById(`result${rtime}`);
	console.log(rtime)
	
	// span 마크업은 innerHTML 로 값 가져오기!!!!
	let rcount = rcountin.innerHTML;
	 
	console.log(rtime)

	if(rcount <= 0){
		alert("인원수를 선택하세요.")
		return;
	}
	
	
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
			if(data > 0){
				alert("예약 성공")
				requestPay(rtime, rcount, rno)
				// location.reload(true); // 예약 성공 시 새로고침
				// return data;
			}else{
				alert("예약 불가 : 관리자에게 문의")
				//location.href = "/rental/member/rental.jsp"
				return 0;
			}
		})
		.catch(e => {console.log(e); return 0;})

// 아임포트 API 로 결제 구현  
function requestPay(rtime, rcount, rno) {
	// rtime = (rtime < 10 ? "0" +rtime : rtime )
	
	// let rcountin = document.getElementById(`result${rtime}`);
	console.log("시간",rtime)
	console.log("인원",rcount)
	console.log("번호",rno)	
		// span 마크업은 innerHTML 로 값 가져오기!!!!
	// let rcount = rcountin.innerHTML;
	
	//if(rcount <= 0){
	//		alert("인원수를 선택하세요.")
	//		return;
	//}
		
		
	IMP.init('imp51664346'); //iamport 대신 자신의 "가맹점 식별코드"를 사용
	IMP.request_pay({
    pg: "html5_inicis",
    pay_method: "card",
    merchant_uid : 'merchant_'+new Date().getTime(),
    name : '슬로건짐',
    amount : rcount * 10000,
    buyer_email : 'iamport@siot.do',
    buyer_name : '구매자',
    buyer_tel : '010-1234-5678',
    buyer_addr : '서울특별시 강남구 삼성동',
    buyer_postcode : '123-456'
  }, function (rsp) { // callback
      if (rsp.success) {
		console.log(rsp.imp_uid)
		console.log(rsp.success)
		console.log(rsp.paid_amount)
		///////
		// let rno = addRental(rtime);
		
		console.log(rno)
		
		if(rno > 0){
	        let obj = {
				imp_uid : rsp.imp_uid,
				paid_amount : rsp.paid_amount,
				rno : rno
			}
			
			const option = {
				method : 'POST',
				headers : { "Content-Type": "application/json"},
				body : JSON.stringify(obj)
			}
			
			fetch("/rental/rental/pay", option)
				.then(r => r.json())
				.then(data => {
					if(data == true){
						alert("예약 성공")
						requestPay(rtime, rcount, rno)
						location.reload(true); // 예약 성공 시 새로고침
										
						console.log("DB연동 성공")
					}else{
						alert("DB 연동 실패")
					}
				})
				.catch(e =>  {console.log(e)})
			
		}else{
			console.log("대여 실패 ")
		}	
		//////
		
		// addRental(rtime)
      } else {
		
 		alert('결제실패');
      }
	  
  }
  );
}


/*

// rentalList 에서 예약되지 않은 rtime 을 사용자가 선택 시 예약 정보 request
const addRental = (rtime) => {
	// rtime = (rtime < 10 ? "0" +rtime : rtime )
	let rcountin = document.getElementById(`result${rtime}`);
	console.log(rtime)
	
	// span 마크업은 innerHTML 로 값 가져오기!!!!
	let rcount = rcountin.innerHTML;
	 
	console.log(rtime)

	if(rcount <= 0){
		alert("인원수를 선택하세요.")
		return;
	}
	
	
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
			if(data > 0){
				alert("예약 성공")
				location.reload(true); // 예약 성공 시 새로고침
				return data;
			}else{
				alert("예약 불가 : 관리자에게 문의")
				//location.href = "/rental/member/rental.jsp"
				return 0;
			}
		})
		.catch(e => {console.log(e); return 0;})
}
*/
// [1] 현재 예약 완료되어 예약 불가능한 시간대 조회
let sno = new URL(location.href).searchParams.get("sno");
let rdate = new URL(location.href).searchParams.get("rdate")  ;  
console.log(sno)
console.log(rdate)

function rentalList() {
  let rentbox = document.querySelector(".rentalbox");
  let html = ``;

  console.log("확인", sno, rdate);

  fetch(`/rental/rental/state?sno=${sno}&rdate=${rdate}`)
    .then(response => response.json())
    .then(data => {
      console.log(data);

      let timeList = new Set();
      if (data && data.length > 0) {
        data.forEach((t) => {
          timeList.add(t);
        });
      }

      for (let i = 0; i <= 24; i++) {
        let rtime = i < 10 ? `0${i}` : `${i}`;

        if (timeList.has(rtime)) {
          html += `
            <tr>
              <td>${rdate}</td>
              <td>${rtime}</td>
              <td>10,000</td>
              <td>예약불가합니다</td>
              <td><button class="btn btn-danger" disabled>예약불가</button></td>
            </tr>
          `;
        } else {
          html += `
            <tr>
              <td>${rdate}</td>
              <td>${rtime}</td>
              <td>10,000</td>
              <td>
                <input type='button' onclick='count("minus", ${rtime})' value='-' class="btn btn-primary" style="background-color: #212529;"/>
                <span id="result${rtime}">0</span>
                <input type='button' onclick='count("plus", ${rtime})' value='+' class="btn btn-primary" style="background-color: #212529;"/>
              </td>
              <td class="rentalbtn">
                <button type="button" onclick="addRental(${rtime})" class="btn btn-primary" style="background-color: #212529; border: none;">예약 가능</button>
              </td>
            </tr>
          `;
        }
      }

      rentbox.innerHTML = html;
    })
    .catch(error => {
      console.error('요청 처리 오류:', error);
    });
}

rentalList(); // JS 실행 시 최초 실해

// [2] Rental table 에 insert 성공 시 아임표트 결제로 이동
const addRental = (rtime) => {
  rtime = (rtime < 10 ? "0" + rtime : rtime);
  let rcountin = document.getElementById(`result${rtime}`);
  let rcount = rcountin.innerHTML;

  if (rcount <= 0) {
    alert("인원수를 선택하세요.");
    return;
  }

  let obj = {
    rcount: rcount,
    rdate: rdate,
    sno: sno,
    rtime: rtime,
  };
  const option = {
    method: `POST`,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(obj),
  };

  fetch("/rental/member/rental", option)
    .then(r => r.json())
    .then(data => {
      if (data > 0) {
        // alert("예약 성공");
        let rno = data;  // 예약 성공 시 rno 값 받기
		console.log(rno)
        requestPay(rtime, rcount, rno); // 결제 처리
        // location.reload(true);  // 예약 성공 후 페이지 새로고침
      } else {
        alert("예약 불가 : 관리자에게 문의");
        return 0;
      }
    })
    .catch(e => { console.log(e); return 0; });
};

// 아임포트 결제
function requestPay(rtime, rcount, rno) {
  console.log("시간", rtime);
  console.log("인원", rcount);
  console.log("번호", rno);

  IMP.init('imp51664346'); // iamport 대신 자신의 "가맹점 식별코드"를 사용
  IMP.request_pay({
    pg: "html5_inicis",
    pay_method: "card",
    merchant_uid: 'merchant_' + new Date().getTime(),
    name: '슬로건짐',
    amount: rcount * 10000,
    buyer_email: 'iamport@siot.do',
    buyer_name: '구매자',
    buyer_tel: '010-1234-5678',
    buyer_addr: '서울특별시 강남구 삼성동',
    buyer_postcode: '123-456'
  }, function (rsp) {
    if (rsp.success) {
      console.log(rsp.imp_uid);
      console.log(rsp.success);
      console.log(rsp.paid_amount);

      if (rno > 0) {
        let obj = {
          imp_uid: rsp.imp_uid,
          paid_amount: rsp.paid_amount,
          rno: rno
        };

        const option = {
          method: 'POST',
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(obj)
        };

        fetch("/rental/rental/pay", option)
          .then(r => r.json())
          .then(data => {
            if (data === true) {

              alert("결제 성공");
              location.reload(true); // 예약 및 결제 성공 후 페이지 새로고침
              console.log("DB 연동 성공");
            } else {
              alert("DB 연동 실패");
            }
          })
          .catch(e => { console.log(e); });
      } else {
        console.log("대여 실패");
      }
    } else {
      alert('결제 실패');
    }
  });
}

