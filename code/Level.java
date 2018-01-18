import java.lang.*;
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.window.Keyboard.*;
import org.jsfml.graphics.*;

class Level 
{
	private static String Title   = "Level";

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

		// create all objects
		Player player = new Player();
		Platform[] platform = new Platform[2];
		CircleShape circle = new CircleShape(2);
		circle.setFillColor(Color.CYAN);
		circle.setPosition(Utils.PlatformGameWidth/2,player.getYBottomPosition());

		platform[0] = new Platform(Utils.PlatformPositions[0][0],Utils.PlatformPositions[0][1],Utils.PlatformPositions[0][2],Utils.PlatformPositions[0][3]);
		platform[1] = new Platform(Utils.PlatformPositions[1][0],Utils.PlatformPositions[1][1],Utils.PlatformPositions[1][2],Utils.PlatformPositions[1][3]);

		CircleShape circCentre = new CircleShape(2);
		circCentre.setFillColor(Color.YELLOW);
		circCentre.setPosition(Utils.PlatformGameWidth/2,Utils.PlatformGameHeight/2);

		while (window.isOpen()) 
		{
			// fill the window with black
			window.clear(Color.BLACK);

			// add all objects onto the window
			for (int i = 0; i < platform.length; i++)
				window.draw(platform[i].getPlatform());
			window.draw(player.getSprite());
			window.draw(circle);
			window.draw(circCentre);

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
							// if the player wants to go left, move everything else right
							for (int i = 0; i < platform.length; i++)
								platform[i].move(2,0);
						}
						else if ((keyEvent.key == Keyboard.Key.RIGHT) || (keyEvent.key == Keyboard.Key.D))
						{
							// if the player wants to go right, move everything else left
							for (int i = 0; i < platform.length; i++)
								platform[i].move(-2,0);
						}
						else if ((keyEvent.key == Keyboard.Key.UP) || (keyEvent.key == Keyboard.Key.W))
						{
							// Test that we can move the player up
							player.move(0,-2);
						}
						else if ((keyEvent.key == Keyboard.Key.DOWN) || (keyEvent.key == Keyboard.Key.S))
						{
							// Test that we can move the player down
							player.move(0,2);
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
		Level l = new Level( );
		l.run( );
	}
}	
