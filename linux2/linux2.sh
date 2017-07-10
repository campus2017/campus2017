#########################################################################
# File Name: linux2.sh
# Author: chuangfeng.wang
# E-mail: chuangfeng.wang@qunar.com
# Created Time: 2017年07月10日 星期一 11时59分32秒
#########################################################################
#!/bin/bash

# 上传文件到远程服务器
# 提供文件路径作为第一个参数

local_file=$1
remote_dir=/tmp

if [ ! -f "$local_file" ]; then
   echo "error: 提供的本地文件不存在，请确认"
   exit 1
fi

scp "$local_file" chuangfeng.wang@l-etl2.wap.cn5:$remote_dir

