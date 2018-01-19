/**
 * This class manages the display and movement of the player sprite
 */
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
//import org.jsfml.window.*;
//import org.jsfml.window.event.*;
//import org.jsfml.window.Keyboard.*;
import org.jsfml.graphics.*;

class Player
{
	Sprite sprite;
	Vector2i size;
	Vector2f position;
	
	/**
	 * Player constructor - creates a player sprite
	 */
	public Player()
	{
		Texture texture = new Texture();
		try {
			// try to load the texture from file
			texture.loadFromFile(Paths.get("sprite.png"));

			// texture was loaded successfully - retrieve and print size
			size = texture.getSize();
		} catch(IOException ex) {
			//ex.printStackTrace();
			System.out.println("Unable to open image file");
		}

		sprite = new Sprite(texture);

		// put it on the screen
		// TODO set the yPosition to be on the lowest platform - probably need to pass this as a parameter
		sprite.setOrigin(0,0);
		sprite.setPosition(Utils.PlayerXPosition-(size.x/2), 500);
		System.out.println("Y position of Sprite: " + this.getYBottomPosition());
	}

	/**
	 * getSprite - returns the shape object for this player
	 * @return Sprite, the shape object
	 */
	public Sprite getSprite()
	{
		return sprite;
	}
		
	/**
	 * move - moves the player by the specified amount
	 * @param xInc - the increment to move on the x-axis
	 * @param yInc - the increment to move on the y-axis
	 */
	public void move(int xInc, int yInc)
	{
		sprite.move(xInc, yInc);
	}
	
	/**
	 * getYBottomPosition - returns the yPosition of the bottom edge of the player
	 * @return int - the position of the bottom edge of the player
	 */
	public int getYBottomPosition()
	{
		return Math.round(sprite.getPosition().y + size.y);
	}
	
	/**
	 * getXPosition - returns the xPosition of the middle of the player sprite
	 * (behaviour is the same on left or right edge)
	 * @return int - the xPosition of the player
	 */
	public int getXPosition()
	{
		return Math.round(sprite.getPosition().x + size.x/2);
	}
	
	/**
	 * touching - returns true if any part of the player is vertically touching the specified position
	 * @param itemXPosition - x position of (left edge of) item
	 * @param itemYPosition - y position of (top edge of) item
	 * @param itemWidth - width of item
	 * @param itemHeight - height of item
	 * @return boolean - returns true if player vertically touching item
	 */
	public boolean touching(int itemXPosition, int itemYPosition, int itemWidth, int itemHeight)
	{
		position = sprite.getPosition();
		// if left of sprite is beyond right of item, then not touching
		if (Math.round(position.x) > (itemXPosition + itemWidth))
			return false;
		// if right of sprite is before left of item, then not touching
		if (Math.round(position.x + size.x) < itemXPosition)
			return false;
		// if top of sprite is below bottom of item, then not touching
		if (Math.round(position.y) > (itemYPosition + itemHeight))
			return false;
		// if bottom of sprite is above top of item, then not touching
		if (Math.round(position.y + size.y) < itemYPosition)
			return false;
		return true;
	}
	
	/**
	 * standingOn - returns true if bottom edge of player is touching the specified position
	 * @param itemXPosition - x position of (left edge of) item
	 * @param itemYPosition - y position of (top edge of) item
	 * @param itemWidth - width of item
	 * @param itemHeight - height of item
	 * @return boolean - returns true if bottom of player touching item
	 */
	public boolean standingOn(int itemXPosition, int itemYPosition, int itemWidth, int itemHeight)
	{
		position = sprite.getPosition();
		// if 60% of sprite is beyond right of item, then not standing on item
		if (Math.round(position.x + (size.x*0.4)) > (itemXPosition + itemWidth))
			return false;
		// if 60% of sprite is before left of item, then not standing on item
		if (Math.round(position.x + (size.x*0.6)) < itemXPosition)
			return false;
		// if bottom of sprite is below bottom of item, then not standing on item
		if (Math.round(position.y + size.y) > (itemYPosition + itemHeight))
			return false;
		// if bottom of sprite is above top of item, then not touching
		if (Math.round(position.y + size.y) < itemYPosition)
			return false;
		return true;
	}
}	
