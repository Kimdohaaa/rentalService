console.log("admStroe 확인");

// <script src="/rental/JS/admin/admstore.js"></script>


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




const 번호조회함수 = () => {
	
	  // 사용자가 입력한 사업자번호를 가져옴
	  const 사업자번호 = document.querySelector('.smno').value;
	  // 서버로 보낼 데이터 객체 생성 (사업자번호를 배열로 감싸서 전송)
	  let data = { "b_no": [ 사업자번호.replaceAll('-','') ] }  // 테스트 사업자번호: 6408101354 (더조은 사업자번호)
	  // fetch 요청 옵션 설정
	  const option = {
	    method: 'POST',  // 요청 방식: POST
	    headers: {
	      'Content-Type': 'application/json'  // 요청 헤더에 Content-Type을 'application/json'으로 설정
	    },
	    body: JSON.stringify(data)  // 데이터 객체를 JSON 문자열로 변환하여 전송
	  }
	  // 공공 API URL 및 인증키 설정 (https://www.data.go.kr/tcs/dss/selectApiDataDetailView.do?publicDataPk=15081808)
	  const appKey = 'nwPZ%2F9Z3sVtcxGNXxOZfOXwnivybRXYmyoIDyvU%2BVDssxywHNMU2tA55Xa8zvHWK0bninVkiuZAA4550BDqIbQ%3D%3D' // 일반 인증키 (Encoding) - API 호출을 위한 인증키를 입력해야 함
	  fetch(`https://api.odcloud.kr/api/nts-businessman/v1/status?serviceKey=${appKey}`, option)  // 공공 API 호출
	    .then(response => response.json())  // 응답을 JSON 형식으로 변환
	    .then(data => {
	      console.log(data);  // 콘솔에 반환된 데이터를 출력
	      document.querySelector('.조회결과').innerText = data.data[0].tax_type  // tax_type 값을 화면에 표시
		   if (data.match_cnt && data.match_cnt === 1) {
		          // match_cnt가 1이면 정상 처리
		          document.querySelector('.조회결과').innerText = data.data[0].tax_type;  
		          console.log("사업자 번호가 정상적으로 조회되었습니다.");
				  const registerButton = document.querySelector('#registerBtn');
				  if (registerButton) {
				              registerButton.disabled = false;
				          }
				  
		      } else {
		          // match_cnt가 1이 아닐 경우
		          alert("유효하지 않은 사업자 번호입니다. 다시 확인해주세요.");
		          document.querySelector('.조회결과').innerText = "조회 실패";  

		          // 등록 버튼 비활성화 (선택 사항)
		          const registerButton = document.querySelector('#registerBtn'); // 버튼 ID를 확인 후 변경
		          if (registerButton) {// 버튼 다시 비활성화
		              registerButton.disabled = true; 
		          }
		      }
		  })
		  .catch(error => {
		      console.error('Error:', error);
		      alert("API 요청 중 오류가 발생했습니다.");
		  });
		// data 배열로 b_no 로 사업자 번호를 보냄
		// data 0 : 안에 배열 형태로 들어가 있음
		//맞으면 match_cnt 가 1임
		//틀리면 match_cnt 가 없음
		//배열을 돌리고 확인 
		
		
}


