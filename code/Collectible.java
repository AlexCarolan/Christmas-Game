/**
 * This class manages the display and movement of each collectible
 */
class Collectible extends Platform
{
	private boolean itemCollected;
	private static Score playerScore;

	/**
	 * Collectible constructor - creates an item for the player to collect
	 * @param xPosition - the left hand side of the collectible
	 * @param yPosition - the top of the collectible
	 * @param width - the width of the collectible
	 * @param height - the height of the collectible
	 * @param filename - the filename for the image
	 * @param score - the score class tracking the players score
	 */
	public Collectible(int width, int height, int xPosition, int yPosition, String filename, Score score)
	{
		super(width, height, xPosition, yPosition, filename, false);
		itemCollected = false;
		playerScore = score;
	}
	
	/**
	 * collected - returns true if item has been collected
	 * @return boolean, true if item already collected
	 */
	public boolean collected()
	{
		return itemCollected;
	}
	
	/**
	 * collect - marks this item as collected
	 */
	public void collect()
	{
		itemCollected = true;
		playerScore.increaseScore(100);
		
	}
}
 