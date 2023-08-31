#ifndef SHIMEJI_H
#define SHIMEJI_H

#include <iostream>
#include <SFML/Graphics.hpp>

#include <cmath>

class Mascot {
	//actually, not implemented to make more than 1 mascot.
public:
	Mascot();
	std::string update();
	int physics(const int dt);
	int tick();
	int resetPhysics();
	void setPos(int x, int y);
private :
	//sfml
	sf::RenderWindow window;
	sf::WindowHandle winHandle;
	sf::Clock clock;

	// Frames loaded
	std::vector<sf::Image> framesI;
	std::vector<sf::Texture> frames;

	sf::Sprite currentFrame;

	unsigned int lastTime, physics_t;
	int x,y, clickTime;
	bool pressing, grabbing, applyPhysics;
	sf::Vector2i v0, pos0;
	std::array<int, 4> workarea;
};


#endif
