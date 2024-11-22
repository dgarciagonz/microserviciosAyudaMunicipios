const url = 'http://localhost:8080/solicitudes';
const municipiosURL = 'http://localhost:8080/municipios';
const provinciasURL = 'http://localhost:8080/provincias';

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
        mostrarMunicipios(municipios);
    } catch (error) {
        console.error('Error al cargar los municipios:', error);
    }
}

async function cargarProvincias() {
    try {
        const response = await fetch(`${provinciasURL}`, {
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
        const provincias = data.provincias;
        rellenarNuevos(provincias);
    } catch (error) {
        console.error('Error al cargar los municipios:', error);
    }
}

async function cargarMunicipio(idmunicipio) {
    try {
        const response = await fetch(`${municipiosURL}/${idmunicipio}`, {
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
        const municipio = data.municipio;
        rellenarDatos(municipio);

    } catch (error) {
        console.error('Error al cargar las solicitudes:', error);
    }
}

async function cargarSolicitudesPorMunicipio(municipioId) {
    const token = localStorage.getItem("token");

    if (token) {
    } else {
        window.location.href = "login.html";
    }
    try {
        const response = await fetch(`${url}/municipio/${municipioId}`, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
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

async function borrarMunicipio(municipioIdId) {
    const token = localStorage.getItem("token");

    if (token) {
    } else {
        window.location.href = "login.html";
    }
    try {
        const response = await fetch(`${municipiosURL}/${municipioIdId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
        });

        if (!response.ok) {
            const error = await response.json();
            alert(`Inicia sesión para hacer esta acción`);
            return;
        }
        window.location.reload();


    } catch (error) {
        console.error('Error al cargar las solicitudes:', error);
    }
}

async function crearMunicipio() {

    const nombre = document.getElementById('nuevoNombre').value;
    const cp = Number(document.getElementById('nuevoCP').value);
    const provincia = document.getElementById('nuevoProvincia').value;

    if (nombre === "" || cp === "" || provincia === "") {
        alert(`Campos no rellenados'}`);
    } else {
        const token = localStorage.getItem("token");

        if (token) {
        } else {
            window.location.href = "login.html";
        }
        try {
            const response = await fetch(`${municipiosURL}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json', 
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify({nombre,cp,provincia }),
            });

            if (!response.ok) {
                const error = await response.json();
                alert(`Error: ${error.detail || 'Error desconocido'}`);
                return;
            }
            window.location.href = "municipios.html";

        } catch (error) {
            console.error('Error al crear la solicitud:', error);
        }
    }
}

function mostrarSolicitudes(solicitudes){
    const nombreMunicipio = document.getElementById('solicitud-municipio');
    nombreMunicipio.classList.add('fw-bold');
    nombreMunicipio.textContent = solicitudes[0].municipio.nombre;

    const tbody = document.getElementById('tabla-solicitudes');
    while (tbody.firstChild) {
        tbody.removeChild(tbody.lastChild);
      }

    solicitudes.forEach(solicitud => {
        const tr = document.createElement('tr');

        const tdFecha = document.createElement('td');
        tdFecha.textContent = solicitud.fecha_publicacion;

        const tdAyuda = document.createElement('td');
        tdAyuda.textContent = solicitud.ayuda;

        const tdCalle = document.createElement('td');
        tdCalle.textContent = solicitud.calle;

        const tdPrioridad = document.createElement('td');
        tdPrioridad.textContent = solicitud.prioridad;

        const tdCompletado = document.createElement('td');
        tdCompletado.textContent = (solicitud.completado ? "Completado":"No completado");

        tr.appendChild(tdFecha);
        tr.appendChild(tdAyuda);
        tr.appendChild(tdCalle);
        tr.appendChild(tdPrioridad);
        tr.appendChild(tdCompletado);
        tbody.appendChild(tr)
    });

}

function mostrarMunicipios(municipios){
    const tbody = document.getElementById('tabla-datos');

    municipios.forEach(municipio => {

        const tr = document.createElement('tr');

        const tdNombre = document.createElement('td');
        tdNombre.textContent = municipio.nombre;

        const tdPostal = document.createElement('td');
        tdPostal.textContent = municipio.cp;

        const tdProvincia = document.createElement('td');
        tdProvincia.textContent = municipio.provincia.nombre;

        const  tdAcciones = document.createElement('td');
        
        const solicitudesButon = document.createElement('button');
        solicitudesButon.setAttribute('type', 'button');
        solicitudesButon.setAttribute('data-bs-toggle', "modal");
        solicitudesButon.setAttribute('data-id', municipio.id);
        solicitudesButon.setAttribute('data-bs-target', "#form-solicitudes");
        solicitudesButon.classList.add("btn", "btn-dark","m-1");
        solicitudesButon.textContent = "Solicitudes";
        solicitudesButon.addEventListener('click', (e) => {
            const municipioId = e.target.getAttribute('data-id');
            cargarSolicitudesPorMunicipio(municipioId);
        });

        const editarButon = document.createElement('button');
        editarButon.setAttribute('type', 'button');
        editarButon.classList.add("btn", "btn-secondary","m-1");
        editarButon.setAttribute('data-id', municipio.id);
        editarButon.setAttribute('data-bs-toggle',"modal");
        editarButon.setAttribute("data-bs-target","#form-cambiar");
        editarButon.textContent = "Editar";
        editarButon.addEventListener('click', (e) => {
            const municipioId = e.target.getAttribute('data-id');
            cargarMunicipio(municipioId);
        });

       
        
        const borrarbuton = document.createElement('button');
        borrarbuton.setAttribute('type', 'button');
        borrarbuton.classList.add("btn", "btn-danger","m-1");
        borrarbuton.setAttribute('data-id', municipio.id);
        borrarbuton.textContent = "Borrar";
        borrarbuton.addEventListener('click', (e) => {
            const municipioId = e.target.getAttribute('data-id');
            borrarMunicipio(municipioId);
        });

        
        tdAcciones.appendChild(solicitudesButon);
        tdAcciones.appendChild(editarButon);
        tdAcciones.appendChild(borrarbuton);
        tr.appendChild(tdNombre);
        tr.appendChild(tdPostal);
        tr.appendChild(tdProvincia);
        tr.appendChild(tdAcciones);
        tbody.appendChild(tr)
        
    });
}

async function editarMunicipio(idmunicipio,provincia) {

    const nombre = document.getElementById('cambioNombre').value;
    const cp = Number(document.getElementById('cambioCP').value);

    if (nombre === "" || cp === "" ) {
        alert(`Campos no rellenados`);
    } else {
        const token = localStorage.getItem("token");

        if (token) {
        } else {
            window.location.href = "login.html";
        }
        try {
            const response = await fetch(`${municipiosURL}/${idmunicipio}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json', 
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify({ nombre,cp,provincia }),
            });

            if (!response.ok) {
                const error = await response.json();
                alert(`Error: ${error.detail || 'Error desconocido'}`);
                return;
            }
            window.location.href = "municipios.html";

        } catch (error) {
            console.error('Error al cambiar:', error);
        }
    }
}

function rellenarNuevos(provincias) {
    const nuevoSelect = document.getElementById('nuevoProvincia');

    provincias.forEach(provincia => {
        const opciones = document.createElement('option');
        opciones.value = provincia.id; 
        opciones.textContent = `${provincia.nombre}`;

        nuevoSelect.appendChild(opciones);
    });
}

function rellenarDatos(municipio) {

    const nombre = document.getElementById('cambioNombre').value=municipio.nombre;
    const cp = document.getElementById('cambioCP').value=municipio.cp;
    

    document.getElementById('guardarDatos').addEventListener('click', (e) => {
        e.preventDefault();
        editarMunicipio(municipio.id,municipio.provincia.id);
    });
    
}


window.onload = function () {
    cargarMunicipios();
    cargarProvincias();
}

document.getElementById('guardarNuevo').addEventListener('click', (e) => {
    e.preventDefault();
    crearMunicipio();
});