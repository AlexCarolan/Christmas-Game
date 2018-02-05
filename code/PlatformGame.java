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

class PlatformGame
{
	private String Title = "Level ";
	private int lastNumCollected = 0;
	private long lastTimeMoved = 0;
	private boolean runDirectionRight = true;

	/**
	 * run - handle display and movement of the platform game for this level
	 * @param gameLevel - the current game level
	 * @return true is platform level completed successfully
	 *         if window closed without finishing, returns false
	 */
	public boolean run(int gameLevel)
	{
		// create the window
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode(Utils.PlatformGameWidth, Utils.PlatformGameHeight),
					Title + (gameLevel+1),
					WindowStyle.CLOSE | WindowStyle.TITLEBAR);	// window can't be resized

		// set the frame-rate
		window.setFramerateLimit(60);
		
		int moveX = 0;
		int moveY = 0;
		int inertiaX = 1;
		int inertiaY = Utils.JumpAmount;
		double gravity = Utils.MinGravity;
		
		// create new score object
		Score playerScore = new Score(0);

		// create all objects - player, platforms, obstacles, collectibles
		Player player = new Player(gameLevel);
		
		int numPlatforms = Utils.PlatformPositions[gameLevel].length;
		//System.out.println("Number of platforms: " + numPlatforms);
		Platform[] platform = new Platform[numPlatforms];
		for (int i = 0; i < numPlatforms; i++)
			platform[i] = new Platform(Utils.PlatformPositions[gameLevel][i][0],Utils.PlatformPositions[gameLevel][i][1],
										Utils.PlatformPositions[gameLevel][i][2],Utils.PlatformPositions[gameLevel][i][3],
										Utils.PlatformImages[gameLevel][i],Utils.PlatformCeilings[gameLevel][i]);

		int numObstacles = Utils.ObstaclePositions[gameLevel].length;
		//System.out.println("Number of obstacles: " + numObstacles);
		Platform[] obstacle = new Platform[numObstacles];
		for (int i = 0; i < numObstacles; i++)
			obstacle[i] = new Platform(Utils.ObstaclePositions[gameLevel][i][0],Utils.ObstaclePositions[gameLevel][i][1],
										Utils.ObstaclePositions[gameLevel][i][2],Utils.ObstaclePositions[gameLevel][i][3],
										Utils.ObstacleImages[gameLevel][i],true);


		int numCollectibles = Utils.CollectiblePositions[gameLevel].length;
		//System.out.println("Number of collectibles: " + numCollectibles);
		Collectible[] collectible = new Collectible[numCollectibles];
		for (int i = 0; i < numCollectibles; i++)
			collectible[i] = new Collectible(Utils.CollectiblePositions[gameLevel][i][0],Utils.CollectiblePositions[gameLevel][i][1],
											Utils.CollectiblePositions[gameLevel][i][2],Utils.CollectiblePositions[gameLevel][i][3],
											Utils.CollectibleImages[gameLevel][i],Utils.CollectibleKeys[gameLevel][i],playerScore);

		int numKeysToCollect = Utils.numKeys[gameLevel];
		int numKeysCollected = 0;

		Platform door = new Platform(Utils.DoorPosition[gameLevel][0],Utils.DoorPosition[gameLevel][1],
										Utils.DoorPosition[gameLevel][2],Utils.DoorPosition[gameLevel][3],
										Utils.ShutDoorImage,true);

