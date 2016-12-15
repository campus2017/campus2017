#!/bin/bash

#解析命令行参数
while getopts f:d: opt
do
	case $opt in
		f) path=$OPTARG;;
		?) echo "Unknow option '$opt'"
			exit 0;;
	esac
	shift
done

#验证文件路径
if ! [ -n $path ]; then
	read -p "Input the log file path:" path
fi

patten='(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])'

#解析IP地址
cat $path | grep -oP $patten | sort -t"." -k1,1n  -k2,2n  -k3,3n  -k4,4n |uniq -c | sort -k1,1nr|head -10
