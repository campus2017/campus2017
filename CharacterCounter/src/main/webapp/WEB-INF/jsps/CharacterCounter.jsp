<%@ page import="com.JimmyWang.springmvc.Word" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<html>
<head>
    <title>字符统计</title>
    <style>
        a {
            display: inline-block;
            width: 80px;
            height: 30px;
            background: #00ccff;
            position: relative;
            border-radius:6px;
            text-decoration: none;
            color: #fff;
        }
        input[type='file'] {
            opacity: 0;
            position: absolute;
            width: 80px;
            height: 30px;
            left: 0;
        }
    </style>
    <script type="text/javascript">
        function hide(i) {
            if (i == 0) {
                document.getElementById('text').style.visibility = 'hidden';
                document.getElementById('file').style.visibility = 'visible';
            } else if (i == 1) {
                document.getElementById('file').style.visibility = 'hidden';
                document.getElementById('text').style.visibility = 'visible';
            }
        }
        function ClearTextArea() {
            document.getElementById('t').value = '';
        }
    </script>
    <%
        Word word = (Word) request.getAttribute("word");
        List<Word> list = (List<Word>) request.getAttribute("top");
    %>
</head>
<body>
<div align="center">
    <table>
        <tr>
            <td>
                <span onclick="hide()">
                    <input type="radio" name="type" onclick="hide(0)">文件上传
                    <input type="radio" name="type" onclick="hide(1)" checked>文本输入
                </span>
            </td>
        </tr>
        <%--文件上传--%>
        <tr id="file" style="visibility: hidden">
            <td>
                <form action="/FileCounter" method="post" enctype="multipart/form-data">
                    <a href="#">
                        上传文件<input type="file" name="file"/>
                    </a>
                    <input type="submit" style="width: 80px;height: 30px;background-color: #00ccff" value="统计">
                </form>
            </td>
        </tr>
        <%--文本输入--%>
        <tr id="text">
            <td>
                <table>
                    <tr>
                        <form id="form" action="TextCounter" method="post">
                            <td width="200px" height="35">
                                <textarea id="t" name="text" rows="5" cols="30"></textarea>
                            </td>
                            <td width="200px" height="35">
                                <table>
                                    <tr>
                                        <td width="300px">
                                            <input type="submit" id="btn_submit"
                                                   style="width: 80px;height: 30px;background-color: #00ccff"
                                                   value="统计"/>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="200px" height="35">
                                            <input type="button" style="width: 80px;height: 30px;background: #ff9900"
                                                   value="清空" onclick="ClearTextArea();"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </form>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                各项统计内容的个数如下：
                <table border="1" cellpadding="0" cellspacing="0" width="233px">
                    <tr align="center">
                        <td width="200px" height="35">统计项</td>
                        <td width="200px" height="35">个数</td>
                    </tr>
                    <tr align="center">
                        <td width="200px" height="35">英文字母</td>
                        <td width="200px" height="35"><%= word == null ? "" : word.getEnCount()%>
                        </td>
                    </tr>
                    <tr align="center">
                        <td width="200px" height="35">数字</td>
                        <td width="200px" height="35"><%= word == null ? "" : word.getDigitCount()%>
                        </td>
                    </tr>
                    <tr align="center">
                        <td width="200px" height="35">中文汉字</td>
                        <td width="200px" height="35"><%= word == null ? "" : word.getCnCount()%>
                        </td>
                    </tr>
                    <tr align="center">
                        <td width="200px" height="35">中英文标点符号</td>
                        <td width="200px" height="35"><%= word == null ? "": word.getPunCount()%>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td>
                文字中出现频率最高的三个字是：
                <table border="1" cellpadding="0" cellspacing="0" width="233px">
                    <tr align="center">
                        <td width="200px" height="35">名称</td>
                        <td width="200px" height="35">个数</td>
                    </tr>
                    <tr align="center">
                        <td width="200px" height="35"><%= list == null ? "" : list.get(0).getStr() %>
                        </td>
                        <td width="200px" height="35"><%= list == null ? "" : list.get(0).getCount() %>
                        </td>
                    </tr>
                    <tr align="center">
                        <td width="200px"
                            height="35"><%= list == null ? "" : (list.size() < 2 ? "" : list.get(1).getStr()) %>
                        </td>
                        <td width="200px"
                            height="35"><%= list == null ? "" : (list.size() < 2 ? "" : list.get(1).getCount()+"") %>
                        </td>
                    </tr>
                    <tr align="center">
                        <td width="200px"
                            height="35"><%= list == null ? "" : (list.size() < 3 ? "" : list.get(2).getStr()) %>
                        </td>
                        <td width="200px"
                            height="35"><%= list == null ? "" : (list.size() < 3 ? "" : list.get(2).getCount()+"") %>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</div>
</body>
</html>