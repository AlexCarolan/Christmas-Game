//import java.lang.*;
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.window.Keyboard.*;
import org.jsfml.graphics.*;

class Puzzle0
{
	private static String wall   = "images\\NewTiles\\Trees2.png";
	private static String floor   = "images\\NewTiles\\Ice.png";
	private static String player   = "images\\NewTiles\\Player.png";
	

	/**
	 * run - handle display and movement of the platform game for this level
	 */
	public static void run () 
	{
		// create the window
		RenderWindow window = new RenderWindow( );
		//window.create(new VideoMode((19*35), (19*35)),
		window.create(new VideoMode((19*35), Utils.PlatformGameHeight),
						"Maze Puzzle, Level 1",
						WindowStyle.CLOSE | WindowStyle.TITLEBAR);	// window can't be resized

		// limit the framerate
		window.setFramerateLimit(60);

		// create all object
		//PuzzleTile t = new PuzzleTile(0,0,"T0.png");
		int[] blankTile = new int[2];
		int[] switchTile = new int[2];
		blankTile[0] = 1;
		blankTile[1] = 0;
		switchTile[0] = 1;
		switchTile[1] = 1;
		MazeTile[][] tiles = new MazeTile[19][19];
		PuzzleTile[][] endTiles = new PuzzleTile[3][3];

		// tiles at start of puzzle game
		for (int i = 0; i < 19; i++)
		{
			for (int j = 0; j < 19; j++)
			{
				tiles[i][j] = new MazeTile((i*35),(j*35),wall,35,35,false);
			}
		}
		tiles[18][17] = new MazeTile((18*35),(17*35),floor,35,35,true);
		for(int i = 0; i < 8; i++)
		{
			tiles[1][i] = new MazeTile((1*35),(i*35),floor,35,35,true);
		}
		for(int i = 2; i < 3; i++)
		{
			tiles[i][7] = new MazeTile((i*35),(7*35),floor,35,35,true);
		}
		for(int i = 1; i < 3; i++)
		{
			tiles[i][9] = new MazeTile((i*35),(9*35),floor,35,35,true);
		}
		for(int i = 1; i < 3; i++)
		{
			tiles[i][7] = new MazeTile((i*35),(7*35),floor,35,35,true);
		}
		for(int i = 7; i < 12; i++)
		{
			tiles[3][i] = new MazeTile((3*35),(i*35),floor,35,35,true);
		}
		for(int i = 1; i < 3; i++)
		{
			tiles[i][11] = new MazeTile((i*35),(11*35),floor,35,35,true);
		}
		tiles[1][12] = new MazeTile((1*35),(12*35),floor,35,35,true);
		for(int i = 1; i < 8; i++)
		{
			tiles[i][13] = new MazeTile((i*35),(13*35),floor,35,35,true);
		}
		for(int i = 1; i < 4; i++)
		{
			tiles[i][15] = new MazeTile((i*35),(15*35),floor,35,35,true);
		}
		tiles[1][16] = new MazeTile((1*35),(16*35),floor,35,35,true);
		for(int i = 1; i < 6; i++)
		{
			tiles[i][17] = new MazeTile((i*35),(17*35),floor,35,35,true);
		}
		for(int i = 14; i < 17; i++)
		{
			tiles[5][i] = new MazeTile((5*35),(i*35),floor,35,35,true);
		}
		for(int i = 14; i < 17; i++)
		{
			tiles[7][i] = new MazeTile((7*35),(i*35),floor,35,35,true);
		}
		for(int i = 7; i < 14; i++)
		{
			tiles[i][17] = new MazeTile((i*35),(17*35),floor,35,35,true);
		}
		for(int i = 15; i < 17; i++)
		{
			tiles[13][i] = new MazeTile((13*35),(i*35),floor,35,35,true);
		}
		for(int i = 15; i < 18; i++)
		{
			tiles[15][i] = new MazeTile((15*35),(i*35),floor,35,35,true);
		}
		tiles[14][15] = new MazeTile((14*35),(15*35),floor,35,35,true);
		for(int i = 3; i < 18; i++)
		{
			tiles[i][1] = new MazeTile((i*35),(1*35),floor,35,35,true);
		}
		tiles[2][3] = new MazeTile((2*35),(3*35),floor,35,35,true);
		tiles[3][2] = new MazeTile((3*35),(2*35),floor,35,35,true);
		tiles[3][3] = new MazeTile((3*35),(3*35),floor,35,35,true);
		tiles[7][2] = new MazeTile((7*35),(2*35),floor,35,35,true);
		tiles[7][3] = new MazeTile((7*35),(3*35),floor,35,35,true);
		tiles[6][3] = new MazeTile((6*35),(3*35),floor,35,35,true);
		tiles[5][3] = new MazeTile((5*35),(3*35),floor,35,35,true);
		tiles[5][4] = new MazeTile((5*35),(4*35),floor,35,35,true);
		tiles[5][5] = new MazeTile((5*35),(5*35),floor,35,35,true);
		tiles[4][5] = new MazeTile((4*35),(5*35),floor,35,35,true);
		tiles[3][5] = new MazeTile((3*35),(5*35),floor,35,35,true);
		tiles[9][2] = new MazeTile((9*35),(2*35),floor,35,35,true);
		tiles[9][3] = new MazeTile((9*35),(3*35),floor,35,35,true);
		for(int i = 9; i < 18; i++)
		{
			tiles[i][3] = new MazeTile((i*35),(3*35),floor,35,35,true);
		}
		tiles[15][4] = new MazeTile((15*35),(4*35),floor,35,35,true);
		tiles[15][5] = new MazeTile((15*35),(5*35),floor,35,35,true);
		for(int i = 3; i < 12; i++)
		{
			tiles[17][i] = new MazeTile((17*35),(i*35),floor,35,35,true);
		}
		tiles[16][7] = new MazeTile((16*35),(7*35),floor,35,35,true);
		tiles[15][7] = new MazeTile((15*35),(7*35),floor,35,35,true);
		for(int i = 8; i < 12; i++)
		{
			tiles[15][i] = new MazeTile((15*35),(i*35),floor,35,35,true);
		}
		tiles[14][11] = new MazeTile((14*35),(11*35),floor,35,35,true);
		for(int i = 5; i < 12; i++)
		{
			tiles[13][i] = new MazeTile((13*35),(i*35),floor,35,35,true);
		}
		for(int i = 7; i < 13; i++)
		{
			tiles[i][5] = new MazeTile((i*35),(5*35),floor,35,35,true);
		}
		tiles[7][6] = new MazeTile((7*35),(6*35),floor,35,35,true);
		for(int i = 5; i < 12; i++)
		{
			tiles[i][7] = new MazeTile((i*35),(7*35),floor,35,35,true);
		}
		for(int i = 8; i < 12; i++)
		{
			tiles[11][i] = new MazeTile((11*35),(i*35),floor,35,35,true);
		}
		for(int i = 8; i < 12; i++)
		{
			tiles[5][i] = new MazeTile((5*35),(i*35),floor,35,35,true);
		}
		tiles[6][11] = new MazeTile((6*35),(11*35),floor,35,35,true);
		tiles[7][11] = new MazeTile((7*35),(11*35),floor,35,35,true);
		tiles[7][10] = new MazeTile((7*35),(10*35),floor,35,35,true);
		tiles[7][9] = new MazeTile((7*35),(9*35),floor,35,35,true);
		tiles[8][9] = new MazeTile((8*35),(9*35),floor,35,35,true);
		tiles[9][9] = new MazeTile((9*35),(9*35),floor,35,35,true);
		for(int i = 10; i < 16; i++)
		{
			tiles[9][i] = new MazeTile((9*35),(i*35),floor,35,35,true);
		}
		tiles[10][15] = new MazeTile((10*35),(15*35),floor,35,35,true);
		tiles[11][15] = new MazeTile((11*35),(15*35),floor,35,35,true);
		tiles[11][14] = new MazeTile((11*35),(14*35),floor,35,35,true);
		for(int i = 11; i < 18; i++)
		{
			tiles[i][13] = new MazeTile((i*35),(13*35),floor,35,35,true);
		}
		for(int i = 14; i < 18; i++)
		{
			tiles[17][i] = new MazeTile((17*35),(i*35),floor,35,35,true);
		}
		tiles[1][0] = new MazeTile((1*35),(0*35),player,35,35,true);

		// set up instructions
										
		// Load the font for the instructions
		Font sansRegular = new Font( );
		try {
			sansRegular.loadFromFile(
					Paths.get("fonts\\LucidaSansRegular.ttf"));
		} catch (IOException ex) {
			ex.printStackTrace( );
		}
		Text text1 = new Text("Can you help the Christmas Elf to find her way through the Maze?", sansRegular, 18);
		Text text2 = new Text("Use the arrow keys, or WASD, to move", sansRegular, 18);
		Text text3 = new Text("Well done, you completed the maze!", sansRegular, 18);
		text1.setColor(Color.RED);
		text2.setColor(Color.RED);
		text3.setColor(Color.WHITE);
		text2.setStyle(Text.ITALIC);
		text1.setPosition(50, Utils.PlatformGameHeight-80);
		text2.setPosition(50, Utils.PlatformGameHeight-60);
		text3.setPosition(50, Utils.PlatformGameHeight-40);
		

		boolean finished = false;

		while (window.isOpen() && !finished) 
		{
			// fill the window with black
			window.clear(Color.BLACK);

			// add all objects onto the window
			for (int i = 0; i < 19; i++)
			{
				for (int j = 0; j < 19; j++)
				{
					window.draw(tiles[i][j].getTile());
				}
			}

			// display instructions
			window.draw(text1);
			window.draw(text2);

			// display what was drawn on the window
			window.display();

			// handle keyboard/mouse events (movement can be via WASD or arrow keys)
			if (Keyboard.isKeyPressed(Keyboard.Key.S) || Keyboard.isKeyPressed(Keyboard.Key.DOWN))
			{
				switchTile[1] = blankTile[1] + 1;
				if(tiles[switchTile[0]][switchTile[1]].isPassable())
				{
					if (blankTile[1] != 19)
					{
						//switchTile[1] = blankTile[1] + 1;
						String temp = tiles[switchTile[0]][switchTile[1]].getPicture();
						tiles[switchTile[0]][switchTile[1]].setPicture(tiles[blankTile[0]][blankTile[1]].getPicture());
						tiles[blankTile[0]][blankTile[1]].setPicture(temp);
						blankTile[1] = switchTile[1];
					}
				}
				else
				{
					switchTile[1] = blankTile[1];
				}
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.W) || Keyboard.isKeyPressed(Keyboard.Key.UP))
			{
				switchTile[1] = blankTile[1] - 1;
				if (blankTile[1] != 0)
				{
					if(tiles[switchTile[0]][switchTile[1]].isPassable())
					{
						String temp = tiles[switchTile[0]][switchTile[1]].getPicture();
						tiles[switchTile[0]][switchTile[1]].setPicture(tiles[blankTile[0]][blankTile[1]].getPicture());
						tiles[blankTile[0]][blankTile[1]].setPicture(temp);
						blankTile[1] = switchTile[1];
					}
					else
					{
						switchTile[1] = blankTile[1];
					}
				}
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.D) || Keyboard.isKeyPressed(Keyboard.Key.RIGHT))
			{
				// if possible, get tile above blank tile
				if (blankTile[0] != 18)
				{
					switchTile[0] = blankTile[0] + 1;
					if(tiles[switchTile[0]][switchTile[1]].isPassable())
					{
						// swap tiles
						switchTile[0] = blankTile[0] + 1;
						String temp = tiles[switchTile[0]][switchTile[1]].getPicture();
						tiles[switchTile[0]][switchTile[1]].setPicture(tiles[blankTile[0]][blankTile[1]].getPicture());
						tiles[blankTile[0]][blankTile[1]].setPicture(temp);
						blankTile[0] = switchTile[0];
					}
					else
					{
						switchTile[0] = blankTile[0];
					}
				}
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.A) || Keyboard.isKeyPressed(Keyboard.Key.LEFT))
			{
				switchTile[0] = blankTile[0] - 1;
				if(tiles[switchTile[0]][switchTile[1]].isPassable())
				{
					if (blankTile[0] != 0)
					{
						// swap tiles
						
						String temp = tiles[switchTile[0]][switchTile[1]].getPicture();
						tiles[switchTile[0]][switchTile[1]].setPicture(tiles[blankTile[0]][blankTile[1]].getPicture());
						tiles[blankTile[0]][blankTile[1]].setPicture(temp);
						blankTile[0] = switchTile[0];
					}
				}
				else
				{
					switchTile[0] = blankTile[0];
				}
			}

			for (Event event : window.pollEvents()) 
			{
				switch(event.type) 
				{
					case CLOSED:
						System.out.println("Close clicked");
						window.close();
						break;
				}
			}

			if (window.isOpen())
			{
				// check whether images are all in the right place
				
				finished = true;
				if (tiles[18][17].getPicture() != player)
					finished = false;
				if (finished)
				{
					System.out.println("Well done, you completed the maze!");
					window.draw(text3);
					window.display();
					try {					// pause so player can see success message
						Thread.sleep(1000);
					} catch (Exception e) {
						System.out.println();
					}
					window.close();
				}
			}
			try {							// pause so key repeat is not too fast
				Thread.sleep(80);
			} catch (Exception e) {
				System.out.println();
			}
		}
		if (window.isOpen())
			window.close();
	}

	//public static void main (String args[ ])
	//{
	//	Puzzle0 p = new Puzzle0( );
	//	p.run( );
	//	p = null;
	//}
}	
