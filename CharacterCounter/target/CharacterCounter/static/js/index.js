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
                    $("#fail-msg").empty(); //清空错误提示
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
                    $("#fail-msg").empty();
                    $("#fail-msg").html(data.msg+"");
                }
            }
        });
        return e.preventDefault();
    });

    $("#reset").click(function () {
        $("#engAlphabet").html("");
        $("#number").html("");
        $("#chiCharacters").html("");
        $("#punctuation").html("");
        for(i = 0; i < 3; i++) {
            $("#top" + i).html("&nbsp;");
        }
        for(i = 0; i < 3; i++) {
            $("#count" + i).html("");
        }
    })

});