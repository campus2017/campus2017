 #!/bin/bash

 #author joeTsai
 #统计所有日志文件（tomcat 的 access log）中，列出访问次数最多的 10 个 IP，以及对应的次数;
 #文件记录在result.txt中；

 if [ ! -d $1 ];then
    echo "输入非目录结构，请检查输入" >&2
    exit 1
 fi
 
 DIRHOME=`ls $1/localhost_access_log*.txt`
 declare -A count

 #遍历每一个log文件，记录访问ip以及访问的次数
 for file in $DIRHOME
 do
        while read line
        do
                ipaddr=${line% - -*}
                if [ -z "${count[$ipaddr]}" ];then
                        count[$ipaddr]=1
               	else
               	        count[$ipaddr]=$[ ${count[$ipaddr]}+1 ]
               	fi

        done<$file
 done

 #记录访问的IP以及对应的次数至中间文件tmp.txt中
 for key in `echo ${!count[*]}`
 do
         echo "$key:${count[$key]}">>tmp.txt
 done

 #对中间文件tmp.txt进行排序后输出访问次数排名前10的ip地址以及次数
 sort -n -r -k 2 -t ":" tmp.txt | head -n 10  > result.txt
 #删除中间文件tmp.txt
 rm -rf tmp.txt &> /dev/null
