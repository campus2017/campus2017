#!/bin/bash
ip=`nslookup l-test.dev.cn1 | grep Address | tail -n 1 | awk '{print $2}'`
scp dir1 root@$ip:/tmp