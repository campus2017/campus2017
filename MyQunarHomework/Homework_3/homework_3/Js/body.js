function record_body_head (obj) {
    var all=document.getElementsByClassName('all')[0];
    var success=document.getElementsByClassName('success')[0];
    var fail=document.getElementsByClassName('fail')[0];
     clear_bg(all);
     clear_bg(success);
     clear_bg(fail);
    obj.style.backgroundColor='#3775c0';
    obj.style.color='#fff';
}
function clear_bg(obj) {
    obj.style.backgroundColor='#fff';
    obj.style.color='#0c5fc4';
}
function reset_date() {

    var temp=document.getElementsByClassName('s');
    temp[0].value='';
     temp=document.getElementsByClassName('e');
    temp[0].value='';
    temp_all=document.getElementsByClassName('all');
    temp_success=document.getElementsByClassName('success');
    temp_fail=document.getElementsByClassName('fail');
    clear_bg(temp_all[0]);
    clear_bg(temp_success[0]);
    clear_bg(temp_fail[0]);
}
function search_for() {
    var all=document.getElementsByClassName('all')[0];
    var success=document.getElementsByClassName('success')[0];
    var fail=document.getElementsByClassName('fail')[0];
   var req=new XMLHttpRequest();
    var url='table.json';
    req.open('get',url);
    req.send();
    req.onreadystatechange=function () {
        if(req.readyState===4&&req.status===200){
            var jsondate=req.responseText;
            var json=JSON.parse(jsondate);
            if (getStyle(all,'background-color')=='rgb(55, 117, 192)') {
                clear_table();
                for (var i in json) {
                    construct(json[i]);
                }

            }
            if (getStyle(success,'background-color')=='rgb(55, 117, 192)') {
                clear_table();
                for (var i in json) {
                    if (json[i][4].indexOf('成功')>0) {
                        construct(json[i]);
                    }
                }

            }
            if (getStyle(fail,'background-color')=='rgb(55, 117, 192)') {
                clear_table();
                for (var i in json) {
                    if (json[i][4].indexOf('失败')>0) {
                        construct(json[i]);
                    }
                }

            }
        }

    }

}
function construct(obj) {
    var table=document.getElementById('table_body');
    var tr=document.createElement('tr');
    tr.setAttribute('class','con');
    var div=document.createElement('div');
    div.setAttribute('class','exinformation');
    var a=document.createElement('a');
    a.setAttribute('href','#');
    var td1=document.createElement('td');
    var td2=document.createElement('td');
    var td3=document.createElement('td');
    var td4=document.createElement('td');
    var td5=document.createElement('td');
    var td6=document.createElement('td');
    td6.setAttribute('class','cont');
    var td7=document.createElement('td');
    var td8=document.createElement('td');

    td1.innerHTML=obj[0].date+'<br/>'+obj[0].date_time;

    td2.innerHTML=obj[1];
    td3.innerHTML=obj[2];
    td4.innerHTML=obj[3];
    td5.innerHTML=obj[4];
    td6.innerHTML=obj[5];
    a.innerHTML=obj[6];
    div.appendChild(a);
    td7.appendChild(div);
    td8.innerHTML=obj[7];
    tr.appendChild(td1);
    tr.appendChild(td2);
    tr.appendChild(td3);
    tr.appendChild(td4);
    tr.appendChild(td5);
    tr.appendChild(td6);
    tr.appendChild(td7);
    tr.appendChild(td8);
    table.appendChild(tr);

}
function getStyle(obj,name){
    if (obj.currentStyle)
        return obj.currentStyle[name];
    else
        return getComputedStyle(obj,false)[name];

}
function clear_table() {
    var tb = document.getElementById('table_body');
    var rowNum=tb.rows.length;
    for (i=0;i<rowNum;i++)
    {
        tb.deleteRow(i);
        rowNum=rowNum-1;
        i=i-1;
    }

}