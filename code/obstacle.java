/**
 * This class manages the display and movement of each platform that the player can stand on or run under/into
 */
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
import org.jsfml.graphics.*;

class obstacle extends Platform 
{
	private RectangleShape plat;
	private Texture texture = new Texture();
	private boolean ceiling;
	private AnimatedCollectible animation;
	
	private boolean Damaging;
	
	/**
	 * Platform constructor - creates a platform (for the sprite to stand on)
	 * @param xPosition - the left hand side of the platform
	 * @param yPosition - the top of the platform
	 * @param width - the width of the platform
	 * @param height - the height of the platform
	 * @param filename - the filename for the platform image
	 * @param noJump - true if this platform is a ceiling that cannot be jumped up through
	 */
	public obstacle(int xPosition, int yPosition, int width, int height, String filename, boolean noJump, boolean isDamaging)
	{
		super(xPosition, yPosition, width, height, filename, noJump);
		Damaging = isDamaging;
	}
	
	/**
	 * getDamage - returns whether the object is damaging to the player
	 * @return boolean
	 */
	public boolean getDamage()
	{
		return Damaging;
	}
}
 