#!/bin/bash

#linux下如何从本地目录dirl拷贝一个文件到目标机I-test.dev.cnl的//tmp目录下?请写出具体语句。

read -p "Input the filename you want to send: " filename
if [ ! -f dir1/${filename} ]
then
    echo "Nothing can be send!"
else
    scp dir1/${filename} root@l-test.dev.cn1:/tmp
fi
