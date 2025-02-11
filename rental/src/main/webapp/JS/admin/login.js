console.log("js확인");

// 관리자 로그인

const login = () => {
	let logaid = document.querySelector(".logaid");
	let logpwd = document.querySelector(".logpwd");
	
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
	
}//f end