// 가맹점 조회
const findStore = () => {
	
	let storebox = document.querySelector(".storebox");
	let html = ``;
	
	fetch("/rental/member/store")
		.then(r => r.json())
		.then(data => {
			data.forEach((s) => {
				
				html += `<div class="col mb-5 store">
				                        
						<div class="card h-100">
					             <!-- Product image-->
					         <img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg"/>
					         <!-- Product details-->
					         <div class="card-body p-4">
					         	<div class="text-center">
					         		<h3>${s.sname}</h3>
								   	<div class="fw-bolder">${s.saddr}</div>
					                <!-- Product price-->
					                    10,000~
					            </div>
					         </div>
					         <!-- Product actions-->
					         <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
					         	<div class="text-center">
									<a class="btn btn-outline-dark mt-auto" href="calendar.jsp?sno=${s.sno}">
										예약
									</a>
								</div>
					         </div>
						 </div>
						 </div>`
				         			
			})
			storebox.innerHTML = html;
		})
		.catch(e => {console.log(e)})
}
findStore()
