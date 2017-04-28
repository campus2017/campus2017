/**
 * Created by DW on 2016/12/26.
 */
$(document).ready(function(){

    //页面刷新时根据之前单选框的值决定显示内容
    var type = $('input[name="textType"]:checked').val();
    //刷新时清空文件域
    clearFile();
    displayType(type);
    //单选按钮改变时触发
    $('input:radio[name="textType"]').change(function(){
        var type = $('input[name="textType"]:checked').val();
        displayType(type);
        //切换单选按钮时清空统计结果
        clearResult();
    });
    //上传文件按钮旁显示文件名
    $(".upload").on("change","input[type='file']",function(){
        var filePath=$(this).val();
        var extStart = filePath.lastIndexOf(".");
        var ext = filePath.substring(extStart, filePath.length).toUpperCase();
        if(ext==".TXT"){
            var arr=filePath.split('\\');
            var fileName=arr[arr.length-1];
            $(".filename").html(fileName);
        }
        else{
            alert("请上传txt类型文本文件！");
            return false
        }
    })
});


//根据单选按钮决定显示内容
function displayType(type) {
    if(type==0){
        $(".inputType").hide();
        $(".uploadType").show();
    }
    else{
        $(".uploadType").hide();
        $(".inputType").show();
    }
}

//table中显示统计项的个数
function result_num(eng,num,chn,pun) {
    $("#eng").html(eng);
    $("#num").html(num);
    $("#chn").html(chn);
    $("#pun").html(pun);
}
//table中显示出现频率最高的三个字符及其次数
function result_freq(first,firstNum,second,secondNum,third,thirdNum) {
    $("#first").html(first);
    $("#firstNum").html(firstNum);
    $("#second").html(second);
    $("#secondNum").html(secondNum);
    $("#third").html(third);
    $("#thirdNum").html(thirdNum);
}

//清空统计表单
function clearResult() {
    result_freq("","","","","","");
    result_num("","","","");
}
//清空文件域
function clearFile() {
    $("#file").val("");
    $(".filename").html("");
}