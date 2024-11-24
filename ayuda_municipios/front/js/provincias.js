const provinciasURL = 'http://localhost:8080/provincias';

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
        mostrarProvincias(provincias);
    } catch (error) {
        console.error('Error al cargar las provincias:', error);
    }
}

async function cargarProvincia(idProvincia) {
    try {
        const response = await fetch(`${provinciasURL}/${idProvincia}`, {
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
        const provincia = data.provincia;
        rellenarDatos(provincia);

    } catch (error) {
        console.error('Error al cargar las provincias:', error);
    }
}

async function borrarProvincia(idProvincia) {
    const token = localStorage.getItem("token");

    if (token) {
    } else {
        window.location.href = "login.html";
    }
    try {
        const response = await fetch(`${provinciasURL}/${idProvincia}`, {
            method: 'DELETE',
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
        window.location.reload();


    } catch (error) {
        console.error('Error al eliminar la provincia:', error);
    }
}

async function editarProvincia(idProvincia) {

    const nombre = document.getElementById('cambiarProvincia').value;

    if (nombre === "") {
        alert(`Campos no rellenados`);
    } else {
        const token = localStorage.getItem("token");

        if (token) {
        } else {
            window.location.href = "login.html";
        }
        try {
            const response = await fetch(`${provinciasURL}/${idProvincia}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json', 
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify({ nombre }),
            });

            if (!response.ok) {
                const error = await response.json();
                alert(`Error: ${error.detail || 'Error desconocido'}`);
                return;
            }
            window.location.href = "provincias.html";

        } catch (error) {
            console.error('Error al cambiar:', error);
        }
    }
}

async function crearProvincia() {

    const nombre = document.getElementById('nombreProvincia').value;

    if (nombre === "") {
        alert(`Campos no rellenados'}`);
    } else {
        const token = localStorage.getItem("token");

        if (token) {
        } else {
            window.location.href = "login.html";
        }
        try {
            const response = await fetch(`${provinciasURL}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json', 
                    'Authorization': `Bearer ${token}`,
                },
                body: JSON.stringify({nombre }),
            });

            if (!response.ok) {
                const error = await response.json();
                alert(`Error: ${error.detail || 'Error desconocido'}`);
                return;
            }
            window.location.href = "provincias.html";

        } catch (error) {
            console.error('Error al crear las provincia:', error);
        }
    }
}

function mostrarProvincias(provinncias){
    const tbody = document.getElementById('tabla-datos');

    provinncias.forEach(provincia => {

        const tr = document.createElement('tr');

        const tdNombre = document.createElement('td');
        tdNombre.textContent = provincia.nombre;

        const  tdAcciones = document.createElement('td');

        const editarButon = document.createElement('button');
        editarButon.setAttribute('type', 'button');
        editarButon.classList.add("btn", "btn-secondary","m-1");
        editarButon.setAttribute('data-id', provincia.id);
        editarButon.setAttribute('data-bs-toggle',"modal");
        editarButon.setAttribute("data-bs-target","#form-cambiar");
        editarButon.textContent = "Editar";
        editarButon.addEventListener('click', (e) => {
            const provinciaId = e.target.getAttribute('data-id');
            cargarProvincia(provinciaId);
        });
        
        const borrarbuton = document.createElement('button');
        borrarbuton.setAttribute('type', 'button');
        borrarbuton.classList.add("btn", "btn-danger","m-1");
        borrarbuton.setAttribute('data-id', provincia.id);
        borrarbuton.textContent = "Borrar";
        borrarbuton.addEventListener('click', (e) => {
            const provinciaId = e.target.getAttribute('data-id');
            borrarProvincia(provinciaId);
        });

        
        tdAcciones.appendChild(editarButon);
        tdAcciones.appendChild(borrarbuton);
        tr.appendChild(tdNombre);
        tr.appendChild(tdAcciones);
        tbody.appendChild(tr)
        
    });
}

function rellenarDatos(provincia) {
    formCrear.style.visibility="hidden";
    formCambiar.style.visibility="visible";

    const nombre = document.getElementById('cambiarProvincia').value=provincia.nombre;

    document.getElementById('cambiarDatos').addEventListener('click', (e) => {
        e.preventDefault();
        editarProvincia(provincia.id);
    });    
}

window.onload = function () {
    cargarProvincias();
    formCambiar.style.visibility="hidden";

}

document.getElementById("cancelarCambio").addEventListener('click', (e)=>{
    e.preventDefault();
    formCambiar.style.visibility="hidden";
    formCrear.style.visibility="visible";
}
)

document.getElementById("guardarDatos").addEventListener('click', (e)=>{
    e.preventDefault();
    crearProvincia();
}
)


const formCrear = document.getElementById('crearForm');
const formCambiar = document.getElementById('cambiarForm');