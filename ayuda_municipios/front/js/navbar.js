document.getElementById(logout) .addEventListener('submit',e =>{
    e.preventDefault();
    localStorage.removeItem("token");
})
    
