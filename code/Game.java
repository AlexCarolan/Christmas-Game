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
		Platform room = new Platform(0,0,Utils.PlatformGameWidth,Utils.PlatformGameHeight-350,Utils.RoomImage[level.getLevel()],false);
										
		// Load the font for the menu options
		Font sansRegular = new Font( );
		try {
			sansRegular.loadFromFile(
					Paths.get("fonts\\LucidaSansRegular.ttf"));
		} catch (IOException ex) {
			ex.printStackTrace( );
		}
		// create the game introduction text
		Text textIntro1 = new Text("Can you help us give Tiny Tim's family a great Christmas?", sansRegular, 18);
		Text textIntro2 = new Text("Complete each level to add something special to the Christmas Room.", sansRegular, 18);
		Text textIntro3 = new Text("Look out for items to collect along the way. And there's a fun puzzle to solve at the end of each level.", sansRegular, 18);
		textIntro1.setColor(Color.RED);
		textIntro2.setColor(Color.RED);
		textIntro3.setColor(Color.RED);
		textIntro1.setStyle(Text.ITALIC);
		textIntro2.setStyle(Text.ITALIC);
		textIntro3.setStyle(Text.ITALIC);
		textIntro1.setPosition(50, Utils.PlatformGameHeight-340);
		textIntro2.setPosition(50, Utils.PlatformGameHeight-320);
		textIntro3.setPosition(50, Utils.PlatformGameHeight-300);
		// create the menu text
		Text textPlatform1 = new Text("1. Play Level 1 Platform Game", sansRegular, 18);
		textPlatform1.setPosition(100, Utils.PlatformGameHeight-250);
		Text textPuzzle1 = new Text("2. Play Level 1 Puzzle Game", sansRegular, 18);
		textPuzzle1.setPosition(100, Utils.PlatformGameHeight-230);
		Text textPlatform2 = new Text("3. Play Level 2 Platform Game", sansRegular, 18);
		textPlatform2.setPosition(100, Utils.PlatformGameHeight-200);
		Text textPuzzle2 = new Text("4. Play Level 2 Puzzle Game", sansRegular, 18);
		textPuzzle2.setPosition(100, Utils.PlatformGameHeight-180);
		Text textPlatform3 = new Text("5. Play Level 3 Platform Game", sansRegular, 18);
		textPlatform3.setPosition(100, Utils.PlatformGameHeight-150);
		Text textPuzzle3 = new Text("6. Play Level 3 Puzzle Game", sansRegular, 18);
		textPuzzle3.setPosition(100, Utils.PlatformGameHeight-130);
		Text textPlatform4 = new Text("7. Play Level 4 Platform Game", sansRegular, 18);
		textPlatform4.setPosition(100, Utils.PlatformGameHeight-100);
		Text textPuzzle4 = new Text("8. Play Level 4 Puzzle Game", sansRegular, 18);
		textPuzzle4.setPosition(100, Utils.PlatformGameHeight-80);

		while (window.isOpen())
		{
			int gameLevel = level.getLevel();
			room.setImage(Utils.RoomImage[gameLevel]);
			window.clear(Color.BLACK);
			window.draw(room.getPlatform());
			window.draw(textIntro1);
			window.draw(textIntro2);
			window.draw(textIntro3);
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
			if (Keyboard.isKeyPressed(Keyboard.Key.NUM1) || Keyboard.isKeyPressed(Keyboard.Key.NUMPAD1))
			{
				PlatformGame platGame = new PlatformGame();
				if ((platGame.run(0) == true) && (level.getLevel() == 0))
				{
					level.incrementLevel();
					Puzzle0 puzzle = new Puzzle0();
					puzzle.run();
					puzzle = null;
				}
				platGame = null;
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.NUM2) || Keyboard.isKeyPressed(Keyboard.Key.NUMPAD2))
			{
				Puzzle0 puzzle = new Puzzle0();
				puzzle.run();
				puzzle = null;
			}
			else if ((Keyboard.isKeyPressed(Keyboard.Key.NUM3) || Keyboard.isKeyPressed(Keyboard.Key.NUMPAD3)) && level.getLevel() > 0)
			{
				PlatformGame platGame = new PlatformGame();
				if (platGame.run(1) && (level.getLevel() == 1))
				{
					level.incrementLevel();
					Puzzle1 puzzle = new Puzzle1();
					puzzle.run();
					puzzle = null;
				}
				platGame = null;
			}
			else if ((Keyboard.isKeyPressed(Keyboard.Key.NUM4) || Keyboard.isKeyPressed(Keyboard.Key.NUMPAD4)) && level.getLevel() > 0)
			{
				Puzzle1 puzzle = new Puzzle1();
				puzzle.run();
				puzzle = null;
			}
			else if ((Keyboard.isKeyPressed(Keyboard.Key.NUM5) || Keyboard.isKeyPressed(Keyboard.Key.NUMPAD5)) && level.getLevel() > 1)
			{
				PlatformGame platGame = new PlatformGame();
				if (platGame.run(2) && (level.getLevel() == 2))
				{
					level.incrementLevel();
					Puzzle2 puzzle = new Puzzle2();
					puzzle.run();
					puzzle = null;
				}
				platGame = null;
			}
			else if ((Keyboard.isKeyPressed(Keyboard.Key.NUM6) || Keyboard.isKeyPressed(Keyboard.Key.NUMPAD6)) && level.getLevel() > 1)
			{
				Puzzle2 puzzle = new Puzzle2();
				puzzle.run();
				puzzle = null;
			}
			else if ((Keyboard.isKeyPressed(Keyboard.Key.NUM7)|| Keyboard.isKeyPressed(Keyboard.Key.NUMPAD7)) && level.getLevel() > 2)
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
			else if ((Keyboard.isKeyPressed(Keyboard.Key.NUM8) || Keyboard.isKeyPressed(Keyboard.Key.NUMPAD8)) && level.getLevel() > 2)
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
