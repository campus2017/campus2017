function forTest(){
    var divValue = document.getElementById('filename');
    alert(divValue.innerText);
}
function torealup(){
    //forTest();
    var filetypes = document.getElementsByName('filetypes');
    for(i=0; i<filetypes.length; i++){
        if(filetypes[i].checked && filetypes[i].value != 'file'){
            alert('请勾选文件上传方式') ;
            return ;
        }
    }
    realup();
}
function realup(){//加载真正的上传文件按钮
    //var fdata = document.getElementById('uploadform');
    //var xmlrequest = new XMLHttpRequest();
    var input = document.createElement('input');
    input.setAttribute('id', 'realFileInput');
    input.setAttribute('type', 'file');
    input.setAttribute('name', 'realfileupload');
    document.body.appendChild(input);
    input.style.display = 'none';
    input.click();
    if(navigator.userAgent.indexOf('Trident') >= 0){  //！！！！IE与chrome不同
        fileEvent(input);
        return ;
    }
    input.onchange = function(){
        fileEvent(input) ;
        return ;
    }
}
function fileEvent(input){
    //alert(input.files[0].size) ;
    var filenameText = document.getElementById('filename');
    var index = input.value.lastIndexOf('\\');
    var filename = input.value.substring(index+1);
    filenameText.innerText = filename;
    filenameText.style.display = 'block';
    return ;
}
function selectFile(){//选择文件上传
    var uploadButton = document.getElementById('upload');
    var wordsText = document.getElementById('words');
    var cleanButton = document.getElementById('cleanword');
    uploadButton.style.display = 'block';
    //wordsText.innerText = '';
    wordsText.style.display = 'none';
    cleanButton.style.display = 'none';
}
function selectText(){//选择文本上传
    var uploadButton = document.getElementById('upload');
    var wordsText = document.getElementById('words');
    var cleanButton = document.getElementById('cleanword');
    var filename = document.getElementById('filename');
    uploadButton.style.display = 'none';
    filename.style.display = 'none';
    filename.innerText = '';
    wordsText.style.display = 'block';
    cleanButton.style.display = 'block';
}
function cleanwords(){//清除文本框
    var textContent = document.getElementById('words');
    textContent.value = '';
    return ;
}

//http://www.cnblogs.com/Sinte-Beuve/p/5730553.html springMVC IDEA
//http://www.cnblogs.com/ssslinppp/p/4675495.html springmvc
//http://www.cnblogs.com/java-maowei/p/5950930.html git IDEA
//http://www.cnblogs.com/heroine/p/5852748.html  about css定位
//http://www.zui88.com/blog/view-394.html	js文件后缀获得
//http://www.cnblogs.com/yuanlong1012/p/5127497.html	js文件上传