# Compte rendu TP 1 / Logan TANN - Jean Louis Chen

Date rendu: 2 février 2024

# Partie 1

## Section 1 : Installation VM

Pour cette partie, j’ai choisi d’installer Lubuntu (car j’avais déjà l’ISO et dérivé debian) sur VmWare (car compatible avec HyperV).

J’ai mis en place les deux interfaces en équivalent VMWare

![Untitled](Compte%20rendu%20TP%201%20Logan%20TANN%20-%20Jean%20Louis%20Chen%205a5123c991e540128c43e32caf05c6e9/Untitled.png)

![Untitled](Compte%20rendu%20TP%201%20Logan%20TANN%20-%20Jean%20Louis%20Chen%205a5123c991e540128c43e32caf05c6e9/Untitled%201.png)

Nous avons bien internet et le DNS qui fonctionnent.

![Untitled](Compte%20rendu%20TP%201%20Logan%20TANN%20-%20Jean%20Louis%20Chen%205a5123c991e540128c43e32caf05c6e9/Untitled%202.png)

## Section 2 : Config réseau

1. Affichez les adresses IP et les masques réseau de vos cartes réseau actives.
> Avec la commande `ip address`
2. Afficher les adresses IP et les masques réseau sous forme abrégée et colorée.
> En ajoutant l’option `-h -p -c` (human readable, pretty, color)
3. Afficher les adresses IP version 4 uniquement et les masques réseau sous forme abrégée et colorée.
> En ajoutant l’option -S
    
    ![Untitled](Compte%20rendu%20TP%201%20Logan%20TANN%20-%20Jean%20Louis%20Chen%205a5123c991e540128c43e32caf05c6e9/Untitled%203.png)
    
4. Afficher l’adresse MAC. Peut-on la changer ?
> En utilisant la commande `ip neigh`
    
    ![Untitled](Compte%20rendu%20TP%201%20Logan%20TANN%20-%20Jean%20Louis%20Chen%205a5123c991e540128c43e32caf05c6e9/Untitled%204.png)
    
5. Affichez les tables de routage (et donc l’adresse de la passerelle par défaut)
> En utilisant la commande `ip route list`
6. Quelle est le résultat de la commande ip link show enp0s3 ?
> numéro: interface: <type connexions supportées> mtu [minTrameUnit] ??? état [etat] mode group ??
link/[type lien] adresse mac et masque
nom alternatif
    
    ![Untitled](Compte%20rendu%20TP%201%20Logan%20TANN%20-%20Jean%20Louis%20Chen%205a5123c991e540128c43e32caf05c6e9/Untitled%205.png)
    

**Affichez la connectivité d’une interface ethernet avec la commande ethtool**

![Untitled](Compte%20rendu%20TP%201%20Logan%20TANN%20-%20Jean%20Louis%20Chen%205a5123c991e540128c43e32caf05c6e9/Untitled%206.png)

**Affichez la liste des passerelles traversées pour atteindre google.com**

En utilisant la commande traceroute. 

![Pour une raison inconnue, cela ne fonctionne pas alors que la machine a accès à internet.](Compte%20rendu%20TP%201%20Logan%20TANN%20-%20Jean%20Louis%20Chen%205a5123c991e540128c43e32caf05c6e9/Untitled%207.png)

Pour une raison inconnue, cela ne fonctionne pas alors que la machine a accès à internet.

**Affichez la configuration réseau traditionnelle d’un système Debian (cat
/etc/network/interfaces)**

Ok mauvaise idée d’utiliser ubutu car le fichier /etc/network/interfaces n’existe plus depuis la version 17.04, le fichier de configuration a été remplacé par **Netplan**.

Pour la suite du TP, je vais configurer avec le network manager !!

**Rajouter dans /etc/network/interfaces les lignes de configuration de la carte enp0s8 en
statique puis sauvegarder le fichier (voir Fig 2)**

J’ai donc configuré manuellement avec l’interface graphique. Le ping fonctionne.

![Untitled](Compte%20rendu%20TP%201%20Logan%20TANN%20-%20Jean%20Louis%20Chen%205a5123c991e540128c43e32caf05c6e9/Untitled%208.png)

![Untitled](Compte%20rendu%20TP%201%20Logan%20TANN%20-%20Jean%20Louis%20Chen%205a5123c991e540128c43e32caf05c6e9/Untitled%209.png)

# Partie 2 : Création d’une seconde VM et serveur web

J’ai cloné la VM et changé la configuration de la seconde interface réseau. Les deux machines peuvent communiquer entre elles.

![Untitled](Compte%20rendu%20TP%201%20Logan%20TANN%20-%20Jean%20Louis%20Chen%205a5123c991e540128c43e32caf05c6e9/Untitled%2010.png)