		// create and start animations
		AnimatedPlayer idleRight = new AnimatedPlayer(player, Utils.IdleRightPath, 4, 175);
		AnimatedPlayer idleLeft = new AnimatedPlayer(player, Utils.IdleLeftPath, 4, 175);
		AnimatedPlayer runningLeft = new AnimatedPlayer(player, Utils.RunningLeftPath, 12, 90);
		AnimatedPlayer runningRight = new AnimatedPlayer(player, Utils.RunningRightPath, 12, 90);
		//AnimatedPlayer sleighRight = new AnimatedPlayer(player, Utils.SleighRightPath, 2, 100);
		AnimatedCollectible bauble = new AnimatedCollectible(collectible[0], Utils.Bauble1Path, 2, 250);
		AnimatedCollectible axe = new AnimatedCollectible(collectible[1], Utils.AxePath, 10, 100);
		bauble.start();
		axe.start();
		idleRight.start();
		idleLeft.start();
		runningLeft.start();
		runningRight.start();
		//sleighRight.start();
		//if (gameLevel != Utils.SleighGameLevel)
			idleRight.setActive(true);
		//else
			//sleighRight.setActive(true);
		bauble.setActive(true);
		axe.setActive(true);
		
		// load the font
		Font scoreFont = new Font( );
		try {
			scoreFont.loadFromFile(
					Paths.get("fonts\\joystix monospace.ttf"));
		} catch (IOException ex) {
			ex.printStackTrace( );
		}
		
		// add the score tracker
		Text scoreText = new Text("Score:0", scoreFont, 18);
		scoreText.setPosition(10, Utils.PlatformGameHeight-(Utils.PlatformGameHeight-10));

