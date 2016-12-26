/**
 * Created by Edward on 2016/12/9.
 */
$(document).ready(function () {
            $("#count").click(
                function () {
                    if(document.getElementById("submit").checked==true)
                    {
                        if($("#showFileName").html()==""){
                            alert("请先选择文件！");
                            return;
                    }
                        $("#uploadform").ajaxSubmit({
                            type:'post',
                            url:'/upLoadCount',
                            success:function(response){
                                $("#en_letter").html( response.en_letter);
                                $("#ch_letter").html( response.ch_letter);
                                $("#num").html( response.num);
                                $("#punctuation").html( response.punctuation);
                                for (var i=0;i<3;i++)
                                {
                                    $(("#char_top"+i)).html(response.top3List[i].key);
                                    $(("#num_top"+i)).html(response.top3List[i].value);
                                }
                                $("#char_top1").html( response.char_top1);
                                $("#char_top2").html( response.char_top2);
                                $("#char_top3").html( response.char_top3);
                                $("#num_top1").html( response.num_top1);
                                $("#num_top2").html( response.num_top2);
                                $("#num_top3").html( response.num_top3);
                            },
                            error:function(){
                                alert("文件上传失败")
                            }
                        });
                    }else {
                        var str = $("#strdata").val();
                        $.ajax({
                            url: 'getCount',
                            type: 'GET',
                            data: "str=" + str, // Request body
                            contentType: 'application/json; charset=utf-8',
                            dataType: 'json',
                            success: function (response) {//请求成功
                                $("#en_letter").html( response.en_letter);
                                $("#ch_letter").html( response.ch_letter);
                                $("#num").html( response.num);
                                $("#punctuation").html( response.punctuation);
                                for (var i=1;i<4;i++)
                                {
                                    $(("#char_top"+i)).html(response.top3List[i-1].key);
                                    $(("#num_top"+i)).html(response.top3List[i-1].value);
                                }
                            },
                            error: function (response) {
                                alert("error");
                            }
                        });
                    }
                });

    $("#clear").click(function () {
        $("textarea").val("").focus();
    });
    $("input[type=radio]").click(function () {
        if ($(this).val() == "上传文件") {
            $(".box1").show();
            $(".box2").hide();
            $("#clear").hide();
        }
        else {
            $(".box1").hide();
            $(".box2").show();
            $("#clear").show();
        }
    });
    $(".file").on("change", "input[type='file']", function () {
        var filePath = $(this).val();
        var index=filePath.lastIndexOf('.');
        var type =filePath.substr(index+1).toLowerCase();
        if(type!="txt"){
            $("#showFileName").html("");
            $("#uploadform").resetForm();
            alert("暂时只支持txt文件！");
            return;
        }
        var arr = filePath.split('\\');
        var fileName = arr[arr.length - 1];
        $("#showFileName").html(fileName);
    })

});
