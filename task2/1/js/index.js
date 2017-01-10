function changeLayerState(flag) {
    var layerId = document.getElementById("layer-bg");
    if (flag === 'close') {
        layerId.style.display = "none";
    } else {
        layerId.style.display = "block";
    }
}
