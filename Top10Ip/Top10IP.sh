 #!/bin/bash
 #author zhenghan
 if [ ! -d $1 ];then
    echo "不是目录" >&2
    exit 1
 fi
 DIRHOME=`ls $1/localhost_access_log*.txt`
 declare -A ip_count
 #读取每一个access_log进行处理
 for file in $DIRHOME
 do
        while read line
        do
                ipaddress=${line% - -*}
                if [ -z "${ip_count[$ipaddress]}" ];then
                        ip_count[$ipaddress]=1
               	else
               	        ip_count[$ipaddress]=$[ ${ip_count[$ipaddress]}+1 ]
               	fi
        done<$file
        echo "$(date '+%F %T') 载入了文件${file}"
 done
 #对得到的key_value写入文件
 for key in `echo ${!ip_count[*]}`
 do
         echo "$key:${ip_count[$key]}">>merge_log.txt
 done
 echo "$(date '+%F %T') 准备进行排序"
 sort -n -r -k 2 -t ":" merge_log.txt | head -n 10  > access_log.txt
 rm -rf merge_log.txt &> /dev/null
 echo "$(date '+%F %T') 完成!"