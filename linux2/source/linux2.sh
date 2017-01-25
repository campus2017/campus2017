#!/usr/bin/expect -f
#思路：两台系统之间的文件传递，我们可以理解为c/s系统，把一台看做服务器，另外一台则看做是一个客户端，这样我们就可以根据实际情况进行文件的传递

#1.ssh连接
#eg: ssh -l GuoChongyuan -p 8080 198.0.0.1
#ssh -l username   -p port   hostname or IP
ssh -l GuoChongyuan -p 8080 198.0.0.1

#发送文件
#eg:scp -P 8080 a.txt GuoChongyuan@198.0.0.1 :/tmp
#scp -P port local_file_path user_name@host_name or ip : remot_file_path
scp _P 8989 dir1 remote@-test.dev.cn1 :/tmp #如果是从自己的机器发送的话

#下载文件
#scp -P 22 username@hostname or ip:remotefilepath or ip:remotefilepath localfilepath
#eg:scp -P 22 kevin_u@192.168.1.1:/tmp/aa.txt ./
scp -P 8080 remote@-test.dev.cn1:/dir1 ./tmp #如果是要求别人给自己发文件的话
