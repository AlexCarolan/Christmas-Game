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

class Test 
{
	private static int screenWidth  = 1024;
	private static int screenHeight = 768;
	private static String Title   = "Val's Test";

	public static void run () 
	{
		// create the window
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode(screenWidth, screenHeight),
					Title,
					WindowStyle.DEFAULT);

		// limit the framerate
		window.setFramerateLimit(24);

		// create a rectangle shape
		RectangleShape rect = new RectangleShape(new Vector2f(50, 30));
		rect.setFillColor(Color.GREEN);
		rect.setPosition(20,40);

		// create a cyan circle shape
		//CircleShape circle = new CircleShape(150);
		//circle.setFillColor(Color.CYAN);
		//circle.setPosition(400,400);

		// create a regular hexagon - by making a circle approximated using only 6 points - with a thick blue outline
		//CircleShape hexagon = new CircleShape(65, 6);
		//hexagon.setOutlineColor(Color.BLUE);
		//hexagon.setOutlineThickness(4);
		//hexagon.setPosition(500,500);

		// create a rectangular triangle with a thick blue outline
		//ConvexShape triangle = new ConvexShape(new Vector2f(0, 0),
		//										new Vector2f(200, 100),
		//										new Vector2f(0, 100));
		//triangle.setFillColor(Color.BLACK);
		//triangle.setPosition(300,200);

		// create a Texture instance
		Texture texture = new Texture();
		try {
			// try to load the texture from file
			texture.loadFromFile(Paths.get("grass.jpg"));

			// texture was loaded successfully - retrieve and print size
			Vector2i size = texture.getSize();
			System.out.println("The texture is " + size.x + "x" + size.y);
		} catch(IOException ex) {
			//ex.printStackTrace();
			System.out.println("Unable to open texture file");
		}

		Sprite sprite = new Sprite(texture);

		//Set its origin to its centre and put it on the screen
		sprite.setOrigin(Vector2f.div(new Vector2f(texture.getSize()), 2));
		sprite.setPosition(320, 240);


		try 
		{
			// load the image.
			Image image = new Image();
			image.loadFromFile(Paths.get("sprite.png"));

			// load the image into the texture
			texture.loadFromImage(image);
		} catch(IOException|TextureCreationException ex) 
		{
			System.err.println("Something went wrong loading image ");
			//ex.printStackTrace();
		}


		while (window.isOpen()) 
		{
			// fill the window with red
			window.clear(Color.BLACK);

			rect.move(1,0);
			//triangle.rotate(-10);
			//circle.scale(1, 0.99f);

			window.draw(rect);
			//window.draw(circle);
			//window.draw(hexagon);
			//window.draw(triangle);
			window.draw(sprite);

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
						if (keyEvent.key == Keyboard.Key.LEFT)
							sprite.move(-2,0);
						else if (keyEvent.key == Keyboard.Key.RIGHT)
							sprite.move(2,0);
						else if (keyEvent.key == Keyboard.Key.UP)
							sprite.move(0,-2);
						else if (keyEvent.key == Keyboard.Key.DOWN)
							sprite.move(0,2);
						break;
					case TEXT_ENTERED:
						TextEvent textEvent = event.asTextEvent();
						//char keyPressed = textEvent.getChar();
						//if (keyPressed == 'A')
						//	sprite.move(-4,0);
						//else if (keyPressed == 'D')
						//	sprite.move(4,0);
						//else if (keyPressed == 'W')
						//	sprite.move(0,-4);
						//else if (keyPressed == 'S')
						//	sprite.move(0,4);
						break;
					case MOUSE_BUTTON_PRESSED:
						mouseEvent = event.asMouseButtonEvent();
						System.out.println("The user clicked a mouse button: " + mouseEvent.type);
						if (mouseEvent.equals(Mouse.Button.LEFT))
						{
							sprite.move(-2,0);
							//System.out.println("The user clicked the Left mouse button");
						}
						else if (mouseEvent.equals(Mouse.Button.RIGHT))
						{
							sprite.move(2,0);
							//System.out.println("The user clicked the Right mouse button");
						}
						break;
					case MOUSE_BUTTON_RELEASED:
						mouseEvent = event.asMouseButtonEvent();
						System.out.println("The user released a mouse button: " + mouseEvent.type);
						break;
					//case MOUSE_WHEEL_MOVED:
					//	System.out.println("The user moved the mouse wheel!");
					//	break;
				}
			}

			// display what was drawn (... the red color!)
			window.display();
		}
	}

	public static void main (String args[ ]) 
	{
		Test t = new Test( );
		t.run( );
	}
}	
