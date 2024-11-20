const url = 'http://localhost:8080/solicitudes';

async function cargarSolicitudes() {
    try {
        const response = await fetch(`${url}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            const error = await response.json();
            alert(`Error en el login: ${error.detail || 'Error desconocido'}`);
            return;
        }
        const data = await response.json();  // Respuesta completa
        const solicitudes = data.solicitudes;  
        mostrarSolicitudes(solicitudes);
    } catch (error) {
        console.error('Error al cargar las solicitudes:', error);
    }
}

function mostrarSolicitudes(solicitudes) {
    const solicitudesContainer = document.getElementById('contenedor-solicitudes');
    solicitudesContainer.innerHTML = " ";

    solicitudes.forEach(solicitud => {

        let prioridad = solicitud.prioridad;

        const tarjeta = document.createElement('div');

        switch (prioridad) {
            case "ALTO":
                tarjeta.classList.add("text-bg-danger");
                break;

            case "MEDIO":
                tarjeta.classList.add("text-bg-secondary");
                break;

            case "BAJO":
                tarjeta.classList.add("text-bg-danger");
                break;
        }
        tarjeta.classList.add("card");

        const tarjetaBody = document.createElement('div');
        tarjetaBody.classList.add("card-body");

        const titulo = document.createElement('h5');
        titulo.classList.add("card-title");
        titulo.textContent = `${solicitud.municipio.nombre}`;

        const subtitulo = document.createElement('h6');
        subtitulo.classList.add("card-subtitle", "mb-2", "text-body-secondary");
        subtitulo.textContent = `${solicitud.municipio.provincia.nombre} - ${solicitud.fecha_publicacion}`;

        const texto = document.createElement('p');
        texto.classList.add("card-text");
        texto.textContent = `Calle: ${solicitud.calle}
        Ayuda: ${solicitud.ayuda}\n
        Autor: ${solicitud.creador.username}\n`;

        let completado = solicitud.completado;
        if (completado) {
            texto.textContent += `Completado`
        } else {
            texto.textContent += `No completado`
        }

        tarjetaBody.appendChild(titulo);
        tarjetaBody.appendChild(subtitulo);
        tarjetaBody.appendChild(texto);
        tarjeta.appendChild(tarjetaBody);
        solicitudesContainer.appendChild(tarjeta);
    });
}

window.onload = function () {
    cargarSolicitudes();
};

