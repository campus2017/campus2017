#!/bin/bash

find . -name "*.log" | xargs cat
        | cut -d' ' -f1 | sort | uniq -c | sort -k1 -nr | head -10
