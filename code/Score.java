/**
 * This class holds the players score for the current level
 */
class Score
{
	private int playerScore;
	
	/**
	 * Score constructor - creates an object to track the players score
	 * @param s - the score starting value
	 */
	public score(int score)
	{
		playerScore = score;
	}

	/**
	 * increase the players score
	 * @param value - the number that the score will be increased by
	 */
	public void increaseScore(int value)
	{
		playerScore = playerScore + value;
	}
	
	/**
	 * accessor for the players current score
	 * @return the players score
	 */
	public int getScore()
	{
		return playerScore;
	}

}
