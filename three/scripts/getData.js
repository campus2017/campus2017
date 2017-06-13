$(document).ready(function(){

    const getDataPromise = new function(){
        return new Promise(function(resolve,reject){
            $.get("data/data.json",function(str,status){
                let data =  $.parseJSON(str);
                console.log(data);
                if(status == "success"){
                    resolve();
                }else{
                    reject();
                }
            });
        });
    }
});