#!/bin/bash
#copy file from local to dest

scp -r ./dir1 l-test.dev.cn1@192.168.231.130:/tmp
#192.168.231.130修改成目标机ip地址