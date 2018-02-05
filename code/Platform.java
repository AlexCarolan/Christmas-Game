/**
 * This class manages the display and movement of each platform that the player can stand on or run under/into
 */
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
import org.jsfml.graphics.*;

class Platform 
{
	private RectangleShape plat;
	private Texture texture = new Texture();
	private boolean ceiling;
	
	/**
	 * Platform constructor - creates a platform (for the sprite to stand on)
	 * @param xPosition - the left hand side of the platform
	 * @param yPosition - the top of the platform
	 * @param width - the width of the platform
	 * @param height - the height of the platform
	 * @param filename - the filename for the platform image
	 * @param noJump - true if this platform is a ceiling that cannot be jumped up through
	 */
	public Platform(int xPosition, int yPosition, int width, int height, String filename, boolean noJump)
	{
		// create a rectangle shape
		plat = new RectangleShape(new Vector2f(width, height));
		//texture = new Texture();
		try {
			// try to load the texture from file
			texture.loadFromFile(Paths.get(filename));
		} catch(IOException ex) {
			System.out.println("Unable to open image file: " + filename);
		}
		if (height == Utils.PlatformGameHeight)
			plat.setFillColor(Color.RED);
		else
			plat.setTexture(texture);
		resetPosition(xPosition, yPosition);
		ceiling = noJump;
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
	 * getXPosition - returns the xPosition for this platform
	 * @return int, the xPosition of this platform
	 */
	public int getXPosition()
	{
		return Math.round(plat.getPosition().x);
	}
	
	/**
	 * getYPosition - returns the yPosition for this platform
	 * @return int, the yPosition of this platform
	 */
	public int getYPosition()
	{
		return Math.round(plat.getPosition().y);
	}
	
	/**
	 * getXSize - returns the xSize for this platform
	 * @return int, the xSize of this platform
	 */
	public int getXSize()
	{
		return Math.round(plat.getSize().x);
	}
	
	/**
	 * getYSize - returns the ySize for this platform
	 * @return int, the ySize of this platform
	 */
	public int getYSize()
	{
		return Math.round(plat.getSize().y);
	}
	
	/**
	 * isCeiling - returns true if this platform is a ceiling that cannot be jumped up through
	 * @return boolean, true if this is a ceiling
	 */
	public boolean isCeiling()
	{
		return ceiling;
	}

	/**
	 * resetPosition - show the platform at its starting position for the platform game
	 */
	public void resetPosition(int xPosition, int yPosition)
	{
		// TODO set the yPosition to be on the lowest platform - probably need to pass this as a parameter
		plat.setPosition(xPosition, yPosition);
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

	/**
	 * setImage - changes the image for the platform
	 * @param filename - the path and file for the image
	 */
	public void setImage(String filename)
	{
		try {
			// try to load the texture from file
			texture.loadFromFile(Paths.get(filename));
		} catch(IOException ex) {
			System.out.println("Unable to open image file: " + filename);
		}
		plat.setTexture(texture);
	}
	
	/**
	 * setSprite - sets a new texture for the sprite
	 * @param texture - the new texture for the sprite
	 */
	public void setSprite(Texture texture)
	{
		plat.setTexture(texture);
	}
}
 