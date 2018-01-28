/**
 * This class manages the display and movement of each collectible
 */
class Collectible extends Platform
{
	private boolean itemCollected;
	private boolean keyItem;

	/**
	 * Collectible constructor - creates an item for the player to collect
	 * @param width - the width of the collectible
	 * @param height - the height of the collectible
	 * @param xPosition - the left hand side of the collectible
	 * @param yPosition - the top of the collectible
	 */
	public Collectible(int width, int height, int xPosition, int yPosition, String filename, boolean key)
	{
		super(width, height, xPosition, yPosition, filename);
		keyItem = key;
		itemCollected = false;
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
	}
	
	/**
	 * isKey - returns true if item is a key (to the door)
	 * @return boolean, true if item is a key
	 */
	public boolean isKey()
	{
		return keyItem;
	}
}
 