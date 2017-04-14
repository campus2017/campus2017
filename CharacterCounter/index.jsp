<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>

<head>
<style>
.fl {
	float: left;
}

.fr {
	float: right;
}

.mr20 {
	margin-right: 20px;
}

.btn {
	display: block;
	margin-left: 20px;
	width: 100px;
	color: #fff;
	padding: 3px;
	border: 1px solid transparent;
}

.container {
	border: 1px solid black;
	width: 700px;
	margin: 20px auto;
}

.inputBox {
	padding: 0px 10px 10px 10px;
}

.content {
	overflow: hidden;
	margin-top: 10px;
	display: none;
}

.choice {
	margin-left: 30px;
}

.cancel {
	background-color: #074169
}

.submit {
	background-color: #6F3531
}

.filectrl {
	background-color: #2588A7;
	display: inline-block;
	margin-left: 0;
}

.ctrl {
	padding-top: 10px;
}

.count,.frequency {
	padding: 0px 10px 10px 40px;
}

.leftInput {
	width: 443px;
}

.choice input[type="radio"]:nth-of-type(1):checked ~.ctrl {
	display: block;
}

.choice input[type="radio"]:nth-of-type(1):checked ~.content {
	display: none;
}

.choice input[type="radio"]:nth-of-type(2):checked ~.content {
	display: block;
}

.choice input[type="radio"]:nth-of-type(2):checked ~.ctrl {
	display: none;
}

table {
	border: 1px solid black;
	width: 443px;
	border-collapse: collapse;
}

table tr td,table tr th {
	border: 1px solid black;
}

table tr td {
	text-align: center;
	padding: 5px;
	height: 20px;
}

table tr th:first-child {
	width: 50%;
}
</style>
<title>字符统计</title>
<meta name="content-type" content="text/html; charset=UTF-8">
</head>
<%
	request.setCharacterEncoding("utf-8");
%>

<body>
	<div class="container">
		<form action="count.do" method="post">
			<div class="inputBox">
				<div class="title">
					<p>请选择一段文字</p>
				</div>
				<div class="choice">
					<input type="radio" name="selector">文件上传 <input
						type="radio" name="selector" checked="checked">文本输入
					<div class="ctrl">
						<button class="btn filectrl">上传文件</button>
						<button class="btn filectrl fr mr20">统计</button>
					</div>
					<div class="content">
						<div class="leftInput fl">
							<textarea name="text" id="text"
								onblur="if(this.innerHTML==''){this.innerHTML='在这里输入文字';this.style.color='#D1D1D1'}
							if(this.innerHTML==${text}){this.style.color='#D1D1D1'}"
								style="COLOR: #000"
								onfocus="if(this.innerHTML=='在这里输入文字'){this.innerHTML='';this.style.color='#000'}"
								cols="60" rows="5"></textarea>
						</div>
						<div class="rightCtrl fl">
							<input class="button1 btn submit" type="submit" value="统计">&nbsp;
							<input class="button1 btn cancel" type="reset" value="清空内容">
						</div>
					</div>
				</div>
			</div>
		</form>
		<div class="count">
			<p>各统计内容的个数如下：</p>
			<table>
				<thead>
					<tr>
						<th>统计项</th>
						<th>个数</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>英文字母</td>
						<td>${eLen}</td>
					</tr>
					<tr>
						<td>数字</td>
						<td>${nLen}</td>
					</tr>
					<tr>
						<td>中文汉字</td>
						<td>${cLen}</td>
					</tr>
					<tr>
						<td>中英文标点符号</td>
						<td>${sLen}</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="frequency">
			<p>文字中出现频率最高的三个字是：</p>
			<table>
				<thead>
					<tr>
						<th>名称</th>
						<th>个数</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td>${s1}</td>
						<td>${n1}</td>
					</tr>
					<tr>
						<td>${s2}</td>
						<td>${n2}</td>
					</tr>
					<tr>
						<td>${s3}</td>
						<td>${n3}</td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
</body>
</html>
