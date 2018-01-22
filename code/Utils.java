/**
 * This class defines utilities and constants 
 */
public class Utils
{
	// define size of window for platform game
	public static final int PlatformGameWidth = 1024;
	public static final int PlatformGameHeight = 768;
	public static final int PlayerXPosition = PlatformGameWidth/2;

	public static final int MoveAmountX = 5;
	public static final int MoveAmountY = 4;
	public static final int JumpAmount = 40;
	public static final int Gravity = 2;
	
	//File paths for sprites
	public static final String IdlePath = "Sprites\\Idle\\Santa_Elf_Idle";
	public static final String RunningPath = "Sprites\\Running\\Santa_Elf_Running";

	// define width, height, xPosition, yPosition for all the platforms for each level
	// to access this array: first index is level, second index is platform, 
	// third index is width, height, xPosition, yPosition of that platform
	public static final int PlatformPositions[][][] = {{ // gameLevel 1
														{PlatformGameWidth,25,0,PlatformGameHeight - 25},	// base
														{150,30,300,600},
														{80,25,100,700},
														{100,35,500,500},
														{2,PlatformGameHeight,0,0}},						// left hand edge
														{ // gameLevel 2
														{PlatformGameWidth,25,0,PlatformGameHeight - 25},	// base
														{100,25,200,550},
														{2,PlatformGameHeight,0,0}},						// left hand edge
													};
	// define image for each platform on each level, in same order as PlatformPositions array
	public static final String PlatformImages[][] = {{ // gameLevel 1
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png",
														"images\\platform\\bricks.png"},
														{ // gameLevel 2
														"images\\platform\\grass.png",
														"images\\platform\\grass.png",
														"images\\platform\\grass.png"},
													};

	// define size of window for puzzle games
	public static final int PuzzleGameWidth = 1000;
	public static final int PuzzleGameHeight = 700;


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