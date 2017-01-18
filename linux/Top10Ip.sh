#!/usr/bin/env bash

if [ $# -ne 1 ]
then
    echo "Usage: ./Top10TP.sh log"
fi

cat $1 | awk '{print $1}' | sort | uniq -c | sort -t ' ' -k 1nr | head -10 | awk '{print $2}' 
