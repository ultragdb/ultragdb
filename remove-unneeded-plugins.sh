#!/bin/sh
# Copyright (c) 2009-2012  Xu,Chiheng(徐持恒) (chiheng.xu@gmail.com).  All rights reserved.
# Use is subject to license terms.

# $0 is the absolute path of the current script
CURRENT_DIRECTORY=${0%/*}
# konsole must specify --workdir=..., otherwise, different konsole instances will interfere
nohup konsole --nofork --workdir="$CURRENT_DIRECTORY" -e bash remove-unneeded-plugins.sh.txt
