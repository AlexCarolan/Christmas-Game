# Christmas-Game

Compile and run the code with the commands:

	javac -cp jsfml.jar;. *.java
	java  -cp jsfml.jar;. Game

ToDo:

Platform Game
1. Sort out jumping (currently too high, and sometimes seems to fall off bottom of screen)
2. make key collectibles flash (but not other collectibles)
3. consider adding hazards

Puzzle Game
1. Puzzle class needs to return true on successful completion (like platformGame) - 
   needed for setting menu options correctly
   Can't do this while class is static (while file contains main)
2. If cursor moves off puzzle window onto ChristmasRoom window, then puzzle window seems
   to lose focus and can't be closed (or clicked on to re-focus)
3. Finish tree bauble puzzle
4. Do Maze puzzle
5. Do Traffic puzzle (perhaps using joined/grouped tiles)

Design - Gary
1. Design layout of all platform levels - where are the various platforms and obstacles

Graphics - Leo
1. platform images
2. Christmas Room images (bare, + tree, +tree+decorations, +tree+decor+food, +tree+decor+food+gifts)
3. Exit door for each platform level

Misc - Alex
1. Add lives
2. Improve puzzle graphics
3. Collected items tracker
