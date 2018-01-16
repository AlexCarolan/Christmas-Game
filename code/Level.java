//
//import java.util.function.*;
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
	private static int screenWidth  = 1024;
	private static int screenHeight = 768;
	private static String Title   = "Level";

	public static void run () 
	{
		// create the window
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode(screenWidth, screenHeight),
					Title,
					WindowStyle.DEFAULT);

		// limit the framerate
		window.setFramerateLimit(24);


		// create all objects
		Player player = new Player();
		Platform[] platform = new Platform[2];
		CircleShape circle = new CircleShape(2);
		circle.setFillColor(Color.CYAN);
		circle.setPosition(512,player.getYBottomPosition());

		platform[0] = new Platform(1024,20,0,740);
		platform[1] = new Platform(200,5,300,200);


		while (window.isOpen()) 
		{
			// fill the window with red
			window.clear(Color.BLACK);


			// for all objects
			for (int i = 0; i < platform.length; i++)
				window.draw(platform[i].getPlatform());
			window.draw(player.getSprite());
			window.draw(circle);

			// handle events
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
							for (int i = 0; i < platform.length; i++)
								platform[i].move(2,0);
							//sprite.move(-2,0);
						}
						else if ((keyEvent.key == Keyboard.Key.RIGHT) || (keyEvent.key == Keyboard.Key.D))
						{
							for (int i = 0; i < platform.length; i++)
								platform[i].move(-2,0);
							//sprite.move(2,0);
						}
						else if ((keyEvent.key == Keyboard.Key.UP) || (keyEvent.key == Keyboard.Key.W))
							player.move(0,-2);
						else if ((keyEvent.key == Keyboard.Key.DOWN) || (keyEvent.key == Keyboard.Key.S))
							player.move(0,2);
						break;
				}
			}

			// display what was drawn (... the red color!)
			window.display();
		}
	}

	public static void main (String args[ ]) 
	{
		Level l = new Level( );
		l.run( );
	}
}	
