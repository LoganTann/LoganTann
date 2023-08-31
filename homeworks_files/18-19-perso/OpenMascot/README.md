OpenMascot
===================

**A shimeji-like software in cpp for Linux**

I noticed the original software "Shimeji" is very very slow... mostly in linux systems.

Shimeji is a japanese software, and his config files is in japanese too. Even the source-code is in japanese. It's a pain for me to understand everything !

Because i love this sofware, i'm trying to make a rewrite of this software in C++, but with a different system (I'm not based with the original system, it is my own, that use json for config files). Thus, you can't convert shimejis configuration files (xml) into openmascot configuration files (xml) because the system of openMascot is not a binding/fork of shimeji in C++ (in fact, it is possible to convert animations and their conditions, but it is not possible to convert behaviors)

### Compiling

**Linux** :
Using cmake/make (best) :
```bash
# Compile
cmake .
make

# Run the file :
cd src
./OpenMascot
```

Using G++ :
```bash
# Compile
cd src
g++ main.cpp system.cpp shimeji.cpp -lsfml-graphics -lsfml-window -lsfml-system -lX11 -lXext --std=c++17 -O3

# Run the file
./OpenMascot
```

Requires SFML-2.5.1 (64bit binaries available at sfml.org) and X11 + X11-extensions (for shaped window)



No windows/mac version ATM
