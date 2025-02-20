console.log("myinfo.js open")
// [1] 내정보 조회
const myinfo = () => {
	console.log("myinfomethod")
	fetch("/rental/member/myinfo")
		.then(r => r.json())
		.then(data => {
			if(data != null){
				document.querySelector(".midbox").value = data.mid;
				document.querySelector(".mpwdbox").value = data.mpwd;
				document.querySelector(".mphonebox").value = data.mphone;
				document.querySelector(".maddrbox").value = data.maddr;
				if(data.mgender == 1){
					document.querySelector(".mgenderbox").value = "남성";
								
				}else if(data.mgender ==2){
					document.querySelector(".mgenderbox").value = "여성";
				}
			}
		})
		.catch(e => {console.log(e)})
}
myinfo();

// [2] 회원탈퇴
const ondelete = () => {
	let result = confirm("정말 탈퇴하시겠습니까?")
	
	if(result == false){
		return;
	}  
	
	fetch("/rental/member/myinfo", {method : 'DELETE'})
		.then(r => r.json())
		.then(data =>{
			if(data == true){
				alert("회원탈퇴 성공")
				location.href = "/rental/signup/signup.jsp"
			}else{
				alert("회원탈퇴 실패")
			}
		})
		.catch(e => {console.log(e)})
}
