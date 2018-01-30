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

	public static void run() 
	{
		// create the game level
		Level level = new Level();

		// create a window
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode(Utils.PlatformGameWidth, Utils.PlatformGameHeight),
						"Christmas Game Menu",
						WindowStyle.DEFAULT);

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
		Text text1 = new Text("1. Play Platform Game", sansRegular, 18);
		text1.setPosition(100, Utils.PlatformGameHeight-280);
		Text text2 = new Text("2. Play Puzzle Game", sansRegular, 18);
		text2.setPosition(100, Utils.PlatformGameHeight-260);

		while (window.isOpen() && level.getLevel() < Utils.MaxLevel)
		{
			room.setImage(Utils.RoomImage[level.getLevel()]);
			window.clear(Color.BLACK);
			window.draw(room.getPlatform());
			window.draw(text1);
			window.draw(text2);

			// display what was drawn on the window
			window.display();

			// handle keyboard events (movement can be via WASD or arrow keys)
			// if we're playing a platform game, play it at the current level
			// if we're playing a puzzle, play it at the current level
			if (Keyboard.isKeyPressed(Keyboard.Key.NUM1))
			{
				PlatformGame platGame = new PlatformGame();
				platGame.run(level.getLevel());
				platGame = null;
				level.incrementLevel();
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.NUM2))
			{
				Puzzle1 puzzle = new Puzzle1();
				puzzle.run();
				puzzle = null;
			}

			// handle mouse events
			for (Event event : window.pollEvents()) 
			{
				MouseEvent mouseEvent;
				switch(event.type) 
				{
					case CLOSED:
						window.close();
						level = null;
						room = null;
						break;
				}
			}
		}
	}

	public static void main (String args[ ]) 
	{
		Game game = new Game();
		game.run();
		game = null;
	}
}
