/**
 * Created by chenli on 2017/7/1.
 */

function judge() {
    var filetypes = document.getElementsByName('filetypes');
    for(var i=0; i<filetypes.length; i++){
        if(filetypes[i].checked && filetypes[i].value == 'file'){
            var filenameText = document.getElementById('filename');
            if(filenameText.innerText == ''){
                alert('请选择上传文件');
                return '';
            }else{
                return 'file';
            }
        }else if(filetypes[i].checked && filetypes[i].value == 'word') {
            //var reg=/\s+/;
            var words = document.getElementById('words');
            if (words.value.trim().length == 0) {
                alert('请输入文字');
                return '';
            } else {
                return 'word';
            }
        }
    }
    return '';
}
function submitFile(){
    var fd = FormData();
    alert('file2');
    fd.append(document.getElementById("realFileInput").files[0]);
    var xhr = new XMLHttpRequest();
    xhr.open('post', '/receiveData');
    xhr.send(fd);
}
function submitText(){
    var words = document.getElementById('words');
    if (words.value.trim().length == 0) {
        alert('请输入文字');
        return '';
    } else {
        document.getElementById('uploadform').submit();
        return 'word';
    }
}

