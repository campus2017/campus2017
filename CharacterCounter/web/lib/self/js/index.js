/**
 * Created by Administrator on 2017/2/10.
 */
$(function () {
    //page init
    $("#container").height($(window).height() - 40);
    $("#divTextArea").width($("#container").width() - 180);
    $(window).resize(function (e) {
        $("#container").height($(window).height() - 40);
        $("#divTextArea").width($("#container").width() - 180);
        e.stopPropagation();
    });

    //radio init
    radioChange();

    //radio click
    $("input[name=optionsRadiosinline]").click(function(){
        radioChange();
    });

    //button click
    $("#fileUpload").click(function(){
        $('input[id=lefile]').click();
    });
    $("#fileCount").click(function(){
        var text = $("#filePath").html();
        if(text != '' && text != undefined) {
            //post request
            $('#fileForm').submit();
        }
        else{
            alert('请先选择上传文件');
            showData('');
        }
    });
    $("#textCount").click(function(){
        var text = $("#textArea").val();

        if(text != '' && text != undefined){
            //ajax request
            $.ajax({
                url: "/text?value="+text,
                type: "GET",
                dataType: "json",
                contentType:'application/json;charset=UTF-8',
                success: function(data) {
                    if(data == null || data == undefined){
                        alert("获取数据失败");
                        return;
                    }

                    showData(data);
                },
                error: function(e) {
                    alert("请求失败");
                }
            });
        }
        else{
            alert('请先输入文本内容');
        }
    });
    $("#textClear").click(function(){
        $("#textArea").val('');
        showData('');
    });
});

function radioChange() {
    switch($("input[name=optionsRadiosinline]:checked").attr("id")){
        case "textRadio":
            $("#fileDiv").hide();
            $("#textDiv").show();
            break;
        case "fileRadio":
            $("#textDiv").hide();
            $("#fileDiv").show();
            break;
        default:
            break;
    }
}

function fileChange(fileInput) {
    var filePath = $(fileInput).val();
    if(filePath == '' || filePath == undefined || fileInput == null){
        $('#filePath').html('');
        return ;
    }
    if(filePath.substr(filePath.lastIndexOf('.')+1) != "txt"){
        alert('请上传txt文件');
        return;
    }
    $('#filePath').html(filePath);
}

function formCallBack(code,msg){
    if(code == "1"){
        showData(msg);
    }
    else{
        alert(msg);
    }
}

function showData(data) {
    if(data == ''){
        data = {};
        data.engCount = '';
        data.numCount = '';
        data.chnCount = '';
        data.symCount = '';
        data.firstName = '';
        data.firstCount = '';
        data.secondName = '';
        data.secondCount = '';
        data.thirdName = '';
        data.thirdCount = '';
    }
    $("#engCount").html(data.engCount == undefined?'':data.engCount);
    $("#numCount").html(data.numCount == undefined?'':data.numCount);
    $("#chnCount").html(data.chnCount == undefined?'':data.chnCount);
    $("#symCount").html(data.symCount == undefined?'':data.symCount);
    $("#firstName").html(data.firstName == undefined?'':getChineseName(data.firstName));
    $("#firstCount").html(data.firstCount == undefined?'':data.firstCount);
    $("#secondName").html(data.secondName == undefined?'':getChineseName(data.secondName));
    $("#secondCount").html(data.secondCount == undefined?'':data.secondCount);
    $("#thirdName").html(data.thirdName == undefined?'':getChineseName(data.thirdName));
    $("#thirdCount").html(data.thirdCount == undefined?'':data.thirdCount);
}

function getChineseName(english) {
    var ret = "";
    switch (english){
        case "engCount":
            ret = "英文字母";
            break;
        case "numCount":
            ret="数字";
            break;
        case "chnCount":
            ret = "中文汉字";
            break;
        case "symCount":
            ret="中英文标点符号";
            break;
        default:
            break;
    }
    return ret;
}

