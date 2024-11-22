const url = 'http://localhost:8080/solicitudes';
const municipiosURL = 'http://localhost:8080/municipios';

const token = localStorage.getItem('token');

async function cargarSolicitudesIncompletas() {
    try {
        const response = await fetch(`${url}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            const error = await response.json();
            alert(`Error: ${error.detail || 'Error desconocido'}`);
            return;
        }
        const data = await response.json();
        const solicitudes = data.solicitudes;
        mostrarSolicitudes(solicitudes);
    } catch (error) {
        console.error('Error al cargar las solicitudes:', error);
    }
}

async function cargarSolicitudesCompletas() {
    try {
        const response = await fetch(`${url}/completadas`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            const error = await response.json();
            alert(`Error: ${error.detail || 'Error desconocido'}`);
            return;
        }
        const data = await response.json();
        const solicitudes = data.solicitudes;
        mostrarSolicitudes(solicitudes);
    } catch (error) {
        console.error('Error al cargar las solicitudes:', error);
    }
}

async function cargarSolicitudes() {
    try {
        const response = await fetch(`${url}/todas`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            const error = await response.json();
            alert(`Error: ${error.detail || 'Error desconocido'}`);
            return;
        }
        const data = await response.json();
        const solicitudes = data.solicitudes;
        mostrarSolicitudes(solicitudes);
    } catch (error) {
        console.error('Error al cargar las solicitudes:', error);
    }
}

async function cargarSolicitud(solicitudId) {
    try {
        const response = await fetch(`${url}/${solicitudId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            const error = await response.json();
            return;
        }

        const data = await response.json();
        const solicitud = data.solicitud;
        rellenarDatos(solicitud);

    } catch (error) {
        console.error('Error al cargar las solicitudes:', error);
    }
}

async function completarSolicitud(solicitudId) {
    const token = localStorage.getItem("token");

    if (token) {
    } else {
        window.location.href = "login.html";
    }
    try {
        const response = await fetch(`${url}/completar/${solicitudId}`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
        });

        if (!response.ok) {
            const error = await response.json();
            alert(`Solo los autores pueden completar las solicitudes`);
            return;
        }
        window.location.reload();


    } catch (error) {
        console.error('Error al cargar las solicitudes:', error);
    }
}

async function borrarSolicitud(solicitudId) {
    const token = localStorage.getItem("token");

    if (token) {
    } else {
        window.location.href = "login.html";
    }
    try {
        const response = await fetch(`${url}/${solicitudId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
        });

        if (!response.ok) {
            const error = await response.json();
            alert(`Solo los autores pueden eliminar las solicitudes`);
            return;
        }
        window.location.reload();


    } catch (error) {
        console.error('Error al cargar las solicitudes:', error);
    }
}

async function cargarMunicipios() {
    try {
        const response = await fetch(`${municipiosURL}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
            },
        });

        if (!response.ok) {
            const error = await response.json();
            alert(`Error: ${error.detail || 'Error desconocido'}`);
            return;
        }
        const data = await response.json();
        const municipios = data.municipios;
        rellenarNuevos(municipios);
    } catch (error) {
        console.error('Error al cargar los municipios:', error);
    }
}

