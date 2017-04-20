window.onload = function () {
    function timelineHandler() {
        var current = Date.now(),
            timeNode = [],
            timeNodeEle = document.querySelectorAll(".timeline>li>h2");
        // 取得时间并转换成毫秒数，存入timeNode
        [].slice.call(timeNodeEle).map(function (item, index, array) {
            var tempTimeStr = item.innerHTML.replace(/\./g, "/");
            timeNode[index] = new Date(tempTimeStr.slice(-5) + "/" + tempTimeStr.slice(0, 4)).getTime();
        })

        timeNode.some(function (item, index, array) {
            if (current < array[0] || current > item && current < array[index + 1] || typeof array[index + 1] === "undefined") {
                timeNodeEle[index].parentNode.classList.add("onTimeNode");
                return true;
            } else {
                timeNodeEle[index].parentNode.classList.add("offTimeNode");
            }
        })
    }
    function tabCityHandler() {
        var userCity = remote_ip_info["city"],
            cityList = document.querySelectorAll(".location-city>h4");

        [].slice.call(cityList).some(function (item) {
            if (userCity === item.innerHTML) {
                item.parentNode.classList.add("highlight-city");
                return true;
            }
        })

        document.addEventListener("keydown", function (e) {
            if (e.keyCode === 9) {
                e.preventDefault();
                var highlightCity = document.getElementsByClassName("highlight-city")[0];
                if (highlightCity.nextElementSibling) {
                    highlightCity.nextElementSibling.classList.add("highlight-city");
                } else {
                    highlightCity.parentNode.firstElementChild.classList.add("highlight-city");
                }

                highlightCity.classList.remove("highlight-city");
            }
        });
    }
    timelineHandler();
    tabCityHandler();
}
