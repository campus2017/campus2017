#!/bin/bash

#Linux环境下
#拷贝单个文件至远程主机
scp /home/administrator/test/test.txt root@192.168.1.100:/root/

#拷贝本机/home/administrator/test整个目录至远程主机192.168.1.100的/root目录下
scp -r /home/administrator/test/ root@192.168.1.100:/root/