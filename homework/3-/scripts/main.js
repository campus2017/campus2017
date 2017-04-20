window.onload = function () {
    var xhr = new XMLHttpRequest(),
        tableEle = document.querySelector(".query-result>table");
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status >= 200 && xhr.status < 300 || xhr.status === 304) {
                dataHandler(xhr.responseText, tableEle);
            }
        }
    };
    xhr.open("get", "data.json", true);
    xhr.send(null);

    function dataHandler(data, parentEle) {
        data = JSON.parse(data);

        data.map(function (item, index) {
            var trEle = document.createElement("tr");
            trEle.className = "query-content";
            for (var key in item) {
                if (item.hasOwnProperty(key)) {
                    var tdEle = document.createElement("td");
                    tdEle.className = key;
                    tdEle.innerHTML = item[key];
                    trEle.appendChild(tdEle);
                }
            }
            var tdEle = document.createElement("td");
            tdEle.innerHTML = "<button>兑换信息</button>";
            trEle.appendChild(tdEle);
            trEle.appendChild(document.createElement("td"));
            parentEle.appendChild(trEle);
        });
    }
}
