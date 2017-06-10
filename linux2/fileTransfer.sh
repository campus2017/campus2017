#!/bin/bash
#
#  suppose that the ip is 192.168.1.199
#
filename=$1

scp $filename l-test.dev.cn1@192.168.1.199:/tmp
