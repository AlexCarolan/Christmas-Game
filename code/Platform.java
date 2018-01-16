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

class Platform 
{
	RectangleShape plat;
	
	public Platform()
	{
		// create a rectangle shape
		plat = new RectangleShape(new Vector2f(1024, 20));
		plat.setFillColor(Color.GREEN);
		plat.setPosition(0,740);
	}
	
	public RectangleShape getPlatform()
	{
		return plat;
	}
	
	public void move(int xInc, int yInc)
	{
		plat.move(xInc, yInc);
	}
}
 