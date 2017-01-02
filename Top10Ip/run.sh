#!/bin/bash


#递归读取文件夹下的所有文件
function read_dir() {
    for file in `ls $1`
    do 
        if [ -d $1"/"$file ]
        then
            read_dir $1"/"$file
        else
            cat $1"/"$file

        fi
    done 
}

#调用读取目录下文件的函数来统计日志文件中访问次数最多的10的IP
read_dir $1 | awk '{print $1}'| sort |uniq -c |sort -rn -k1,1 |head -n 10  
