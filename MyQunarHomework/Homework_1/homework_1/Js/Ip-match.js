function Ip_match(){
    var Targ_Ip=4;
    var temp='';
    var Tar='北上'+Targ_Ip;
var Arry=document.getElementsByClassName('address');
     temp=Arry[0].childNodes;
    for (var i=0;i<temp.length;i++) {
        if (temp[i].nodeType===1){
            var p=temp[i].childNodes[1];
            var span=p.childNodes[0];
            Curr_Ip=span.innerHTML;
           if(Curr_Ip==Tar) {
               temp[i].childNodes[1].setAttribute('class',null);
               temp[i].childNodes[1].setAttribute('class','addr-12-content');
               temp[i].childNodes[3].setAttribute('class',null);
               temp[i].childNodes[3].setAttribute('class','addr-12');
           }
        }
    }
}
