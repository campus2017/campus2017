/**
 * Created by Haruko on 2017/2/25.
 */
function loadXMLDoc() {
    var xmlhttp;
    if (window.XMLHttpRequest) {    // code for IE7+, Firefox, Chrome, Opera, Safari
        xmlhttp=new XMLHttpRequest();
    }else{      // code for IE6, IE5
        xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
    }
    xmlhttp.onreadystatechange=function()
    {
        if (xmlhttp.readyState==4 && xmlhttp.status==200)
        {
            var data = JSON.parse(xmlhttp.responseText);
            addRecordHtml(data);
        }
    };
    xmlhttp.open("GET","data.json",true);
    xmlhttp.send();
}
function addRecordHtml(res) {
    var table = document.getElementById('record-table');
    for(var i = 0, len = res.length; i < len; i++){
        var temp  = document.createElement('tr');
        var data = res[i];
        temp.innerHTML = '<td>'+ data.time + '</td><td>' + data.name + '</td><td>' + data.num + '</td>' +
            '<td>' + data.eCoin + '</td><td>' + (data.status ? "兑换成功" : "兑换失败") + '</td><td>' + data.info + '</td>' +
            '<td><div class="btn">兑换信息</div></td><td></td>';
        table.appendChild(temp);
    }
}
loadXMLDoc();