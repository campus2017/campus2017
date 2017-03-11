function head() {
    var record_body=document.getElementsByClassName('record-body')[0];
    var detail_body=document.getElementsByClassName('detail-body')[0];
    var change_body=document.getElementsByClassName('change-body')[0];
    var collection=document.getElementsByClassName('head');
    var ul=collection[0].childNodes[3];
    var change=ul.childNodes[5];//change
   // alert(change.getAttribute('class'));
    var detail=ul.childNodes[3];
    var record=ul.childNodes[1];
    change.onmouseover=function () {
        choose(this,detail,record,record_body,detail_body,change_body);
    };
    detail.onmouseover=function () {
       choose(this,change,record,record_body,detail_body,change_body);
    };
    record.onmouseover=function () {
        choose(this,change,detail,record_body,detail_body,change_body);
    };

}
function choose(obj,other1,other2,record_body,detail_body,change_body) {
    var classname=obj.getAttribute('class');

    if (classname=='change') {
        other1.setAttribute('class','detail')
        other2.setAttribute('class','record')
        record_body.style.display='none';
        detail_body.style.display='none';
        change_body.style.display='block';
    }
    if (classname=='detail') {
        other1.setAttribute('class','change');
        other2.setAttribute('class','record');
        record_body.style.display='none';
        detail_body.style.display='block';
        change_body.style.display='none';

    }
    if (classname=='record') {
        other1.setAttribute('class','change');
        other2.setAttribute('class','detail');
        record_body.style.display='block';
        detail_body.style.display='none';
        change_body.style.display='none';
    }

    obj.setAttribute('class','target');

}
