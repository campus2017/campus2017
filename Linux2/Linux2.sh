#使用ssh命令登陆服务器
ssh -l wangpeng -p 1-test.dev.cn1 [port] [ip]
#复制目录，将本地拷到远程
scp -r dir1 root@ [1-test.dev.cn1 ip] :/tmp
