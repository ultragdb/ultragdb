 

 http://mirrors.163.com/fedora/releases/18/Fedora/source/SRPMS/k/konsole-4.9.4-1.fc18.src.rpm
 

rpm2cpio konsole-4.9.4-1.fc18.src.rpm  | cpio -i 


yum -v -y install desktop-file-utils kdelibs4-devel kde-baseapps-devel
yum -v -y install pkgconfig


yum -v -y install desktop-file-utils kdelibs-devel kde-baseapps-devel



./konsole_openpty_build/src/konsole
./konsole_openpty_build/lib/libkonsoleprivate.so
./konsole_openpty_build/lib/libkonsolepart.so
./konsole_openpty_build/lib/libkdeinit4_konsole.so





OPENPTY=  ./konsole_openpty_build/src/konsole






Konsole - Development Information
https://www.kde.org/applications/system/konsole/development
Clone Konsole source code:
git clone git://anongit.kde.org/konsole






