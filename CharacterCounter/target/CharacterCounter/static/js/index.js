/**
 * Created by Administrator on 2017/6/26.
 */
$(function(){
    $(".form").submit(function(e){
        var formData = new FormData($(this)[0]);
        //console.log(formData)
        $.ajax({
            type: "POST",
            url: $(this).attr("action"),
            data: formData,
            dataType: "json",
            processData: false,
            contentType: false,
            success: function(data){
                if(data.status == "success") {
                    //console.log("1")
                    $("#fail-msg").empty(); //清空上一次错误提示信息
                    $("#engAlphabet").html(data.msg["engAlphabet"]);
                    $("#number").html(data.msg["number"]);
                    $("#chiCharacters").html(data.msg["chiCharacters"]);
                    $("#punctuation").html(data.msg["punctuation"]);
                    $(".top3-char tbody tr td").html("&nbsp"); //清空上次统计结果，有些文字可能不同字符数小于3
                    for(i = 0; i < data.msg["highFrequency"].length; i++) {
                        $("#top" + i).html(data.msg["highFrequency"][i].element);
                        $("#count" + i).html(data.msg["highFrequency"][i].count);
                    }
                } else if(data.status == "failed") {
                    $("#fail-msg").empty();//清空上一次错误提示信息
                    $("#fail-msg").html(data.msg);
                }
            }
        });
        return e.preventDefault(); // 阻止submit
    });

    //清空
    $("#reset").click(function () {
        $(".all-char tbody tr td:odd").html("");
        $(".top3-char tbody tr td").html("&nbsp");
    })

});