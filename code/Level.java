/**
 * This class handles the game level
 */

class Level 
{
	private int level;
	/**
	 * constructor - create level
	 */
	public Level()
	{
		level = 0;
	}

	/**
	 * getLevel - returns the current level
	 */
	public int getLevel()
	{
		return level;
	}
	 
	/**
	 * incrementLevel - increments the current level
	 * @return - true if level is now at maximum
	 */
	public boolean incrementLevel()
	{
		if (level == Utils.MaxLevel)
			return true;
		level++;
		return false;
	}
}
