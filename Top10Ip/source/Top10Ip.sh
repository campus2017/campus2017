#!/bin/bash
#@path the source path and file_name
#@num the number we want to show

#example:

#path = $1
#row = $2
#num = $3
#cat $path | #read file_content
#awk '{print $row}'| #get ip col
#sort | #sort_the_result
#uniq -c | #unique and count
#head -n $num
cd /tmp/test

echo "count ip" >> test_result.txt
echo "===========" >> test_result.txt

cat test |  #read file_content
awk '{print $1}'|     #get ip col
sort |                #sort_the_result
uniq -c |             #unique and count
head -n 10    >> test_result.txt       #head line

