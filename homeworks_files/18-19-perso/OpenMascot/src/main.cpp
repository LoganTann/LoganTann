#include <iostream>
#include <SFML/Graphics.hpp>
#include "system.h"
#include "mascot.h"

int main(){
    std::cout<<"OpenMascot V1.1\n";
    std::cout<<"C++ shimeji-like software\n";
    std::cout<<"Under the MIT license. Copyright (c) 2019, 2020 Logan Tann\n\n";

    bool open(1);
	const short int i = 0;

	Mascot mascot;

    while (open) {
		const std::string state = mascot.update();
		if (state=="quit"){
			open = false;
		}
    }
    return 0;
}
