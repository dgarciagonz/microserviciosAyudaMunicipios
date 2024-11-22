document.getElementById('logout').addEventListener('click', e => {
    e.preventDefault();
    const token = localStorage.getItem('token');
    localStorage.removeItem(token);
    window.location.href="login.html";
})

