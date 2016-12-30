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
//清空输入框
function clearContent() {
    $("#text").val("");
}
//统计上传文件
function countUpload() {
    if($("#file").val()==""){
        alert("请选择待上传文件！");
        return false;
    }
    var formData = new FormData($("#upload")[0]);
    $.ajax({
        url: 'count/upload.do',
        type: 'post',
        data: formData,
        // 不处理发送的数据
        processData: false,
        // 不设置Content-Type请求头
        contentType: false,
        beforeSend:function(){
            console.log("正在进行，请稍候");
        },
        success: function(data) {
            showResult(data);
        },
        error : function() {
            alert("上传失败！");
        }
    });
}
//统计输入文本
function countInput() {
    var text = $("#text").val();
    //文本为空时显示0
    if(text.trim()==""){
        result_num(0,0,0,0);
        result_freq("","","","","","");
    }
    //文本不为空，向后台提交数据处理
    else{
        $.ajax({
            url:'count/input.do',
            data:{
                text:text
            },
            type:'post',
            cache:false,
            dataType:'json',
            success:function(data) {
                showResult(data);
            }
        });
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
//显示统计结果
function showResult(data) {
    if(data==""){
        alert("输入有误！");
        return;
    }
    result_freq("","","","","","");
    result_num(data.eng,data.num,data.chn,data.pun);
    //分3种情况：文本仅包含一种字符、两种字符、3种以上字符
    var len = data.ch_list.size;
    if(len<=0) return;
    else if(len==1){
        result_freq(data.ch_list[0],data.val_list[0],"","","","");
    }
    else if(len==2){
        result_freq(data.ch_list[0],data.val_list[0],data.ch_list[1],data.val_list[1],"","");
    }
    else{
        result_freq(data.ch_list[0],data.val_list[0],data.ch_list[1],data.val_list[1],data.ch_list[2],data.val_list[2]);
    }
}
//清空上传文件处选择的文件
function clearFileInput(file){
    if (file.outerHTML) {  // for IE, Opera, Safari, Chrome
        file.outerHTML = file.outerHTML;
    } else { // FF(包括3.5)
        file.value = "";
    }
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