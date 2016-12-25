#!/usr/bin/expect

# 在执行前需要安装ecpect

# 获取远程主机ip
ip=`nslookup l-test.dev.cn1 | grep Address | tail -n 1 | awk '{print $2}'`

##判断是否存在expect,不存在就安装
#if ! hash expect 2>/dev/null `; then
#    apt-get install expect
#fi

#使用expect 和 scp
set timeout 20

set local_file dir1
set remote_path /tmp
set remote_user root
set passwd linux2

spawn scp $local_file $remote_user@$ip:$remote_path

expect{
    "*assword:"{
        set timeout 1000
        send "$passwd\r"
        exp_continue
    }
    "*es/no"{
        send "yes\r"
        exp_continue
    }
}

