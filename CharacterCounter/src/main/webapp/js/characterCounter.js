/*功能：文件上传div和输入div切换
 * 作者：Yung
 * 时间：2017/2/19.
* */
var showDiv=function (op) {
    if(op=="upload"){
        $("#uploadinfo").show();
        $("#inputinfo").hide();
    }else if(op=="input"){
        $("#uploadinfo").hide();
        $("#inputinfo").show();
    }
}

/*功能：清空文本框内容
 * 作者：Yung
 * 时间：2017/2/19.
 * */
var emptyContent=function () {
    $("#charactercount").val('');
}

/*功能：统计输入框中数字个数
 * 作者：Yung
 * 时间：2017/2/19.
 * */
var count=function () {
    var charactercount=$("#charactercount").val().trim();
    $.ajax({
        url: 'count/charactercount.action',
        dataType: 'json',   // 可以'text'
        cache: false,       //不缓存数据
        async: false,       //同步
        contentType: "application/json; charset=utf-8",
        data: {
             'character':charactercount
        },
        success: function (data) {
            var flag0=0;
            var flag1=0;
            if(data && data!=null){
                var countCharacter=data.countCharacter;
                var countMostCharacter=data.countMostCharacter;
                $("#englishcount").html(countCharacter.englishCount);
                $("#digitcount").html(countCharacter.digitCount);
                $("#chaniesecount").html(countCharacter.chineseCount);
                $("#punctuationcount").html(countCharacter.punctuationCount);
                $.each(countMostCharacter,function (i,item) {
                    $("#countMostKey"+(flag0++)).html(i);
                    $("#countMostValue"+(flag1++)).html(countMostCharacter[i]);
                });
            }
        }
    });
}

/*功能：统计上传文件中文字个数
 * 作者：Yung
 * 时间：2017/2/19.
 * */
var countfile=function () {
    $.ajaxFileUpload({
        type: 'post',
        url: 'count/uploadcharactercount.action',
        secureuri: false, //一般设置为false
        fileElementId: 'fileUpload', // 上传文件的id、name属性名
        dataType: 'application/json;charset=utf-8', //返回值类型，一般设置为json、application/json
        success: function(data){
            var data = $.parseJSON(data.replace(/<.*?>/ig,"")); //ajaxfileupload是不解析json的,需要此方法解决
            var flag0=0;
            var flag1=0;
            if(data && data!=null){
            var countCharacter=data.countCharacter;
            var countMostCharacter=data.countMostCharacter;
            $("#englishcount").html(countCharacter.englishCount);
            $("#digitcount").html(countCharacter.digitCount);
            $("#chaniesecount").html(countCharacter.chineseCount);
            $("#punctuationcount").html(countCharacter.punctuationCount);
            $.each(countMostCharacter,function (i,item) {
                $("#countMostKey"+(flag0++)).html(i);
                $("#countMostValue"+(flag1++)).html(countMostCharacter[i]);
            });
            }
        }
    });
}