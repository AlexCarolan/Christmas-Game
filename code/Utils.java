/**
 * This class defines utilities and constants 
 */
public class Utils
{
	// define size of window for platform game
	public static final int PlatformGameWidth = 1024;
	public static final int PlatformGameHeight = 768;
	public static final int PlayerXPosition = PlatformGameWidth/2;

	// define width, height, xPosition, yPosition for all the platforms for each level
	// to access this array, first index is platform, third index is width, height, xPosition, yPosition
	public static final int PlatformPositions[][] = {	// gameLevel 1
														{PlatformGameWidth,20,0,PlatformGameHeight - 20},
														{150,5,300,600}
													};

	// define size of window for puzzle games
	public static final int PuzzleGameWidth = 1000;
	public static final int PuzzleGameHeight = 700;
}