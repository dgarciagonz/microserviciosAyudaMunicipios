
const loginout=document.getElementById('loginout');
const token = localStorage.getItem('token');
if (token) {
    loginout.textContent = "Cerrar Sesión";
    loginout.addEventListener('click', e => {
        e.preventDefault();
        const token = localStorage.getItem('token');
        localStorage.removeItem(token);
        window.location.href="login.html";
    })
}else{
    loginout.textContent = "Iniciar Sesión";
    loginout.addEventListener('click', e => {
        e.preventDefault();
        window.location.href="login.html";
    })
}




