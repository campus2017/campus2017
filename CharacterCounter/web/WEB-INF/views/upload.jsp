<%--
  Created by IntelliJ IDEA.
  User: makai
  Date: 16-11-6
  Time: 下午10:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
  <title>页面示例</title>
  <link rel="stylesheet" href="css/bootstrap.min.css"/>
  <link rel="stylesheet" href="css/style.css"/>
  <link rel="stylesheet" href="css/webuploader.css"/>
  <script src="js/jquery-1.12.3.min.js" type="text/javascript"></script>
  <script src="js/bootstrap.min.js" type="text/javascript"></script>
  <script src="js/webuploader.min.js" type="text/javascript"></script>
</head>

<body>

<div class="container">
  <div class="row">
    <br/>
    <br/>
    <div class="lead">请选择一段文字</div>
    <div>
      <label class="radio-inline">
        <input type="radio" name="way" id="_file" value="文件上传" checked="checked"/>文件上传
      </label>

      <label class="radio-inline">
        <input type="radio" name="way" id="_text" value="文本输入"/>文本输入
      </label>
    </div>

    <div>
      <table class='table' id="change1" style="display:none">
        <tr>
          <td>
            <div id="upload">文件上传</div>
            <div id="fname"></div>
          </td>
          <td>
            <button id="fs" class='btn btn-info pull-right'> 统 计</button>
          </td>
        </tr>
      </table>
      <table class='table' id="change2" style="display:none">
        <tr>
          <td width='80%'>
            <textarea id="text" class='form-control' rows='5' placeholder='请在此输入文内容'></textarea>
          </td>
          <td>
            <table class='table'>
              <tr>
                <td align='center'>
                  <button id="ts" class='btn btn-info'> 统 计</button>
                </td>
              </tr>
              <tr>
                <td align='center'>
                  <button id='reset' class='btn btn-warning'>清空内容</button>
                </td>
              </tr>
            </table>
          </td>
        </tr>
      </table>
    </div>

    <br/>
    <br/>
    <br/>

    <div id="the_end">
      <p>各统计内容的个数如下:</p>
      <table id="num" class="table table-bordered">
        <tr>
          <td align="center" width="50%">统计项</td>
          <td align="center">个数</td>
        </tr>
        <tr>
          <td align="center">英文字母</td>
          <td align="center" id="e_num"></td>
        </tr>
        <tr>
          <td align="center">数字</td>
          <td align="center" id="n_num"></td>
        </tr>
        <tr>
          <td align="center">中文汉字</td>
          <td align="center" id="c_num"></td>
        </tr>
        <tr>
          <td align="center">中英文标点符号</td>
          <td align="center" id="d_num"></td>
        </tr>
      </table>

      <br/>
      <br/>
      <br/>
      <p>文字中出现频率最高的三个字是:</p>
      <table id="max_num" class="table table-bordered">
      </table>
    </div> <!-- the_end -->
  </div>
</div>

<script type="text/javascript">
  var BASE_URL = 'http://127.0.0.1:8080';
  var uploader = WebUploader.create({
    // swf文件路径
    swf: BASE_URL + '/js/Uploader.swf',
    // 文件接收服务端。
    server: '/uploadFile',
    // 选择文件的按钮。可选。
    // 内部根据当前运行是创建，可能是input元素，也可能是flash.
    pick: '#upload',
    // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
    resize: false
  });

  function set_max_num(data) {
    var max_num_html = "<tr><td align=\"center\" width=\"50%\">名称</td><td align=\"center\">个数</td></tr>";
    var chars = data.chars;
    var cnums = data.cnums;
    var i;
    for (i = 0; i !== 3; ++i)
      max_num_html = max_num_html.concat(
          "<tr><td align=\"center\">" + chars[i] + "</td><td align=\"center\">" + cnums[i] + "</td></tr>");
    $('#max_num').html(max_num_html);
  }

  function set_num(data) {
    $('#e_num').html(data[0]);
    $('#n_num').html(data[1]);
    $('#c_num').html(data[2]);
    $('#d_num').html(data[3]);
  }

  function clean() {
    $('#the_end').hide();
    set_num(['', '', '', '']);
    $('#max_num').val('');
    $('#fname').html('');
  }

  $(function () {
    uploader.on('fileQueued', function (file) {
      $('#fname').html(file.name);
      console.log('fname = ' + file.name);
    });

    uploader.on('uploadSuccess', function (file, response) {
      console.log('s = ' + JSON.stringify(response));
      set_num(response.nums);
      set_max_num(response);
      $('#the_end').show();
    });

    uploader.on('uploadError', function (file) {
      alert('上传出错');
    });

    uploader.on('uploadComplete', function (file) {
    });

    $("#change1").show();
    $('#the_end').hide();

    $('#_file').click(function () {
      $('#change2').hide();
      $('#change1').show();
    });

    $('#_text').click(function () {
      $('#change2').show();
      $('#change1').hide();
    });

    $('#fs').click(function () {
      uploader.upload();
    });

    $('#ts').click(function () {
      $.ajaxSetup({
        contentType : 'application/json'
      });
      $.post('/uploadStr', JSON.stringify($('#text').val()), function (response) {
        set_num(response.nums);
        set_max_num(response);
        $('#the_end').show();
      });
    });

    $('#reset').click(function () {
      $('#text').val('');
    });

    $('#_file,#_text,#upload,#reset').click(clean);
  });
</script>
</body>
</html>
