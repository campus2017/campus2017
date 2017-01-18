#!/bin/bash

if ! hash $1 2>/dev/null ; then
    echo "not exit"
else
    echo "exit"
fi