const onStore = () => {
    if (coords === null) {
        alert("주소를 먼저 검색해주세요.");
        return;
    }

    /*const smnoselect = document.querySelector('.smno');
    const saddrselect = document.querySelector('.saddr');
    const snameselect = document.querySelector('.sname');
    const simgselect = document.querySelector('.simg');

    console.log(snameselect);

    const smno = smnoselect.value;
    const saddr = saddrselect.value;
    const sname = snameselect.value;
    const simg = simgselect.value;

    console.log(sname);

    let obj = {
        smno: smno,
        saddr: saddr,
        sname: sname,
        simg: simg,
        lat: coords.getLat(), // 위도 정보 추가
        lon: coords.getLng() // 경도 정보 추가

		    }
			
*/
	
	const signupform = document.querySelector('#storAddform');
	console.log(signupform);

   	const signupformData = new FormData(signupform);
    //FormData는 별도의 Content-Type 설정 불필요
	
	signupformData.append("lat" , coords.getLat() )
	signupformData.append("lon" , coords.getLng() )
	
    //formData로 변경
    //const formData = new FormData();
    /*for (const key in obj) {
        formData.append(key, obj[key]);
    }*/


    const option = {
        method: 'POST',
        body: signupformData
						
    }

    console.log(option);
    fetch('/rental/store/info', option)
        .then(response => {
            if (!response.ok) {
                throw new Error("서버 응답 오류"); // 서버 오류 발생 시 에러 던지기
            }
            return response.json();
        })
        .then(data => {
            console.log(data);

            alert('등록성공');


        })
        .catch(error => {
            console.error(error);
            alert("등록 실패: " + error.message); // 사용자에게 에러 메시지 표시
        })

}// f end





// [1] 가맹점 등록함수
/*const onStore = () => {
	
	let test = sample5_execDaumPostcode();
	console.log(test);
	
	
	const smnoselect = document.querySelector('.smno');
	const saddrselect = document.querySelector('.saddr');
	const snameselect = document.querySelector('.sname');
	const simgselect = document.querySelector('.simg');
	
	console.log(snameselect);
	
	const smno = smnoselect.value;
	const saddr = saddrselect.value;
	const sname = snameselect.value;
	const simg = simgselect.value;
	
	console.log(sname);
	
	let obj = { smno : smno , saddr : saddr , sname : sname , simg : simg }
	
	console.log(obj);
	//FormData는 별도의 Content-Type 설정 불필요
	
	//formData로 변경
	const formData = new FormData();
	for(const key in obj){
		formData.append(key, obj[key]);
	}
	
	
	const option = {
		method : 'POST' ,
		body : formData
	
	}
	
	console.log(option);
	fetch('/rental/store/info' , option)
		.then(r => r.json())
		.then( data => {
			console.log(data);
				
			alert('등록성공');
			
			
		})
		.catch( e => {console.log(e);})
	
	
}// f end*/

// [2] 가맹점 전체출력함수
	const findAll = () => {
		
		const option = { method : 'GET'}
		console.log(option);
		fetch('/rental/store/info' , option)
			.then(r => r.json())
			.then(data => {
				
				let storeUpdate = document.querySelector(".store");
				
				
				
				console.log(storeUpdate);
				let html = ``;
				
				data.forEach((store) => {
						if(store.sstate == 1){
							store.sstate = "운영중";
						}else if(store.sstate == 2){
							store.sstate = "휴점";
						}else if(store.sstate == 0){
							store.sstate = "폐점";
						}
					
					html += `	
							     <thead class="table-light"> 
									<tr>
										<th> ${store.sno}</th>
										<th> ${store.smno} </th>
										<th> ${store.saddr} </th>
										<th> ${store.sname} </th>
										<th> ${store.sstate}</th>
										
									</tr>
									
								</thead>	
								`
					
					
				})
				storeUpdate.innerHTML = html;
				console.log(storeUpdate);
			})
			.catch(e => {console.log(e);})
	}//f end
	findAll();
	
// [3] 수정 함수

const onUpdate = () => {
	
	const sno = document.querySelector('.sno').value;
	const smno = document.querySelector('.smno').value;
	const saddr = document.querySelector('.saddr').value;
	const sname = document.querySelector('.sname').value;

	
	const obj = { smno :smno , saddr : saddr , sname : sname , sno: sno }
	console.log(obj);
	
	const option = {
		method : 'PUT' ,
		headers : {'Content-Type' : 'application/json'},
		body : JSON.stringify(obj)
	}
	
	fetch('/rental/store/info' , option)
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
	
	

	

