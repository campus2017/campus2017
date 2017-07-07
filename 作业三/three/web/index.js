/**
 * Created by chao on 2017/7/3.
 */
/**
 * 顶部最大标签页切换
 * @type {NodeList}
 */
var lis = document.getElementById("tab").getElementsByTagName("li");
var contents = document.getElementById("content").children;
for (var i = 0; i < lis.length; i++) {
    tabChange(lis[i], i);
}
function tabChange(li, flag) {
    li.addEventListener("click", function () {
        for (var i = 0; i < lis.length; i++) {
            lis[i].className = "";
        }
        for (var i = 0; i < contents.length; i++) {
            contents[i].style.display = "none";

        }
        contents[flag].style.display = "block";
        this.className = "on";
    });
}


/**
 * 请求数据，请求条件获取
 * @type {string}
 */
var isSuccess = "all";
getData();
document.getElementById("all_filter").addEventListener("click", function () {
    isSuccess = "all";
    getData();
});
document.getElementById("success_filter").addEventListener("click", function () {
    isSuccess = "success";
    getData();
});
document.getElementById("fail_filter").addEventListener("click", function () {
    isSuccess = "fail";
    getData();
});
document.getElementById("search").addEventListener("click", function () {
    getData();
});
document.getElementById("reset").addEventListener("click", function () {
    document.getElementsByName("start_time")[0].value = "";
    document.getElementsByName("end_time")[0].value = "";
});


function getData() {
    var startTime = document.getElementsByName("start_time")[0].value;
    var endTime = document.getElementsByName("end_time")[0].value;

    var data = [];
    data.push("isSuccess=" + isSuccess);
    data.push("startTime=" + startTime);
    data.push("endTime=" + endTime);

    xhrRequest({
        method: "post",
        url: "/getData.json",
        data: data.join("&"),
        success: function (text) {
            try {
                var data = JSON.parse(text);
                tableRefresh(data);
            } catch (e) {
                console.log(e);
            }
        },
        fail: function (status, text, xhr) {
            alert(status);
        }
    });
}

function tableRefresh(data) {
    var tbody = document.getElementsByClassName("table")[0].getElementsByTagName("tbody")[0];
    tbody.innerHTML = "";
    var df = document.createDocumentFragment();
    for (var i in data) {
        var tr = document.createElement("tr");
        var flagArray = ["time", "name", "number", "cost", "state", "info"];
        for (var h  in flagArray) {
            var td = document.createElement("td");
            td.innerText = data[i][flagArray[h]];
            tr.appendChild(td);
        }
        var td = document.createElement("td");
        td.innerHTML = '<button class="button button_light">兑换信息</button>';
        tr.appendChild(td);
        df.appendChild(tr);
    }
    tbody.appendChild(df);
}
function xhrRequest(object) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState == 4) {
            if (xhr.status >= 200 && xhr.status <= 300 || xhr.status == 304) {
                if (object.success) {
                    object.success(xhr.responseText);
                }
            } else {
                if (object.fail) {
                    object.fail(xhr.status, xhr.responseText, xhr);
                }
            }
        }
    };
    object.method = object.method || "get";
    object.url = object.url || "/";
    object.data = object.data || null;
    xhr.open(object.method, object.url, true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send(object.data);
}
