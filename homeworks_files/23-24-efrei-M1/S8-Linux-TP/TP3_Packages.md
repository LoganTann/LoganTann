# TP3

Logan TANN

```bash
# Exercice 1 - Compression Gzip au -fichier etc.tar.gz
sudo tar zcf etc.tar.gz /etc

# Exercice 2 - Compression bzip (jcf)
sudo tar jcf etc.tar.bz2 /etc

# Exercice 3
ls -lh
# Résultat :
#  -rw-r--r-- 1 root  root  379K Apr 26 09:40 etc.tar.bz2
#  -rw-r--r-- 1 logan logan 405K Apr 26 09:37 etc.tar.gz

# Exercice 4
mkdir /tmp/restore1
sudo tar zxf ./etc.tar.gz -C /tmp/restore1

# Exercice 5
mkdir /tmp/restore2
sudo tar jxf ./etc.tar.bz2 -C /tmp/restore2

# Exercice 6
sudo apt install nsnake -y

# Exercice 7
dpkg -S /usr/bin/passwd # à quel package appartient ce fichier
dpkg -s tar | grep Version # Extraire la version dans les infos du paquet
dpkg -L tar | grep bin/ # trouver le fichier binaire de tar
dpkg -L systemd | grep systemctl # Est-ce que systemd installe systemctl
dpkg --list | wc -l # Nombre de packages installés sur la machine

# Exercice 8
sudo apt install apache2
curl localhost:80 | less
sudo apt purge apache2 # cleanup

# Exercice 9
mkdir apache && cd apache
wget https://dlcdn.apache.org/httpd/httpd-2.4.59.tar.bz2
tar xjf ./httpd-2.4.59.tar.bz2
cd httpd-2.4.59/

# fix WSL spécifique avant d'installer APR (https://github.com/microsoft/WSL/issues/5548#issuecomment-2016968256)
#  sudo rm /usr/lib/wsl/lib/libcuda.so /usr/lib/wsl/lib/libcuda.so.1
#  sudo ln -s /usr/lib/wsl/lib/libcuda.so.1.1 /usr/lib/wsl/lib/libcuda.so.1
#  sudo ln -s /usr/lib/wsl/lib/libcuda.so.1 /usr/lib/wsl/lib/libcuda.so
#  sudo ldconfig

# Installation des dépendances de dev
sudo apt install build-essential # Compilateur
sudo apt install libapr1 libapr1-dev libaprutil1-dev # APR et APR-utils
sudo apt install libpcre3 libpcre3-dev # Perl

./configure
make
sudo make install

/usr/local/apache2/apachectl -k start
curl localhost:80 | less

# Cleanup
/usr/local/apache2/apachectl -k stop
sudo checkinstall
sudo dpkg -r httpd
sudo rm -rf /usr/local/apache2
make clean
sudo apt purge libapr1 libapr1-dev libaprutil1-dev libpcre3-dev
sudo apt autoremove
```

![Untitled](TP3%20eefa65fe816545a2b8d9fb3c0694db97/Untitled.png)