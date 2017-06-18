

$(function(){

    $("body a").on("change","input[type='file']",function(){
      var filePath=$(this).val();
      var arr=filePath.split('\\');
      var fileName=arr[arr.length-1];
      $(".filename").html(fileName);

      arr = filePath.split('\.');
      var filesuff = arr[arr.length-1];
      if(!/(txt|doc|docx)/.test(filesuff)){
        $(".fileerror").html("不支持的文件类型");
      } else{
        $(".fileerror").html("");
      }
    });

    $("#filebut").click(function(){
      var filename = $(".filename").html();
      if (filename == ""){
        $(".fileerror").html("请先选择文件");
        return;
      }

      var arr = filename.split('\.');
      var filesuff = arr[arr.length-1];
      if(!/(txt|doc|docx)/.test(filesuff)){
        $(".fileerror").html("不支持的文件类型");
        return;
      }

      $(".fileerror").html("");
      var formData = new FormData();
      formData.append('file', $('#file')[0].files[0]);

      $.ajax({
        type: "POST",
        url: "filecount",
        contentType: false,
        processData: false,
        data: formData,        
        success: function(res){
          if (res.code == '1'){
            $(".fileerror").html(res.error);
          }
          else{
            var result = res.result;
            $("#letterNum").html(result.letterNum);
            $("#digitNum").html(result.digitNum);
            $("#chineseNum").html(result.chineseNum);
            $("#punctuationNum").html(result.punctuationNum);

            var index = 1; 
            for(var key in result.freMap){
              $("#frename"+index).html(key);
              $("#frenum"+index).html(result.freMap[key]);
              index++;
            }
          }

        } 
      });

    });




    $(".options").click(function(){
      clearresult();
      var id = $(this).attr("id");
      if (id == "fileredio"){
        $(".textcon").css('display', 'none');
        $(".filecon").css('display', 'block');
      }
      else{
        $(".filecon").css('display', 'none');
        $(".textcon").css('display', 'block');
      }

    });


    $("#text-clear").click(function(){
      $("#text-area").val("");
      clearresult();
    });

    $("#text-count").click(function(){
      var text = $("#text-area").val();
      if (text == ""){
        $(".fileerror").html("文本内容不能为空");
        return;
      }

      $(".fileerror").html("");

      $.ajax({
        type: "POST",
        url: "textcount",
        dataType: "json",
        data:{ 'textarea': text },        
        success: function(res){
          if (res.code == '1'){
            $(".fileerror").html(res.error);
          }
          else{
            var result = res.result;
            $("#letterNum").html(result.letterNum);
            $("#digitNum").html(result.digitNum);
            $("#chineseNum").html(result.chineseNum);
            $("#punctuationNum").html(result.punctuationNum);

            var index = 1; 
            for(var key in result.freMap){
              $("#frename"+index).html(key);
              $("#frenum"+index).html(result.freMap[key]);
              index++;
            }
          }

        } 
      });

    });


    function clearresult(){
      $("#letterNum").html("");
      $("#digitNum").html("");
      $("#chineseNum").html("");
      $("#punctuationNum").html("");
      for(var i=1; i<4; i++){
          $("#frename"+i).html("");
          $("#frenum"+i).html("");
      }
      $(".fileerror").html("");

    }


   
});