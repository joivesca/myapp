document.addEventListener("DOMContentLoaded", function() {
    const dropdownToggle = document.querySelector(".dropdown-toggle");
    const dropdownMenu = document.querySelector(".dropdown-menu");

    dropdownToggle.addEventListener("click", function(event) {
        event.stopPropagation(); // Evita que el clic cierre el menú
        dropdownMenu.style.display = (dropdownMenu.style.display === "block") ? "none" : "block";
    });

    document.addEventListener("click", function(event) {
        if (!dropdownToggle.contains(event.target) && !dropdownMenu.contains(event.target)) {
            dropdownMenu.style.display = "none"; // Solo cierra si el clic es fuera del dropdown
        }
    });
});