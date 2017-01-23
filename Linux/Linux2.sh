#linux下如何从本地目录dir1拷贝一个文件到目标机l-test.dev.cn1的/tmp目录下？
scp -r dir1 root@I-test.dev.cn1:/tmp
#基本语法： 
# scp [参数] source target

#常见参数 
#-v ： 显示进度，可以用来查看连接、认证或是配置错误
#-r ： 赋值目录
#-C ：使能压缩选项 
#-P ：选择端口
#-4 ： 强行使用 IPV4 地址
#-6 ： 强行使用 IPV6 地址

#常见的使用方式：
#将本地复制到远程
# scp local_file remote_username@remote_ip:remote_folder
# scp local_file remote_username@remote_ip:remote_folder/remote_file
# scp local_file remote_ip:remote_folder
# scp local_file remote_ip:remote_folder/remote_file