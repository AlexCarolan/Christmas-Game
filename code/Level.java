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
	private static String Title = "Level ";
	private static int gameLevel = 0;
	private static int lastNumCollected = 0;
	private static long lastTimeMoved = 0;
	private static boolean runDirectionRight = true;

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

		// set the frame-rate
		window.setFramerateLimit(60);
		
		int moveX = 0;
		int moveY = 0;
		int inertiaX = 1;
		int inertiaY = Utils.JumpAmount;

		// create all objects - player, platforms, obstacles, collectibles
		Player player = new Player();
		
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

		//Create and start animations
		Animation idleRight = new Animation(player, Utils.IdleRightPath, 4, 175);
		Animation idleLeft = new Animation(player, Utils.IdleLeftPath, 4, 175);
		Animation runningLeft = new Animation(player, Utils.RunningLeftPath, 12, 90);
		Animation runningRight = new Animation(player, Utils.RunningRightPath, 12, 90);
		idleRight.start();
		idleLeft.start();
		runningLeft.start();
		runningRight.start();
		idleRight.setActive(true);
		

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

			// modify inertial movement per game tick (so that player movement gradually slows down, rather than being jerky)
			// apply inertia to left and right movement, and to movement upwards
			// movement downwards is gravity (or an extra boost from the keyboard)
			if (moveX < 0)
				moveX += inertiaX;
			else if (moveX > 0)
				moveX -= inertiaX;
			if (moveY < 0)
			{
				inertiaY = inertiaY/2;
				if (inertiaY == 0)
					moveY = 0;
				else
					moveY += inertiaY;
			}

			// apply idle animation when still
			if (runningRight.getActive() || runningLeft.getActive() &&
				((System.currentTimeMillis() - lastTimeMoved) > 100))
			{
				runningRight.setActive(false);
				runningLeft.setActive(false);
				if(runDirectionRight == true)
				{
					idleRight.setActive(true);
				}else{
					idleLeft.setActive(true);
				}
			}

			// handle keyboard events (movement can be via WASD or arrow keys)
			if (Keyboard.isKeyPressed(Keyboard.Key.A) || Keyboard.isKeyPressed(Keyboard.Key.LEFT))
			{
				idleRight.setActive(false);
				idleLeft.setActive(false);
				runningLeft.setActive(true);
				lastTimeMoved = System.currentTimeMillis();
				runDirectionRight = false;
				moveX = 0-Utils.MoveAmountX;
			}
			if (Keyboard.isKeyPressed(Keyboard.Key.D) || Keyboard.isKeyPressed(Keyboard.Key.RIGHT))
			{
				idleRight.setActive(false);
				idleLeft.setActive(false);
				runningRight.setActive(true);
				lastTimeMoved = System.currentTimeMillis();
				runDirectionRight = true;
				moveX = Utils.MoveAmountX;
			}
			if (Keyboard.isKeyPressed(Keyboard.Key.W) || Keyboard.isKeyPressed(Keyboard.Key.UP))
			{
				inertiaY = Utils.JumpAmount;
				moveY = 0-Utils.JumpAmount;
			}
			if (Keyboard.isKeyPressed(Keyboard.Key.S) || Keyboard.isKeyPressed(Keyboard.Key.DOWN))
			{
				inertiaY = 0;
				moveY = Utils.MoveAmountY;
			}

			// handle mouse events
			for (Event event : window.pollEvents()) 
			{
				MouseEvent mouseEvent;
				switch(event.type) 
				{
					case CLOSED:
						window.close();
						idleRight.kill();
						idleLeft.kill();
						runningLeft.kill();
						runningRight.kill();
						break;
				}
			}
			

			boolean touching = false;
			if (moveX < 0)	// player moving left
			{	
				// check that player is not trying to run into an obstacle or the side of a platform
				for (int i = 0; i < numPlatforms; i++)
					if (player.touchingLeft(platform[i].getXPosition()+moveX/2,platform[i].getYPosition(),
							platform[i].getXSize()-moveX,platform[i].getYSize()))
					{
						//System.out.println("touchingLeft platform " + i);
						touching = true;
						break;
					}
				if (!touching)
				{
					for (int i = 0; i < numObstacles; i++)
						if (player.touchingLeft(obstacle[i].getXPosition()+moveX/2,obstacle[i].getYPosition(),
								obstacle[i].getXSize()-moveX,obstacle[i].getYSize()))
						{
							//System.out.println("touchingLeft obstacle " + i);
							touching = true;
							break;
						}
				}
			}
			else if (moveX > 0)	// player moving right
			{
				// check that player is not trying to run into an obstacle or the side of a platform
				for (int i = 0; i < numPlatforms; i++)
					if (player.touchingRight(platform[i].getXPosition()-moveX/2,platform[i].getYPosition(),
							platform[i].getXSize()+moveX,platform[i].getYSize()))
					{
						//System.out.println("touchingRight platform " + i);
						touching = true;
						break;
					}
				if (!touching)
				{
					for (int i = 0; i < numObstacles; i++)
						if (player.touchingRight(obstacle[i].getXPosition()-moveX/2,obstacle[i].getYPosition(),
								obstacle[i].getXSize()+moveX,obstacle[i].getYSize()))
						{
							//System.out.println("touchingRight obstacle " + i);
							touching = true;
							break;
						}
				}
			}
			// if the player wants to move horizontally and there's nothing in the way, move everything else in the opposite direction
			if ((moveX != 0) && !touching)
			{
				for (int i = 0; i < numPlatforms; i++)
					platform[i].move(0-moveX,0);
				for (int i = 0; i < numObstacles; i++)
					obstacle[i].move(0-moveX,0);
				for (int i = 0; i < numCollectibles; i++)
					collectible[i].move(0-moveX,0);
			}

			// do vertical movement
			// player can jump past platforms/obstacles, so don't need to check if touching
			// if player ends up touching the platform/obstacle, then they'll be moved to stand on the item
			player.move(0,moveY);

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
					// make player stand in same vertical position above platform
					player.standOn(platform[i].getYPosition()-Utils.MoveAmountY/2);
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
					// make player stand in same vertical position above obstacle
					player.standOn(obstacle[i].getYPosition()-Utils.MoveAmountY/2);
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
				//System.out.println("gravity move");
			}

			// if player has fallen off the bottom of the window, put all the items back to the start
			if (player.fallenBelowWindow(Utils.PlatformGameHeight))
			{
				player.resetPosition();
				for (int i = 0; i < numPlatforms; i++)
					platform[i].resetPosition(Utils.PlatformPositions[gameLevel][i][2],Utils.PlatformPositions[gameLevel][i][3]);
				for (int i = 0; i < numObstacles; i++)
					obstacle[i].resetPosition(Utils.ObstaclePositions[gameLevel][i][2],Utils.ObstaclePositions[gameLevel][i][3]);
				for (int i = 0; i < numCollectibles; i++)
					collectible[i].resetPosition(Utils.CollectiblePositions[gameLevel][i][2],Utils.CollectiblePositions[gameLevel][i][3]);
				moveX = 0;
				moveY = 0;
				inertiaY = 0;
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
