console.log( new URL( location.href).searchParams )
console.log( new URL( location.href).searchParams.get( 'sno' ) )
console.log(new URL(location.href).searchParams.get('rdate'))
const rentalRead = () => {
	const sno = new URL(location.href).searchParams.get('sno')
	let rdate = new URL(location.href).searchParams.get('rdate')
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
					html += ``;
				})
			 })
}