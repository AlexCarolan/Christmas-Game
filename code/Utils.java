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
	public static final int JumpAmount = 18;	//60
	public static final int Gravity = 1;
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
	public static final String HeartPath = "Sprites\\Heart\\heart.png";

	// file path for platform background images, and the width of these
	public static final String PlatformBackgroundImage[] = {"images\\platform\\forest.png",		// platform level 1
															"images\\platform\\attic.png",		// platform level 2
															"images\\platform\\Kitchen.png",	// platform level 3
															"images\\platform\\Village.png"		// platform level 4
															};
	public static final int PlatformBackgroundWidth[] = {7564, 7564, 7564, 2*PlatformGameWidth+652};

	// define width, height, xPosition, yPosition for all the platforms for each level
	// to access this array: first index is level, second index is platform, 
	// third index is xPosition, yPosition, width, height of that platform
	public static final int PlatformPositions[][][] = {{ // gameLevel 1
														{0,700,1200,50},	// base 1
														{5300,700,1200,50},	// base 2
														{1150,600,100,50},	//3
														{1280,550,100,50},	//4
														{1405,510,100,50},	//5
														{1530,560,100,50},	//6
														{1680,610,200,90},	//7 
														{1550,420,300,50},	//8 frozen pond
														{1900,570,80,30},	//9
														{2050,590,80,30},	//10
														{2150,650,200,80},	//11
														{2200,440,400,80},	//12 bigger frozen pond
														{2360,670,90,40},	//13
														{2460,670,90,54},	//14
														{2570,660,90,40},	//15
														{2665,645,100,50},	//16
														{2800,615,100,50},	//17
														{2920,600,100,50},	//18
														{3100,570,100,50},	//19
														{3300,540,100,50},	//20
														{3500,510,100,50},	//21
														{3650,480,100,50},	//22
														{3800,460,100,50},	//23
														{3950,450,100,50},	//24
														{4100,480,100,50},	//25
														{4250,500,80,30},	//26
														{4400,520,80,30},	//27
														{4550,545,80,35},	//28
														{4700,560,90,40},	//29
														{4850,580,100,50},	//30
														{5000,610,100,50},	//31
														{5150,640,80,30},	//32
														{5300,660,100,50},	//33
														{6000,650,100,50},	//34
														{6100,600,100,50},	//35
														{6230,550,100,50},	//36
														{-250,500,250,400},	// left hand edge
														{6500,0,PlatformGameWidth/2,PlatformGameHeight+1}},	// right hand edge
														{ // gameLevel 2
														{0,700,5200,50},	// base 1
														{5300,700,1200,50},	// base 2
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
														{-250,500,250,400},	// left hand edge
														{6500,0,PlatformGameWidth/2,PlatformGameHeight+1}},	// right hand edge
														{ // gameLevel 3
														{0,700,3900,50},	// base 1
														{4000,700,1200,50},	// base 2
														{5300,700,1200,50},	// base 3
														{600,550,200,50},	//4
														{700,450,50,50},	//5
														{900,600,200,100},	//6
														{1150,500,150,50},	//7
														{1400,500,150,50},	//8
														{1550,600,150,100},	//9
														{1900,650,50,50},	//10
														{1950,600,150,100},	//11
														{2150,500,200,50},	//12
														{2350,650,50,50},	//13
														{2400,600,750,100},	//14
														{2550,500,150,100},	//15
														{2750,400,350,50},	//16
														{3150,450,300,250},	//17
														{3450,450,150,30},	//18
														{3450,515,100,30},	//19
														{3450,580,50,30},	//20
														{3700,450,150,30},	//
														{3900,520,150,30},	//
														{4100,600,150,50},	//21
														{4300,500,200,50},	//22
														{4650,650,300,50},	//23
														{20-PlatformGameWidth/2,510,PlatformGameWidth/2+20,PlatformGameHeight-510},	// left hand edge
														{6500,510,PlatformGameWidth/2,PlatformGameHeight-510}},	// right hand edge
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
														"images\\platform\\Rock_Platform.png",	//33
														"images\\platform\\Rock_Platform.png",	//34
														"images\\platform\\Rock_Platform.png",	//35
														"images\\platform\\Rock_Platform.png",	//36
														"images\\obstacles\\long-logpile.png",	//left hand edge
														"images\\obstacles\\ForestOne.png"},	// right hand edge
														{ // gameLevel 2
														"images\\platform\\bricks.png",	//base1
														"images\\platform\\bricks.png",	//base2
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",	//10
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",	//20
														"images\\platform\\bricks.png"},
														{ // gameLevel 3
														"images\\platform\\LogLarge.png",	//base1
														"images\\platform\\LogLarge.png",	//base2
														"images\\platform\\logLarge.png",	//base3
														"images\\platform\\LogLarge.png",	//4
														"images\\platform\\LogLarge.png",	//5
														"images\\platform\\KitchenCounter.png",	//6
														"images\\platform\\LogLarge.png",	//7
														"images\\platform\\LogLarge.png",	//8
														"images\\platform\\Fireplace.png",	//9
														"images\\platform\\LogLarge.png",	//10
														"images\\platform\\KitchenCounter.png",	//11
														"images\\platform\\LogLarge.png",	//12
														"images\\platform\\LogLarge.png",	//13
														"images\\platform\\KitchenCounter.png",	//14
														"images\\platform\\Fireplace.png",	//15
														"images\\platform\\LogLarge.png",	//16
														"images\\platform\\Shelf.png",	//17
														"images\\platform\\LogLarge.png",	//18
														"images\\platform\\LogLarge.png",	//19
														"images\\platform\\LogLarge.png",	//20
														"images\\platform\\LogLarge.png",	//
														"images\\platform\\LogLarge.png",	//
														"images\\platform\\LogLarge.png",	//21
														"images\\platform\\LogLarge.png",	//22
														"images\\platform\\KitchenCounter.png",	//23
														"images\\platform\\KitchenCounter.png",	//left hand edge
														"images\\platform\\KitchenCounter.png"},	//right hand edge
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
														false,	//10
														true,
														true,
														false,
														false,
														false,
														false,
														false,
														false,
														true,
														true,	//20
														false,
														false,
														false,
														false,
														false,
														false,
														false,
														false,
														false,
														false,	//30
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
														true,	// base1
														true,	// base2
														true,	// base3
														true,
														true,
														true,
														true,
														true,
														true,
														true,	// 10
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,
														true,	// 20
														true,
														true,
														true,
														true,
														true,
														true,
														true},
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
														{800,670,50,50}},
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
														"images\\obstacles\\logs1.png"},
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
														"images\\obstacles\\box.png"},
														{ // gameLevel 4
														"images\\obstacles\\box.png",
														"images\\obstacles\\rock.png"}
													};

	// define width, height, xPosition, yPosition for the collectibles for each level
	// to access this array: first index is level, second index is collectible, 
	// third index is xPosition, yPosition, width, height of that collectible,
	public static final int CollectiblePositions[][][] = {{ // gameLevel 1
														{1630,400,15,15},
														{2400,400,35,35},	// axe
														{800,500,15,15},
														{6400,520,15,15}},
														{ // gameLevel 2
														{1510,535,15,15},
														{1650,670,35,35},	// axe
														{2120,685,15,15},
														{2660,685,15,15},
														{4980,385,15,15}},
														{ // gameLevel 3
														{1800,685,15,15},
														{3450,665,35,35},	// key
														{2900,585,15,15},
														{4500,685,15,15},
														{4850,635,15,15}},
														{ // gameLevel 4
														{220,535,15,15},
														{PlatformGameWidth+200,PlatformGameHeight - 80,35,35}},
													};
	// define image for each collectible on each level, in same order as CollectiblePositions array
	public static final String CollectibleImages[][] = {{ // gameLevel 1
														"images\\collectibles\\OrangeBauble.png",
														"images\\collectibles\\Axe.png",
														"images\\collectibles\\BlueBauble.png",
														"images\\collectibles\\RedBauble.png"},
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
												{6300,600,50,100},
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