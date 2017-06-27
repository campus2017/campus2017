function onSelect() {
    var file=$('#file_input').val();

    $('#file_selected_name').text(file);
    console.log(file);
}

function upload() {
    if (window.FormData) {
        var formData = new FormData();
        // 建立一个upload表单项，值为上传的文件
        formData.append('file', document.getElementById("file_input").files[0]);

        var xhr = new XMLHttpRequest();
        xhr.open('POST', $('#upload_form').attr('action'));
        // 定义上传完成后的回调函数
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var array = JSON.parse(xhr.responseText);
                createTable(array);
            }
        };
        xhr.send(formData);
    }
}
    function formReset() {
        $(':input,#text_form')
            .not(':button, :submit, :reset, :hidden')
            .val('')
            .removeAttr('text_area')
          //  .removeAttr('selected');
    }

    function text() {
    if (window.FormData) {
        var formData = new FormData();
        // 建立一个upload表单项，值为上传的文件
        formData.append('text', $("#text_area").val());

        var xhr = new XMLHttpRequest();
        xhr.open('POST', $('#text_form').attr('action'));
        // 定义上传完成后的回调函数
        xhr.onreadystatechange = function () {
            if (xhr.readyState == 4 && xhr.status == 200) {
                var array = JSON.parse(xhr.responseText);
                createTable(array);
            }
        };
        xhr.send(formData);
    }
}

function createTable(json) {
    $('#count_table_letter').html(json.letterCount);
    $('#count_table_num').html(json.numCount);
    $('#count_table_chinese').html(json.chineseCount);
    $('#count_table_pun').html(json.punctuationCount);

    var body = $("#top_table_body");
    body.empty();

    var top = json.top;
    for (var node in top) {
        var tr = document.createElement("tr");

        var td = document.createElement("td");
        td.innerHTML = top[node].c;
        tr.appendChild(td);

        var td = document.createElement("td");
        td.innerHTML = top[node].count;
        tr.appendChild(td);

        body.append(tr);
    }
}