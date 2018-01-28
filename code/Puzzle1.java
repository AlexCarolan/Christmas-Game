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
		window.create(new VideoMode(Utils.PuzzleGameWidth, Utils.PuzzleGameHeight),
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
		PuzzleTile[][] tiles = new PuzzleTile[3][3];
		PuzzleTile[][] endTiles = new PuzzleTile[3][3];

		// tiles at start of puzzle game
		tiles[0][0] = new PuzzleTile(0,0,"images\\tilePuzzle\\T7.png");
		tiles[0][1] = new PuzzleTile(200,0,"images\\tilePuzzle\\T2.png");
		tiles[0][2] = new PuzzleTile(400,0,"images\\tilePuzzle\\T4.png");
		tiles[1][0] = new PuzzleTile(0,200,"images\\tilePuzzle\\T5.png");
		tiles[1][1] = new PuzzleTile(200,200,"images\\tilePuzzle\\T0.png"); //blank default
		tiles[1][2] = new PuzzleTile(400,200,"images\\tilePuzzle\\T6.png");
		tiles[2][0] = new PuzzleTile(0,400,"images\\tilePuzzle\\T8.png");
		tiles[2][1] = new PuzzleTile(200,400,"images\\tilePuzzle\\T3.png");
		tiles[2][2] = new PuzzleTile(400,400,"images\\tilePuzzle\\T1.png");

		// position of tiles to successfully complete puzzle game
		endTiles[0][0] = new PuzzleTile(0,0,"images\\tilePuzzle\\T0.png");
		endTiles[0][1] = new PuzzleTile(200,0,"images\\tilePuzzle\\T1.png");
		endTiles[0][2] = new PuzzleTile(400,0,"images\\tilePuzzle\\T2.png");
		endTiles[1][0] = new PuzzleTile(0,200,"images\\tilePuzzle\\T3.png");
		endTiles[1][1] = new PuzzleTile(200,200,"images\\tilePuzzle\\T4.png");
		endTiles[1][2] = new PuzzleTile(400,200,"images\\tilePuzzle\\T5.png");
		endTiles[2][0] = new PuzzleTile(0,400,"images\\tilePuzzle\\T6.png");
		endTiles[2][1] = new PuzzleTile(200,400,"images\\tilePuzzle\\T7.png");
		endTiles[2][2] = new PuzzleTile(400,400,"images\\tilePuzzle\\T8.png");

		boolean finished = false;

		while (window.isOpen() && !finished) 
		{
			// fill the window with black
			window.clear(Color.BLACK);

			// add all objects onto the window
			for (int i = 0; i < 3; i++)
			{
				for (int j = 0; j < 3; j++)
				{
					window.draw(tiles[i][j].getTile());
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

			// display what was drawn on the window
			window.display();

			// check whether images are all in the right place
			finished = true;
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
		}
	}

	public static void main (String args[ ]) 
	{
		Puzzle1 p = new Puzzle1( );
		p.run( );
	}
}	
