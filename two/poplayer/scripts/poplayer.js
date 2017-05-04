window.onload = function(){
    var li = document.getElementById("nav").getElementsByTagName("li");
    li[0].onclick = function(e){
        overlay();
    }
    var buttonClose = document.getElementById("button-close");
    buttonClose.onclick = function(e){
        closeOverlay();
    }
}
function overlay(){
    var modal = document.getElementsByClassName("modal-overlay")[0];
    modal.style.visibility = "visible";
}
function closeOverlay(){
    var modal = document.getElementsByClassName("modal-overlay")[0];
    modal.style.visibility = "hidden";
}