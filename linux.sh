#!/bin/bash

#scp -r current_dir remote_usernan@remote_ip:destination_dir

#remote
scp -r ./dir1 root@l-test.dev.cn:/tmp

#localhost test
scp -r ./scptest root@localhost:/usr
