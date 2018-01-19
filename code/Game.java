/**
 * This class runs the Christmas Game 
 */
class Game 
{

	public static void main (String args[ ]) 
	{
		// TODO: display the ChristmasRoom
		// if we're playing a platform game, play it at the current level
		// if we're playing a puzzle, play it at the current level
		// FOR NOW, just display a platform game
		Level level = new Level( );
		level.run( );
	}
}
