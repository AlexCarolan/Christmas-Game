/**
 * This class plays the platform game for the current level
 */
import java.lang.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.window.Keyboard.*;
import org.jsfml.graphics.*;

class Level 
{
	private static String Title   = "Level ";
	private static int gameLevel = 0;
	private static int lastNumCollected = 0;
	private static long lastTimeMoved = 0;

	/**
	 * run - handle display and movement of the platform game for this level
	 */
	public static void run () 
	{
		// create the window
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode(Utils.PlatformGameWidth, Utils.PlatformGameHeight),
					Title + (gameLevel+1),
					WindowStyle.DEFAULT);

		// limit the framerate
		window.setFramerateLimit(60);
		
		int inertiaX = 0;
		int inertiaY = 0;
		int decremJump = Utils.JumpAmount;

		// create all objects
		Player player = new Player();
		//CircleShape circle = new CircleShape(2);
		//circle.setFillColor(Color.CYAN);
		//circle.setPosition(Utils.PlatformGameWidth/2,player.getYBottomPosition());
		
		//Create and start animations
		Animation idle = new Animation(player, Utils.IdlePath, 4, 175);
		Animation running = new Animation(player, Utils.RunningPath, 12, 90);
		idle.start();
		idle.setActive(true);
		running.start();
		

		int numPlatforms = Utils.PlatformPositions[gameLevel].length;
		System.out.println("Number of platforms: " + numPlatforms);
		Platform[] platform = new Platform[numPlatforms];
		for (int i = 0; i < numPlatforms; i++)
			platform[i] = new Platform(Utils.PlatformPositions[gameLevel][i][0],Utils.PlatformPositions[gameLevel][i][1],
										Utils.PlatformPositions[gameLevel][i][2],Utils.PlatformPositions[gameLevel][i][3],
										Utils.PlatformImages[gameLevel][i]);

		int numObstacles = Utils.ObstaclePositions[gameLevel].length;
		System.out.println("Number of obstacles: " + numObstacles);
		Platform[] obstacle = new Platform[numObstacles];
		for (int i = 0; i < numObstacles; i++)
			obstacle[i] = new Platform(Utils.ObstaclePositions[gameLevel][i][0],Utils.ObstaclePositions[gameLevel][i][1],
										Utils.ObstaclePositions[gameLevel][i][2],Utils.ObstaclePositions[gameLevel][i][3],
										Utils.ObstacleImages[gameLevel][i]);


		int numCollectibles = Utils.CollectiblePositions[gameLevel].length;
		System.out.println("Number of collectibles: " + numCollectibles);
		Collectible[] collectible = new Collectible[numCollectibles];
		for (int i = 0; i < numCollectibles; i++)
			collectible[i] = new Collectible(Utils.CollectiblePositions[gameLevel][i][0],Utils.CollectiblePositions[gameLevel][i][1],
											Utils.CollectiblePositions[gameLevel][i][2],Utils.CollectiblePositions[gameLevel][i][3],
											Utils.CollectibleImages[gameLevel][i]);

		//CircleShape circCentre = new CircleShape(2);
		//circCentre.setFillColor(Color.YELLOW);
		//circCentre.setPosition(Utils.PlatformGameWidth/2,Utils.PlatformGameHeight/2);

