function judgeStrOrObj(tag, desInput) {
    if (!desInput)return;
    if (typeof desInput == "object") {
        tag.appendChild(desInput);
    } else {
        tag.innerHTML = tag.innerHTML + desInput;
    }
    return tag;
}

function createTag(tagType, className, innerText1, innerText2) {
    var tag = document.createElement(tagType);
    tag.className = className;
    judgeStrOrObj(tag, innerText1);
    judgeStrOrObj(tag, innerText2);
    return tag;
}

function addDataIntoHtml(data) {
    var id = document.getElementById("detail-info"),
        infoList = data.info_list,
        l = infoList.length,
        tempStr = "",
        i;
    for (i = 0; i < l; i++) {
        tempStr = createTag("div", "detail-info");
        tempStr.appendChild(createTag("div", "time", createTag("div", "line-height29", infoList[i].date), createTag("div", "line-height29", infoList[i].time)));
        tempStr.appendChild(createTag("div", "trade-name", infoList[i].trade_name));
        tempStr.appendChild(createTag("div", "number", infoList[i].number));
        tempStr.appendChild(createTag("div", "e-pay", infoList[i].e_pay));
        tempStr.appendChild(createTag("div", "status", infoList[i].status));
        tempStr.appendChild(createTag("div", "info detail", createTag("span", "line-height29", infoList[i].info)));
        tempStr.appendChild(createTag("div", "exchange-info", createTag("span", "btn btn-blue", "兑换信息")));
        id.appendChild(tempStr);
    }
    var currentPage = document.getElementsByClassName("color-red")[0];
    var totalPage = document.getElementsByClassName("total-page")[0];
    currentPage.innerHTML = data.current_page;
    totalPage.innerHTML = data.total_page;
}

window.onload = function () {
    var url = "data.json";
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if ((xhr.status >= 200 && xhr.status < 300) || xhr.status == 304) {
                addDataIntoHtml(JSON.parse(xhr.responseText));
            } else {
                console.log("get data error");
            }
        }
    };
    xhr.open("get", url, true);
    xhr.send(null);
};