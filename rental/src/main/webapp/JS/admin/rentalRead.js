console.log( new URL( location.href).searchParams )
console.log( new URL( location.href).searchParams.get( 'sno' ) )
const rentalRead = () => {
	const sno = new URL(location.href).searchParams.get('sno')
	let page = new URL(location.href).searchParams.get('page')
	if(page == null) page = 1;
	
	const option = {method : 'GET'}
	fetch(`/rental/admin/rental?sno=${sno}&page=${page}`, option)
		.then(r => r.json())
		.then(response => {console.log(response);
				const rentalbox = document.querySelector('.rentalbox')
				let html = ``;
				
				let rentalBox = response.data;
				rentalBox.forEach((rental) => {
					if(rental.rstate == 1){
						html += `<tr>
									<td> ${ rental.rno } </td>
							      <td> ${ rental.rdate } </td>
									<td> ${rental.rtime} </td>
					              <td> ${ rental.rprice} </td>
								<td> ${ rental.rcount } </td>
								<td> <button class="btn btn-danger" disabled>예약마감</button> </td>
								<td> ${ rental.mid } </td>
								</tr>`;
					}
					else{
						html += `<tr>
								<td> ${ rental.rno } </td>
							     <td> ${ rental.rdate } </td>
						         <td> ${rental.rtime} </td>
						         <td> ${ rental.rprice} </td>
						         <td> ${ rental.rcount } </td>
					             <td> <button class="btn btn-primary" disabled>예약가능</button> </td>
								<td> 예약한회원없음 </td>
								</tr>`;					
					}
				})
				rentalbox.innerHTML = html;
				getPageBtn(response, sno);
			 })
			 .catch(e => {console.log(e);})
}
rentalRead();

const getPageBtn = (response, sno) =>{
	page = parseInt(response.page);
	const pagebtnbox = document.querySelector('.pagebtnbox');
	let html = ``;
	html += `	<li class="page-item">
	            <a class="page-link" href="rentalRead.jsp?sno=${ sno }&page=${ page <= 1 ? 1 : page-1 }">이전</a>
	             </li>`;
	for( let index = response.startbtn ; index <= response.endbtn ; index++ ){
		html += `<li class="page-item">
				<a class="page-link ${ page == index ? 'active' : '' }" href="rentalRead.jsp?sno=${sno}&page=${ index }">
                   ${ index }
				      </a>
				</li>`
	}
	html +=`<li class="page-item">
	        <a class="page-link" href="rentalRead.jsp?sno=${ sno }&page=${ page >= response.totalpage ? page : page+1  }">다음</a>
	        </li>`
	pagebtnbox.innerHTML = html;		
}