#!/bin/sh
# Copyright (c) 2013 Xu,Chiheng(徐持恒) (chiheng.xu@gmail.com).  All rights reserved.
# Use is subject to license terms.

# $0 is the absolute path of the current script
CURRENT_DIRECTORY=${0%/*}
# konsole must specify --workdir=..., otherwise, different konsole instances will interfere
nohup konsole --nofork --workdir="${CURRENT_DIRECTORY}" -e bash -c 'mkdir -p konsole_openpty_build && cd konsole_openpty_build && cmake -D CMAKE_BUILD_TYPE=Release ../konsole_openpty && make && echo "Completed !"; read;' &
