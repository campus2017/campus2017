/**
 * Created by XCH on 2017/6/22.
 */
$(document).ready(function () {
    var file_id = -1;
    var file;
    $("#file_operate").hide();
    $("[name='switch']").on("change", function (e) {
        init_result();
        if ($(e.target).val() == "file") {
            $("#text_operate").hide();
            $("#file_operate").show();
        } else {
            $("#file_operate").hide();
            $("#text_operate").show();
        }
    });
    $("#reset").on("click", function (e) {
        init_result();
        $("#text_form").reset();
    });
    $("#file_count").on("click", function (e) {
        if (file_id > 0){
            $.post("/charcounter/file", "id=" + file_id, function (data, status) {
                handleResult(data, status);
            })
        }else {
            alert("请先上传文件！");
        }
    })
    $("#file_form").submit(function () {
        $(this).ajaxSubmit(function (data, status) {
            if (status == "success"){
                if (data.code == -1){
                    file_id = data.data;
                    alert("文件上传成功");
                }else {
                    alert(data.cause);
                }
            }else {
                alert("文件失败，请稍后再试");
            }
        });
        return false;
    });
    $(function () {
        $("#text_form").validate({
            submitHandler: function (form) {
                var $form = $(form);
                $.post("/charcounter/text", $form.serialize(), function (data, status) {
                    handleResult(data, status);

                });
            },
            rules: {
                text: {
                    required: true
                }
            },
            messages: {
                text: {
                    required: "文本内容不能为空"
                }
            }
        });
    });
    function handleFileChange(files) {
//      相同文件不会触发
        file_id = -1;
    }
    function handleResult(data, status) {
        if (status == "success") {
            if (data.code == -1) {
                $("#result_letter").text(data.data.letterCount);
                $("#result_number").text(data.data.numberCount);
                $("#result_chinese").text(data.data.chineseCount);
                $("#result_punctuation").text(data.data.punctuationCount);
                var top = data.data.top;
                var i = 1;
                for (var key in top){
                    $("#top" + i + "_name").text(key);
                    $("#top" + i + "_value").text(top[key]);
                    i++;
                }
            } else {
                alert(data.cause);
            }
        } else {
            alert("失败，请稍后再试");
        }

    }
    function init_result() {
        $("#result_letter").text(0);
        $("#result_number").text(0);
        $("#result_chinese").text(0);
        $("#result_punctuation").text(0);
        $("#top1_name").text("*");
        $("#top1_value").text(0);
        $("#top2_name").text("*");
        $("#top2_value").text(0);
        $("#top3_name").text("*");
        $("#top3_value").text(0);
    }
});