#include "system.h"

#include <iostream>
#include <SFML/Graphics.hpp>
#include <X11/Xatom.h>
#include <X11/Xproto.h>
#include <X11/extensions/shape.h>
#include <regex>

// Window params
bool defaultProp(Window window) {
	addProp32("_NET_WM_WINDOW_TYPE", "_NET_WM_WINDOW_TYPE_UTILITY", window);
	addProp32("_NET_WM_STATE", "_NET_WM_STATE_SKIP_TASKBAR", window);
	addProp32("_NET_WM_STATE", "_NET_WM_STATE_ABOVE", window, true);
	return true;
}

bool addProp32(char* propName, char* propContent, Window window, bool isXmsg) {
	Display* display = XOpenDisplay(NULL);
	Atom wmStateName = XInternAtom(display, propName, True);
	Atom wmStateContent = XInternAtom(display, propContent, True);
	if(wmStateName == 0) {
		std::cerr << "Oopsie on "<<propName<<"\n";
		return false;
	} else if (wmStateContent == 0) {
		std::cerr << "Oopsie on "<<propContent<<"\n";
		return false;
	}

	if (isXmsg) {
		addProp32_Xmsg(display, window, wmStateName, wmStateContent);
	} else {
		addProp32_Xchange(display, window, wmStateName, wmStateContent);
	}

	XFlush(display);
	XCloseDisplay(display);
	return true;
}

void addProp32_Xchange(Display* display, Window window, Atom wmStateName, Atom wmStateContent){
	/*add a window proprety using XChangeProperty*/
	XChangeProperty(display,   // connection to x server
	                     window,    // window whose property we want to change
	                     wmStateName,   // property name
	                     XA_ATOM, // type of property
	                     32,      // format of prop; can be 8, 16, 32
	                     _NET_WM_STATE_ADD,
	                     (unsigned char*) &wmStateContent, // actual data
	                     1         // number of elements
	                    );
}

void addProp32_Xmsg(Display* display, Window window, Atom wmStateName, Atom wmStateContent) {
	/*add a window proprety by sending Xclient message event*/
	XClientMessageEvent xclient;
	memset( &xclient, 0, sizeof (xclient) );

	xclient.type = ClientMessage;
	xclient.window = window;
	xclient.message_type = wmStateName; //gdk_x11_get_xatom_by_name_for_display( display, "_NET_WM_STATE" );
	xclient.format = 32;
	xclient.data.l[0] = _NET_WM_STATE_ADD; // the action : add ? _NET_WM_STATE_ADD : _NET_WM_STATE_REMOVE;
	xclient.data.l[1] = wmStateContent; //first property to alter : gdk_x11_atom_to_xatom_for_display (display, state1);
	xclient.data.l[2] = 0; //second property to alter : gdk_x11_atom_to_xatom_for_display (display, state2);
	xclient.data.l[3] = 0; //source indication (0-unk,1-normal app,2-pager)
	xclient.data.l[4] = 0;

	XSendEvent( display,
		DefaultRootWindow( display ),
		False,
		SubstructureRedirectMask | SubstructureNotifyMask,
		(XEvent *)&xclient
	);
}

bool setShape(Window wnd, const sf::Image& image) {
	const sf::Uint8* pixelData = image.getPixelsPtr();
	Display* display = XOpenDisplay(NULL);

	// Try to set the window shape
	int event_base;
	int error_base;
	if (XShapeQueryExtension(display, &event_base, &error_base))
	{
		Pixmap pixmap = XCreatePixmap(display, wnd, image.getSize().x, image.getSize().y, 1);
		GC gc = XCreateGC(display, pixmap, 0, NULL);

		XSetForeground(display, gc, 1);
		XFillRectangle(display, pixmap, gc, 0, 0, image.getSize().x, image.getSize().y);
		XSetForeground(display, gc, 0);

		for (unsigned int y = 0; y < image.getSize().y; y++)
		{
			for (unsigned int x = 0; x < image.getSize().x; x++)
			{
				if (pixelData[y * image.getSize().x * 4 + x * 4 + 3] == 0)
					XFillRectangle(display, pixmap, gc, x, y, 1, 1);
			}
		}

		XShapeCombineMask(display, wnd, ShapeBounding, 0, 0, pixmap, ShapeSet);
		XFreeGC(display, gc);
		XFreePixmap(display, pixmap);
		XFlush(display);
		XCloseDisplay(display);
		return true;
	}

	XCloseDisplay(display);
}

// Utils

std::string exec(const char* cmd) {
    std::array<char, 128> buffer;
    std::string result;
    std::unique_ptr<FILE, decltype(&pclose)> pipe(popen(cmd, "r"), pclose);
    if (!pipe) {
        throw std::runtime_error("popen() failed!");
    }
    while (fgets(buffer.data(), buffer.size(), pipe.get()) != nullptr) {
        result += buffer.data();
    }
    return result;
}

std::array<int, 4> net_get_workarea(){
	// Dirty fix
	std::string xpropWorkArea = exec("xprop -root _NET_WORKAREA");
	std::regex workAreaRegex("_NET_WORKAREA\\(CARDINAL\\) = (\\d+), (\\d+), (\\d+), (\\d+).*\\W*");
	std::smatch match;
	if (std::regex_match(xpropWorkArea, match, workAreaRegex)){
		return {std::stoi(match[1]), std::stoi(match[2]),
				std::stoi(match[3]), std::stoi(match[4]) };
	} else {
		std::cerr<<"No regex matches for xprop _NET_WORKAREA result"<<std::endl;
		std::cerr<<"OUTPUT : ["<<xpropWorkArea<<']'<<std::endl;
		return {0,0,0,0};
	}
	// Original code that don't work:
	/*
	Display* display = XOpenDisplay(NULL); //<<-- should be entered as arg.
	Atom XA_NET_WM_WORKAREA = XInternAtom(display, "_NET_WORKAREA", False);
	x = y = w = h = 0;
	// un-used :
	Atom typeReturn;
	int format;
	unsigned long n, extra;
	unsigned char* prop;

	int status = XGetWindowProperty( display, DefaultRootWindow(display), XA_NET_WM_WORKAREA,
				0L, 1L, False, XA_CARDINAL, &typeReturn, &format, &n, &extra, (unsigned char**)&prop);
	if (status == Success) {
		CARD32* val = (CARD32*) prop;
		if (val){
			std::cerr<<"_NET_WORKAREA : X: "<<val[0]<<" Y: "<<val[1]<<" W: "<<val[2]<<" H:"<<val[3]<<std::endl;
			x = val[0]; y = val[1];
			w = val[2]; h = val[3];
			XFree((char*) val);
			return true;
		} else {
			std::cerr<<"net_get_workarea:CARD32 val failed !!"<<std::endl;
		}
	} else {
		std::cerr<<"net_get_workarea:XGetWindowProperty failed !!"<<std::endl;
	}
	return false;
	*/
}
#undef None // None conflicts with SFML