function mostrarSolicitudes(solicitudes) {
    const solicitudesContainer = document.getElementById('contenedor-solicitudes');
    solicitudesContainer.innerHTML = " ";

    solicitudes.forEach(solicitud => {

        const tarjeta = document.createElement('div');
        tarjeta.classList.add("card", "m-1", "col-3");

        const tarjetaBody = document.createElement('div');
        tarjetaBody.classList.add("card-body");

        const dropdownContainer = document.createElement('div');
        dropdownContainer.classList.add('dropdown', 'text-end');

        const dropdownButton = document.createElement('button');
        dropdownButton.classList.add('btn', 'bg-blanco', 'dropdown-toggle');
        dropdownButton.setAttribute('type', 'button');
        dropdownButton.setAttribute('data-bs-toggle', 'dropdown');
        dropdownButton.setAttribute('aria-expanded', 'false');
        dropdownContainer.appendChild(dropdownButton);

        const dropdownMenu = document.createElement('ul');
        dropdownMenu.classList.add('dropdown-menu');

        const editarOption = document.createElement('li');
        const editarLink = document.createElement('a');
        editarLink.classList.add('dropdown-item');
        editarLink.textContent = 'Editar solicitud';
        editarLink.setAttribute('data-bs-toggle',"modal");
        editarLink.setAttribute("data-bs-target","#form-cambiar");
        editarLink.addEventListener('click', (e) => {
            cargarSolicitud(solicitud.id);
        });
        editarOption.appendChild(editarLink);

        const borrarOption = document.createElement('li');
        const borrarLink = document.createElement('a');
        borrarLink.classList.add('dropdown-item');
        borrarLink.textContent = 'Eliminar solicitud';
        borrarLink.addEventListener('click', (e) => {
            borrarSolicitud(solicitud.id);
        });
        borrarOption.appendChild(borrarLink);

        const titulo = document.createElement('h5');
        titulo.classList.add("card-title");
        titulo.textContent = `${solicitud.municipio.nombre}`;

        const subtitulo = document.createElement('h6');
        subtitulo.classList.add("card-subtitle", "mb-2");
        subtitulo.textContent = `${solicitud.municipio.provincia.nombre} - ${solicitud.fecha_publicacion}`;

        const texto = document.createElement('p');
        texto.classList.add("card-text");
        texto.innerHTML += `${solicitud.calle}<br>
        Ayuda: ${solicitud.ayuda}<br>
        Autor: ${solicitud.creador.username}<br>`;

        let prioridad = solicitud.prioridad;
        let completado = solicitud.completado;
        const completar = document.createElement('button');
        completar.setAttribute('type', 'button', '');
        completar.setAttribute('data-id', solicitud.id);
        completar.classList.add("btn", "btn-light", "completar", "col-12");
        completar.textContent = `Completar`;

        completar.addEventListener('click', (event) => {
            const solicitudId = event.target.getAttribute('data-id');
            completarSolicitud(solicitudId);
        });
        if (!completado) {


            switch (prioridad) {
                case "ALTA":
                    tarjeta.classList.add("text-bg-danger");
                    texto.innerHTML += `Prioridad ALTA `;
                    break;

                case "MEDIA":
                    tarjeta.classList.add("text-bg-naranja");
                    texto.innerHTML += `Prioridad MEDIA`;
                    break;

                case "BAJA":
                    tarjeta.classList.add("text-bg-amarillo");
                    texto.innerHTML += `Prioridad BAJA`;
                    break;
            }

        } else {
            tarjeta.classList.add("border-secondary");
            titulo.classList.add("text-secondary");
            subtitulo.classList.add("text-secondary");
            texto.classList.add("text-secondary");
            completar.textContent = `Completado`;
            completar.removeAttribute("data-id");
            completar.classList.remove("btn-light");
            completar.classList.add("btn-success");
            completar.disabled = true;

            switch (prioridad) {
                case "ALTA":
                    texto.innerHTML += `Prioridad ALTA `;
                    break;

                case "MEDIA":
                    texto.innerHTML += `Prioridad MEDIA`;
                    break;

                case "BAJA":
                    texto.innerHTML += `Prioridad BAJA`;
                    break;
            }
        }

        dropdownMenu.appendChild(editarOption);
        dropdownMenu.appendChild(borrarOption);
        dropdownContainer.appendChild(dropdownMenu);
        tarjetaBody.appendChild(dropdownContainer);
        tarjetaBody.appendChild(titulo);
        tarjetaBody.appendChild(subtitulo);
        tarjetaBody.appendChild(texto);
        tarjetaBody.appendChild(completar);
        tarjeta.appendChild(tarjetaBody);
        solicitudesContainer.appendChild(tarjeta);
    });
}

