fetch('/admin/dropdown')
    .then(response => response.json())
    .then(data => {
        const dropdown = document.getElementById('storeDropdown');
        dropdown.innerHTML = ''; 
        data.forEach(store => {
            const li = document.createElement('li');
            li.innerHTML = `<li><a class="dropdown-item" href="/rental/admin/total.jsp?sno=${store.sno}" >${store.sname}</a></li>`;
            dropdown.appendChild(li);
        });
    })
    .catch(error => console.error('Error fetching store data:', error));