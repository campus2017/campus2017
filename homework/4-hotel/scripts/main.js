window.onload = function () {
    var productList = document.getElementsByClassName("product-item");
    [].map.call(productList, function (item) {
        item.addEventListener("click", function (e) {
            e.preventDefault();
            this.classList.toggle("onshow");
        })
    })
}
