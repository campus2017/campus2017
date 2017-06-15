<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>CharacterCounter</title>
    <script type="text/javascript" src="statics/js/jquery-3.2.1.min.js"></script>
    <link type="text/css" rel="stylesheet" href="statics/css/style.css" />

    <script type="text/javascript">
        $(function () {
            $(":radio").click(function () {
                if (this.checked) {
                    if ($(this).attr("id") == "rdFile") {
                        $("#trFile").show();
                        $("#trText").hide();

                    }
                    if ($(this).attr("id") == "rdText") {
                        $("#trFile").hide();
                        $("#trText").show();
                    }
                }
            });
        });

        $(function () {
            $(".btnClear").first().click(function(){
                $("textarea").val("请在此输入文本内容");
            })
        });

        function change() {
            var file = $("#file").val();
            var lblFileName = document.getElementById("lblFileName");
            var arr = file.split('\\');//注split可以用字符或字符串分割
            var filename = arr[arr.length-1];//这就是要取得的文件名称
            lblFileName.innerText = filename;
        }

    </script>
</head>
<body>
<div align="center" style="width:400px;">
    <div class="divTitle" >
        <div><strong>请选择一段文字</strong><br/></div>
        <div style="margin-left:20px; ">
        <input type="radio" name="set" id="rdFile" checked="checked" value="trFile"/><span>文件上传</span>
        <span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
        <input type="radio" name="set" id="rdText" value="trText"/><span>文本上传</span></div>
    </div>
    <div>
        <table cellspacing="0" cellpadding="0">
            <tr id="trFile">
                <form action="/uploadFile.do" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
                    <td colspan="2">
                        <div class="new-contentarea">
                            <a href="javascript:void(0)" class="btn-upload-img">上传文件</a>
                            <input type="file" id="file" onchange="change();" name="upload-file" id="upload-file" />
                            <label id="lblFileName" >&nbsp;</label>
                        </div>

                    </td>
                    <td>
                        <input type="submit" class="button" value="统计"/>
                    </td>
                </form>
            </tr>
            <tr id="trText" style="display: none;">
                <form action="/uploadText.do" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
                    <td colspan="2">
                        <textarea rows="5" name="text" onfocus="if(value=='请在此输入文本内容'){value=''}"
                                  onblur="if (value ==''){value='请在此输入文本内容'}">请在此输入文本内容</textarea>
                    </td>

                    <td>
                        <input type="submit" class="button" value="统计"/><br/>
                        <input type="button" class="btnClear" value="清除内容"/>
                    </td>
                </form>
            </tr>
            <tr>
                <td colspan="3">
                    <div class="divTitle"><strong>各统计内容的格式如下：</strong></div>
                    <table border="1" cellspacing="0" cellpadding="0">
                        <tr>
                            <td><strong>统计项</strong></td>
                            <td><strong>个数</strong></td>
                        </tr>
                        <tr>
                            <td>英文字母</td>
                            <td>${CharacterCounter.sumEnglish}</td>
                        </tr>
                        <tr>
                            <td>数字</td>
                            <td>${CharacterCounter.sumNumber}</td>
                        </tr>
                        <tr>
                            <td>中文汉字</td>
                            <td>${CharacterCounter.sumChinese}</td>
                        </tr>
                        <tr>
                            <td>中英文标点符号</td>
                            <td>${CharacterCounter.sumSymbol}</td>
                        </tr>

                    </table>
                    <div class="divTitle"><strong>文字中出现频率最高的三个字：</strong></div>
                    <table cellpadding="0" cellspacing="0" border="1">
                        <tr>
                            <td><strong>名称</strong></td>
                            <td><strong>个数</strong></td>
                        </tr>
                        <c:forEach items="${CharacterCounter.linkedHashMap}" var="map" begin="1" end="3" step="1">
                            <tr>
                                <td>${map.key}</td>
                                <td>${map.value}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </td>

            </tr>
        </table>
    </div>
</div>
</body>
</html>
