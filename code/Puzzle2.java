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
	/**
	 * run - handle display and movement of the platform game for this level
	 * @return boolean - success indicator (true if puzzle completed)
	 */
	public boolean run()
	{
		// create the window
		PuzzleTile background = new PuzzleTile(0,0,"images\\knotPuzzle\\tree.png", 520, 601);
		PuzzleTile selectTool = new PuzzleTile(380,140,"images\\knotPuzzle\\select.png", 35, 35);
		int selectPosition = 0;
		int[][] selectPositions = new int[4][2];
		selectPositions[0][0] = 380;
		selectPositions[0][1] = 140;
		selectPositions[1][0] = 100;
		selectPositions[1][1] = 205;
		selectPositions[2][0] = 455;
		selectPositions[2][1] = 295;
		selectPositions[3][0] = 25;
		selectPositions[3][1] = 360;
		int puzzleHeight = 601;

		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode(520, Utils.PlatformGameHeight),
					"Christmas Tree Lights Puzzle, Level 3",
					WindowStyle.DEFAULT);

		// limit the framerate
		window.setFramerateLimit(24);

		// create all objects
		PuzzleTile[][] Strings = new PuzzleTile[4][5];
		PuzzleTile[][] endStrings = new PuzzleTile[4][5];
		PuzzleTile[] TempHolder = new PuzzleTile[5];
		PuzzleTile ConstantBulb = new PuzzleTile(302,161,"images\\knotPuzzle\\pinkB.png", 35, 35);

		// strings at start of puzzle game
		Strings[0][0] = new PuzzleTile(135,213,"images\\knotPuzzle\\orangeB.png", 35, 35);
		Strings[0][1] = new PuzzleTile(185,210,"images\\knotPuzzle\\purpleB.png", 35, 35);
		Strings[0][2] = new PuzzleTile(230,195,"images\\knotPuzzle\\pinkB.png", 35, 35);
		Strings[0][3] = new PuzzleTile(270,185,"images\\knotPuzzle\\silverB.png", 35, 35);
		Strings[0][4] = new PuzzleTile(302,161,"images\\knotPuzzle\\redB.png", 35, 35);
		
		Strings[1][0] = new PuzzleTile(135,213,"images\\knotPuzzle\\silverB.png", 35, 35);
		Strings[1][1] = new PuzzleTile(190,255,"images\\knotPuzzle\\purpleB.png", 35, 35);
		Strings[1][2] = new PuzzleTile(265,280,"images\\knotPuzzle\\pinkB.png", 35, 35);
		Strings[1][3] = new PuzzleTile(340,295,"images\\knotPuzzle\\redB.png", 35, 35);
		Strings[1][4] = new PuzzleTile(405,300,"images\\knotPuzzle\\orangeB.png", 35, 35);
		
		Strings[2][0] = new PuzzleTile(75,380,"images\\knotPuzzle\\pinkB.png", 35, 35);
		Strings[2][1] = new PuzzleTile(175,385,"images\\knotPuzzle\\silverB.png", 35, 35);
		Strings[2][2] = new PuzzleTile(295,370,"images\\knotPuzzle\\redB.png", 35, 35);
		Strings[2][3] = new PuzzleTile(370,345,"images\\knotPuzzle\\orangeB.png", 35, 35);
		Strings[2][4] = new PuzzleTile(405,300,"images\\knotPuzzle\\purpleB.png", 35, 35);
		
		Strings[3][0] = new PuzzleTile(75,380,"images\\knotPuzzle\\redB.png", 35, 35);
		Strings[3][1] = new PuzzleTile(145,425,"images\\knotPuzzle\\purpleB.png", 35, 35);
		Strings[3][2] = new PuzzleTile(225,440,"images\\knotPuzzle\\silverB.png", 35, 35);
		Strings[3][3] = new PuzzleTile(345,455,"images\\knotPuzzle\\orangeB.png", 35, 35);
		Strings[3][4] = new PuzzleTile(430,464,"images\\knotPuzzle\\pinkB.png", 35, 35);
		
		// strings at end of puzzle
		endStrings[0][0] = new PuzzleTile(135,213,"images\\knotPuzzle\\silverB.png", 35, 35);
		endStrings[0][1] = new PuzzleTile(185,210,"images\\knotPuzzle\\redB.png", 35, 35);
		endStrings[0][2] = new PuzzleTile(230,195,"images\\knotPuzzle\\orangeB.png", 35, 35);
		endStrings[0][3] = new PuzzleTile(270,185,"images\\knotPuzzle\\purpleB.png", 35, 35);
		endStrings[0][4] = new PuzzleTile(302,161,"images\\knotPuzzle\\pinkB.png", 35, 35);
		
		endStrings[1][0] = new PuzzleTile(135,213,"images\\knotPuzzle\\silverB.png", 35, 35);
		endStrings[1][1] = new PuzzleTile(190,255,"images\\knotPuzzle\\purpleB.png", 35, 35);
		endStrings[1][2] = new PuzzleTile(265,280,"images\\knotPuzzle\\pinkB.png", 35, 35);
		endStrings[1][3] = new PuzzleTile(340,295,"images\\knotPuzzle\\redB.png", 35, 35);
		endStrings[1][4] = new PuzzleTile(405,300,"images\\knotPuzzle\\orangeB.png", 35, 35);
		
		endStrings[2][0] = new PuzzleTile(75,380,"images\\knotPuzzle\\purpleB.png", 35, 35);
		endStrings[2][1] = new PuzzleTile(175,385,"images\\knotPuzzle\\pinkB.png", 35, 35);
		endStrings[2][2] = new PuzzleTile(295,370,"images\\knotPuzzle\\silverB.png", 35, 35);
		endStrings[2][3] = new PuzzleTile(370,345,"images\\knotPuzzle\\redB.png", 35, 35);
		endStrings[2][4] = new PuzzleTile(405,300,"images\\knotPuzzle\\orangeB.png", 35, 35);
		
		endStrings[3][0] = new PuzzleTile(75,380,"images\\knotPuzzle\\purpleB.png", 35, 35);
		endStrings[3][1] = new PuzzleTile(145,425,"images\\knotPuzzle\\silverB.png", 35, 35);
		endStrings[3][2] = new PuzzleTile(225,440,"images\\knotPuzzle\\orangeB.png", 35, 35);
		endStrings[3][3] = new PuzzleTile(345,455,"images\\knotPuzzle\\pinkB.png", 35, 35);
		endStrings[3][4] = new PuzzleTile(430,464,"images\\knotPuzzle\\redB.png", 35, 35);

		// set up instructions
										
		// Load the font for the instructions
		Font sansRegular = new Font( );
		try {
			sansRegular.loadFromFile(
					Paths.get("fonts\\LucidaSansRegular.ttf"));
		} catch (IOException ex) {
			ex.printStackTrace( );
		}
		Text text1a = new Text("Can you untangle the lights?", sansRegular, 18);
		Text text1b = new Text("Each row should have no repeat colours", sansRegular, 18);
		Text text2a = new Text("Use Up/Down (or WS) keys to select the row", sansRegular, 18);
		Text text2b = new Text("and Left/Right (or AD) keys to rotate the lights", sansRegular, 18);
		Text text2c = new Text("Top and bottom rows are fixed", sansRegular, 18);
		Text text3 = new Text("Well done, you untangled the lights!", sansRegular, 18);
		text1a.setColor(Color.BLACK);
		text1b.setColor(Color.BLACK);
		text2a.setColor(Color.BLUE);
		text2b.setColor(Color.BLUE);
		text2c.setColor(Color.BLACK);
		text3.setColor(Color.RED);
		text2a.setStyle(Text.ITALIC);
		text2b.setStyle(Text.ITALIC);
		text1a.setPosition(50, puzzleHeight+20);
		text1b.setPosition(50, puzzleHeight+40);
		text2a.setPosition(50, puzzleHeight+60);
		text2b.setPosition(50, puzzleHeight+80);
		text2c.setPosition(50, puzzleHeight+100);
		text3.setPosition(50, puzzleHeight+120);


		boolean finished = false;
		while (window.isOpen() && !finished) 
		{
			// handle keyboard/mouse events (movement can be via WASD or arrow keys)
			for (Event event : window.pollEvents()) 
			{
				switch(event.type) 
				{
					case CLOSED:
						window.close();
						break;
					case KEY_PRESSED:
						KeyEvent keyEvent = event.asKeyEvent();
						if ((keyEvent.key == Keyboard.Key.LEFT) || (keyEvent.key == Keyboard.Key.A))
						{
							for (int i = 0; i < 5; i ++)
							{
								//System.out.println(Strings[selectPosition][i].getX());
								TempHolder[i] = Strings[selectPosition][i];
							}
							Strings[selectPosition][0] = new PuzzleTile(TempHolder[0].getX(),TempHolder[0].getY(), TempHolder[4].getPicture(), 35, 35);
							for (int i = 1; i < 5; i ++)
							{
								Strings[selectPosition][i] = new PuzzleTile(TempHolder[i].getX(),TempHolder[i].getY(), TempHolder[i-1].getPicture(), 35, 35);
							}
						}
						else if ((keyEvent.key == Keyboard.Key.RIGHT) || (keyEvent.key == Keyboard.Key.D))
						{
							for (int i = 0; i < 5; i ++)
							{
								TempHolder[i] = Strings[selectPosition][i];
							}
							Strings[selectPosition][4] = new PuzzleTile(TempHolder[4].getX(),TempHolder[4].getY(), TempHolder[0].getPicture(), 35, 35);
							for (int i = 0; i < 4; i ++)
							{
								Strings[selectPosition][i] = new PuzzleTile(TempHolder[i].getX(),TempHolder[i].getY(), TempHolder[i+1].getPicture(), 35, 35);
							}
						}
						else if ((keyEvent.key == Keyboard.Key.UP) || (keyEvent.key == Keyboard.Key.W))
						{
							if (selectPosition > 0)
							{
								selectPosition--;
								selectTool = new PuzzleTile(selectPositions[selectPosition][0],selectPositions[selectPosition][1],"images\\knotPuzzle\\select.png", 35, 35);
							}
						}
						else if ((keyEvent.key == Keyboard.Key.DOWN) || (keyEvent.key == Keyboard.Key.S))
						{
							if (selectPosition < 3)
							{
								selectPosition++;
								selectTool = new PuzzleTile(selectPositions[selectPosition][0],selectPositions[selectPosition][1],"images\\knotPuzzle\\select.png", 35, 35);
							}
						}
						break;
				}
			}

			if (window.isOpen())
			{
				// fill the window with white
				window.clear(Color.WHITE);
				window.draw(background.getTile());

				// add all objects onto the window
				for (int i = 0; i < 5; i++)
				{
					for (int j = 0; j < 4; j++)
					{
						window.draw(Strings[j][i].getTile());
					}
				}
				window.draw(ConstantBulb.getTile());
				window.draw(selectTool.getTile());

				// add instructions
				window.draw(text1a);
				window.draw(text1b);
				window.draw(text2a);
				window.draw(text2b);
				window.draw(text2c);

				// check whether images are all in the right place
				finished = true;
				for (int i = 0; i < 4; i++)
					if (finished)
						for (int j = 0; j < 5; j++)
							if (Strings[i][j].getPicture() != endStrings[i][j].getPicture())
								finished = false;

				if (finished)
				{
					System.out.println("Well done, you untangled the lights!");
					window.draw(text3);
				}

				// display what was drawn on the window
				window.display();

				if (finished)
				{
					try {					// pause so player can see success message
						Thread.sleep(2000);
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
