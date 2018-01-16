//
//import java.util.function.*;
import java.lang.*;
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.window.Keyboard.*;
import org.jsfml.graphics.*;

class Player
{
	Sprite sprite;
	Vector2i size;
	Vector2f position;
	
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

		//Set its origin to its centre and put it on the screen
		sprite.setOrigin(0,0);
		sprite.setPosition(512-(size.x/2), 384);
		System.out.println("Y poistion of Sprite: " + this.getYBottomPosition());
	}

	public Sprite getSprite()
	{
		return sprite;
	}
		
	public void move(int xInc, int yInc)
	{
		sprite.move(xInc, yInc);
	}
	
	public int getYBottomPosition()
	{
		position = sprite.getPosition();
		return Math.round(position.y + size.y);
	}
	
	public boolean touching(int y)
	{
		//if player yposition + player height >= yInc
		//  
		
		return false;
	}
}	
