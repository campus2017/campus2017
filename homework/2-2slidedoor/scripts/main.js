window.onload = function () {
    var liEles = document.getElementsByClassName("slide-item"),
        liEleArr = [].slice.call(liEles);
    liEleArr.map(function (item) {
        item.style.width = "237.5px";
        item.addEventListener("mouseover", function () {
            var self = this;
            // 触发事件元素展开
            slideElement(this, 475, 5);
            // 找出当前展开的元素，缩小
            slideElement(liEleArr.filter(function (item) {
                if (item.classList.contains("onshow-item") && item !== self) {
                    item.classList.remove("onshow-item");
                    return true;
                }
            })[0], 237.5, 5);
            this.classList.add("onshow-item");
        })
    })
}

function slideElement(targetEle, finalWidth, interval) {
    if (!targetEle) return false;
    if (targetEle.movement) {
        clearTimeout(targetEle.movement);
    }

    var currentWidth = parseInt(targetEle.style.width);

    if (currentWidth == finalWidth) {
        return true;
    }
    if (currentWidth < finalWidth) {
        var dist = Math.ceil((finalWidth - currentWidth) / 10);
        currentWidth += dist;
    }
    if (currentWidth > finalWidth) {
        var dist = Math.ceil((currentWidth - finalWidth) / 10);
        currentWidth -= dist;
    }

    targetEle.style.width = currentWidth + "px";

    targetEle.movement = setTimeout(function () {
        slideElement(targetEle, finalWidth, interval)
    }, interval);
}
