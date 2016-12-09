
document.getElementById("aim").onclick = function(){
    dialog();    
}

function dialog(){
    var dialog = document.getElementById("dialog");
    dialog.style.display = "flex";
    document.getElementById("close").onclick = function(){
        dialog.style.display = "none";
    }
}