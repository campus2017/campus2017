<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <title>My</title>

    <script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js" charset="utf-8"></script>
    <script src="js/material.min.js " type="text/javascript"></script>
    <script src="js/index.js " type="text/javascript"></script>
    <link rel="stylesheet " href="css/material.min.css ">
    <link rel="stylesheet" href="css/index.css">

</head>

<body>
<div class="mdl-layout mdl-js-layout mdl-layout--fixed-header mdl-layout--fixed-tabs ">
    <header class="mdl-layout__header ">
        <div class="mdl-layout__header-row ">
            <!-- Title -->
            <span class="mdl-layout-title ">文字统计</span>
        </div>
        <!-- Tabs -->
        <div class="mdl-layout__tab-bar mdl-js-ripple-effect ">
            <a href="#fixed-tab-1 " class="mdl-layout__tab is-active ">文件上传</a>
            <a href="#fixed-tab-2 " class="mdl-layout__tab ">文本输入</a>
        </div>
    </header>

    <main class="mdl-layout__content">
        <section class="mdl-layout__tab-panel is-active " id="fixed-tab-1">
            <div class="page-content mdl-grid">
                <div class="mdl-cell mdl-cell--4-col">
                    <form id="upload_form" action="/upload" method="post" enctype="multipart/form-data">
                        <label class="mdl-button mdl-button--colored mdl-js-button mdl-button--raised mdl-js-ripple-effect ">
                            上传文件
                            <input id="file_input" type="file" style="display: none;" onchange="onSelect()">
                        </label>
                    </form>
                </div>
                <div class="mdl-cell mdl-cell--8-col">
                    <div>选择上传的文件：</div>
                    <div id="file_selected_name">无</div>
                </div>
            </div>
        </section>
        <section class="mdl-layout__tab-panel" id="fixed-tab-2">
            <div class="page-content ">
                <div class="page-content mdl-grid">
                    <div class="mdl-cell mdl-cell--12-col">
                        <form id="text_form" action="/text" method="post" style="text-align:center">
                            <div class="mdl-textfield mdl-js-textfield">
                                <textarea class="mdl-textfield__input" type="text" rows="5" id="text_area"></textarea>
                                <label class="mdl-textfield__label" for="text_area">请输入待统计文本</label>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <div id="begin" class="mdl-grid">
            <div class="mdl-cell--12-col">
                <button class="mdl-button mdl-js-button
                mdl-button--raised mdl-button--colored" onclick="upload()">
                    开始统计
                </button>
            </div>
        </div>
        <div class="mdl-grid">

            <table class="mdl-data-table mdl-js-data-table">
                <caption>各统计内容个数如下：</caption>
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">统计项</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody id="count_table_body">
                <tr>
                    <td class="mdl-data-table__cell--non-numeric">英文字母</td>
                    <td id="count_table_letter"></td>
                </tr>
                <tr>
                    <td class="mdl-data-table__cell--non-numeric">数字</td>
                    <td id="count_table_num"></td>

                </tr>
                <tr>
                    <td class="mdl-data-table__cell--non-numeric">中文汉字</td>
                    <td id="count_table_chinese"></td>

                </tr>
                <tr>
                    <td class="mdl-data-table__cell--non-numeric">中英文标点符号</td>
                    <td id="count_table_pun"></td>
                </tr>
                </tbody>
            </table>

            <table class="mdl-data-table mdl-js-data-table">
                <caption>文字中出现频率最高的三个字是：</caption>
                <thead>
                <tr>
                    <th class="mdl-data-table__cell--non-numeric">名称</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody id="top_table_body">
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
        </div>
    </main>

</div>
</body>

</html>
