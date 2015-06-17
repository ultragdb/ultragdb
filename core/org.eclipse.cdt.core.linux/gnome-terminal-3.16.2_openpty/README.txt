



wget http://mirrors.kernel.org/fedora/releases/22/Everything/source/SRPMS/g/gnome-terminal-3.16.2-1.fc22.src.rpm
wget http://mirrors.kernel.org/fedora/releases/22/Everything/source/SRPMS/v/vte291-0.40.2-1.fc22.src.rpm


rpm2cpio gnome-terminal-3.16.2-1.fc22.src.rpm  | cpio -i 
rpm2cpio vte291-0.40.2-1.fc22.src.rpm  | cpio -i 




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






