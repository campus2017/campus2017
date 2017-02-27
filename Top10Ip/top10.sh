#!/bin/bash
walkDir() {
dirPath=$1
tmpfile='tmpfile'
for fn in `ls -A $dirPath`
 do
  pathName=$dirPath$fn
  if [ -f $pathName ]
   then
    awk '/^[0-9]/{print $1}' $pathName >> $tmpfile
  fi
 done
 echo $tmpfile
}

# start
tmpfile=`walkDir $1`
#calucate
awk '{ print $1 }' ${tmpfile} | sort | uniq -c | sort -rn | head -n 10 > res.log 
#delete tmpfile
rm -r ${tmpfile}
