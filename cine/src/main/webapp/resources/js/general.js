$( document ).ready(function() {
    $pathname = document.location.pathname;
    $("title").append($pathname.split('/')[2] + " - " + $pathname.split('/')[3]);//setea el titulo de la pagina
});