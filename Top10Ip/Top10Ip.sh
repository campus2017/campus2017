#!/usr/bin/expect
cat data.log | sort | uniq -c | sort -n -r | head -n 10 >> res.log