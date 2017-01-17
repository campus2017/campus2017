#!/bin/bash
# linux2

echo "begin..."

if [ ! -n "$1" ]; then
	echo "no file found"
else
	scp dir1/$1 root@l-test.dev.cn1:/tmp
fi

echo "end..."