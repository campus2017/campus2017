#!/bin/sh

#please input filename

echo -n "please input filename:"

read file

scp dir1/$file root@l-test.dev.cn1:/tmp
