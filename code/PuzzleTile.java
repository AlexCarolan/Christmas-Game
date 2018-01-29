import java.lang.*;
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
//import org.jsfml.window.*;
//import org.jsfml.window.event.*;
//import org.jsfml.window.Keyboard.*;
import org.jsfml.graphics.*;

class PuzzleTile 
{
	RectangleShape Tile;
	String shownPicture;
	int x;
	int y;
	
	public PuzzleTile(int xPosition, int yPosition, String picture, int xSize, int ySize)
	{
		// create a rectangle shape
		int x = xPosition;
		int y = yPosition;
		shownPicture = picture;
		
		Tile = new RectangleShape(new Vector2f(xSize, ySize));
		Tile.setFillColor(Color.WHITE);
		setPicture(shownPicture);
		Tile.setPosition(xPosition,yPosition);
	}
	
	
	public void setPicture(String picture)
	{
		shownPicture = picture;
		Texture texture = new Texture();
		try {
			// try to load the texture from file
			texture.loadFromFile(Paths.get(shownPicture));
		} catch(IOException ex) {
			//ex.printStackTrace();
			System.out.println("Unable to open texture file");
		}
		Tile.setTexture(texture);
	}
	
	/**
	 * getPlatform - returns the shape object for this platform
	 * @return RectangleShape, the shape object
	 */
	public RectangleShape getTile()
	{
		return Tile;
	}
	
	/**
	 * move - moves the platform by the specified amount
	 * @param xInc - the increment to move on the x-axis
	 * @param yInc - the increment to move on the y-axis
	 */
	public void move(int xInc, int yInc)
	{
		Tile.move(xInc, yInc);
	}
	
	public String getPicture()
	{
		return shownPicture;
	}
	
	public int getX()
	{
		return x;
	}
	
	public int getY()
	{
		return y;
	}
}
 