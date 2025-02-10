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
				document.querySelector(".mgenderbox").value = data.mgender;
			}
		})
		.catch(e => {console.log(e)})
}
myinfo();

// [2] 회원 정보 수정
const onupdate = () => {
	const mpwdin = document.querySelector(".mpwdbox");
	const mphonein = document.querySelector(".mphonebox");
	const maddrin = d
}
