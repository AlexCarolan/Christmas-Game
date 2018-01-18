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
			System.out.println("Unable to open texture file");
		}

		sprite = new Sprite(texture);

		// set its origin to its centre and put it on the screen
		sprite.setOrigin(0,0);
		sprite.setPosition(512-(size.x/2), 384);
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
		position = sprite.getPosition();
		return Math.round(position.y + size.y);
	}
	
	/**
	 * touching - returns true if any part of the player is vertically touching the specified position
	 * @return boolean - returns true if touching
	 */
	public boolean touching(int y)
	{
		//if player yposition + player height >= yInc
		//  
		
		return false;
	}
}	