async function crearSolicitud() {

    const fechaActual = new Date();
    const dia = fechaActual.getDate();
    const mes = fechaActual.getMonth();
    const anyo = fechaActual.getFullYear();

    const fecha_publicacion = `${dia}-${mes}-${anyo}`;

    const ayuda = document.getElementById('nuevoAyuda').value;
    let municipio = document.getElementById('nuevoMunicipio').value;
    const calle = document.getElementById('nuevoCalle').value;
    const prioridad = document.getElementById('nuevoPrioridad').value;

    if (ayuda === "" || nuevoMunicipio === "" || calle === "" || prioridad === "") {
        alert(`Campos no rellenados'}`);
    } else {
        const token = localStorage.getItem("token");

        if (token) {
        } else {
            window.location.href = "login.html";
        }
        try {
            const response = await fetch(`${url}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json', 
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify({ fecha_publicacion, ayuda, municipio, calle, prioridad }),
            });

            if (!response.ok) {
                const error = await response.json();
                alert(`Error: ${error.detail || 'Error desconocido'}`);
                return;
            }
            window.location.href = "index.html";

        } catch (error) {
            console.error('Error al crear la solicitud:', error);
        }
    }
}

async function cambiarSolicitud(idsolicitud,fecha_publicacion,municipio,) {

    const ayuda = document.getElementById('cambioAyuda').value;
    const calle = document.getElementById('cambioCalle').value;
    const prioridad = document.getElementById('cambioPrioridad').value;

    if (ayuda === "" || calle === "" || prioridad === "") {
        alert(`Campos no rellenados`);
    } else {
        const token = localStorage.getItem("token");

        if (token) {
        } else {
            window.location.href = "login.html";
        }
        try {
            const response = await fetch(`${url}/${idsolicitud}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json', 
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify({ fecha_publicacion, ayuda, municipio, calle, prioridad }),
            });

            if (!response.ok) {
                const error = await response.json();
                alert(`Error: ${error.detail || 'Error desconocido'}`);
                return;
            }
            window.location.href = "index.html";

        } catch (error) {
            console.error('Error al cambiar:', error);
        }
    }
}


function rellenarNuevos(municipios) {
    const nuevoSelect = document.getElementById('nuevoMunicipio');

    municipios.forEach(municipio => {
        const opciones = document.createElement('option');
        opciones.value = municipio.id; 
        opciones.textContent = `${municipio.nombre} - ${municipio.provincia.nombre}`;

        nuevoSelect.appendChild(opciones);
    });
}

function rellenarDatos(solicitud) {
    const fechaActual = new Date();
    const dia = fechaActual.getDate();
    const mes = fechaActual.getMonth();
    const anyo = fechaActual.getFullYear();

    const fecha_publicacion = `${dia}-${mes}-${anyo}`;

    const ayuda = document.getElementById('cambioAyuda').value=solicitud.ayuda;
    const municipio = solicitud.municipio.id;
    const calle = document.getElementById('cambioCalle').value=solicitud.calle;
    const prioridad = document.getElementById('cambioPrioridad').value=solicitud.prioridad;

    document.getElementById('guardarDatos').addEventListener('click', (e) => {
        e.preventDefault();
        cambiarSolicitud(solicitud.id,fecha_publicacion,municipio);
    });
    
}

function limpiarDatos(){
    const ayuda = document.getElementById('cambioAyuda').value="";
    const calle = document.getElementById('cambioCalle').value=solicitud="";
    const prioridad = document.getElementById('cambioPrioridad').value="Prioridad";
}

window.onload = function () {
    cargarSolicitudesIncompletas();
    cargarMunicipios();
};

document.getElementById('todasSolicitudes').addEventListener('click', (e) => {
    e.preventDefault();
    cargarSolicitudes();
});

document.getElementById('solicitudNoCompletada').addEventListener('click', (e) => {
    e.preventDefault();
    cargarSolicitudesIncompletas();
});


document.getElementById('solicitudCompletada').addEventListener('click', (e) => {
    e.preventDefault();
    cargarSolicitudesCompletas();
});


document.getElementById('guardarNuevo').addEventListener('click', (e) => {
    e.preventDefault();
    crearSolicitud();
});

document.getElementById('eliminarDatos').addEventListener('click', (e) => {
    e.preventDefault();
    limpiarDatos();
});
