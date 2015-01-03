@echo off
@rem Copyright (c) 2013 Xu,Chiheng(Ðì³Öºã) (chiheng.xu@gmail.com).  All rights reserved.
@rem Use is subject to license terms.


@set PATH="%CYGWIN_DIR%\bin";%PATH%

start mintty.exe --hold=always --exec /bin/bash -c 'cd mintty_openpty; make clean; echo Completed !'
