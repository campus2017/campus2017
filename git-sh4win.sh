# 启动SSH认证客户端服务
eval "$(ssh-agent -s)"

# 添加SSH key
ssh-add ~/.ssh/manlixin-github
