/**
 * This class manages the display and movement of each platform that the player can stand on or run under/into
 */
import org.jsfml.system.*;
//import org.jsfml.window.*;
//import org.jsfml.window.event.*;
//import org.jsfml.window.Keyboard.*;
import org.jsfml.graphics.*;

class Platform 
{
	RectangleShape plat;
	
	/**
	 * Platform constructor - creates a platform (for the sprite to stand on)
	 * @param xSize - the width of the platform
	 * @param ySize - the height of the platform
	 * @param xPosition - the left hand side of the platform
	 * @param yPosition - the top of the platform
	 */
	public Platform(int xSize, int ySize, int xPosition, int yPosition)
	{
		// create a rectangle shape
		plat = new RectangleShape(new Vector2f(xSize, ySize));
		plat.setFillColor(Color.GREEN);
		plat.setPosition(xPosition,yPosition);
	}
	
	/**
	 * getPlatform - returns the shape object for this platform
	 * @return RectangleShape, the shape object
	 */
	public RectangleShape getPlatform()
	{
		return plat;
	}
	
	/**
	 * move - moves the platform by the specified amount
	 * @param xInc - the increment to move on the x-axis
	 * @param yInc - the increment to move on the y-axis
	 */
	public void move(int xInc, int yInc)
	{
		plat.move(xInc, yInc);
	}
}
 