		while (window.isOpen()) 
		{
			
			// fill the window with black
			window.clear(Color.BLACK);
			
			// add all objects onto the window
			for (int i = 0; i < numPlatforms; i++)
				window.draw(platform[i].getPlatform());
			for (int i = 0; i < numObstacles; i++)
				window.draw(obstacle[i].getPlatform());
			for (int i = 0; i < numCollectibles; i++)
				if (!collectible[i].collected())
					window.draw(collectible[i].getPlatform());
			window.draw(player.getSprite());
			//window.draw(circle);
			//window.draw(circCentre);
			
			//modify intertia per game tick
			if(inertiaX < 0)
			{
				inertiaX++;
			}
			if(inertiaX > 0)
			{
				inertiaX--;
			}
			decremJump = decremJump/2;
			if(inertiaY < 0)
			{
				if(decremJump == 0)
				{
					inertiaY = 0;
				}
				else
				{
					inertiaY = inertiaY + decremJump;
					System.out.println("inertiaY:" + inertiaY);
				}
			}
			
			
			
			if(inertiaX < 0)
			{	
				// check that player is not trying to run into an obstacle or the side of a platform
				boolean touching = false;
				for (int i = 0; i < numPlatforms; i++)
					if (player.touchingLeft(platform[i].getXPosition()-inertiaX/2,platform[i].getYPosition(),
							platform[i].getXSize()+inertiaX,platform[i].getYSize()))
					{
						touching = true;
						break;
					}
				if (!touching)
				{
					for (int i = 0; i < numObstacles; i++)
						if (player.touchingLeft(obstacle[i].getXPosition()-inertiaX/2,obstacle[i].getYPosition(),
								obstacle[i].getXSize()+inertiaX,obstacle[i].getYSize()))
						{
							touching = true;
							break;
						}
				}
			
				if (!touching)
				{
					// if the player wants to go left, move everything else right
					for (int i = 0; i < numPlatforms; i++)
						platform[i].move(inertiaX,0);
					for (int i = 0; i < numObstacles; i++)
						obstacle[i].move(inertiaX,0);
					for (int i = 0; i < numCollectibles; i++)
						collectible[i].move(inertiaX,0);
				}
			}
			
			else if(inertiaX > 0)
			{
				// check that player is not trying to run into an obstacle or the side of a platform
				boolean touching = false;
				for (int i = 0; i < numPlatforms; i++)
					if (player.touchingRight(platform[i].getXPosition()-inertiaX/2,platform[i].getYPosition(),
							platform[i].getXSize()+inertiaX,platform[i].getYSize()))
					{
						touching = true;
						break;
					}
				if (!touching)
				{
					for (int i = 0; i < numObstacles; i++)
						if (player.touchingRight(obstacle[i].getXPosition()-inertiaX/2,obstacle[i].getYPosition(),
								obstacle[i].getXSize()+inertiaX,obstacle[i].getYSize()))
						{
							touching = true;
							break;
						}
				}
				if (!touching)
				{
					// if the player wants to go right, move everything else left
					for (int i = 0; i < numPlatforms; i++)
						platform[i].move(inertiaX,0);
					for (int i = 0; i < numObstacles; i++)
						obstacle[i].move(inertiaX,0);
					for (int i = 0; i < numCollectibles; i++)
						collectible[i].move(inertiaX,0);
				}
			}

			player.move(0,inertiaY);
			System.out.println("Y move");			
			
			// apply idle anumation when still
			if((System.currentTimeMillis() - lastTimeMoved) > 50)
			{
				idle.setActive(true);
				running.setActive(false);
			}
			
			if(Keyboard.isKeyPressed(Keyboard.Key.A) || Keyboard.isKeyPressed(Keyboard.Key.LEFT))
			{
				idle.setActive(false);
				running.setActive(true);
				lastTimeMoved = System.currentTimeMillis();
				inertiaX = Utils.MoveAmountX;
			}
			if(Keyboard.isKeyPressed(Keyboard.Key.D) || Keyboard.isKeyPressed(Keyboard.Key.RIGHT))
			{
				idle.setActive(false);
				running.setActive(true);
				lastTimeMoved = System.currentTimeMillis();
				inertiaX = 0-Utils.MoveAmountX;
			}
			if(Keyboard.isKeyPressed(Keyboard.Key.W) || Keyboard.isKeyPressed(Keyboard.Key.UP))
			{
				decremJump = Utils.JumpAmount;
				inertiaY = 0-Utils.JumpAmount;
			}
			if(Keyboard.isKeyPressed(Keyboard.Key.S) || Keyboard.isKeyPressed(Keyboard.Key.DOWN))
			{
				// TODO Test that we can move the player down
				player.move(0,Utils.MoveAmountY);
			}
			
			/*
			// handle keyboard/mouse events (movement can be via WASD or arrow keys)
			for (Event event : window.pollEvents()) 
			{
				MouseEvent mouseEvent;
				switch(event.type) 
				{
					case CLOSED:
						window.close();
						idle.stop();
						running.stop();
						break;
					case KEY_PRESSED:
						KeyEvent keyEvent = event.asKeyEvent();
						if ((keyEvent.key == Keyboard.Key.LEFT) || (keyEvent.key == Keyboard.Key.A))
						{
							// Set player animation as running 
							idle.setActive(false);
							running.setActive(true);
							lastTimeMoved = System.currentTimeMillis();
							inertiaX = Utils.MoveAmountX;
						}
						if ((keyEvent.key == Keyboard.Key.RIGHT) || (keyEvent.key == Keyboard.Key.D))
						{
							// Set player animation as running 
							idle.setActive(false);
							running.setActive(true);
							lastTimeMoved = System.currentTimeMillis();
							inertiaX = 0-Utils.MoveAmountX;
						}
						if ((keyEvent.key == Keyboard.Key.DOWN) || (keyEvent.key == Keyboard.Key.S))
						{
							// TODO Test that we can move the player down
							player.move(0,Utils.MoveAmountY);
						}
						if ((keyEvent.key == Keyboard.Key.UP) || (keyEvent.key == Keyboard.Key.W))
						{
							decremJump = Utils.JumpAmount;
							inertiaY = 0-Utils.JumpAmount;
						}
					break;
				}
			}*/
			
			// if bottom of player sprite within a platform, then stand on platform
			// (include the distance between vertical moves when doing the check)
			boolean standing = false;
			for (int i = 0; i < numPlatforms; i++)
			{
				if (player.standingOn(platform[i].getXPosition(),platform[i].getYPosition()-Utils.MoveAmountY/2,
										platform[i].getXSize(),platform[i].getYSize()+Utils.MoveAmountY))
				{
					//System.out.println("Player is standing on platform " + i);
					standing = true;
					break;
				}
			}
			for (int i = 0; i < numObstacles; i++)
			{
				if (player.standingOn(obstacle[i].getXPosition(),obstacle[i].getYPosition()-Utils.MoveAmountY/2,
										obstacle[i].getXSize(),obstacle[i].getYSize()+Utils.MoveAmountY))
				{
					//System.out.println("Player is standing on obstacle " + i);
					standing = true;
					break;
				}
			}

			// if touching a collectible, then pick it up
			for (int i = 0; i < numCollectibles; i++)
			{
				if (!collectible[i].collected())
					if (player.touching(collectible[i].getXPosition()-Utils.MoveAmountX/2,collectible[i].getYPosition()-Utils.MoveAmountY/2,
										collectible[i].getXSize()+Utils.MoveAmountX,collectible[i].getYSize()+Utils.MoveAmountY))
						collectible[i].collect();
			}

			// handle gravity - if the player is not standing on a platform, they're falling
			if (!standing)
			{
				player.move(0,Utils.Gravity);
				System.out.println("gravity move");
				// if player has fallen off the bottom of the window, put all the items back to the start
				// except collectibles - only redraw those that are still waiting to be collected
				if (player.fallenBelowWindow(Utils.PlatformGameHeight))
				{
					player.resetPosition();
					for (int i = 0; i < numPlatforms; i++)
						platform[i].resetPosition(Utils.PlatformPositions[gameLevel][i][2],Utils.PlatformPositions[gameLevel][i][3]);
					for (int i = 0; i < numObstacles; i++)
						obstacle[i].resetPosition(Utils.ObstaclePositions[gameLevel][i][2],Utils.ObstaclePositions[gameLevel][i][3]);
					for (int i = 0; i < numCollectibles; i++)
						collectible[i].resetPosition(Utils.CollectiblePositions[gameLevel][i][2],Utils.CollectiblePositions[gameLevel][i][3]);
				}
			}

			// display what was drawn on the window
			window.display();

			// if any items were collected this time, then list all the items collected
			int itemsCollected = 0;
			for (int i = 0; i < numCollectibles; i++)
				if (collectible[i].collected())
					itemsCollected++;
			if (itemsCollected > lastNumCollected)
			{
				for (int i = 0; i < numCollectibles; i++)
					if (collectible[i].collected())
						System.out.println("Collected: " + Utils.CollectibleImages[gameLevel][i]);
				System.out.println();
				lastNumCollected = itemsCollected;
			}
		}
	}
}