# web 作业：文件比较

## 题目功能
- 上传两个文本文件（文件 < 1k）
- 对比差异
- 存储差异结果到数据库
- 展示历史对比记录
- 登陆后可删除历史对比记录

## 两个页面

### 上传页面
<pre>
&lt;form action="..." method="post" enctype="multipart/form-data">
 &lt;input type="file" name="source" >
 &lt;input type="file" name="target" >
 &lt;input type="submit">
</form>
&lt;h3>最近5条历史对比结果&lt;/h3>
&lt;table>
 &lt;tr>
 &lt;td>对比时间&lt;/td>
 &lt;td>源文件内容&lt;/td>
 &lt;td>目标文件内容&lt;/td>
 &lt;td>差异&lt;/td>
 &lt;td>操作&lt;/td>
 &lt;/tr>
 &lt;tr>....&lt;/tr>
&lt;/table>
查看更多
</pre>
- 顶部为上传文件表单
- 下方为历史对比结果(每页2条)
- 不足3条时"查看更多"不显示
- 点击"查看更多"直接显示第二页

### 登陆页面
帐户信息存在 `account.properties` 中，如:
<pre>
san.zhang=123456
si.li=admin888
</pre>
- 登陆后, 历史记录"操作"列显示"删除"链接，点击可删除记录
- 未登录时，不能删除记录

### 对比规则
文件内容均为普通键值对，如下两个文件a和b
<pre>
a1=x
a2=y
a3=z
</pre>
和:
<pre>
a1=x
a3=x
a4=n
</pre>
分如下3种情况输出结果:
- 源存在、目标不存在 => `-a2=y`
- 源不存在、目标存在 => `+a4=n`
- 源和目标存在、但不同 => `-a3=z;+a3=x`
