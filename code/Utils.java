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
	public static final double SleighGravity = 0.05;

	public static final int SleighGameLevel = 3;

	// file path for Christmas Room image
	public static final String RoomImage[] = {"images\\christmasRoom\\Christmas_Room_Bare.png",	// game level 1 start
												"images\\christmasRoom\\Christmas_Room_Tree.png",	// game level 2 start
												"images\\christmasRoom\\Christmas_Room_Tree_decorations.png",	// game level 3 start
												"images\\christmasRoom\\Christmas_Room_Complete.png",	// game level 4 start
												"images\\christmasRoom\\Christmas_Room_Complete.png"};	// game level 4 end
	
	// file paths for sprites
	public static final String IdleRightPath = "Sprites\\Idle_Right\\Idle";
	public static final String IdleLeftPath = "Sprites\\Idle_Left\\Idle";
	public static final String RunningRightPath = "Sprites\\Running_Right\\Santa_Elf_Running";
	public static final String RunningLeftPath = "Sprites\\Running_Left\\Santa_Elf_Running";
	public static final String SleighIdleRightPath = "Sprites\\Sleigh_Idle_Right\\Sleigh";
	public static final String SleighIdleLeftPath = "Sprites\\Sleigh_Idle_Left\\Sleigh";
	public static final String SleighRunningRightPath = "Sprites\\Sleigh_Running_Right\\Sleigh";
	public static final String SleighRunningLeftPath = "Sprites\\Sleigh_Running_Left\\Sleigh";
	public static final String Bauble1Path = "Sprites\\Baubles\\Bauble";
	public static final String KeyPath = "Sprites\\Key\\Key";
	public static final String HeartPath = "Sprites\\Heart\\heart.png";
	public static final String StaticKeyPath = "Sprites\\Key\\Key1.png";
	
	// file path for menu
	public static final String MenuPath = "images\\mainMenu\\background";
	
	// file path for platform background images, and the width of these
	public static final String PlatformBackgroundImage[] = {"images\\platform\\forest.png",		// platform level 1
															"images\\platform\\attic.png",		// platform level 2
															"images\\platform\\Kitchen.png",	// platform level 3
															"images\\platform\\Village.png"		// platform level 4
															};
	public static final int PlatformBackgroundWidth = 7564;

	// define width, height, xPosition, yPosition for all the platforms for each level
	// to access this array: first index is level, second index is platform, 
	// third index is xPosition, yPosition, width, height of that platform
	public static final int PlatformPositions[][][] = {{ // gameLevel 1
														{0,700,1150,50},	// base 1
														{5350,700,1200,50},	// base 2
														{1150,600,100,50},	//3
														{1280,550,100,50},	//4
														{1405,510,100,50},	//5
														{1530,560,100,50},	//6
														{1680,610,200,90},	//7 
														{1550,420,300,50},	//8 frozen pond
														{1900,570,80,30},	//9
														{2050,590,80,30},	//10
														{2150,650,200,80},	//11
														{2200,440,400,60},	//12 bigger frozen pond
														{2360,670,90,40},	//13
														{2463,665,90,54},	//14
														{2571,657,90,40},	//15
														{2665,647,100,50},	//16
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
														{ // gameLevel 2 - attic
														{0,700,1350,100},		// base 1
														{1450,700,500,100},		// base 2
														{2050,700,400,100},		// base 3
														{2550,700,1900,100},	// base 4
														{4500,700,700,100},		// base 5
														{5300,700,1200,100},	// base 6
														{1150,600,100,100},	//7
														{1250,650,100,50},	//8
														{1400,500,100,50},	//9
														{1400,550,200,80},	//10
														{1650,600,300,100},	//11
														{1850,500,100,100},	//12
														{2050,540,150,80},	//13
														{2250,600,200,90},	//14
														{2550,620,100,80},	//15
														{2550,560,200,70},	//16
														{3200,500,200,200},	//17
														{3400,600,600,100},	//18
														{4500,550,200,150},	//19
														{4800,600,400,100},	//20
														{4900,430,200,100},	//21
														{5000,330,100,100},	//22
														{-250,200,250,650},		// left hand edge
														{6500,200,250,650}},	// right hand edge
														{ // gameLevel 3 - kitchen
														{0,700,3950,50},	// base 1
														{4000,700,1200,50},	// base 2
														{5300,700,1200,50},	// base 3
														{600,550,200,15},	//4
														{700,450,50,15},	//5
														{850,500,250,200},	//6
														{1150,400,150,15},	//7
														{1350,500,150,15},	//8
														{1550,595,150,5},	//9
														{1850,650,50,50},	//10
														{1900,500,300,200},	//11
														{2250,450,100,15},	//12
														{2350,650,50,50},	//13
														{2400,600,750,100},	//14
														{2550,495,150,5},	//15
														{2750,400,350,15},	//16
														{3150,450,300,250},	//17
														{3450,450,150,15},	//18
														{3450,515,100,15},	//19
														{3450,580,50,15},	//20
														{3700,450,150,15},	//21
														{3900,500,150,15},	//22
														{4100,600,150,15},	//23
														{4300,500,200,15},	//24
														{4650,500,400,200},	//25
														{5050,650,50,50},	//26
														{5750,650,50,50},	//27
														{5800,600,100,100},	//28
														{5850,550,50,50},	//29
														{5900,650,50,50},	//30
														{20-PlatformGameWidth/2,510,PlatformGameWidth/2+20,PlatformGameHeight-510},	// left hand edge
														{6500,510,PlatformGameWidth/2,PlatformGameHeight-510}},	// right hand edge
														{ // gameLevel 4
														{0,PlatformGameHeight - 25,PlatformGameWidth,25},	// base 1
														{PlatformGameWidth+150,PlatformGameHeight - 25,500,50}, // base 2
														{5900,PlatformGameHeight - 20,1100,50},	// base 3
														//{-10,495,260,10},	// 4
														//{650,325,550,10},	// 5
														{0-PlatformGameWidth/2,40,PlatformGameWidth/2,PlatformGameHeight-40},	// left hand edge
														{6500,0,PlatformGameWidth/2,PlatformGameHeight+1}}	// right hand edge
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
														"images\\platform\\FrozenPond.png",		//12
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
														"images\\platform\\bricks.png",	//base 1
														"images\\platform\\bricks.png",	//base 2
														"images\\platform\\bricks.png",	//base 3
														"images\\platform\\bricks.png",	//base 4
														"images\\platform\\bricks.png",	//base 5
														"images\\platform\\bricks.png",	//base 6
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
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\obstacles\\Shelf_Single.png",	//left hand edge
														"images\\obstacles\\Shelf_Single.png"},	// right hand edge
														{ // gameLevel 3
														"images\\platform\\LogLarge.png",	//base1
														"images\\platform\\LogLarge.png",	//base2
														"images\\platform\\logLarge.png",	//base3
														"images\\platform\\LogLarge.png",	//4
														"images\\platform\\LogLarge.png",	//5
														"images\\platform\\KitchenCounter.png",	//6
														"images\\platform\\LogLarge.png",	//7
														"images\\platform\\LogLarge.png",	//8
														"images\\platform\\LogLarge.png",	//9 (above fireplace)
														"images\\platform\\LogLarge.png",	//10
														"images\\platform\\KitchenCounter.png",	//11
														"images\\platform\\LogLarge.png",	//12
														"images\\platform\\LogLarge.png",	//13
														"images\\platform\\KitchenCounter.png",	//14
														"images\\platform\\LogLarge.png",	//15 (above fireplace)
														"images\\platform\\LogLarge.png",	//16
														"images\\platform\\Shelf.png",	//17
														"images\\platform\\LogLarge.png",	//18
														"images\\platform\\LogLarge.png",	//19
														"images\\platform\\LogLarge.png",	//20
														"images\\platform\\LogLarge.png",	//21
														"images\\platform\\LogLarge.png",	//22
														"images\\platform\\LogLarge.png",	//23
														"images\\platform\\LogLarge.png",	//24
														"images\\platform\\KitchenCounter.png",	//25
														"images\\platform\\LogLarge.png",	//26
														"images\\platform\\LogLarge.png",	//27
														"images\\platform\\LogLarge.png",	//28
														"images\\platform\\LogLarge.png",	//29
														"images\\platform\\LogLarge.png",	//30
														"images\\platform\\KitchenCounter.png",	//left hand edge
														"images\\platform\\KitchenCounter.png"},	//right hand edge
														{ // gameLevel 4
														"images\\platform\\Rock_Platform.png",	// base 1
														"images\\platform\\Rock_Platform.png",	// base 2
														"images\\platform\\Rock_Platform.png",	// base 3
														//"images\\platform\\Roof_Line.png",		// 4
														//"images\\platform\\Roof_Line.png",		// 5
														"images\\platform\\Cabin_Large.png",	// left hand side
														"images\\obstacles\\ForestOne.png"}	// right hand side
													};
	// specify whether platform is a ceiling (can't be jumped up through)
	public static final boolean PlatformCeilings[][] = {{ // gameLevel 1
														true,	// base1
														true,	// base 2
														false,
														true,
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
														false,
														false,	//20
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
														true,	// base 1
														true,	// base 2
														true,	// base 3
														true,	// base 4
														true,	// base 5
														true,	// base 6
														false,
														false,
														true,
														true,	// 10
														false,
														false,
														true,
														false,
														true,
														true,
														false,
														false,
														false,
														false,	// 20
														true,
														true,
														false,	// left edge
														false},	// right edge
														{ // gameLevel 3
														true,	// base1
														true,	// base2
														true,	// base3
														true,
														false,
														false,
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
														false,
														false,
														false,
														false,
														true,
														true,
														true,
														true,
														true,
														true,	// 30
														true,
														true},
														{ // gameLevel 4
														true,
														true,
														true,
														//false,
														//false,
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
														{600,610,95,90},
														{680,580,140,125},
														{823,642,100,60},
														{2330,540,60,60},
														{3050,650,150,50},
														{3630,530,80,70},
														{3710,510,100,95},
														{3810,558,120,50},
														{4100,480,260,220},
														{4540,485,105,65},
														{5700,600,245,100},
														{5950,600,200,105},
														{6220,640,100,60},
														{5835,500,230,100},
														{5923,435,90,65}},
														{ // gameLevel 3
														},
														{ // gameLevel 4
														//{500,50,200,70},
														{1750,500,600,400},
														{2250,370,700,500},
														{3000,510,500,300},
														{3470,530,510,310},
														{3100,590,550,350},
														{4000,490,560,320},
														{4500,400,1000,500},
														{5050,600,800,300},
														}
													};
	// define image for each obstacle on each level, in same order as ObstaclePositions array
	public static final String ObstacleImages[][] = {{ // gameLevel 1
														"images\\obstacles\\big-log.png",
														"images\\obstacles\\big-log.png",
														"images\\obstacles\\rock.png"},
														{ // gameLevel 2
														"images\\obstacles\\Box_Blue.png",
														"images\\obstacles\\Box_Tan.png",
														"images\\obstacles\\Box_Blue.png",
														"images\\obstacles\\Box_Tan.png",
														"images\\obstacles\\Box_Red.png",
														"images\\obstacles\\Box_Tan.png",
														"images\\obstacles\\Box_Blue.png",
														"images\\obstacles\\Box_Tan.png",
														"images\\obstacles\\Box_Tan.png",
														"images\\obstacles\\Box_Tan.png",
														"images\\obstacles\\Box_Blue.png",
														"images\\obstacles\\Box_Tan.png",
														"images\\obstacles\\Box_Blue.png",
														"images\\obstacles\\Box_Tan.png",
														"images\\obstacles\\Box_Blue.png"},
														{ // gameLevel 3
														},
														{ // gameLevel 4
														//"images\\obstacles\\Cloud.png",
														"images\\platform\\Cabin_Small.png",
														"images\\platform\\Cabin_Medium.png",
														"images\\platform\\Cabin_Small.png",
														"images\\platform\\Cabin_Small.png",
														"images\\platform\\Cabin_Small.png",
														"images\\platform\\Cabin_Small.png",
														"images\\platform\\Cabin_Medium.png",
														"images\\platform\\Cabin_Small.png",
														}
													};

	// define width, height, xPosition, yPosition for the hazards for each level
	// to access this array: first index is level, second index is hazard, 
	// third index is xPosition, yPosition, width, height of that hazard
	public static final int HazardPositions[][][] = {{},	// gameLevel 1
													 {		// gameLevel 2
													  {1350,600,100,200},
													  {1950,550,100,250},
													  {2450,350,100,450},
													  {5200,350,100,450},
													 },
													 {		// gameLevel 3
													  {700,500,50,50},
													  {650,650,50,50},
													  {1300,600,100,100},
													  {1550,550,50,50},
													  {1550,600,150,100},
													  {2200,650,50,50},
													  {2550,500,150,100},
													  {3050,500,100,100},
													  {3250,400,50,50},
													  {3650,600,100,100},
													  {4400,450,50,50},
													  {4600,650,50,50},
													  {4700,450,50,50},
													  {5100,650,50,50},
													  {5450,600,100,100},
													  {5900,600,50,50},
													 },
													 {		// gameLevel 4
													  {328,225,55,200},
													  {1268,50,55,200},
													  {1808,251,55,200},
													  {2225,400,30,100},
													  {2345,155,70,200},
													  {2802,220,45,150},
													  {3465,7,55,150},
													  {3968,219,50,200},
													  {4580,54,50,200},
													  {5295,200,50,200},
													  {5680,420,50,180},
													 }
													};
	// define image path for each hazard on each level, in same order as HazardPositions array
	// first path is image for no damage, second path is image for damage
	// there MUST be one image within the noDamage path, labelled Image1
	// there MUST be ten images within the damage path, labelled Image1..Image10
	public static final String HazardImages[][][] = {{},	// gameLevel 1
													 {		// gameLevel 2
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													 },
													 {		// gameLevel 3
													  {"Sprites\\Fire_Off","Sprites\\Fire"},
													  {"Sprites\\Fire_Off","Sprites\\Fire"},	// should be a knife
													  {"Sprites\\Fire_Off","Sprites\\Fire"},
													  {"Sprites\\Fire_Off","Sprites\\Fire"},
													  {"Sprites\\Fireplace","Sprites\\Fireplace_With_Fire"},
													  {"Sprites\\Cleaver_Down","Sprites\\Cleaver_Up"},
													  {"Sprites\\Fireplace","Sprites\\Fireplace_With_Fire"},
													  {"Sprites\\Fire_Off","Sprites\\Fire"},
													  {"Sprites\\Cleaver_Down","Sprites\\Cleaver_Up"},
													  {"Sprites\\Fire_Off","Sprites\\Fire"},
													  {"Sprites\\Fire_Off","Sprites\\Fire"},
													  {"Sprites\\Fire_Off","Sprites\\Fire"},
													  {"Sprites\\Cleaver_Down","Sprites\\Cleaver_Up"},
													  {"Sprites\\Fire_Off","Sprites\\Fire"},
													  {"Sprites\\Fire_Off","Sprites\\Fire"},
													  {"Sprites\\Cleaver_Down","Sprites\\Cleaver_Up"},
													 },
													 {		// gameLevel 4
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													  {"Sprites\\Smoke_Off","Sprites\\Smoke_On"},
													 }
													};

	// define width, height, xPosition, yPosition for the collectibles for each level
	// to access this array: first index is level, second index is collectible, 
	// third index is xPosition, yPosition, width, height of that collectible,
	// NOTE: key MUST be first collectible
	public static final int CollectiblePositions[][][] = {{ // gameLevel 1
														{2400,400,37,35},	// key
														{800,500,15,15},
														{1630,400,15,15},
														{2500,650,15,15},
														{6400,520,15,15}},
														{ // gameLevel 2 attic
														{2650,665,37,35},	// key
														{1510,535,15,15},
														{1620,685,15,15},
														{2120,685,15,15},
														{4980,415,15,15}},
														{ // gameLevel 3 kitchen
														{3450,665,37,35},	// key
														{1800,685,15,15},
														{2900,585,15,15},
														{4460,685,15,15},
														{4850,485,15,15}},
														{ // gameLevel 4
														{3650,470,37,35}, // key
														{130,480,15,15},
														{800,310,15,15},
														{3330,650,15,15},
														{4940,500,15,15}}
														};
	// define image for each collectible on each level, in same order as CollectiblePositions array
	// NOTE: key MUST be first collectible
	public static final String CollectibleImages[][] = {{ // gameLevel 1
														"Sprites\\Key\\KeyMain.png",
														"images\\collectibles\\BlueBauble.png",
														"images\\collectibles\\OrangeBauble.png",
														"images\\collectibles\\GreenBauble.png",
														"images\\collectibles\\RedBauble.png"},
														{ // gameLevel 2
														"Sprites\\Key\\KeyMain.png",
														"images\\collectibles\\OrangeBauble.png",
														"images\\collectibles\\RedBauble.png",
														"images\\collectibles\\GreenBauble.png",
														"images\\collectibles\\BlueBauble.png"},
														{ // gameLevel 3
														"Sprites\\Key\\KeyMain.png",
														"images\\collectibles\\GreenBauble.png",
														"images\\collectibles\\OrangeBauble.png",
														"images\\collectibles\\RedBauble.png",
														"images\\collectibles\\BlueBauble.png"},
														{ // gameLevel 4
														"Sprites\\Key\\KeyMain.png",
														"images\\collectibles\\RedBauble.png",
														"images\\collectibles\\GreenBauble.png",
														"images\\collectibles\\BlueBauble.png",
														"images\\collectibles\\OrangeBauble.png"}
													};

	// define width, height, xPosition, yPosition for the door for each level
	// to access this array: first index is level, 
	// second index is xPosition, yPosition, width, height of the door
	public static final int DoorPosition[][] = { // gameLevel 1
												{6220,425,48,66},
												 // gameLevel 2
												{5950,265,48,66},
												 // gameLevel 3
												{6300,450,48,66},
												 // gameLevel 4
												{6300,300,48,66}
												};
	// define images for shut and open doors
	public static final String ShutDoorImage = "Sprites\\Scroll\\LockScroll.png";
	public static final String OpenDoorImage = "Sprites\\Scroll\\OpenScroll.png";

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