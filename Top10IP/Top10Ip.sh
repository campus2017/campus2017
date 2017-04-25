awk '{
         a[$1] += 1;
     } END {
         for (i in a) print a[i], i;
     }' $1 |sort -n -r -k 2 | head -10
