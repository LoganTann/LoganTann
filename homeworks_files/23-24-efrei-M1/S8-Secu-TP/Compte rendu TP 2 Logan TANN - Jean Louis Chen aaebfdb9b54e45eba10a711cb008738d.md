# Compte rendu TP 2 / Logan TANN - Jean Louis Chen

# TP 2

## Configuration de la machine

J’ai importé l’image de la machine métasploitable et assigné la seule carte réseau au réseau host only.

![Untitled](Compte%20rendu%20TP%202%20Logan%20TANN%20-%20Jean%20Louis%20Chen%20aaebfdb9b54e45eba10a711cb008738d/Untitled.png)

![Machine configurée avec une commande pour activer le clavier azerty.](Compte%20rendu%20TP%202%20Logan%20TANN%20-%20Jean%20Louis%20Chen%20aaebfdb9b54e45eba10a711cb008738d/Untitled%201.png)

Machine configurée avec une commande pour activer le clavier azerty.

## Trouver le préfixe IP de la machine

Pour trouver le préfixe IP de la machine, si elle est connectée sur notre réseau, il suffit de se baser sur son masque. Si nous sommes connectés sur le même réseau et avec le même masque, alors nous avons le même préfixe IP.

Dans mon cas : 192.168.85.x

Ou sinon écouter les paquets qui circulent sur le réseau, comme suggéré dans la section suivante. Ce serait une bonne technique dans des cas réels. On appelle cette opération **le scanning**.

## Trouver l’IP de la machine

Comme suggéré dans le TP, nous allons utiliser Nmap. La documentation recommande un scan TCP avec l’option `-sS` (c’est l’option par défaut)

Après une courte phase de scan, nmap a trouvé l’IP de la machine métasploitable, mais aussi les ports qui semblent ouvert (et à quel service ils semblent être associés)

![Untitled](Compte%20rendu%20TP%202%20Logan%20TANN%20-%20Jean%20Louis%20Chen%20aaebfdb9b54e45eba10a711cb008738d/Untitled%202.png)

Il a aussi été capable de découvrir les autres machines (la locale et nous-même)

![Untitled](Compte%20rendu%20TP%202%20Logan%20TANN%20-%20Jean%20Louis%20Chen%20aaebfdb9b54e45eba10a711cb008738d/Untitled%203.png)

Il existe d’autres types de scans. En lisant la documentation avec `man nmap`, on apprend que le scan par défaut (TCP SYN) est très rapide et relativement discret. 

Le scan UDP est fait pour découvrir des services UDP mais la nature du protole le rend plus lent.

On a les ping scans, qui sont moins bruyant. Il existe des options pour réduire la fréquence d’émission.

## Quels sont les services lancés sur la machine.

Beaucoup. On reconnait les classiques FTP, SSH et HTTP. Pour obtenir la version des services, on utilisera le scan `-sV`

![Untitled](Compte%20rendu%20TP%202%20Logan%20TANN%20-%20Jean%20Louis%20Chen%20aaebfdb9b54e45eba10a711cb008738d/Untitled%204.png)

## Chercher une vulnérabilité qui affecte la version de cette machine et exploiter.

Le serveur FTP est vsftpd 2.3.4. 

Après une courte recherche sur google, on apprend l’existence d’une blackdoor écrite par une personne mal intentionné dans cette version. Pour l’activer, il suffit de terminer le nom d’utilisateur FTP par un smiley.

Donc, je me connecte en suffixant mon nom d’utilisateur par un smiley, puis j’ouvre un shell netcat sur le port 6200.

![Untitled](Compte%20rendu%20TP%202%20Logan%20TANN%20-%20Jean%20Louis%20Chen%20aaebfdb9b54e45eba10a711cb008738d/Untitled%205.png)

![Untitled](Compte%20rendu%20TP%202%20Logan%20TANN%20-%20Jean%20Louis%20Chen%20aaebfdb9b54e45eba10a711cb008738d/Untitled%206.png)

## Bonus : chercher dans metasploit un exploit de cette vulnérabilité

- Utiliser la commande `search vsftp` pour trouver les exploits connus sur vsftp.
- Choisir l’exploit (1, car on veut utiliser la blackdoor)
- Changer la cible (`set RHOSTS 192.168.85.129`)
- Démarrer “exploit”
- Dès que la console est connectée, c’est bon. `whoami` pour tester qui on est sur la blackdoor.

![Untitled](Compte%20rendu%20TP%202%20Logan%20TANN%20-%20Jean%20Louis%20Chen%20aaebfdb9b54e45eba10a711cb008738d/Untitled%207.png)

# TP 2.BIS

## Partie 1

```jsx
// TODO
```

![Untitled](Compte%20rendu%20TP%202%20Logan%20TANN%20-%20Jean%20Louis%20Chen%20aaebfdb9b54e45eba10a711cb008738d/Untitled%208.png)

- Comment faire pour scanner sa machine
    - On définis HOST pour être “localhost”

## Partie 2

Nous avons utilisé optparse pour créer deux options et démarrer la fonction correctement.

![Untitled](Compte%20rendu%20TP%202%20Logan%20TANN%20-%20Jean%20Louis%20Chen%20aaebfdb9b54e45eba10a711cb008738d/Untitled%209.png)

![Untitled](Compte%20rendu%20TP%202%20Logan%20TANN%20-%20Jean%20Louis%20Chen%20aaebfdb9b54e45eba10a711cb008738d/Untitled%2010.png)

## Partie 3

![Untitled](Compte%20rendu%20TP%202%20Logan%20TANN%20-%20Jean%20Louis%20Chen%20aaebfdb9b54e45eba10a711cb008738d/Untitled%2011.png)