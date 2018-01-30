/**
 * This class runs the Christmas Game 
 */
//import java.lang.*;
//import java.util.*;
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.window.Keyboard.*;
import org.jsfml.graphics.*;

class Game 
{
	private static int fontSize = 30;

	public static void main (String args[ ]) 
	{
		// create the game level
		Level level = new Level();

		// create a window
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode(Utils.PlatformGameWidth, Utils.PlatformGameHeight),
						"Christmas Game Menu",
						WindowStyle.CLOSE | WindowStyle.TITLEBAR);	// window can't be resized

		// set the frame-rate
		window.setFramerateLimit(60);

		// display the Christmas Room (as a platform)
		Platform room = new Platform(Utils.PlatformGameWidth, Utils.PlatformGameHeight-350,0,0,Utils.RoomImage[level.getLevel()]);
										
		// Load the font for the menu options
		Font sansRegular = new Font( );
		try {
			sansRegular.loadFromFile(
					Paths.get("fonts\\LucidaSansRegular.ttf"));
		} catch (IOException ex) {
			ex.printStackTrace( );
		}
		// create the menu text
		Text textPlatform1 = new Text("1. Play Level 1 Platform Game", sansRegular, 18);
		textPlatform1.setPosition(100, Utils.PlatformGameHeight-280);
		Text textPuzzle1 = new Text("2. Play Level 1 Puzzle Game", sansRegular, 18);
		textPuzzle1.setPosition(100, Utils.PlatformGameHeight-260);
		Text textPlatform2 = new Text("3. Play Level 2 Platform Game", sansRegular, 18);
		textPlatform2.setPosition(100, Utils.PlatformGameHeight-230);
		Text textPuzzle2 = new Text("4. Play Level 2 Puzzle Game", sansRegular, 18);
		textPuzzle2.setPosition(100, Utils.PlatformGameHeight-210);
		Text textPlatform3 = new Text("5. Play Level 3 Platform Game", sansRegular, 18);
		textPlatform3.setPosition(100, Utils.PlatformGameHeight-180);
		Text textPuzzle3 = new Text("6. Play Level 3 Puzzle Game", sansRegular, 18);
		textPuzzle3.setPosition(100, Utils.PlatformGameHeight-160);
		Text textPlatform4 = new Text("7. Play Level 4 Platform Game", sansRegular, 18);
		textPlatform4.setPosition(100, Utils.PlatformGameHeight-130);
		Text textPuzzle4 = new Text("8. Play Level 4 Puzzle Game", sansRegular, 18);
		textPuzzle4.setPosition(100, Utils.PlatformGameHeight-110);

		while (window.isOpen())
		{
			int gameLevel = level.getLevel();
			room.setImage(Utils.RoomImage[gameLevel]);
			window.clear(Color.BLACK);
			window.draw(room.getPlatform());
			window.draw(textPlatform1);
			if (gameLevel > 0)
			{
				window.draw(textPuzzle1);
				window.draw(textPlatform2);
			}
			if (gameLevel > 1)
			{
				window.draw(textPuzzle2);
				window.draw(textPlatform3);
			}
			if (gameLevel > 2)
			{
				window.draw(textPuzzle3);
				window.draw(textPlatform4);
			}
			if (gameLevel > 3)
			{
				window.draw(textPuzzle4);
			}

			// display what was drawn on the window
			window.display();

			// handle keyboard events (movement can be via WASD or arrow keys)
			// if we're playing a platform game, play it at the current level
			// if we're playing a puzzle, play it at the current level
			if (Keyboard.isKeyPressed(Keyboard.Key.NUM1))
			{
				PlatformGame platGame = new PlatformGame();
				if ((platGame.run(0) == true) && (level.getLevel() == 0))
				{
					level.incrementLevel();
					Puzzle1 puzzle = new Puzzle1();
					puzzle.run();
					puzzle = null;
				}
				platGame = null;
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.NUM2))
			{
				Puzzle1 puzzle = new Puzzle1();
				puzzle.run();
				puzzle = null;
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.NUM3) && level.getLevel() > 0)
			{
				PlatformGame platGame = new PlatformGame();
				if (platGame.run(1) && (level.getLevel() == 1))
				{
					level.incrementLevel();
					//Puzzle2 puzzle = new Puzzle2();
					//puzzle.run();
					//puzzle = null;
				}
				platGame = null;
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.NUM4) && level.getLevel() > 0)
			{
				//Puzzle2 puzzle = new Puzzle2();
				//puzzle.run();
				;//puzzle = null;
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.NUM5) && level.getLevel() > 1)
			{
				PlatformGame platGame = new PlatformGame();
				if (platGame.run(2) && (level.getLevel() == 2))
				{
					level.incrementLevel();
					//Puzzle3 puzzle = new Puzzle3();
					//puzzle.run();
					//puzzle = null;
				}
				platGame = null;
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.NUM6) && level.getLevel() > 1)
			{
				//Puzzle3 puzzle = new Puzzle3();
				//puzzle.run();
				;//puzzle = null;
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.NUM7) && level.getLevel() > 2)
			{
				PlatformGame platGame = new PlatformGame();
				if (platGame.run(3) && (level.getLevel() == 3))
				{
					level.incrementLevel();
					//Puzzle4 puzzle = new Puzzle4();
					//puzzle.run();
					//puzzle = null;
				}
				platGame = null;
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.NUM8) && level.getLevel() > 2)
			{
				//Puzzle4 puzzle = new Puzzle4();
				//puzzle.run();
				;//puzzle = null;
			}

			// handle keyboard/mouse events
			for (Event event : window.pollEvents()) 
			{
				switch(event.type) 
				{
					case CLOSED:
						window.close();
						System.exit(0);
						break;
				}
			}
		}
	}
}
