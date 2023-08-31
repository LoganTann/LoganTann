#include <iostream>
#include "numbers.h"
#include "nbUtils.h"

int main() {
    std::string input1, input2;
    std::cout << "Entrez un grand nombre: ";
    std::cin >> input1;
    Number_t* nb1 = createNumberFromString(input1);

    std::cout << "Entrez un autre grand nombre: ";
    std::cin >> input2;
    Number_t* nb2 = createNumberFromString(input2);

    Number_t* sum = addNumbers(nb1, nb2);
    std::cout << "Somme des deux nombres = ";
    printNumber(sum);

    std::cout << std::endl;
    return 0;
}