		//System.out.println("At start: moveY=" + moveY + ", inertiaY=" + inertiaY + ", gravity=" + gravity);
		boolean finished = false;
		while (window.isOpen() && !finished)
		{
			// fill the window with black
			window.clear(Color.BLACK);
			
			// add the players score to the window
			scoreText.setString("Score:" + playerScore.getScore());
			window.draw(scoreText);

			// add all objects onto the window
			for (int i = 0; i < numPlatforms; i++)
				window.draw(platform[i].getPlatform());
			for (int i = 0; i < numObstacles; i++)
				window.draw(obstacle[i].getPlatform());
			for (int i = 0; i < numCollectibles; i++)
				if (!collectible[i].collected())
					window.draw(collectible[i].getPlatform());
			window.draw(door.getPlatform());
			window.draw(player.getSprite());

			// modify inertial movement per game tick (so that player movement gradually slows down, rather than being jerky)
			// apply inertia to left and right movement, and to movement upwards
			// movement downwards is gravity (or an extra boost from the keyboard)
			if (moveX < 0)
				moveX += inertiaX;
			else if (moveX > 0)
				moveX -= inertiaX;
			if (moveY < 0)					// if moving upwards
			{
				inertiaY = inertiaY/2;		// then gradually slow down
				if (inertiaY == 0)
					moveY = 0;
				else if (moveY < 0)
					moveY -= inertiaY;
				else
					moveY += inertiaY;
			}
			else							// otherwise gradually speed up moving down
			{
				if (gravity < Utils.MaxGravity)
				{
					gravity = gravity * Utils.GravityMultiplier[gameLevel];
					if (gravity > Utils.MaxGravity)
						gravity = Utils.MaxGravity;
					//if (gravity > 0)
					//	System.out.println("gravity set to " + gravity);
				}
			}
			//System.out.println("moveY=" + moveY + ", inertiaY=" + inertiaY + ", gravity=" + gravity);

			// apply idle animation when still
			if (runningRight.getActive() || runningLeft.getActive() &&
				((System.currentTimeMillis() - lastTimeMoved) > 10))
			{
				//if (gameLevel != Utils.SleighGameLevel)
				{
					runningRight.setActive(false);
					runningLeft.setActive(false);
					if (runDirectionRight == true)
						idleRight.setActive(true);
					else
						idleLeft.setActive(true);
				}
			}

			// handle keyboard events (movement can be via WASD or arrow keys)
			if (Keyboard.isKeyPressed(Keyboard.Key.A) || Keyboard.isKeyPressed(Keyboard.Key.LEFT))	// going left
			{
				//if (gameLevel != Utils.SleighGameLevel)
				{
					// turn off animation for runningRight and idleRight/Left
					idleRight.setActive(false);
					idleLeft.setActive(false);
					runningRight.setActive(false);
					// turn on animation for runningLeft
					runningLeft.setActive(true);
				}
				//else
					//sleighRight.setActive(true);
				lastTimeMoved = System.currentTimeMillis();
				runDirectionRight = false;
				moveX = 0-Utils.MoveAmountX;
			}
			if (Keyboard.isKeyPressed(Keyboard.Key.D) || Keyboard.isKeyPressed(Keyboard.Key.RIGHT))	// going right
			{
				//if (gameLevel != Utils.SleighGameLevel)
				{
					// turn off animation for runningLeft and idleRight/Left
					idleRight.setActive(false);
					idleLeft.setActive(false);
					runningLeft.setActive(false);
					// turn on animation for runningRight
					runningRight.setActive(true);
				}
				//else
					//sleighRight.setActive(true);
				lastTimeMoved = System.currentTimeMillis();
				runDirectionRight = true;
				moveX = Utils.MoveAmountX;
			}
			if (Keyboard.isKeyPressed(Keyboard.Key.W) || Keyboard.isKeyPressed(Keyboard.Key.UP) || Keyboard.isKeyPressed(Keyboard.Key.SPACE))
			{
				// can only jump if bottom of player sprite standing on a platform or obstacle
				boolean standing = false;
				for (int i = 0; i < numPlatforms; i++)
				{
					if (player.standingOn(platform[i].getXPosition(),platform[i].getYPosition()-Utils.MoveAmountY/2,
											platform[i].getXSize(),platform[i].getYSize()+Utils.MoveAmountY/2))
					{
						//System.out.println("Player is standing on platform " + i);
						standing = true;
						break;
					}
				}
				if (!standing)
				{
					for (int i = 0; i < numObstacles; i++)
					{
						if (player.standingOn(obstacle[i].getXPosition(),obstacle[i].getYPosition()-Utils.MoveAmountY/2,
												obstacle[i].getXSize(),obstacle[i].getYSize()+Utils.MoveAmountY/2))
						{
							//System.out.println("Player is standing on obstacle " + i);
							standing = true;
							break;
						}
					}
				}
				//if (Utils.MinGravity * Utils.GravityMultiplier[gameLevel] == gravity) //??WHAT IS THIS LINE?
				if (standing)
				{
					inertiaY = 0-Utils.JumpAmount/2;
					moveY = 0-Utils.JumpAmount;
					//System.out.println("Standing on something, so moveY="+moveY);
				}
				//System.out.println("Up pressed: moveY=" + moveY + ", inertiaY=" + inertiaY + ", gravity=" + gravity);
			}
			if (Keyboard.isKeyPressed(Keyboard.Key.S) || Keyboard.isKeyPressed(Keyboard.Key.DOWN))
			{
				inertiaY = 0;
				moveY = Utils.MoveAmountY;
				//System.out.println("Down pressed: moveY=" + moveY + ", inertiaY=" + inertiaY + ", gravity=" + gravity);
			}

			// handle mouse events
			for (Event event : window.pollEvents()) 
			{
				switch(event.type) 
				{
					case CLOSED:
						idleRight.kill();
						idleLeft.kill();
						runningLeft.kill();
						runningRight.kill();
						//sleighRight.kill();
						bauble.kill();
						axe.kill();
						window.close();
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
				door.move(0-moveX,0);
			}

			// do vertical movement
			// player cannot jump up through some platforms
			touching = false;	// don't care if touching left/right; check touchingAbove
			if (moveY < 0)	// player moving up
			{	
				// check that player is not trying to jump up into a ceiling
				for (int i = 0; i < numPlatforms; i++)
					if (player.touchingAbove(platform[i].getXPosition(),platform[i].getYPosition()+moveY/2,
							platform[i].getXSize(),platform[i].getYSize()-moveY))
					{
						//System.out.println("touchingAbove platform " + i);
						// if this is a ceiling, we're not allowed to jump through it
						if (platform[i].isCeiling())
						{
							touching = true;
							break;
						}
					}
			}

			if ((moveY != 0) && !touching)
			{
				//System.out.println("move player by " + moveY);
				player.move(0,moveY);
			}

			// if bottom of player sprite within a platform, then stand on platform
			// (include the distance between vertical moves when doing the check)
			boolean standing = false;
			for (int i = 0; i < numPlatforms; i++)
			{
				if (player.standingOn(platform[i].getXPosition(),platform[i].getYPosition()-Utils.MoveAmountY/2,
										platform[i].getXSize(),platform[i].getYSize()+Utils.MoveAmountY))
				{
					//System.out.println("Player is NOW standing on platform " + i);
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
					//System.out.println("Player is NOW standing on obstacle " + i);
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
					if (player.touching(collectible[i].getXPosition(),collectible[i].getYPosition(),
										collectible[i].getXSize(),collectible[i].getYSize()))
					{
						collectible[i].collect();
						if (collectible[i].isKey())
							if (++numKeysCollected >= numKeysToCollect)
							{
								door.setImage(Utils.OpenDoorImage);
								System.out.println("Collected the key(s) to the exit door");
							}
					}
			}

			if (player.touching(door.getXPosition(),door.getYPosition(),
								door.getXSize(),door.getYSize()) &&
				numKeysCollected >= numKeysToCollect)
				finished = true;

			// handle gravity - if the player is not standing on a platform, they're falling
			if (!standing)
			{
				if (gravity < Utils.MinGravity)
				{
					gravity = Utils.MinGravity;
					//System.out.println("gravity set to " + Utils.MinGravity);
				}
				player.move(0,(int)gravity);
				//System.out.println("Move player by gravity=" + (int)gravity);
			}
			else
			{
				//moveY = 0;
				gravity = Utils.MinGravity;
			}

			// if player has fallen off the bottom of the window, put all the items back to the start
			if (player.fallenBelowWindow(Utils.PlatformGameHeight))
			{
				player.resetPosition();
				for (int i = 0; i < numPlatforms; i++)
					platform[i].resetPosition(Utils.PlatformPositions[gameLevel][i][0],Utils.PlatformPositions[gameLevel][i][1]);
				for (int i = 0; i < numObstacles; i++)
					obstacle[i].resetPosition(Utils.ObstaclePositions[gameLevel][i][0],Utils.ObstaclePositions[gameLevel][i][1]);
				for (int i = 0; i < numCollectibles; i++)
					collectible[i].resetPosition(Utils.CollectiblePositions[gameLevel][i][0],Utils.CollectiblePositions[gameLevel][i][1]);
				door.resetPosition(Utils.DoorPosition[gameLevel][0],Utils.DoorPosition[gameLevel][1]);
				moveX = 0;
				moveY = 0;
				inertiaY = 0;
				gravity = Utils.MinGravity;
			}

			// display what was drawn on the window
			window.display();
			try {
				Thread.sleep(1); }
			catch (InterruptedException e) {
				System.out.println("My sleep was interrupted"); }

			//System.out.println("moveY="+moveY+", inertiaY="+inertiaY+", gravity="+gravity);

			// if any items were collected this time, then list all the items collected
			int itemsCollected = 0;
			for (int i = 0; i < numCollectibles; i++)
				if (collectible[i].collected())
					itemsCollected++;
			if (itemsCollected > lastNumCollected)
			{
				for (int i = 0; i < numCollectibles; i++)
					if (collectible[i].collected())
						System.out.println("Collected item " + i + ": " + Utils.CollectibleImages[gameLevel][i]);
				System.out.println();
				lastNumCollected = itemsCollected;
			}
		}
		idleRight.kill();
		idleLeft.kill();
		runningLeft.kill();
		runningRight.kill();
		//sleighRight.kill();
		bauble.kill();
		axe.kill();
		window.close();
		return finished;	// returns true if platform completed successfully
							// if window closed without finishing, returns false
	}
}
