#!/bin/bash

cat access_log | awk '{a[$1]+=1;} END {for(i in a) printf("d%,s%\n"a[i],i);}' | sort -n | tail -10

