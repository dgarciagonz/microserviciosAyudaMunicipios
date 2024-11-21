const url = 'http://localhost:8080/auth';

document.getElementById('registerForm').addEventListener('submit', async (e) => {
    e.preventDefault();

    const email = document.getElementById('registerEmail').value;
    const password = document.getElementById('registerPassword').value;
    const username = document.getElementById('registerUsername').value;

    try {
        const response = await fetch(`${url}/registro`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({ email, password, username }),
        });

        if (!response.ok) {
            const error = await response.json();
            alert(`Error en el registro: ${error.detail || 'Error desconocido'}`);
            return;
        }

        alert('Registro exitoso. Ahora puedes iniciar sesión.');
        window.location.href="login.html";

    } catch (error) {
        console.error('Error en el registro:', error);
        alert('Error al registrarse.');
    }
});