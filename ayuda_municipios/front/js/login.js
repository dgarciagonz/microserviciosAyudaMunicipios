const url = 'http://localhost:8080/auth';



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

        localStorage.setItem('token', data.accessToken);
        const token = localStorage.getItem('token');

        if (token){
            window.location.href="index.html";
        }else{
            alert('Error al iniciar sesión.');
        }

    } catch (error) {
        console.error('Error en el login:', error);
        alert('Error al iniciar sesión.');
    }
});


