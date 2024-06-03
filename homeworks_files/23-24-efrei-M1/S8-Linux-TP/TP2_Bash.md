# TP2

Logan TANN

## Exercice 1 et 2

```bash
#!/bin/bash

echo EXERCICE 1 ----

echo Reset TP 1...
# cleanup dossiers créés
sudo rm -rf /home/TPUSERS
# cleanup utilisateurs
for user in bartok mozart bach stravinsky;
        do sudo userdel $user -r;
done;
# cleanup groupes
for group in musicien compositeur interprete auteur peintre sculpteur; do
        sudo groupdel $group;
done;

echo Création groupes et user...
# Création groupes
for group in musicien compositeur interprete auteur peintre sculpteur; do
        sudo groupadd $group;
done;
# Ajout utilisateurs
sudo useradd bartok -g musicien -G compositeur,interprete -p IHaveAlzheimer
sudo useradd mozart -g musicien -G compositeur,auteur -p SuperStrongPassword
sudo useradd bach -g musicien -G peintre -p YetAnotherPassword
sudo useradd stravinsky -g compositeur -G interprete,sculpteur -p jePaieLEFREI

echo Création dossiers partagés...
# Dossier partagé sans permission d'écriture
sudo mkdir /home/TPUSERS
sudo chown `whoami` /home/TPUSERS
chmod 755 /home/TPUSERS
# Shared : Lecture écriture possible
mkdir /home/TPUSERS/shared
chmod 777 /home/TPUSERS/shared
# Lecture musicien : lire et traverser le dossier
mkdir /home/TPUSERS/lecture-musiciens
sudo chgrp musicien /home/TPUSERS/lecture-musiciens
chmod 750 /home/TPUSERS/lecture-musiciens
# Pour chaque groupe : 
for group in musicien compositeur interprete auteur peintre sculpteur; do
        # Un dossier
        mkdir /home/TPUSERS/$group;
        sudo chgrp $group /home/TPUSERS/$group;
        chmod 770 /home/TPUSERS/$group

        # Et un fichier partagé en RW
        touch /home/TPUSERS/$group/$group.txt;
        sudo chgrp $group /home/TPUSERS/$group/$group.txt;
        chmod 660 /home/TPUSERS/$group/$group.txt;
done;
# Pour chaque utilisateur
for user in bartok mozart bach stravinsky; do
        for group in `id -nG $user`; do
                # Créer un document propre pour chacun de ses groupes
                touch /home/TPUSERS/$group/$user.txt
                chmod 600 /home/TPUSERS/$group/$user.txt
                sudo chgrp $group /home/TPUSERS/$group/$user.txt;
                sudo chown $user /home/TPUSERS/$group/$user.txt
        done;
done;

echo DONE. \n

echo EXERCICE 2 --
# Installer pdf2text si ce n'est pas encore le cas
echo Installation pdf2text...;
sudo apt-get install poppler-utils -y>/dev/null
# Trouver le chemin
pdf2textPath=`which pdftotext`
if [ $? -eq 0 ]; then
        echo Path du PDF To Text : $pdf2textPath
        # Afficher le type de binaire
        file $pdf2textPath
else
        echo PdfToText introuvable ! Merci d'installer le paquet poppler-utils.
fi
```

## Exercice 2.3

Types de fichiers sous unix : 

- `-` - Fichier simple
- `d` - Dossier
- `l` - Lien symbolique
- `b` - Périphérique manipulant des données en bloc (ex: disque dur)
- `c` - Périphérique manipulant des données par caractères (ex : TTY)
- `p` - FIFO nommée (cas de communication IPC)
- `s` - socket (cas de communication réseau)