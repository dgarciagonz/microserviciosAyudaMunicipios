const url = 'http://localhost:8080/auth';

if(localStorage.getItem("token")) {
    location.assign("index.html");
}

function setCookie(cname, cvalue, exdays) {
    const d = new Date();
    d.setTime(d.getTime() + (exdays*24*60*60*1000));
    let expires = "expires="+ d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/";
  }

document.getElementById('loginForm').addEventListener('submit', async (e) => {
    e.preventDefault();
    
    const email = document.getElementById('loginEmail').value;
    const password = document.getElementById('loginPassword').value;

    try {
        const response = await fetch(`${url}/login`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email, password }),
        });

        if (!response.ok) {
            const error = await response.json();
            alert(`Error en el login: ${error.detail || 'Error desconocido'}`);
            return;
        }

        const data = await response.json();
        setCookie('token', data.token, 1);
        alert('Inicio de sesión exitoso. Token guardado.');
    } catch (error) {
        console.error('Error en el login:', error);
        alert('Error al iniciar sesión.');
    }
});


