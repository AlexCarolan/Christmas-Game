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
import org.jsfml.audio.Music;
import java.nio.file.Paths;

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
	public boolean run(int gameLevel, Collectible[] gameCollectible, RenderWindow win)
	{
		// create the window
		RenderWindow window = win;
		window.create(new VideoMode(Utils.PlatformGameWidth, Utils.PlatformGameHeight),
					Title + (gameLevel+1),
					WindowStyle.CLOSE | WindowStyle.TITLEBAR);	// window can't be resized

		// set the frame-rate
		window.setFramerateLimit(60);
		
		int moveX = 0;
		int moveY = 0;
		double sleighGravity = 0.0;
		
		// add load screen
		Texture loadImg = new Texture();
 		try {
			loadImg.loadFromFile(Paths.get("images\\load\\main.png"));
 		} catch(IOException ex) {
 			System.out.println(ex);
 		}
 		Sprite loadBkg = new Sprite(loadImg);
		loadBkg.setOrigin(0,0);
 		loadBkg.setPosition(0,0);
		window.draw(loadBkg);
		window.display();
		
		// create new score object
		Score playerScore = new Score(0);

		// create the background
		Platform background = new Platform(0-Utils.PlatformGameWidth/2,0,Utils.PlatformBackgroundWidth,Utils.PlatformGameHeight+1,
											Utils.PlatformBackgroundImage[gameLevel],false);

		// create all objects - player, platforms, obstacles, collectibles
		Player player;
		
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
		int numHazards = Utils.HazardPositions[gameLevel].length;
		//System.out.println("Number of hazards: " + numHazards);
		Hazard[] hazard = new Hazard[numHazards];
		for (int i = 0; i < numHazards; i++)
			hazard[i] = new Hazard(Utils.HazardPositions[gameLevel][i][0],Utils.HazardPositions[gameLevel][i][1],
									Utils.HazardPositions[gameLevel][i][2],Utils.HazardPositions[gameLevel][i][3],
									Utils.HazardImages[gameLevel][i][0],Utils.HazardImages[gameLevel][i][1]);

		int numCollectibles = Utils.CollectiblePositions[gameLevel].length;
		//System.out.println("Number of collectibles: " + numCollectibles);
		Collectible[] collectible = new Collectible[numCollectibles];
		for (int i = 0; i < numCollectibles; i++)
			collectible[i] = new Collectible(Utils.CollectiblePositions[gameLevel][i][0],Utils.CollectiblePositions[gameLevel][i][1],
											Utils.CollectiblePositions[gameLevel][i][2],Utils.CollectiblePositions[gameLevel][i][3],
											Utils.CollectibleImages[gameLevel][i],playerScore);

		Platform door = new Platform(Utils.DoorPosition[gameLevel][0],Utils.DoorPosition[gameLevel][1],
										Utils.DoorPosition[gameLevel][2],Utils.DoorPosition[gameLevel][3],
										Utils.ShutDoorImage,true);

		// create and start animations
		AnimatedPlayer idleRight;
		AnimatedPlayer idleLeft;
		AnimatedPlayer runningRight;
		AnimatedPlayer runningLeft;
		if (gameLevel != Utils.SleighGameLevel)
		{
			idleRight = new AnimatedPlayer(Utils.IdleRightPath, 4, 175);
			idleLeft = new AnimatedPlayer(Utils.IdleLeftPath, 4, 175);
			runningRight = new AnimatedPlayer(Utils.RunningRightPath, 12, 90);
			runningLeft = new AnimatedPlayer(Utils.RunningLeftPath, 12, 90);
		}
		else
		{
			idleRight = new AnimatedPlayer(Utils.SleighIdleRightPath, 2, 175);
			idleLeft = new AnimatedPlayer(Utils.SleighIdleLeftPath, 2, 175);
			runningRight = new AnimatedPlayer(Utils.SleighRunningRightPath, 10, 90);
			runningLeft = new AnimatedPlayer(Utils.SleighRunningLeftPath, 10, 90);
		}
		AnimatedCollectible key = new AnimatedCollectible(Utils.KeyPath, 4, 250);

		// first collectible is ALWAYS the key
		collectible[0].setAnimation(key);

		player = new Player(gameLevel);
		player.setAnimation(idleRight);

		AnimatedHazard[] animatedHazard = new AnimatedHazard[numHazards];
		for (int i = 0; i < numHazards; i++)
		{
			animatedHazard[i] = new AnimatedHazard(Utils.HazardImages[gameLevel][i][0],10,Utils.HazardImages[gameLevel][i][1],10,100);
			hazard[i].setAnimation(animatedHazard[i]);
		}
		
		// load the font
		Font scoreFont = new Font( );
		try {
			scoreFont.loadFromFile(
					Paths.get("fonts\\joystix monospace.ttf"));
		} catch (IOException ex) {
			ex.printStackTrace( );
		}

		// add the score tracker to the UI
		Text scoreText = new Text("Score:0", scoreFont, 18);
		scoreText.setPosition(10, 10);
		
		// create and add life counter
		Texture heart = new Texture();
		try {
			heart.loadFromFile(Paths.get(Utils.HeartPath));
		} catch(IOException ex) {
			System.out.println(ex);
		}
		
		Sprite[] lives = new Sprite[3];
		for (int i=0; i<3; i++)
		{
			lives[i] = new Sprite(heart);
			lives[i].setOrigin(0,0);
			lives[i].setPosition(13 + (i*40), 35);
		}

		// Create and add key tracker to the UI
 		Texture keySprite = new Texture();	
 		try {
			keySprite.loadFromFile(Paths.get(Utils.StaticKeyPath));
 		} catch(IOException ex) {
 			System.out.println(ex);
 		}
 		Sprite invKey = new Sprite(keySprite);
		invKey.setOrigin(0,0);
 		invKey.setPosition(135, 35);
		
		//platform sound effects:
		Music jumpSound = new Music();
		Music collectSound = new Music();
		Music hitSound = new Music();
		
		try {
			jumpSound.openFromFile(Paths.get("music\\jump.ogg"));
			collectSound.openFromFile(Paths.get("music\\collect.ogg"));
			hitSound.openFromFile(Paths.get("music\\hurt.aiff"));
		} catch(Exception e){
			System.out.println(e);
		}
		
		
		boolean keyCollected = false;
		
		boolean finished = false;		// set when player completes level
		boolean paused = false;			// set when window loses focus
		while (window.isOpen() && !finished && (player.getLives() > 0))
		{
			// fill the window with black
			window.clear(Color.BLACK);
			window.draw(background.getPlatform());
			
			// add the players score to the window
			scoreText.setString("Score:" + playerScore.getScore());
			window.draw(scoreText);

			// add the life counter to the window
			for (int i = 0; i < player.getLives(); i++)
				window.draw(lives[i]);
			
			// add the key collected display
 			if (keyCollected == true)
 				window.draw(invKey);
			
			// add all objects onto the window
			for (int i = 0; i < numPlatforms; i++)
				window.draw(platform[i].getPlatform());
			for (int i = 0; i < numObstacles; i++)
				window.draw(obstacle[i].getPlatform());
			for (int i = 0; i < numHazards; i++)
				window.draw(hazard[i].getPlatform());
			for (int i = 0; i < numCollectibles; i++)
				if (!collectible[i].collected())
					window.draw(collectible[i].getPlatform());
			window.draw(door.getPlatform());
			window.draw(player.getSprite());

			// modify horizontal movement per game tick so that player gradually slows down, rather than being jerky
			if (gameLevel != Utils.SleighGameLevel)		// COMMENT OUT BLOCK TO TEST LEVEL 4
			{
				if (moveX < 0)
					moveX++;
				else if (moveX > 0)
					moveX--;
			}

			// apply idle animation when still
			if ((gameLevel != Utils.SleighGameLevel) &&
				(runningRight.getActive() || runningLeft.getActive()) &&
				((System.currentTimeMillis() - lastTimeMoved) > 10))
			{
				if (runDirectionRight)
					player.setAnimation(idleRight);
				else
					player.setAnimation(idleLeft);
			}

			// handle keyboard events (movement can be via WASD or arrow keys)
			if (!paused &&
				(Keyboard.isKeyPressed(Keyboard.Key.A) || Keyboard.isKeyPressed(Keyboard.Key.LEFT)))	// going left
			{
				// turn on animation for runningLeft
				player.setAnimation(runningLeft);
				lastTimeMoved = System.currentTimeMillis();
				runDirectionRight = false;
				moveX = 0-Utils.MoveAmountX;
			}
			if (!paused && 
				(Keyboard.isKeyPressed(Keyboard.Key.D) || Keyboard.isKeyPressed(Keyboard.Key.RIGHT)))	// going right
			{
				// turn on animation for runningRight
				player.setAnimation(runningRight);
				lastTimeMoved = System.currentTimeMillis();
				runDirectionRight = true;
				moveX = Utils.MoveAmountX;
			}
			if (!paused &&
				(Keyboard.isKeyPressed(Keyboard.Key.W) || Keyboard.isKeyPressed(Keyboard.Key.UP) || 
				 Keyboard.isKeyPressed(Keyboard.Key.SPACE)))
			{
				// can only jump if bottom of player sprite standing on a platform or obstacle
				// or if in sleigh gameLevel
				
				boolean standing = false;
				if (gameLevel != Utils.SleighGameLevel)
				{
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
					// the sleigh can go past all the obstacles, it stops only for platforms
					if ((!standing) && (gameLevel != Utils.SleighGameLevel))
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
				}
				if (gameLevel == Utils.SleighGameLevel)
				{
					moveY = 0-Utils.MoveAmountY;
					if (moveX == 0)				// COMMENT OUT BLOCK TO TEST LEVEL 4
					{
						if (runDirectionRight)
						{
							moveX = Utils.MoveAmountX;
							player.setAnimation(runningRight);
							lastTimeMoved = System.currentTimeMillis();
						}
						else
						{
							moveX = 0-Utils.MoveAmountX;
							player.setAnimation(runningLeft);
							lastTimeMoved = System.currentTimeMillis();
						}
					}
				}
				else if (standing)
				{
					jumpSound.play();
					moveY = 0-Utils.JumpAmount;
					sleighGravity = 0.0;
					//System.out.println("Standing on something, so moveY="+moveY);
				}
				//System.out.println("Up pressed: moveY=" + moveY);
			}
			if (!paused &&
				(Keyboard.isKeyPressed(Keyboard.Key.S) || Keyboard.isKeyPressed(Keyboard.Key.DOWN)))
			{
				if (gameLevel == Utils.SleighGameLevel)
				{
					moveY = Utils.MoveAmountY;
					if (moveX == 0)				// COMMENT OUT BLOCK TO TEST LEVEL 4
					{
						if (runDirectionRight)
						{
							moveX = Utils.MoveAmountX;
							player.setAnimation(runningRight);
							lastTimeMoved = System.currentTimeMillis();
						}
						else
						{
							moveX = 0-Utils.MoveAmountX;
							player.setAnimation(runningLeft);
							lastTimeMoved = System.currentTimeMillis();
						}
					}
				}
				else
					moveY += Utils.MoveAmountY;
				//System.out.println("Down pressed: moveY=" + moveY);
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
						key.kill();
						return false;
					case LOST_FOCUS:
						paused = true;
						break;
					case GAINED_FOCUS:
						paused = false;
						break;
				}
			}

			boolean touching = false;
			if (moveX < 0)	// player moving left
			{	
				// check that player is not trying to run into an obstacle or the side of a platform
				for (int i = 0; i < numPlatforms; i++)
				{
					// allow sideways movement that goes up a small amount
					if (player.touchingLeft(platform[i].getXPosition()+moveX/2,platform[i].getYPosition()+20,
							platform[i].getXSize()-moveX,platform[i].getYSize()-20))
					{
						if ((gameLevel != Utils.SleighGameLevel) || platform[i].isCeiling())
						{
							//System.out.println("touchingLeft platform " + i);
							touching = true;
							break;
						}
					}
				}
				// the sleigh can go past all the obstacles, it stops only for platforms
				if ((!touching) && (gameLevel != Utils.SleighGameLevel))
				{
					for (int i = 0; i < numObstacles; i++)
					{
						// allow sideways movement that goes up a small amount
						if (player.touchingLeft(obstacle[i].getXPosition()+moveX/2,obstacle[i].getYPosition()+20,
								obstacle[i].getXSize()-moveX,obstacle[i].getYSize()-20))
						{
							//System.out.println("touchingLeft obstacle " + i);
							touching = true;
							break;
						}
					}
				}
			}
			else if (moveX > 0)	// player moving right
			{
				// check that player is not trying to run into an obstacle or the side of a platform
				for (int i = 0; i < numPlatforms; i++)
				{
					// allow sideways movement that goes up a small amount
					if (player.touchingRight(platform[i].getXPosition()-moveX/2,platform[i].getYPosition()+20,
							platform[i].getXSize()+moveX,platform[i].getYSize()-20))
					{
						if ((gameLevel != Utils.SleighGameLevel) || platform[i].isCeiling())
						{
							//System.out.println("touchingRight platform " + i);
							touching = true;
							break;
						}
					}
				}
				// the sleigh can go past all the obstacles, it stops only for platforms
				if ((!touching) && (gameLevel != Utils.SleighGameLevel))
				{
					for (int i = 0; i < numObstacles; i++)
					{
						// allow sideways movement that goes up a small amount
						if (player.touchingRight(obstacle[i].getXPosition()-moveX/2,obstacle[i].getYPosition()+20,
								obstacle[i].getXSize()+moveX,obstacle[i].getYSize()-20))
						{
							//System.out.println("touchingRight obstacle " + i);
							touching = true;
							break;
						}
					}
				}
			}
			// if the player wants to move horizontally and there's nothing in the way, move everything else in the opposite direction
			if ((moveX != 0) && !touching)
			{
				background.move(0-moveX,0);
				for (int i = 0; i < numPlatforms; i++)
					platform[i].move(0-moveX,0);
				for (int i = 0; i < numObstacles; i++)
					obstacle[i].move(0-moveX,0);
				for (int i = 0; i < numHazards; i++)
					hazard[i].move(0-moveX,0);
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
							moveY = 0;
							break;
						}
					}
			}

			if (moveY != 0)
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
					standing = true;
					// make player stand in same vertical position above platform
					player.standOn(platform[i].getYPosition()-Utils.MoveAmountY/2);
					//System.out.println("Player is NOW standing on platform " + i + ", moveY=0");
					break;
				}
			}
			// the sleigh can go past all the obstacles, it stops only for platforms
			if (gameLevel != Utils.SleighGameLevel)
			{
				for (int i = 0; i < numObstacles; i++)
				{
					if (player.standingOn(obstacle[i].getXPosition(),obstacle[i].getYPosition()-Utils.MoveAmountY/2,
											obstacle[i].getXSize(),obstacle[i].getYSize()+Utils.MoveAmountY))
					{
						standing = true;
						// make player stand in same vertical position above obstacle
						player.standOn(obstacle[i].getYPosition()-Utils.MoveAmountY/2);
						//System.out.println("Player is NOW standing on obstacle " + i + ", moveY=0");
						break;
					}
				}
			}
			if (standing)
			{
				moveY = 0;
				sleighGravity = 0.0;
			}
			else
			{
				if (gameLevel == Utils.SleighGameLevel)
				{
					sleighGravity += Utils.SleighGravity;
					if (sleighGravity >= 1)
					{
						if (moveY < Utils.Gravity)
							moveY += 1;
						sleighGravity = 0.0;
					}
				}
				else
					moveY += Utils.Gravity;
					//System.out.println("Not standing, so gravity; moveY=" + moveY);
			}

			// check if player is touching a hazard that is currently damaging
			// check if touching the centre bottom of the image
			boolean damaged = false;
			for (int i = 0; i < numHazards; i++)
			{
				if (animatedHazard[i].isDamaging())
				{
					int hazardousWidth = hazard[i].getXSize() / 4;
					if (hazardousWidth < 2)
						hazardousWidth = 2;
					int hazardousHeight = hazard[i].getYSize() / 2;
					if (hazardousHeight < 2)
						hazardousHeight = 2;
					int startX = hazard[i].getXPosition() + hazardousWidth + hazardousWidth/2;
					int startY = hazard[i].getYPosition() + hazardousHeight/2;
					//if (player.touching(startX,hazard[i].getYPosition() + hazard[i].getYSize() - 10,
					//					hazardousWidth,10) && animatedHazard[i].isDamaging())
					if (player.touching(startX,startY,hazardousWidth,hazardousHeight + hazardousHeight/2))
					{
						damaged = true;
						//System.out.println("Touching damaging hazard " + i);
						//System.out.println("XPos=" + hazard[i].getXPosition() + ", Width=" + hazard[i].getXSize() +
						//					", hazardLeft=" + startX + ", hazardRight=" + (startX + hazardousWidth));
						System.out.println("Touched a damaging hazard");
						break;
					}
				}
			}

			// if touching a damaging hazard, or
			// if player has fallen off the bottom of the window, then lose a life and put all the items back to the start
			if (damaged || player.fallenBelowWindow(Utils.PlatformGameHeight))
			{
				window.display();
				try {					// pause so player can see success message
						Thread.sleep(100);
					} catch (Exception e) {
						System.out.println();
					}
				player.resetPosition();
				player.takeLife();
				hitSound.play();
				background.resetPosition(0-Utils.PlatformGameWidth/2,0);
				for (int i = 0; i < numPlatforms; i++)
					platform[i].resetPosition(Utils.PlatformPositions[gameLevel][i][0],Utils.PlatformPositions[gameLevel][i][1]);
				for (int i = 0; i < numObstacles; i++)
					obstacle[i].resetPosition(Utils.ObstaclePositions[gameLevel][i][0],Utils.ObstaclePositions[gameLevel][i][1]);
				for (int i = 0; i < numHazards; i++)
					hazard[i].resetPosition(Utils.HazardPositions[gameLevel][i][0],Utils.HazardPositions[gameLevel][i][1]);
				for (int i = 0; i < numCollectibles; i++)
					collectible[i].resetPosition(Utils.CollectiblePositions[gameLevel][i][0],Utils.CollectiblePositions[gameLevel][i][1]);
				door.resetPosition(Utils.DoorPosition[gameLevel][0],Utils.DoorPosition[gameLevel][1]);
				moveX = 0;
				moveY = 0;
				if (runDirectionRight)
					player.setAnimation(idleRight);
				else
					player.setAnimation(idleLeft);
			}

			// if touching a collectible, then pick it up
			for (int i = 0; i < numCollectibles; i++)
			{
				if (!collectible[i].collected())
					if (player.touching(collectible[i].getXPosition(),collectible[i].getYPosition(),
										collectible[i].getXSize(),collectible[i].getYSize()))
					{
						collectible[i].collect();
						collectSound.play();
						if (i == 0)				// key is always first collectible
						{
							keyCollected = true;
							door.setImage(Utils.OpenDoorImage);
							System.out.println("Collected the key to the platform exit");
						}
					}
			}

			// if touching the exit door and holding the exit key, then finished platform
			if (keyCollected && player.touching(door.getXPosition(),door.getYPosition(),
												door.getXSize(),door.getYSize()))
				finished = true;

			// display what was drawn on the window
			window.display();
			try {
				Thread.sleep(1); }
			catch (InterruptedException e) {
				System.out.println("My sleep was interrupted"); }

			// if any items were collected this time, then list all the items collected
			int itemsCollected = 0;
			for (int i = 0; i < numCollectibles; i++)	// count how many collected currently
				if (collectible[i].collected())
					itemsCollected++;
			if (itemsCollected > lastNumCollected)		// if more than we had before
			{
				for (int i = 0; i < numCollectibles; i++)
					if (collectible[i].collected())		// list everything collected
						System.out.println("Collected " + Utils.CollectibleImages[gameLevel][i]);
				System.out.println();
				lastNumCollected = itemsCollected;		// and update the count of how many we have
			}
		}
		idleRight.kill();
		idleLeft.kill();
		runningLeft.kill();
		runningRight.kill();
		key.kill();
		if (player.getLives() <= 0)
			return false;	// return false if all lives lost
		if (finished)
		{	// if we successfully finished the game then update the outer list with all the items collected this time
			for (int i = 0; i < numCollectibles; i++)
				if (collectible[i].collected())
					gameCollectible[i].collect();
		}
		return finished;	// returns true if platform completed successfully
							// if window closed without finishing, returns false
	}
}
