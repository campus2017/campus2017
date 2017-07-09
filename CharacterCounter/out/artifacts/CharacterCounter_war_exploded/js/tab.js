var tab=document.getElementsByClassName('tab');
var forms=document.getElementsByClassName('inputData');
for(var i=0;i<tab.length;i++){
    (function(i){
        tab[i].addEventListener('click',function(event){
            var index=i;
            for(var j=0;j<forms.length;j++){
                forms[j].style.display='none';
            }
            console.log(index);
            forms[index].style.display='block';
        });
    })(i);
}
