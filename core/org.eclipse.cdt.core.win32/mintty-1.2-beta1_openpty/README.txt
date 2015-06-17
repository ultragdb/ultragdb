中文

http://mirrors.163.com/cygwin/x86_64/release/mintty/




Use this command to anonymously check out the latest project source code:


# Non-members may check out a read-only working copy anonymously over HTTP.
svn checkout http://mintty.googlecode.com/svn/trunk/ mintty-read-only


#in one console
OPENPTY=  ./mintty_openpty/mintty.exe
/dev/pty4


#in another console
gdb
file emacs
set inferior-tty /dev/pty4
show inferior-tty
run

还可以使用tee vi bash等交互性的程序来代替emacs

