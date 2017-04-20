window.onload = function () {
    var main = document.getElementsByClassName("main")[0];

    main.addEventListener("click", function () {
        var overlayEle = document.createElement("div"),
            layerEle = document.createElement("div"),
            layerCloseBtn = document.createElement("div");

        overlayEle.className = "overlay";
        overlayEle.style.height = document.documentElement.scrollHeight + "px";
        overlayEle.style.width = document.documentElement.scrollWidth + "px";
        document.body.appendChild(overlayEle);

        layerEle.className = "layer";
        layerCloseBtn.className = "layer-close";
        layerEle.appendChild(layerCloseBtn);
        document.body.appendChild(layerEle);

        layerEle.style.top = (document.documentElement.clientHeight - layerEle.offsetHeight) / 2 + 'px';
        layerEle.style.left = (document.documentElement.clientWidth - layerEle.offsetWidth) / 2 + 'px';

        layerCloseBtn.addEventListener("click", function () {
            document.body.removeChild(overlayEle);
            document.body.removeChild(layerEle);
        })
    })
}