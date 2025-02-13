console.log("logout.js open")


// [1] 로그아웃
const logout = () => {
	
	fetch("/rental/member/login" , {method :'DELETE'})
		.then(r => r.json())
		.then(data => {
			if(data == true){
				alert("로그아웃 성공")
				location.href = "/rental/login/login.jsp"
			}else{
				alert('실패 : 관리자에게 문의')
			}
		})
		.catch(e => {console.log(e)})
}