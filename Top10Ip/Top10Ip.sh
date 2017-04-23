#!/bin/sh

#统计临时文件内的IP,并计算出所有访问中的top10
total10(){
    path=$1
    result='top10.tmp'
    resultpath=${path}${result}
    touch $resultpath

    for file in `ls -A $path`
    do
        filepath=${path}${file}
        if test -f $filepath
        then
            awk '{a[$1]+=$2}END{for(i in a){print i"|"a[i]}}'  ${resultpath} ${filepath} > ${resultpath}
        fi
    done

    cat $resultpath
}

#统计每个文件中top10的ip,并保存到临时文件
top10(){
    #echo $1
    rand=`date +%N`
    output=${2}${rand}
    cat $1 | awk '{print $1}' | sort | uniq -c | sort -rn | head -n 10 >> $output
}

#遍历日志文件目录,对每个日志文件统计TOP10的ip
scan_file(){
    path=$1
    logpath=$2

    for file in `ls -A $path`
    do
        filepath=${path}${file}
        if test -f $filepath
        then
            echo $filepath
            top10 $filepath $logpath
        fi
    done
}

#保存临时结果的目录
tmppath=/tmp/top10ip_accesslog_stats
#构造临时目录
mkdir tmppath
#遍历日志文件夹，统计每个文件中的top10ip，将结构保存到tmppath
scan_file $1 $tmppath
#计算所有的top10ip，统计整个日志中的top10，并输出结果
total10 $tmppath
#统计完成后删除临时目录
rm -rf $tmppath
