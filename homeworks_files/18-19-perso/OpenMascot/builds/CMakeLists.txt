cmake_minimum_required( VERSION 3.10 )
set(CMAKE_BUILD_TYPE Debug)
project(OpenMascot VERSION 1.1)

SET(CMAKE_CXX_FLAGS "-std=c++17")

add_definitions(-g -O2)

find_library( X11 X11 REQUIRED )
find_library( XEXT Xext REQUIRED )
find_library( SFML-SYSTEM sfml-system REQUIRED )
find_library( SFML-WINDOW sfml-window REQUIRED )
find_library( SFML-GRAPHICS sfml-graphics REQUIRED )

add_executable(OpenMascot ../src/main.cpp ../src/mascot.cpp ../src/system.cpp)

target_link_libraries(OpenMascot ${X11} ${XEXT} ${SFML-SYSTEM} ${SFML-WINDOW} ${SFML-GRAPHICS})