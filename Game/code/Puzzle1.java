import java.lang.*;
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.window.Keyboard.*;
import org.jsfml.graphics.*;

class Puzzle1
{
	private static String Title   = "Puzzle1";

	/**
	 * run - handle display and movement of the platform game for this level
	 */
	public static void run () 
	{
		// create the window
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode(Utils.PlatformGameWidth, Utils.PlatformGameHeight),
					Title,
					WindowStyle.DEFAULT);

		// limit the framerate
		window.setFramerateLimit(24);

		// create all object
		//PuzzleTile t = new PuzzleTile(0,0,"T0.png");
		int[] blankTile = new int[2];
		int[] switchTile = new int[2];
		blankTile[0] = 1;
		blankTile[1] = 1;
		switchTile[0] = 1;
		switchTile[1] = 1;
		PuzzleTile[][] Tiles = new PuzzleTile[3][3];
		
		Tiles[0][0] = new PuzzleTile(0,0,"T7.png");
		Tiles[0][1] = new PuzzleTile(200,0,"T2.png");
		Tiles[0][2] = new PuzzleTile(400,0,"T4.png");
		Tiles[1][0] = new PuzzleTile(0,200,"T5.png");
		Tiles[1][1] = new PuzzleTile(200,200,"T0.png"); //blank default
		Tiles[1][2] = new PuzzleTile(400,200,"T6.png");
		Tiles[2][0] = new PuzzleTile(0,400,"T8.png");
		Tiles[2][1] = new PuzzleTile(200,400,"T3.png");
		Tiles[2][2] = new PuzzleTile(400,400,"T1.png");

		while (window.isOpen()) 
		{
			// fill the window with black
			window.clear(Color.BLACK);

			// add all objects onto the window
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					window.draw(Tiles[i][j].getTile());
				}
			}
			
			// handle keyboard/mouse events (movement can be via WASD or arrow keys)
			for (Event event : window.pollEvents()) 
			{
				MouseEvent mouseEvent;
				switch(event.type) 
				{
					case CLOSED:
						window.close();
						break;
					case KEY_PRESSED:
						KeyEvent keyEvent = event.asKeyEvent();
						if ((keyEvent.key == Keyboard.Key.LEFT) || (keyEvent.key == Keyboard.Key.A))
						{
							if(blankTile[1] != 2)
							{
								switchTile[1] = blankTile[1] + 1;
								String temp = Tiles[switchTile[0]][switchTile[1]].getPicture();
								Tiles[switchTile[0]][switchTile[1]].setPicture(Tiles[blankTile[0]][blankTile[1]].getPicture());
								Tiles[blankTile[0]][blankTile[1]].setPicture(temp);
								blankTile[1] = switchTile[1];
							}
						}
						else if ((keyEvent.key == Keyboard.Key.RIGHT) || (keyEvent.key == Keyboard.Key.D))
						{
							if(blankTile[1] != 0)
							{
								switchTile[1] = blankTile[1] - 1;
								String temp = Tiles[switchTile[0]][switchTile[1]].getPicture();
								Tiles[switchTile[0]][switchTile[1]].setPicture(Tiles[blankTile[0]][blankTile[1]].getPicture());
								Tiles[blankTile[0]][blankTile[1]].setPicture(temp);
								blankTile[1] = switchTile[1];
							}
						}
						else if ((keyEvent.key == Keyboard.Key.UP) || (keyEvent.key == Keyboard.Key.W))
						{
							// if possible, get tile above blank tile
							if(blankTile[0] != 2)
							{
								switchTile[0] = blankTile[0] + 1;
								String temp = Tiles[switchTile[0]][switchTile[1]].getPicture();
								Tiles[switchTile[0]][switchTile[1]].setPicture(Tiles[blankTile[0]][blankTile[1]].getPicture());
								Tiles[blankTile[0]][blankTile[1]].setPicture(temp);
								blankTile[0] = switchTile[0];
							}
							// swap tiles
							
						}
						else if ((keyEvent.key == Keyboard.Key.DOWN) || (keyEvent.key == Keyboard.Key.S))
						{
							if(blankTile[0] != 0)
							{
								switchTile[0] = blankTile[0] - 1;
								String temp = Tiles[switchTile[0]][switchTile[1]].getPicture();
								Tiles[switchTile[0]][switchTile[1]].setPicture(Tiles[blankTile[0]][blankTile[1]].getPicture());
								Tiles[blankTile[0]][blankTile[1]].setPicture(temp);
								blankTile[0] = switchTile[0];
							}
							// swap tiles
							
							
						}
						break;
				}
			}

			// TODO handle gravity - if the player is not standing on a platform, they're falling

			// display what was drawn on the window
			window.display();
		}
	}

	public static void main (String args[ ]) 
	{
		Puzzle1 p = new Puzzle1( );
		p.run( );
	}
}	
