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
	public static final int JumpAmount = 40;
	public static final int Gravity = 2;
	public static final int MinGravity = 3;
	public static final int MaxGravity = 30;
	public static final double GravityMultiplier = 1.04;

	// file path for Christmas Room image
	public static final String RoomImage[] = {"images\\christmasRoom\\bareRoom.png",	// game level 1 start
												"images\\christmasRoom\\bareRoom.png",	// game level 2 start
												"images\\christmasRoom\\fullRoom.png",	// game level 3 start
												"images\\christmasRoom\\fullRoom.png",	// game level 4 start
												"images\\christmasRoom\\fullRoom.png"};	// game level 4 end
	
	//File paths for sprites
	public static final String IdleRightPath = "Sprites\\Idle_Right\\Santa_Elf_Idle";
	public static final String IdleLeftPath = "Sprites\\Idle_Left\\Santa_Elf_Idle";
	public static final String RunningRightPath = "Sprites\\Running_Right\\Santa_Elf_Running";
	public static final String RunningLeftPath = "Sprites\\Running_Left\\Santa_Elf_Running";
	public static final String Bauble1Path = "Sprites\\Baubles\\Bauble";

	// define width, height, xPosition, yPosition for all the platforms for each level
	// to access this array: first index is level, second index is platform, 
	// third index is width, height, xPosition, yPosition of that platform
	public static final int PlatformPositions[][][] = {{ // gameLevel 1
														{PlatformGameWidth,25,0,PlatformGameHeight - 25},	// base
														{PlatformGameWidth,30,PlatformGameWidth+100,PlatformGameHeight - 30},
														{150,30,300,600},
														{80,25,100,700},
														{100,35,500,500},
														{2,PlatformGameHeight,0,0}},						// left hand edge
														{ // gameLevel 2
														{PlatformGameWidth,25,0,PlatformGameHeight - 25},	// base
														{500,50,PlatformGameWidth+150,PlatformGameHeight - 50},
														{100,25,200,550},
														{2,PlatformGameHeight,0,0}},						// left hand edge
														{ // gameLevel 3
														{PlatformGameWidth,25,0,PlatformGameHeight - 25},	// base
														{500,50,PlatformGameWidth+150,PlatformGameHeight - 50},
														{100,25,200,550},
														{2,PlatformGameHeight,0,0}},						// left hand edge
														{ // gameLevel 4
														{PlatformGameWidth,25,0,PlatformGameHeight - 25},	// base
														{500,50,PlatformGameWidth+150,PlatformGameHeight - 50},
														{100,25,200,550},
														{2,PlatformGameHeight,0,0}}						// left hand edge
													};
	// define image for each platform on each level, in same order as PlatformPositions array
	public static final String PlatformImages[][] = {{ // gameLevel 1
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png"},
														{ // gameLevel 2
														"images\\platform\\grass.png",
														"images\\platform\\bricks.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png"},
														{ // gameLevel 3
														"images\\platform\\grass.png",
														"images\\platform\\bricks.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png"},
														{ // gameLevel 4
														"images\\platform\\grass.png",
														"images\\platform\\bricks.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png"}
													};


	// define width, height, xPosition, yPosition for the obstacles for each level
	// to access this array: first index is level, second index is obstacle, 
	// third index is width, height, xPosition, yPosition of that obstacle
	public static final int ObstaclePositions[][][] = {{ // gameLevel 1
														{40,40,375,567},
														{35,35,550,467}},
														{ // gameLevel 2
														{40,40,250,520},
														{35,35,800,PlatformGameHeight - 55}},
														{ // gameLevel 3
														{40,40,250,520},
														{35,35,800,PlatformGameHeight - 55}},
														{ // gameLevel 4
														{40,40,250,520},
														{35,35,800,PlatformGameHeight - 55}}
													};
	// define image for each obstacle on each level, in same order as ObstaclePositions array
	public static final String ObstacleImages[][] = {{ // gameLevel 1
														"images\\obstacles\\box.png",
														"images\\obstacles\\rock.png"},
														{ // gameLevel 2
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
	// third index is width, height, xPosition, yPosition of that collectible,
	public static final int CollectiblePositions[][][] = {{ // gameLevel 1
														{15,15,125,685},
														{35,35,570,467}},
														{ // gameLevel 2
														{15,15,220,540},
														{20,20,PlatformGameWidth+200,PlatformGameHeight - 55}},
														{ // gameLevel 3
														{15,15,220,540},
														{20,20,PlatformGameWidth+200,PlatformGameHeight - 55}},
														{ // gameLevel 4
														{15,15,220,540},
														{20,20,PlatformGameWidth+200,PlatformGameHeight - 55}},
													};
	// define image for each collectible on each level, in same order as CollectiblePositions array
	public static final String CollectibleImages[][] = {{ // gameLevel 1
														"images\\collectibles\\OrangeBauble.png",
														"images\\collectibles\\Axe.png"},
														{ // gameLevel 2
														"images\\collectibles\\RedBauble.png",
														"images\\collectibles\\Star.png"},
														{ // gameLevel 3
														"images\\collectibles\\RedBauble.png",
														"images\\collectibles\\Star.png"},
														{ // gameLevel 4
														"images\\collectibles\\RedBauble.png",
														"images\\collectibles\\Star.png"},
													};
	// define whether each collectible on each level in a key to the exit door, in same order as CollectiblePositions array
	public static final boolean CollectibleKeys[][] = {{ // gameLevel 1
														false,
														true},
														{ // gameLevel 2
														false,
														true},
														{ // gameLevel 3
														false,
														true},
														{ // gameLevel 4
														false,
														true}
													};

	public static final int numKeys[] = {	1,	// gameLevel 1
											1,	// gameLevel 2
											1,	// gameLevel 3
											1	// gameLevel 4
										};

	// define width, height, xPosition, yPosition for the door for each level
	// to access this array: first index is level, 
	// second index is width, height, xPosition, yPosition of the door
	public static final int DoorPosition[][] = { // gameLevel 1
												{50,100,1500,640},
												 // gameLevel 2
												{50,100,1500,640},
												 // gameLevel 3
												{50,100,1500,640},
												 // gameLevel 4
												{50,100,1500,640}
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