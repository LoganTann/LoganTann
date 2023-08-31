#ifndef SYSTEM_H
#define SYSTEM_H

#include <iostream>
#include <SFML/Graphics.hpp>
#include <X11/Xatom.h>
#include <X11/extensions/shape.h>

#define _NET_WM_STATE_REMOVE        0    // remove/unset property
#define _NET_WM_STATE_ADD           1    // add/set property
#define _NET_WM_STATE_TOGGLE        2    // toggle property

bool setShape(Window wnd, const sf::Image& image);

bool defaultProp(Window window);

bool addProp32(char* propName, char* propContent, Window window, bool isXmsg = false);
void addProp32_Xchange(Display* display, Window window, Atom wmStateName, Atom wmStateContent);
void addProp32_Xmsg(Display* display, Window window, Atom wmStateName, Atom wmStateContent);
//bool setTransparency(Window wnd, unsigned char alpha);
//std::string exec(const char* cmd);
std::array<int, 4> net_get_workarea();
std::string exec(const char* cmd);
#undef None
#endif
