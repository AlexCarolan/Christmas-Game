# Christmas-Game

Compile and run the code with the commands:

	javac -cp jsfml.jar;. *.java
	java  -cp jsfml.jar;. Game

ToDo:

Christmas Room
1. display collectibles from each level

Platform Game
1. when lives drops to zero and causes game restart, then lives should reset to 3
2. make key collectibles flash (but not other collectibles)
3. add hazards (needed for kitchen)
4. display n/m items collected
5. sleigh level should move right automatically
6. consider adding moving obstacles

Puzzle Game
1. Puzzle class needs to return true on successful completion (like platformGame) - 
   needed for setting menu options correctly
   Can't do this while class is static (while file contains main)
2. Do Traffic puzzle (perhaps using joined/grouped tiles)

Graphics - Leo
1. platform images
2. Christmas Room images (bare, + tree, +tree+decorations, +tree+decor+food, +tree+decor+food+gifts)
3. Exit door for each platform level

Misc - Alex
1. Improve food puzzle graphics
2. Collected items tracker
3. consider adding timer
