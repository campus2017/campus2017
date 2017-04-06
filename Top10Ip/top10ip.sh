#!/bin/bash

awk '{ips[$1]++} END{for (ip in ips) printf("%d %s\n", ips[ip], ip)}'\n $1 |sort -n -r | head -n 10
