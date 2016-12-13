# usage: scp [-12346BCpqrv] [-c cipher] [-F ssh_config] [-i identity_file]
#             [-l limit] [-o ssh_option] [-P port] [-S program]
#             [[user@]host1:]file1 ... [[user@]host2:]file2
# 其中 filename 是要拷贝的文件， ubuntu 是目标机用户名
# 省略用户名将使用本地用户名


scp dir1/filename ubuntu@l-test.dev.cn1:/tmp



# 若没有配置 ssh 免密码登录，则需要输入用户名在目标机上的密码
# 配置ssh 免密码登录：
# 首先生成本机的密钥对：
# me@local $ ssh-keygen -t rsa
# 一路回车使用默认选项，会在用户目录下的 .ssh/ 下生成两个文件，一个私钥 id_rsa 一个公钥id_rsa.pub
# 把公钥的内容加入目标机的 ~/.ssh/authorized_keys 文件（可能不存在，需要创建）尾部即可
# e.g.:
# 先用 scp 命令把公钥传到目标机(need password)
# me@local $ scp ~/.ssh/id_rsa.pub ubuntu@l-test.dev.cn1:~/
# 然后在目标机上把文件内容加入 authorized_keys 尾部
# ubuntu@l-test.dev.cn1 $ cat ~/id_rsa.pub >> ~/.ssh/authorized_keys

# 另外一个命令：rz/sz
# 上传：sudo rz -be （打开对话框选择文件添加后点击上传
# 下载：sudo sz -be filename  （默认下载到本机“下载”文件夹）