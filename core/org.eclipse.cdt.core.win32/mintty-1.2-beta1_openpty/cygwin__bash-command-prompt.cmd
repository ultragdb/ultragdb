@echo off
@rem Copyright (c) 2014 Xu,Chiheng(徐持恒) (chiheng.xu@gmail.com).  All rights reserved.
@rem Use is subject to license terms.


@set PATH="%CYGWIN_DIR%\bin";%PATH%

start mintty.exe /bin/bash

@rem If you use Windows console that launched by cmd.exe to run bash, when you close the console with the "close" button, bash has no chance to save commnand history. You must input the "exit" command, the command history can then be saved.
@rem But if you use mintty to run bash, when you close mintty with the "close" button, bash may probably catch a signal, it has chance to save command history.
@rem cd "../.." && bash.exe
