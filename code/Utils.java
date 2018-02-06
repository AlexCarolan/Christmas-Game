/**
 * This class defines utilities and constants 
 */
public class Utils
{
	// define size of window for platform game
	public static final int PlatformGameWidth = 1024;
	public static final int PlatformGameHeight = 768;
	public static final int PlayerXPosition = PlatformGameWidth/2;
	public static final int PlayerYPosition = PlatformGameHeight - 60;
	
	// define maximum game Level 
	public static final int MaxLevel = 4;	// first level is 0.  Max level will be 3 when implementation finished

	public static final int MoveAmountX = 5;
	public static final int MoveAmountY = 2;
	public static final int JumpAmount = 60;
	public static final int Gravity = 2;
	public static final int MinGravity = 3;
	public static final int MaxGravity = 30;

	public static final int SleighGameLevel = 3;

	//  gravity gradually increases over time
	// except last level, where the sleigh floats like a feather
	public static final double GravityMultiplier[] = {1.04,1.04,1.04,1};

	// file path for Christmas Room image
	public static final String RoomImage[] = {"images\\christmasRoom\\bareRoom.png",	// game level 1 start
												"images\\christmasRoom\\bareRoom.png",	// game level 2 start
												"images\\christmasRoom\\fullRoom.png",	// game level 3 start
												"images\\christmasRoom\\fullRoom.png",	// game level 4 start
												"images\\christmasRoom\\fullRoom.png"};	// game level 4 end
	
	//File paths for sprites
	public static final String IdleRightPath = "Sprites\\Idle_Right\\Idle";
	public static final String IdleLeftPath = "Sprites\\Idle_Left\\Idle";
	public static final String RunningRightPath = "Sprites\\Running_Right\\Santa_Elf_Running";
	public static final String RunningLeftPath = "Sprites\\Running_Left\\Santa_Elf_Running";
	//public static final String SleighRightPath = "Sprites\\Sleigh_Right\\";
	public static final String Bauble1Path = "Sprites\\Baubles\\Bauble";
	public static final String AxePath = "Sprites\\Axe\\Axe";

	// file path for platform background images, and the width of these
	public static final String PlatformBackgroundImage[] = {"images\\platform\\forest.png",	// platform level 1
															"images\\platform\\kitchen.png",	// platform level 2
															"images\\platform\\attic.png",		// platform level 3
															"images\\platform\\rooftops.png"	// platform level 4
															};
	public static final int PlatformBackgroundWidth[] = {7564, 7564, 4000, 2*PlatformGameWidth+652};

