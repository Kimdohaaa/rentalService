console.log("js확인");

// 관리자 로그인

const adlogin = () => {
	let logaid = document.querySelector(".logaid");
	let logapwd = document.querySelector(".logapwd");
	
	const aid = logaid.value;
	const apwd = logapwd.value;
	
	const obj = {
		aid : aid ,
		apwd : apwd
	}
	
	const option = {
		method : 'POST' ,
		Headers : {"Content-Type" : "application/json"},
		body : JSON.stringify(obj)
	}
	
	fetch(`/rental/admin/login` ,option )
		.then(r => r.json())
		.then( data => {
			if(data == true){
							alert("로그인 성공")
							location.href= "/rental/admin/total.jsp"; // 메인 페이지 연결/rental/src/main/webapp/admin/total.jsp
							// js는 로그인 ctrl 클릭 안되고 켜봐야 확인될듯
						}else{
							alert("로그인 실패")
						}
		})
		.catch(e => {console.log(e)})
}//f end