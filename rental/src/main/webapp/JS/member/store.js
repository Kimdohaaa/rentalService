// 가맹점 조회
const findStore = () => {
	
	let storebox = document.querySelector(".storebox");
	let html = ``;
	
	fetch("/rental/member/store")
		.then(r => r.json())
		.then(data => {
			data.forEach((s) => {
				html += `<img class="card-img-top" src="https://dummyimage.com/450x300/dee2e6/6c757d.jpg" alt="..." />
				                          
						<h5 class="fw-bolder">${s.sname}</h5>
				                                    
				                                    
				          <div class="d-flex justify-content-center small text-warning mb-2">
				              <div class="bi-star-fill"></div>
				              <div class="bi-star-fill"></div>
				              <div class="bi-star-fill"></div>
				              <div class="bi-star-fill"></div>
				              <div class="bi-star-fill"></div>
				           </div>
				                                    
				           <span>${s.saddr}</span>
						<div class="text-center">
															<a class="btn btn-outline-dark mt-auto" href="calendar.jsp?sno=${s.sno}">
																예약하기</a>
															</div>
													                        `			
			})
			storebox.innerHTML = html;
		})
		.catch(e => {console.log(e)})
}
findStore()