	// define width, height, xPosition, yPosition for all the platforms for each level
	// to access this array: first index is level, second index is platform, 
	// third index is xPosition, yPosition, width, height of that platform
	public static final int PlatformPositions[][][] = {{ // gameLevel 1
														{0,700,1200,50},				// base 1
														{5300,700,1200,50},				// base 2
														{1150,600,100,50},	//3
														{1280,550,100,50},	//4
														{1405,510,100,50},	//5
														{1530,560,100,50},	//6
														{1680,610,200,100},	//7 
														{1550,420,300,50},	//8 frozen pond
														{1900,570,80,30},	//9
														{2050,590,80,30},	//10
														{2150,640,150,80},	//11
														{2200,440,400,80},	//12 bigger frozen pond
														{2360,670,100,50},	//13
														{2500,680,100,50},	//14
														{2650,670,100,50},	//15
														{2800,650,100,50},	//16
														{2900,630,100,50},	//17
														{3100,620,100,50},	//18
														{3300,670,100,50},	//19
														{3500,670,100,50},	//20
														{3650,670,100,50},	//21
														{3800,670,100,50},	//22
														{3950,670,100,50},	//23
														{4100,670,100,50},	//24
														{4250,670,100,50},	//25
														{4400,670,100,50},	//26
														{4550,670,100,50},	//27
														{4700,670,100,50},	//28
														{4850,670,100,50},	//29
														{5000,670,100,50},	//30
														{5150,670,100,50},	//31
														{5300,670,100,50},	//32
														{-250,500,250,400},		// left hand edge
														{6500,0,PlatformGameWidth/2,PlatformGameHeight+1}},	// right hand edge
														{ // gameLevel 2
														{0,700,5200,50},				// base 1
														{5300,700,1200,50},				// base 2
														{1150,600,100,100},	//3
														{1250,650,100,50},	//4
														{1400,500,100,50},	//5
														{1400,550,200,80},	//6
														{1700,600,300,100},	//7
														{1900,500,100,100},	//8
														{2050,540,150,80},	//9
														{2250,600,200,100},	//10
														{2600,620,50,80},	//11
														{2600,540,150,80},	//12
														{3200,500,200,200},	//13
														{3400,600,600,100},	//14
														{4300,550,250,150},	//15
														{4600,500,200,200},	//16
														{4800,600,400,100},	//17
														{4900,400,200,100},	//18
														{5000,300,100,100},	//19
														{-250,500,250,400},		// left hand edge
														{6500,0,PlatformGameWidth/2,PlatformGameHeight+1}},	// right hand edge
														{ // gameLevel 3
														{0,700,5200,50},				// base
														{700,650,100,50},	//2
														{750,600,100,50},	//3
														{800,500,50,100},	//4
														{800,450,100,50},	//5
														{850,400,100,50},	//6
														{900,300,50,100},	//7
														{900,250,100,50},	//8
														{950,200,100,50},	//9
														{1000,150,350,50},	//10
														{970,450,80,250},	//11
														{1100,550,100,50},	//12
														{1150,500,100,50},	//13
														{1200,450,100,50},	//14
														{1250,400,100,50},	//15
														{1300,350,100,50},	//16
														{1350,300,100,50},	//17
														{1400,250,100,50},	//18
														{1450,200,100,50},	//19
														{1500,150,450,50},	//20
														{1550,100,50,50},	//21
														{1300,650,150,50},	//22
														{1350,600,50,50},	//23
														{1450,500,150,50},	//24
														{1550,350,150,50},	//25
														{1750,300,250,300},	//26
														{1850,100,150,200},	//27
														{2000,200,100,500},
														{0,0,2,PlatformGameHeight}},	// left hand edge
														{ // gameLevel 4
														{0,PlatformGameHeight - 25,PlatformGameWidth,25},	// base
														{PlatformGameWidth+150,PlatformGameHeight - 50,500,50},
														{200,550,100,25},
														{0,0,2,PlatformGameHeight}}		// left hand edge
													};
	// define image for each platform on each level, in same order as PlatformPositions array
	public static final String PlatformImages[][] = {{ // gameLevel 1
														"images\\platform\\rocky-platform.png",	//base 1
														"images\\platform\\rocky-platform.png",	//base 2
														"images\\platform\\Rock_Platform.png",	//3
														"images\\platform\\Rock_Platform.png",	//4
														"images\\platform\\Rock_Platform.png",	//5	
														"images\\platform\\Rock_Platform.png",	//6
														"images\\platform\\Rock_Platform.png",	//7
														"images\\platform\\FrozenPond.png",		//8
														"images\\platform\\Rock_Platform.png",	//9
														"images\\platform\\Rock_Platform.png",	//10
														"images\\platform\\Rock_Platform.png",	//11
														"images\\platform\\FrozenPond_Two.png",	//12
														"images\\platform\\Rock_Platform.png",	//13
														"images\\platform\\Rock_Platform.png",	//14
														"images\\platform\\Rock_Platform.png",	//15
														"images\\platform\\Rock_Platform.png",	//16
														"images\\platform\\Rock_Platform.png",	//17
														"images\\platform\\Rock_Platform.png",	//18
														"images\\platform\\Rock_Platform.png",	//19
														"images\\platform\\Rock_Platform.png",	//20
														"images\\platform\\Rock_Platform.png",	//21
														"images\\platform\\Rock_Platform.png",	//22
														"images\\platform\\Rock_Platform.png",	//23
														"images\\platform\\Rock_Platform.png",	//24
														"images\\platform\\Rock_Platform.png",	//25
														"images\\platform\\Rock_Platform.png",	//26
														"images\\platform\\Rock_Platform.png",	//27
														"images\\platform\\Rock_Platform.png",	//28
														"images\\platform\\Rock_Platform.png",	//29
														"images\\platform\\Rock_Platform.png",	//30
														"images\\platform\\Rock_Platform.png",	//31
														"images\\platform\\Rock_Platform.png",	//32
														"images\\obstacles\\long-logpile.png",	//left hand edge
														"images\\obstacles\\ForestOne.png"},	// right hand edge
														{ // gameLevel 2
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png"},
														{ // gameLevel 3
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png"},
														{ // gameLevel 4
														"images\\platform\\Rock_Platform.png",
														"images\\platform\\Rock_Platform.png",
														"images\\platform\\Rock_Platform.png",
														"images\\platform\\Rock_Platform.png"}
													};
	// specify whether platform is a ceiling (can't be jumped up through)
	public static final boolean PlatformCeilings[][] = {{ // gameLevel 1
														true,	// base1
														true,	// base 2
														false,
														false,
														false,
														false,
														false,
														true,
														true,
														false,
														true,
														true,
														false,
														false,
														false,
														false,
														false,
														true,
														true,
														false,
														false,
														false,
														false,
														false,
														false,
														false,
														false,
														false,
														false,
														false,
														false,
														false,
														false,	// left edge
														false},	// right edge
														{ // gameLevel 2
														true,	// base1
														true,	// base 2
														false,
														false,
														true,
														true,
														false,
														false,
														true,
														false,
														true,
														true,
														false,
														false,
														false,
														false,
														false,
														true,
														true,
														false,	// left edge
														false},	// right edge
														{ // gameLevel 3
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														false,
														false,
														true,
														true,
														true,
														false},
														{ // gameLevel 4
														true,
														true,
														true,
														true}
													};


	// define width, height, xPosition, yPosition for the obstacles for each level
	// to access this array: first index is level, second index is obstacle, 
	// third index is xPosition, yPosition, width, height of that obstacle
	public static final int ObstaclePositions[][][] = {{ // gameLevel 1
														{680,660,50,50},
														{720,620,100,100},
														{800,670,50,50},
														{6113,660,50,50},
														{6150,620,100,100},
														{6230,620,100,100},
														{6195,555,100,100},
														{6230,525,50,50}},
														{ // gameLevel 2
														{680,660,50,50},
														{720,620,100,100},
														{800,670,50,50},
														{2350,570,40,40},
														{3150,660,50,50},
														{3680,560,50,50},
														{3720,520,100,100},
														{3800,570,50,50},
														{4420,520,40,40},
														{6113,660,50,50},
														{6150,620,100,100},
														{6230,620,100,100},
														{6195,555,100,100},
														{6230,525,50,50}},
														{ // gameLevel 3
														{2700,670,40,40},
														{2800,665,35,35}},
														{ // gameLevel 4
														{250,520,40,40},
														{800,PlatformGameHeight - 55,35,35}}
													};
	// define image for each obstacle on each level, in same order as ObstaclePositions array
	public static final String ObstacleImages[][] = {{ // gameLevel 1
														"images\\obstacles\\big-log.png",
														"images\\obstacles\\big-log.png",
														"images\\obstacles\\logs1.png",
														"images\\obstacles\\long-logpile.png",
														"images\\obstacles\\long-logpile.png",
														"images\\obstacles\\long-logpile.png",
														"images\\obstacles\\long-logpile.png",
														"images\\obstacles\\long-logpile.png"},
														{ // gameLevel 2
														"images\\obstacles\\box.png",
														"images\\obstacles\\box.png",
														"images\\obstacles\\box.png",
														"images\\obstacles\\box.png",
														"images\\obstacles\\box.png",
														"images\\obstacles\\box.png",
														"images\\obstacles\\box.png",
														"images\\obstacles\\box.png",
														"images\\obstacles\\box.png",
														"images\\obstacles\\box.png",
														"images\\obstacles\\box.png",
														"images\\obstacles\\box.png",
														"images\\obstacles\\box.png",
														"images\\obstacles\\rock.png"},
														{ // gameLevel 3
														"images\\obstacles\\box.png",
														"images\\obstacles\\rock.png"},
														{ // gameLevel 4
														"images\\obstacles\\box.png",
														"images\\obstacles\\rock.png"}
													};

	// define width, height, xPosition, yPosition for the collectibles for each level
	// to access this array: first index is level, second index is collectible, 
	// third index is xPosition, yPosition, width, height of that collectible,
	public static final int CollectiblePositions[][][] = {{ // gameLevel 1
														{1510,535,15,15},
														{1650,670,35,35},	// axe
														{2120,685,15,15},
														{2660,685,15,15},
														{4980,385,15,15}},
														{ // gameLevel 2
														{1510,535,15,15},
														{1650,670,35,35},	// axe
														{2120,685,15,15},
														{2660,685,15,15},
														{4980,385,15,15}},
														{ // gameLevel 3
														{1830,285,15,15},
														{1960,665,35,35},
														{2005,185,15,15},
														{2750,685,15,15},
														{2850,685,15,15}},
														{ // gameLevel 4
														{220,535,15,15},
														{PlatformGameWidth+200,PlatformGameHeight - 80,35,35}},
													};
	// define image for each collectible on each level, in same order as CollectiblePositions array
	public static final String CollectibleImages[][] = {{ // gameLevel 1
														"images\\collectibles\\OrangeBauble.png",
														"images\\collectibles\\Axe.png",
														"images\\collectibles\\RedBauble.png",
														"images\\collectibles\\GreenBauble.png",
														"images\\collectibles\\BlueBauble.png"},
														{ // gameLevel 2
														"images\\collectibles\\OrangeBauble.png",
														"images\\collectibles\\Axe.png",
														"images\\collectibles\\RedBauble.png",
														"images\\collectibles\\GreenBauble.png",
														"images\\collectibles\\BlueBauble.png"},
														{ // gameLevel 3
														"images\\collectibles\\RedBauble.png",
														"images\\collectibles\\Axe.png",
														"images\\collectibles\\OrangeBauble.png",
														"images\\collectibles\\RedBauble.png",
														"images\\collectibles\\BlueBauble.png"},
														{ // gameLevel 4
														"images\\collectibles\\RedBauble.png",
														"images\\collectibles\\Axe.png"},
													};
	// define whether each collectible on each level in a key to the exit door, in same order as CollectiblePositions array
	public static final boolean CollectibleKeys[][] = {{ // gameLevel 1
														false,
														true,
														false,
														false,
														false},
														{ // gameLevel 2
														false,
														true,
														false,
														false,
														false},
														{ // gameLevel 3
														false,
														true,
														false,
														false,
														false},
														{ // gameLevel 4
														false,
														true},
													};

	// number of keys to be collected at each level before the exit door will open (should match number of trues in CollectibleKeys)
	public static final int numKeys[] = {	1,	// gameLevel 1
											1,	// gameLevel 2
											1,	// gameLevel 3
											1	// gameLevel 4
										};

	// define width, height, xPosition, yPosition for the door for each level
	// to access this array: first index is level, 
	// second index is xPosition, yPosition, width, height of the door
	public static final int DoorPosition[][] = { // gameLevel 1
												{6240,425,50,100},
												 // gameLevel 2
												{6240,425,50,100},
												 // gameLevel 3
												{2500,600,50,100},
												 // gameLevel 4
												{1500,640,50,100},
												};
	// define images for shut and open doors
	public static final String ShutDoorImage = "images\\platform\\shutdoor.png";
	public static final String OpenDoorImage = "images\\platform\\opendoor.png";

	// define size of window for puzzle games
	public static final int PuzzleGameWidth = 600;
	public static final int PuzzleGameHeight = 600;
	
	//Tile Puzzle Tile Sizes
	public static final int tileX = 200;
	public static final int tileY = 200;


	/**
	 * inVerticalRange - returns true if item1 is vertically within item2
	 * @param item1Top - y position of top edge of item1
	 * @param item1Bottom - y position of bottom edge of item1
	 * @param item2Top - y position of top edge of item2
	 * @param item2Bottom - y position of bottom edge of item2
	 * @return boolean - returns true if item1 horizontally touching item2
	 */
	public static boolean inVerticalRange(long item1Top, long item1Bottom, long item2Top, long item2Bottom)
	{
		// if top of item1 is below bottom of item2, then not touching
		if (item1Top > item2Bottom)
			return false;
		// if bottom of item1 is above top of item2, then not touching
		if (item1Bottom < item2Top)
			return false;
		return true;
	}

	/**
	 * inHorizontalRange - returns true if item1 is horizontally within item2
	 * @param item1Left - x position of left edge of item1
	 * @param item1Right - x position of right edge of item1
	 * @param item2Left - x position of left edge of item2
	 * @param item2Right - x position of right edge of item2
	 * @return boolean - returns true if item1 horizontally touching item2
	 */
	public static boolean inHorizontalRange(long item1Left, long item1Right, long item2Left, long item2Right)
	{
		// if left edge of item1 is beyond right edge of item2, then not touching
		if (item1Left > item2Right)
			return false;
		// if right edge of item2 is before left edge of item1, then not touching
		if (item1Right < item2Left)
			return false;
		return true;
	}
}