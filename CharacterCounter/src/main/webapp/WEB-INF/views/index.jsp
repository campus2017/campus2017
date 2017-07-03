<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <!--http://www.divcss5.com/rumen/r120.shtml-->
    <meta charset="utf-8"/>
    <title>文字统计</title>
    <link rel="stylesheet" href="./css/all.css" type="text/css" />
    <script src="./js/forSubmit.js" type='text/javascript' language='JavaScript'> </script>
    <script src="./js/forAll.js" type='text/javascript' language='JavaScript'> </script>
</head>
<body class="body">
<script>

</script>
<div class="first">请选择一段文字</div><br/>
<form id="uploadform" action="/receiveData" method="post">
    <div class="all">
        <input type="hidden" name="file" value="file" id="file" onchange="selectFile()" checked/>
        <input type="hidden" name="word" value="word" id="word" onchange="selectText()"/>

        <div class="fileupname">
            <input type="button" name="upload" id="upload" value="上传文件" class="fileupload" onclick="torealup()" style="display:none"/>
            <div name="filename" id="filename" class="filename" style="display:none">  </div>
        </div>
        <textarea name="words" id="words" class="words" warp="virtual" style="overflow:scroll;
          overflow-x:hidden;" onfocus="if(value.trim()=='请在此输入文本内容'){value=''}"
                  onblur="if (value ==''){value='请在此输入文本内容'}"
        /><c:if test="${words == null}">请在此输入文本内容</c:if>${words} </textarea>
        <div class="leftbutton">
            <input type="button" name="count" value="统计" class="count" onclick="submitText()"/> <br/>
            <input type="button" name="cleanword" value="清除内容" class="cleanword" id="cleanword" onclick="cleanwords()" />
        </div>

        <div class="down">各统计内容的个数如下：  <br/>
            <table class="table">
                <tr>
                    <th>统计项</th> <th>个数</th>
                </tr>

                <tr>
                    <td>英文字母</td>
                    <td>${result.get("letter")}</td>
                </tr>
                <tr>
                    <td>数字</td>
                    <td>${result.get("number")}</td>
                </tr>
                <tr>
                    <td>中文汉字</td>
                    <td>${result.get("chinese")}</td>
                </tr>
                <tr>
                    <td>中英文标点符号</td>
                    <td>${result.get("punctuation")}</td>
                </tr>
            </table><br/>

            文字中出现频率最高的三个字是：<br/><br/>
            <table border="1">
                <tr>
                    <th>名称</th><th>个数</th>
                </tr>
                <c:set var="times" value="${maxThirdWords.size()}"/>
                <tr>
                    <td><c:if test="${times > 0}">
                        ${maxThirdWords.get(0).getKey()}
                    </c:if></td>
                    <td><c:if test="${times > 0}">
                        ${maxThirdWords.get(0).getValue()}
                    </c:if></td>

                </tr>
                <tr>
                    <td><c:if test="${times > 1}">
                        ${maxThirdWords.get(1).getKey()}
                    </c:if></td>
                    <td><c:if test="${times > 1}">
                        ${maxThirdWords.get(1).getValue()}
                    </c:if></td>
                </tr>
                <tr>
                    <td><c:if test="${times > 2}">
                        ${maxThirdWords.get(2).getKey()}
                    </c:if></td>
                    <td><c:if test="${times > 2}">
                        ${maxThirdWords.get(2).getValue()}
                    </c:if></td>
                </tr>
            </table>
        </div>
    </div>
</form>
</body>
</html>