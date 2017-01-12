function changeLayerState(flag) {
    var layer = document.getElementById("layer");
    var layerId = document.getElementById("layer-bg");
    if (flag === 'close') {
        layerId.style.display = "none";
        layer.style.display = "none";
    } else {
        layerId.style.display = "block";
        layer.style.display = "block";
    }
}
