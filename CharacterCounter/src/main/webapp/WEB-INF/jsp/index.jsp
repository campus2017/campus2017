<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: youthlin.chen
  Date: 2016-12-9 009
  Time: 10:51 上午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <%@ include file="head.jsp" %>
    <title>字符统计</title>
    <style>
        .height-70 {
            height: 70px;
        }

        .tab-pane {
            margin-top: 10px;
        }

        #file-input {
            padding-top: 30px;
        }

        .table {
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h4>字符统计</h4>
    <div>
        <!-- Nav tabs -->
        <ul class="nav nav-tabs" role="tablist">
            <li role="presentation" class="active"><a href="#file" role="tab" data-toggle="tab">文件上传</a></li>
            <li role="presentation"><a href="#text" role="tab" data-toggle="tab">文本输入</a></li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">
            <div role="tabpanel" class="tab-pane active" id="file">
                <form class="form form-file" action="<c:url value="/upload"/>" role="form" method="post"
                      enctype="multipart/form-data">
                    <div class="form-group height-70">
                        <input type="file" name="file-input" id="file-input">
                    </div>
                    <button type="submit" class="btn btn-primary submit-btn submit-file">统计</button>
                </form>
            </div>
            <div role="tabpanel" class="tab-pane" id="text">
                <form class="form form-text" action="<c:url value="/text"/>" role="form" method="post">
                    <div class="form-group height-70">
                        <label>
                            <textarea class="form-control" name="text-input" id="text-input" rows="3" cols="200"
                                      required style="max-height: 70px;max-width: 100%;"></textarea>
                        </label>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary submit-btn submit-text">统计</button>
                        <button type="reset" class="btn btn-info">清空</button>
                    </div>
                </form>
            </div>
        </div>
        <div class="text-danger msg">&nbsp;</div>
        <div>
            <p>各统计内容的个数如下：</p>
            <table class="table table-bordered table-hover table-striped">
                <thead>
                <tr>
                    <th>统计项</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>英文字母</td>
                    <td id="en" class="fill"></td>
                </tr>
                <tr>
                    <td>数字</td>
                    <td id="dg" class="fill"></td>
                </tr>
                <tr>
                    <td>中文汉字</td>
                    <td id="cn" class="fill"></td>
                </tr>
                <tr>
                    <td>中英文标点符号</td>
                    <td id="sb" class="fill"></td>
                </tr>
                </tbody>
            </table>
        </div>

        <div>
            <p>文字中出现频率最高的三个字是：</p>
            <table class="table table-bordered table-hover table-striped">
                <thead>
                <tr>
                    <th>名称</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td id="t0" class="fill">&nbsp;</td>
                    <td id="v0" class="fill"></td>
                </tr>
                <tr>
                    <td id="t1" class="fill">&nbsp;</td>
                    <td id="v1" class="fill"></td>
                </tr>
                <tr>
                    <td id="t2" class="fill">&nbsp;</td>
                    <td id="v2" class="fill"></td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        var msg = $('.msg');
        var btn = $('.submit-btn');
        var old = btn.html();
        var en = $('#en');
        var dg = $('#dg');
        var cn = $('#cn');
        var sb = $('#sb');
        var fill = $('.fill');
        var f = $('.form');
        f.submit(function (e) {
            var data = new FormData($(this)[0]);//注意[0]
            $.ajax({
                url: $(this).attr('action'),
                type: 'POST',
                data: data,
                dataType: 'json',
                contentType: false,// 这句不可少, 否则上传的 contentType 还是 urlencoded 而不是 multipart/form-data
                processData: false,// 这句不可少，否则报错 Uncaught TypeError: Illegal invocation
                beforeSend: function () {
                    btn.html('上传中...');
                    btn.attr('disabled', true);
                    msg.html('&nbsp;');
                    fill.html('&nbsp;');
                },
                complete: function () {
                    setTimeout(function () {
                        btn.html(old);
                        btn.attr('disabled', false);
                    }, 1000);
                },
                success: function (r) {
                    if (r.code == 0) {
                        d = r.data;
                        en.html(d.enWords);
                        dg.html(d.numbers);
                        cn.html(d.cnWords);
                        sb.html(d.symbols);
                        for (i = 0; i < d.top3.length; i++) {
                            name = d.top3[i].element;
                            switch (name) {
                                case ' ':
                                    name = "' '(空格)";
                                    break;
                                case '\t':
                                    name = "'\\t(制表符)'";
                                    break;
                                case '\n':
                                    name = "'↵(回车)'";
                                    break;
                            }
                            $('#t' + i).html(name);
                            $('#v' + i).html(d.top3[i].count);
                        }
                    } else {
                        msg.html(r.msg);
                    }
                },
                error: function () {
                    alert("出错了，请检查网络！");
                }
            });
            return e.preventDefault();
        });
    });
</script>
</body>
</html>
