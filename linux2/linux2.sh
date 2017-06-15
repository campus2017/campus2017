#!/bin/bash
#
#Target:file from localhost to target dev
#
#linux下如何从本地目录dir1拷贝一个文件到目标机l-test.dev.cn1的/tmp目录下？请写出具体语句。
#
#首先根据域名查找对应的Ip地址，然后根据SSH命令远程登录目标机，并拷贝文件,需要安装SSH协议
#
#也可以根据FTP或者rsync上传文件，此略
#
#2017-05-16 create by Feng Guangtong.
ip=`nslookup localhost|grep Address |tail -n 1|awk '{print $2}'`
filename='./dir1/filename'

scp $filename root@$ip:/tmp
