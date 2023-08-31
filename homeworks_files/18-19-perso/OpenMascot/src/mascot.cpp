#include "mascot.h"
#include "system.h"

Mascot::Mascot(){
	// Create Window
	window.create( sf::VideoMode(128,128, 32), "Shimeji", sf::Style::None );
	winHandle = window.getSystemHandle();
	window.setFramerateLimit(60);
	// Hide taskbar icon if possible
	defaultProp(winHandle);
	workarea = net_get_workarea();

	// var init
	x = sf::Mouse::getPosition().x;
	pressing = grabbing = false;

	resetPhysics();
	v0.y = 1;

	// Image loading
	sf::Image newImg;
	sf::Texture newFrame;
	std::string spritesImg[] = {"img/shime1.png"};

	for (int i(0); i<std::size(spritesImg);i++){
		// Load and store all images defined by spritesImg[]
		frames.push_back(newFrame);
		framesI.push_back(newImg);
		framesI[i].loadFromFile(spritesImg[i]);
		frames[i].loadFromImage(framesI[i]);
	}
	currentFrame = sf::Sprite(frames[0]);
	setShape(winHandle, framesI[0]);
}
void Mascot::setPos(int x=0, int y=0){
	if ( x != 0 )
		this->x = x;
	if ( y != 0 )
		this->y = y;
}
std::string Mascot::update(){
	sf::Event event;
	const int dt = tick();

	while (window.pollEvent(event)) {

		const sf::Vector2i currentMousePos = sf::Mouse::getPosition();

		if (event.type == sf::Event::Closed || (event.type == sf::Event::KeyPressed && event.key.code == sf::Keyboard::Escape)){
			return "quit";

		} else if (event.type == sf::Event::MouseButtonPressed && event.mouseButton.button == sf::Mouse::Left){

			if ( (!grabbing) or clickTime > 200){
				// the moves averages are computed in 200ms max. After, it will reset.
				clickTime = 0;
				pos0 = currentMousePos;
				grabbing = true;
			} else {
				clickTime += dt;
				x = currentMousePos.x;
				y = currentMousePos.y;
			}
		} else if (grabbing){
			if ( sf::Mouse::isButtonPressed(sf::Mouse::Left) ) {
				clickTime += dt;
				x = currentMousePos.x;
				y = currentMousePos.y;
			} else {
				grabbing = false;
				// determine velocity by mouse movement
				v0.x = currentMousePos.x - pos0.x;
				v0.y = 1.2 * (currentMousePos.y - pos0.y); // increase his value by 120%
				pos0 = currentMousePos; // reset pos0
			}
		}

	}

	if (!grabbing) {
		physics(dt);
	}

	window.setPosition(sf::Vector2i(x, y));
	window.clear(sf::Color::Transparent);
	window.draw(currentFrame);
	window.display();
	return "fine";
}

int Mascot::tick(){
	const int currentTime = clock.getElapsedTime().asMilliseconds();
	const int dt = currentTime - lastTime;
	lastTime = currentTime;
	return dt;
}

int Mascot::physics(const int dt) {
	if (!applyPhysics) {
		if (v0.x != 0 || v0.y != 0){
			applyPhysics = true;
			physics_t = 0;
		} else {
			return 1;
		}
	} else {
		physics_t += dt;

		y += v0.y * dt * 0.001;
		v0.y += 1500 * dt * 0.001;
		x = v0.x * 0.001 * physics_t + pos0.x;

		if (y > workarea[1] + workarea[3] - 128){ //wa.x + wa.h
			y = workarea[1] + workarea[3] - 128;
			resetPhysics();
		}/*
		if (x < 0 or x > 1280){
			physics_t = 0;
			x = (x<0)? 0 : 1280;
			v0 = sf::Vector2i(0,0);
			applyPhysics = false;
		}*/
	}
	return 0;
}
int Mascot::resetPhysics(){
	physics_t = 0;
	v0 = sf::Vector2i(0,0);
	applyPhysics = false;
}
