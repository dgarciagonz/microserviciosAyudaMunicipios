

document.getElementById(logout).addEventListener('submit', e => {
    e.preventDefault();
    document.cookie = "token=; expires=Thu, 01 Jan 1970 00:00:00 GMT; path=/";
})

