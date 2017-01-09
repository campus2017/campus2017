<%--
  Created by IntelliJ IDEA.
  User: gzx
  Date: 17-1-1
  Time: 下午10:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>统计文字频率</title>
      <%-- 使用request.getContexPath()--%>
      <script type="text/javascript" src="<%=request.getContextPath()%>/resources/js/jsForIndex.js"></script>
      <link href="<%=request.getContextPath()%>/resources/css/cssForIndex.css" rel="stylesheet" type="text/css"/>
  </head>
  <body>
     <div id="all">
      <table>
        <tbody>
          <tr>
            <th colspan="2" align="left">
              请选择一段文字
            </th>
          </tr>
          <tr>
            <td class="radioTd,left">
              <input type="radio" value="1" name="type" id = "selectFile" checked>文件上传
            </td>
            <td class="radioTd">
              <input type="radio" value="2" name="type" id = "selectContent">文本输入
            </td>
          </tr>
        </tbody>
      </table>
      <div id="formDiv1">
          <form method="post" action="fileUpload.html" enctype="multipart/form-data" name="fileUploadForm" id = "fileUploadForm">
              <table id="formTable1">
                  <tbody>
                    <tr>
                        <%-- 点击上传按钮，将点击事件传给docFile，并在标签中设置文件名，
                               这里将docFile设置为none不会占用空间，且不可见
                            按钮，利用h5的FormData发起Ajax请求，获得XML并解析，更新两个表格
                        --%>
                        <td class="left">
                            <input type="button" id = "btn" value="文件上传"/>
                            <%-- 不占位置 --%>
                            <input type="file" name="docFile" id="docFile" style="display : none;"/>
                        </td>
                        <td>
                            <label name="filePath" id="filePath"></label>
                        </td>
                        <td class="right">
                            <input type="button" value="统计" id = "submitFile"/>
                        </td>
                    </tr>
                  </tbody>
              </table>
          </form>
      </div>
         <%-- url相对于当前页面的路径，下同 --%>
      <div id="formDiv2">
          <form method = "post" action = "contentUpload.html" name = "contentForm" id = "contentForm">
              <table>
                  <tbody>
                      <tr>
                            <td rowspan="2">
                                <textarea name = "textArea" id = "textArea" placeholder="请在此输入文本内容"></textarea>
                            </td>
                            <td>
                                <input type="button" value="统计" id = "submitContent"/>
                            </td>
                      </tr>
                      <tr>
                          <td>
                              <input type="reset" value="清空内容" id="reset"/>
                          </td>
                      </tr>
                  </tbody>
              </table>
          </form>
      </div>
      <table class = "statisticTable">
          <caption>各统计内容的个数如下：</caption>
          <tbody>
            <tr>
                <th>统计项</th>
                <th>个数</th>
            </tr>
            <tr>
                <td>英文字母</td>
                <td id="alpha"></td>
            </tr>
            <tr>
                <td>数字</td>
                <td id="digit"></td>
            </tr>
            <tr>
                <td>中文汉字</td>
                <td id="chinese"></td>
            </tr>
            <tr>
                <td>中英文标点符号</td>
                <td id="punctuation"></td>
            </tr>
          </tbody>
      </table>

      <table class = "statisticTable">
          <caption>文字中出现频率最高的三个字是：</caption>
          <tbody>
          <tr>
              <th>名称</th>
              <th>个数</th>
          </tr>
          <tr>
              <td id="first_key"></td>
              <td id="first_value"></td>
          </tr>
          <tr>
              <td id="second_key"></td>
              <td id="second_value"></td>
          </tr>
          <tr>
              <td id="third_key"></td>
              <td id="third_value"></td>
          </tr>
          </tbody>
      </table>
     </div>
  </body>
</html>
