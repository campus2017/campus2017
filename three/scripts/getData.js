/* 
* @Author: lichunqiang
* @Date:   2017-06-12 20:27:19
* @Last Modified by:   anchen
* @Last Modified time: 2017-06-12 23:30:09
*/

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