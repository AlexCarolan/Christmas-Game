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
		// TODO: display the ChristmasRoom
		// FOR NOW, display a menu

		// create a menu window
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode(Utils.PlatformGameWidth, Utils.PlatformGameHeight),
						"Christmas Game Menu",
						WindowStyle.DEFAULT);
		//
		// Load the font
		//
		Font sansRegular = new Font( );
		try {
			sansRegular.loadFromFile(
					Paths.get("fonts\\LucidaSansRegular.ttf"));
		} catch (IOException ex) {
			ex.printStackTrace( );
		}
		// create the menu text
		Text text1 = new Text("1. Play Platform Game", sansRegular, 18);
		text1.setPosition(100, 100);
		Text text2 = new Text("2. Play Puzzle Game", sansRegular, 18);
		text2.setPosition(100, 200);

		while (window.isOpen())
		{
			window.clear();
			window.draw(text1);
			window.draw(text2);
			// display what was drawn on the window
			window.display();

			// handle keyboard events (movement can be via WASD or arrow keys)
			// if we're playing a platform game, play it at the current level
			// if we're playing a puzzle, play it at the current level
			if (Keyboard.isKeyPressed(Keyboard.Key.NUM1))
			{
				Level level = new Level();
				level.run();
				level = null;
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.NUM2))
			{
				Puzzle1 puzzle = new Puzzle1();
				puzzle.run();
				puzzle = null;;
			}

			// handle mouse events
			for (Event event : window.pollEvents()) 
			{
				MouseEvent mouseEvent;
				switch(event.type) 
				{
					case CLOSED:
						window.close();
						break;
				}
			}
		}
	}
}
