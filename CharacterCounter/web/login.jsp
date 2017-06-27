<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>Qunar大作业</title>

  <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8"></script>
  <script src="js/material.min.js " type="text/javascript"></script>
  <script src="js/login.js " type="text/javascript"></script>
  <link rel="stylesheet " href="css/material.min.css ">
  <link rel="stylesheet" href="css/login.css">

</head>

<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header mdl-layout--fixed-tabs ">
    <main class="mdl-layout__content" >
      <div class="mdl-grid">
        <h6>请输入一段文字</h6>
        <form id="text_form" action="/result.do" method="post" style="text-align:left">
        <table>
         <tr>
              <td rowspan="2" >
                 <div class="mdl-textfield mdl-js-textfield">
                   <textarea  style="width:380px" type="text" rows="10"  id="text_area"></textarea>
                   <label border="1" class="mdl-textfield__label" for="text_area">请在此处输入文内容</label>
                  </div>

              </td>
              <td>
                <input class="mdl-button mdl-js-button
                mdl-button--raised mdl-button--colored"  value="统计" style="background-color: #03a9f4;width:90px;height:40px;margin-left:100px" type="submit">
              </input>
              </td>
            </tr>
            <tr><td>
              <input class="mdl-button mdl-js-button
                mdl-button--raised mdl-button--colored" value="清空内容" style="background-color: #f9a825;width:90px;height:40px;margin-left:100px" type="reset">
             </input>
            </td></tr>
          </table>
        </form>
      </div>

    <div class="mdl-grid">

      <table border="1" class="mdl-data-table mdl-js-data-table">
        <caption style="text-align:left">各统计内容个数如下：</caption>
        <thead>
        <tr>
          <th class="mdl-data-table__cell--non-numeric" style="text-align:center;width: 50%">统计项</th>
          <th style="text-align:center">个数</th>
        </tr>
        </thead>
        <tbody id="count_table_body">
        <tr>
          <td class="mdl-data-table__cell--non-numeric" style="text-align:center">英文字母</td>
          <td id="count_table_letter"></td>
        </tr>
        <tr>
          <td class="mdl-data-table__cell--non-numeric" style="text-align:center">数字</td>
          <td id="count_table_num"></td>

        </tr>
        <tr>
          <td class="mdl-data-table__cell--non-numeric" style="text-align:center">中文汉字</td>
          <td id="count_table_chinese" style="text-align:center"></td>

        </tr>
        <tr>
          <td class="mdl-data-table__cell--non-numeric" style="text-align:center">中英文标点符号</td>
          <td id="count_table_pun" style="text-align:center"></td>
        </tr>
        </tbody>
      </table>

      <table border="1" class="mdl-data-table mdl-js-data-table">
        <caption style="text-align:left">文字中出现频率最高的三个字是：</caption>
        <thead>
        <tr>
          <th class="mdl-data-table__cell--non-numeric" style="text-align:center">名称</th>
          <th style="text-align:center">个数</th>
        </tr>
        </thead>
        <tbody id="top_table_body">
        <tr>
          <td style="text-align:center"></td>
          <td style="text-align:center"></td>
        </tr>
        <tr>
          <td style="text-align:center"></td>
          <td style="text-align:center"></td>
        </tr>
        <tr>
          <td style="text-align:center"></td>
          <td style="text-align:center"></td>
        </tr>
        </tbody>
      </table>
    </div>

    </main>

</div>
</body>

</html>