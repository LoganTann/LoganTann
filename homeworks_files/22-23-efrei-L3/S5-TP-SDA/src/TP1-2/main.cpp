#include <iostream>
#include "histo.h"
#include "utils.h"

/* 
 * Commandes du prompt :
 * - texte : ajoute une entrée à la position courante de l'historique
 *           Note : Si la position courante est en arrière, alors l'historique qui suivra sera supprimé
 * - x pour supprimer le __dernier__ élément de l'historique
 * - < pour aller à l'élément précédent de l'historique (si possible)
 * - > pour aller à l'élément suivant de l'historique (si possible)
 * - exit pour fermer le shell.
 * 
 * Exemple d'entrée :
 * >  page1 page2 page3 page4 pageX x < < < > pageC pageD < exit
 * Produit : 
 * >  - [ ] page1
 * >  - [ ] page2
 * >  - [x] pageC
 * >  - [ ] pageD
 */

int main(int argc, char** argv) {
    historique_t* histo = startBrowser();
    while (true) {
        std::string input;
        clearScr();
        std::cout << "EFREI BROWSER - Le navigateur qui ne sait que gérer qu'un historique." << std::endl
                  << "CMDs : '<' reculer, '>' avancer, 'x' suppr dernier, 'exit' quitter" << std::endl
                  << "Autre : le texte entré est ajouté dans l'histo à la position actuelle" << std::endl
                  << "        (les éléments qui suivent seront supprimés)." << std::endl
                  << "------ Historique ---------------------------------------------------" << std::endl;
        printHistory(histo);
        std::cout << "---------------------------------------------------------------------" << std::endl;
        std::cout << "Page actuelle : " << getCurrentPage(histo) << std::endl;
        std::cout << "---------------------------------------------------------------------" << std::endl;
        std::cout << "$ ";
        std::cin >> input;

        if (input == "exit") {
            std::cout << "Au revoir." << std::endl;
            return 0;
        }
        if (input == "<") {
            moveCursorPrev(histo);
        } else if (input == ">") {
            moveCursorNext(histo);
        } else if (input == "x") {
            removeLastEntry(histo);
        } else {
            addEntry(histo, input);
        }
    }
}
