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
	/**
	 * run - handle display and movement of the platform game for this level
	 * @return boolean - success indicator (true if puzzle completed)
	 */
	public boolean run() 
	{
		// create the window
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode(Utils.PuzzleGameWidth, Utils.PlatformGameHeight),
					"Picture Puzzle, Level 2",
					WindowStyle.CLOSE | WindowStyle.TITLEBAR);	// window can't be resized

		// limit the framerate
		window.setFramerateLimit(20);	// was 60
		
		// add load screen
		Texture loadImg = new Texture();
 		
 		try {
 		loadImg.loadFromFile(Paths.get("images\\load\\puzzle1.png"));
 		} catch(IOException ex) {
 			System.out.println(ex);
 		}
  
 		Sprite loadBkg = new Sprite(loadImg);
		loadBkg.setOrigin(0,0);
 		loadBkg.setPosition(0,0);

		window.draw(loadBkg);
		window.display();

		// create all objects
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
		tiles[0][0] = new PuzzleTile(0,0,"images\\tilePuzzle\\T7.png", Utils.tileX, Utils.tileY);
		tiles[0][1] = new PuzzleTile(200,0,"images\\tilePuzzle\\T2.png", Utils.tileX, Utils.tileY);
		tiles[0][2] = new PuzzleTile(400,0,"images\\tilePuzzle\\T4.png", Utils.tileX, Utils.tileY);
		tiles[1][0] = new PuzzleTile(0,200,"images\\tilePuzzle\\T5.png", Utils.tileX, Utils.tileY);
		tiles[1][1] = new PuzzleTile(200,200,"images\\tilePuzzle\\T0.png", Utils.tileX, Utils.tileY); //blank default
		tiles[1][2] = new PuzzleTile(400,200,"images\\tilePuzzle\\T6.png", Utils.tileX, Utils.tileY);
		tiles[2][0] = new PuzzleTile(0,400,"images\\tilePuzzle\\T8.png", Utils.tileX, Utils.tileY);
		tiles[2][1] = new PuzzleTile(200,400,"images\\tilePuzzle\\T3.png", Utils.tileX, Utils.tileY);
		tiles[2][2] = new PuzzleTile(400,400,"images\\tilePuzzle\\T1.png", Utils.tileX, Utils.tileY);

		// position of tiles to successfully complete puzzle game
		endTiles[0][0] = new PuzzleTile(0,0,"images\\tilePuzzle\\T0.png", Utils.tileX, Utils.tileY);
		endTiles[0][1] = new PuzzleTile(200,0,"images\\tilePuzzle\\T1.png", Utils.tileX, Utils.tileY);
		endTiles[0][2] = new PuzzleTile(400,0,"images\\tilePuzzle\\T2.png", Utils.tileX, Utils.tileY);
		endTiles[1][0] = new PuzzleTile(0,200,"images\\tilePuzzle\\T3.png", Utils.tileX, Utils.tileY);
		endTiles[1][1] = new PuzzleTile(200,200,"images\\tilePuzzle\\T4.png", Utils.tileX, Utils.tileY);
		endTiles[1][2] = new PuzzleTile(400,200,"images\\tilePuzzle\\T5.png", Utils.tileX, Utils.tileY);
		endTiles[2][0] = new PuzzleTile(0,400,"images\\tilePuzzle\\T6.png", Utils.tileX, Utils.tileY);
		endTiles[2][1] = new PuzzleTile(200,400,"images\\tilePuzzle\\T7.png", Utils.tileX, Utils.tileY);
		endTiles[2][2] = new PuzzleTile(400,400,"images\\tilePuzzle\\T8.png", Utils.tileX, Utils.tileY);

		// set up instructions
										
		// Load the font for the instructions
		Font sansRegular = new Font( );
		try {
			sansRegular.loadFromFile(
					Paths.get("fonts\\LucidaSansRegular.ttf"));
		} catch (IOException ex) {
			ex.printStackTrace( );
		}
		Text text1 = new Text("Can you re-arrange the pieces to find the Christmas picture?", sansRegular, 18);
		Text text2 = new Text("Use the arrow keys, or WASD, to move", sansRegular, 18);
		Text text3 = new Text("Well done, you completed the picture!", sansRegular, 18);
		text1.setColor(Color.RED);
		text2.setColor(Color.RED);
		text3.setColor(Color.WHITE);
		text2.setStyle(Text.ITALIC);
		text1.setPosition(50, Utils.PuzzleGameHeight+20);
		text2.setPosition(50, Utils.PuzzleGameHeight+40);
		text3.setPosition(50, Utils.PuzzleGameHeight+60);
		

		boolean finished = false;
		while (window.isOpen() && !finished) 
		{
			// handle keyboard/mouse events (movement can be via WASD or arrow keys)
			for (Event event : window.pollEvents()) 
			{
				switch(event.type) 
				{
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
					case CLOSED:
						//System.out.println("Close clicked");
						window.close();
						break;
				}
			}

			if (window.isOpen())
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

				// add instructions
				window.draw(text1);
				window.draw(text2);

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
					window.draw(text3);
				}

				// display what was drawn on the window
				window.display();

				if (finished)
				{
					try {					// pause so player can see success message
						Thread.sleep(1000);
					} catch (Exception e) {
						System.out.println();
					}
					window.close();
				}
			}
		}
		if (window.isOpen())
			window.close();
		return finished;
	}
}	
