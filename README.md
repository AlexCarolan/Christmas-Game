# Christmas-Game

Compile and run the code with the commands:

	javac -cp jsfml.jar;. *.java
	java  -cp jsfml.jar;. Game

ToDo:

Christmas Room
1. display collectibles from each level

Platform Game
1. when lives drops to zero and causes game restart, then lives should reset to 3
2. add hazards (needed for kitchen)
3. display n/m items collected
4. consider adding moving obstacles

Puzzle Game
1. Puzzle class needs to return true on successful completion (like platformGame) - 
   needed for setting menu options correctly
   Can't do this while class is static (while file contains main)
2. Do Traffic puzzle (perhaps using joined/grouped tiles)

Graphics - Leo
1. images for Level 4 houses/roofs
2. Level4 Sprite: Christmas Elf in sleigh
3. Christmas Room images (bare, + tree, +tree+decorations, +tree+decor+food, +tree+decor+food+gifts)

Misc - Alex
1. Improve food puzzle graphics
2. Collected items tracker
3. consider adding timer
