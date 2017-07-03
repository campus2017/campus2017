#!/bin/sh

#please input filename

echo -n "please input filename:"

read name

cat $name | cut -d " " -f 1 | sort | uniq -c | sort -k 1 -n -r | head -n 10
