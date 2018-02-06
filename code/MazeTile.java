import java.lang.*;
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
//import org.jsfml.window.*;
//import org.jsfml.window.event.*;
//import org.jsfml.window.Keyboard.*;
import org.jsfml.graphics.*;

class MazeTile extends PuzzleTile
{
	boolean passable = true;
	
	public MazeTile(int xPosition, int yPosition, String picture, int xSize, int ySize, boolean p)
	{
		super(xPosition, yPosition, picture, xSize, ySize);
		passable = p;
	}
	
	public boolean isPassable()
	{
		return passable;
	}
}