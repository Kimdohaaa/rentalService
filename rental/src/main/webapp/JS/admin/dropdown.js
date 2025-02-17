const dropdown = () => {
  const option = { method: 'GET' };
  fetch('/rental/admin/dropdown', option)
    .then((r) => r.json())
    .then((data) => {
      let dropdownMenu = document.querySelector('#storeDropdown'); // 수정된 부분
      let html = ``;
      data.forEach((store) => {
        html += `<li><a class="dropdown-item" href="/rental/admin/rentalRead.jsp?sno=${store.sno}">${store.sname}(${store.sno}호점)</a></li>`;
      });
      dropdownMenu.innerHTML = html; // 올바른 요소에 내용 삽입
    })
    .catch((e) => {
      console.log(e);
    });
};

dropdown();
