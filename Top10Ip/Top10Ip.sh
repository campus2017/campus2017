# sort +awk+uniq 统计文件中出现次数最多的前 10 个单词
# http://dev.son1c.com/show/2586.html
#
# linux awk 命令详解
# http://www.cnblogs.com/ggjucheng/archive/2013/01/13/2858470.html
#
# uniq--- 详解
# http://blog.chinaunix.net/uid-26495963-id-3282526.html
#
# linux 之 sort 用法
# http://www.cnblogs.com/dong008259/archive/2011/12/08/2281214.html




cat access-youthlin.com-2016-10.log | awk '{print $1}' | sort | uniq -c | sort -k1,1nr | head -10 | awk '{print $2"\t"$1}'





# log 格式
# IP - - [时间] "GET /path HTTP/1.1" 200 21827 "refer" "user-agent"
# 42.226.119.111 - - [31/Oct/2016:20:08:45 +0800] "GET /favicon.ico HTTP/1.1" 200 21827 "http://youthlin.com/2014601.html" "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36 Core/1.47.516.400 QQBrowser/9.4.8186.400"

# cat 文件内容给管道，传给 awk.
# awk 抽取每行的第一个域（默认空格每个域）
# sort 排序
# uniq -c: 在每列旁边显示该行重复出现的次数。
# sort 排序 -k1 表示按第一列排序 n 按数值排序而不是字符串 r 逆序
# head -10 取前十个
# awk 调换两列顺序输出