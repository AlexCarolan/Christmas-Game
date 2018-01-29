import java.lang.*;
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.window.Keyboard.*;
import org.jsfml.graphics.*;

class Puzzle2
{
	private static String Title   = "Puzzle1";

	/**
	 * run - handle display and movement of the platform game for this level
	 */
	public static void run () 
	{
		// create the window
		PuzzleTile background = new PuzzleTile(0,0,"images\\knotPuzzle\\tree.png", 520, 601);
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode(520, 601),
					Title,
					WindowStyle.DEFAULT);

		// limit the framerate
		window.setFramerateLimit(24);

		// create all object
		//PuzzleTile t = new PuzzleTile(0,0,"T0.png");
		int[] blankTile = new int[2];
		int[] switchTile = new int[2];

		PuzzleTile[] String1 = new PuzzleTile[5];
		PuzzleTile[] String2 = new PuzzleTile[5];
		PuzzleTile[] String3 = new PuzzleTile[5];
		PuzzleTile[] String4 = new PuzzleTile[5];

		// strings at start of puzzle game
		String1[0] = new PuzzleTile(135,213,"images\\knotPuzzle\\silverB.png", 35, 35);


		// position of tiles to successfully complete puzzle game


		boolean finished = false;

		while (window.isOpen()/* && !finished*/) 
		{
			// fill the window with black
			window.clear(Color.WHITE);
			window.draw(background.getTile());
			// add all objects onto the window
			window.draw(String1[0].getTile());
			/*
			for (int i = 0; i < 5; i++)
			{
				window.draw(String1[i].getTile());
				window.draw(String2[i].getTile());
				window.draw(String3[i].getTile());
				window.draw(String4[i].getTile());
			}
			*/
			
			// handle keyboard/mouse events (movement can be via WASD or arrow keys)
			/*
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
							if (blankTile[1] != 2)
							{
								switchTile[1] = blankTile[1] + 1;
								String temp = tiles[switchTile[0]][switchTile[1]].getPicture();
								tiles[switchTile[0]][switchTile[1]].setPicture(tiles[blankTile[0]][blankTile[1]].getPicture());
								tiles[blankTile[0]][blankTile[1]].setPicture(temp);
								blankTile[1] = switchTile[1];
							}
						}
						else if ((keyEvent.key == Keyboard.Key.RIGHT) || (keyEvent.key == Keyboard.Key.D))
						{
							if (blankTile[1] != 0)
							{
								switchTile[1] = blankTile[1] - 1;
								String temp = tiles[switchTile[0]][switchTile[1]].getPicture();
								tiles[switchTile[0]][switchTile[1]].setPicture(tiles[blankTile[0]][blankTile[1]].getPicture());
								tiles[blankTile[0]][blankTile[1]].setPicture(temp);
								blankTile[1] = switchTile[1];
							}
						}
						else if ((keyEvent.key == Keyboard.Key.UP) || (keyEvent.key == Keyboard.Key.W))
						{
							// if possible, get tile above blank tile
							if (blankTile[0] != 2)
							{
								// swap tiles
								switchTile[0] = blankTile[0] + 1;
								String temp = tiles[switchTile[0]][switchTile[1]].getPicture();
								tiles[switchTile[0]][switchTile[1]].setPicture(tiles[blankTile[0]][blankTile[1]].getPicture());
								tiles[blankTile[0]][blankTile[1]].setPicture(temp);
								blankTile[0] = switchTile[0];
							}
						}
						else if ((keyEvent.key == Keyboard.Key.DOWN) || (keyEvent.key == Keyboard.Key.S))
						{
							if (blankTile[0] != 0)
							{
								// swap tiles
								switchTile[0] = blankTile[0] - 1;
								String temp = tiles[switchTile[0]][switchTile[1]].getPicture();
								tiles[switchTile[0]][switchTile[1]].setPicture(tiles[blankTile[0]][blankTile[1]].getPicture());
								tiles[blankTile[0]][blankTile[1]].setPicture(temp);
								blankTile[0] = switchTile[0];
							}
						}
						break;
				}
			}
			*/

			// display what was drawn on the window
			window.display();

			// check whether images are all in the right place
			finished = true;
			/*
			for (int i = 0; i < 3; i++)
				if (finished)
					for (int j = 0; j < 3; j++)
						if (tiles[i][j].getPicture() != endTiles[i][j].getPicture())
							finished = false;
			if (finished)
			{
				System.out.println("Well done, you completed the picture!");
				window.close();
			}
			*/
		}
	}

	public static void main (String args[ ]) 
	{
		Puzzle2 p = new Puzzle2( );
		p.run( );
	}
}	
