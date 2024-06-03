# TP4

Logan TANN

## Exercice 1

Le firewall par défaut sur ubuntu est ufw. Le service est actif mais éteint.

```bash
# Voir le statut du service
logan@EFX-Logan:~/TP-Linux$ systemctl status ufw
● ufw.service - Uncomplicated firewall
     Loaded: loaded (/lib/systemd/system/ufw.service; enabled; vendor preset: enabled)
     Active: active (exited) since Thu 2024-05-02 08:05:21 CEST; 1h 24min ago
       Docs: man:ufw(8)
    Process: 119 ExecStart=/lib/ufw/ufw-init start quiet (code=exited, status=0/SUCCESS)
   Main PID: 119 (code=exited, status=0/SUCCESS)

May 02 08:05:21 EFX-Logan systemd[1]: Starting Uncomplicated firewall...
May 02 08:05:21 EFX-Logan systemd[1]: Finished Uncomplicated firewall.
```

Pour information, nous pouvons consulter les règles de pare-feu avec `iptables -L`

![Règles de pare feu. Docker est déjà installé sur ma machine linux d’où l’ajout de certaines règles par défaut.](TP4%207133e2ae95484a2e9d622289ac4374df/Untitled.png)

Règles de pare feu. Docker est déjà installé sur ma machine linux d’où l’ajout de certaines règles par défaut.

Activons le service au démarrage avec `systemctl enable`

```bash
# Activer le service au démarrage
logan@EFX-Logan:~/TP-Linux$ sudo systemctl enable ufw.service
[sudo] password for logan:
Synchronizing state of ufw.service with SysV service script with /lib/systemd/systemd-sysv-install.
Executing: /lib/systemd/systemd-sysv-install enable ufw
```

## Exercice 2

Changeons le run level à 1 avec `systemctl set-default [config]`

![le runlevel1 correspond au mode rescue](TP4%207133e2ae95484a2e9d622289ac4374df/Untitled%201.png)

le runlevel1 correspond au mode rescue

Quand nous redémarrons la machine, on a le mode rescue.

![Mode rescue. Penser à appyer sur [enter] pour atteindre le shell.](TP4%207133e2ae95484a2e9d622289ac4374df/Untitled%202.png)

Mode rescue. Penser à appyer sur [enter] pour atteindre le shell.

## Exercice 3

Pour changer de mode dynamiquement, on utilise la commande `systemctl isolate`

```bash
root@lubuntu1:~# systemctl isolate runlevel3.target
```

![Nous sommes passés en mode 3 (multi-utilisateur tty)](TP4%207133e2ae95484a2e9d622289ac4374df/Untitled%203.png)

Nous sommes passés en mode 3 (multi-utilisateur tty)

Et maintenant, passage au mode graphique

```bash
logan@lubuntu1:~$ systemctl isolate runlevel5.target
```

![mode 5 = mode graphique](TP4%207133e2ae95484a2e9d622289ac4374df/Untitled%204.png)

mode 5 = mode graphique

## Exercice 4

Réinitialisons le mode de démarrage par défaut.

```bash
logan@lubuntu:~$ sudo systemctl set-default runlevel5.target
Removed /etc/systemd/system/default.target.
Created symlink /etc/systemd/system/default.target → /lib/systemd/system/graphical.target.
Note: "graphical.target" is the default unit (possibly a runtime override).
```

## Exercice 5

Pour atteindre le menu grub, il faut appuyer longtemps sur `shift`.

Puis appuyer `e` pour modifier la config.

Pour modifier le run level, il suffit d’ajouter son code à la fin de la commande “linux”.

![Modif du mode de démarrage. Appuyer sur ctrl+x pour démarrer.](TP4%207133e2ae95484a2e9d622289ac4374df/Untitled%205.png)

Modif du mode de démarrage. Appuyer sur ctrl+x pour démarrer.

Nous sommes entrés en mode emergency

![Untitled](TP4%207133e2ae95484a2e9d622289ac4374df/Untitled%206.png)

## Exercice 6

Crééons d’abord le script permettant de créer le dossier. Nous allons le mettre dans /usr/local/sbin.

```bash
cd /usr/local/sbin
nano TP.sh
chmod +x TP.sh
```

![mkdir /tmp/testRC](TP4%207133e2ae95484a2e9d622289ac4374df/Untitled%207.png)

mkdir /tmp/testRC

Il faut ensuite créer le fichier de service.

```bash
cd /etc/systemd/system
nano TP.service
chmod +x TP.service
```

![Le fichier de service démarrera le script TP.sh](TP4%207133e2ae95484a2e9d622289ac4374df/Untitled%208.png)

Le fichier de service démarrera le script TP.sh

Et ne pas oublier d’activer ce dernier !

![systemctl enable (nom du fichier service)](TP4%207133e2ae95484a2e9d622289ac4374df/Untitled%209.png)

systemctl enable (nom du fichier service)

Maintenant, en changeant de mode de démarrage, on constate que le dossier a bien été créé.

![Nous pouvons “cd” dans le dossier /tmp/testRC](TP4%207133e2ae95484a2e9d622289ac4374df/Untitled%2010.png)

Nous pouvons “cd” dans le dossier /tmp/testRC