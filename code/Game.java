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
import org.jsfml.audio.Music;
import java.nio.file.Paths;

class Game 
{
	private static int fontSize = 30;
	private static int currentPos = 1;
	private static Level level = new Level();
	private static int levelsUnlocked = 1;
	private static boolean puzzleDone[] = {false,false,false,false};

	public static void main (String args[ ]) 
	{

		// create a window
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode(Utils.PlatformGameWidth, Utils.PlatformGameHeight),
						"Christmas Game Menu",
						WindowStyle.CLOSE | WindowStyle.TITLEBAR);	// window can't be resized

		// set the frame-rate
		window.setFramerateLimit(24);
		
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
		
		//create menu still image of the player
		Player menuPlayer = new Player(1);
		menuPlayer.setLocation(800,700);

		// display the Christmas Room (as a platform)
		Platform room = new Platform(0,0,Utils.PlatformGameWidth,Utils.PlatformGameHeight-350,Utils.RoomImage[level.getLevel()],false);
		
		// display the menu
		Platform background = new Platform(0,Utils.PlatformGameHeight-350,Utils.PlatformGameWidth,350,"images\\mainMenu\\background.png",false);
										
		// Load the font for the menu options
		Font sansRegular = new Font( );
		try {
			sansRegular.loadFromFile(
					Paths.get("fonts\\joystix monospace.ttf"));
		} catch (IOException ex) {
			ex.printStackTrace( );
		}
		// create the game introduction text
		Text textIntro1 = new Text("Can you help decorate", sansRegular, 14);
		Text textIntro2 = new Text("this room for Christmas?", sansRegular, 14);
		Text textIntro3 = new Text("Complete each level", sansRegular, 14);
		Text textIntro4 = new Text("to add to the room", sansRegular, 14);
		Text textIntro5 = new Text("There are items to", sansRegular, 14);
		Text textIntro6 = new Text("collect along the way,", sansRegular, 14);
		Text textIntro7 = new Text("and a fun puzzle at", sansRegular, 14);
		Text textIntro8 = new Text("the end of each level", sansRegular, 14);
		Text textIntro9 = new Text("Be careful, there are", sansRegular, 14);
		Text textIntro10 = new Text("hazards and pitfalls", sansRegular, 14);

		textIntro1.setPosition(670, Utils.PlatformGameHeight-280);
		textIntro2.setPosition(670, Utils.PlatformGameHeight-265);
		textIntro3.setPosition(670, Utils.PlatformGameHeight-240);
		textIntro4.setPosition(670, Utils.PlatformGameHeight-225);
		textIntro5.setPosition(670, Utils.PlatformGameHeight-200);
		textIntro6.setPosition(670, Utils.PlatformGameHeight-185);
		textIntro7.setPosition(670, Utils.PlatformGameHeight-170);
		textIntro8.setPosition(670, Utils.PlatformGameHeight-155);
		textIntro9.setPosition(670, Utils.PlatformGameHeight-130);
		textIntro10.setPosition(670, Utils.PlatformGameHeight-115);
		
		// create the menu text
		Text textPlatform1 = new Text("Level 1 - The Tree", sansRegular, 18);
		textPlatform1.setPosition(135, Utils.PlatformGameHeight-282);
		Text textPlatform2 = new Text("Level 2 - The Decorations", sansRegular, 18);
		textPlatform2.setPosition(135, Utils.PlatformGameHeight-217);
		Text textPlatform3 = new Text("Level 3 - The Food", sansRegular, 18);
		textPlatform3.setPosition(135, Utils.PlatformGameHeight-155);
		Text textPlatform4 = new Text("Level 4 - The Gifts", sansRegular, 18);
		textPlatform4.setPosition(135, Utils.PlatformGameHeight-92);
		
		//Load a music to play
        Music music = new Music();
		Music select = new Music();
		Music levelmusic = new Music();
		
		music.setLoop(true);
		
		levelmusic.setLoop(true);
		
		try{
			music.openFromFile(Paths.get("menu.ogg"));
			select.openFromFile(Paths.get("select.ogg"));
			levelmusic.openFromFile(Paths.get("level.ogg"));
		}catch(Exception e){}
			
        //Play the music
        music.play();

		
		//show collectibles:
		Score playerScore = new Score(0);
		int numCollectiblesPerLevel = Utils.CollectibleImages[0].length;
		//int numCollectibles = numCollectiblesPerLevel * Utils.MaxLevel;
		int yDisplayOffset[] = {40,110,155,185,215};
		int xDisplayOffset[] = {585,620,670,695};
		Collectible[][] collectible = new Collectible[Utils.MaxLevel][numCollectiblesPerLevel];
		for (int gameLevel = 0; gameLevel < Utils.MaxLevel; gameLevel++)
			for (int i = 0; i < numCollectiblesPerLevel; i++)
				//collectible[gameLevel][i] = new Collectible((i*150)+gameLevel*30,720,
				collectible[gameLevel][i] = new Collectible(xDisplayOffset[gameLevel],yDisplayOffset[i],
												Utils.CollectiblePositions[gameLevel][i][2],Utils.CollectiblePositions[gameLevel][i][3],
												Utils.CollectibleImages[gameLevel][i],playerScore);
		
		while (window.isOpen())
		{
			//For testing levels
			//PlatformGame test = new PlatformGame();
			//test.run(2, collectible[2]);
			
			System.out.println(levelsUnlocked);
			
			int gameLevel = level.getLevel();
			room.setImage(Utils.RoomImage[gameLevel]);
			background.setImage((Utils.MenuPath + levelsUnlocked + currentPos + ".png"));
			window.clear(Color.BLACK);
			
			window.draw(background.getPlatform());
			window.draw(room.getPlatform());
			
			window.draw(textIntro1);
			window.draw(textIntro2);
			window.draw(textIntro3);
			window.draw(textIntro4);
			window.draw(textIntro5);
			window.draw(textIntro6);
			window.draw(textIntro7);
			window.draw(textIntro8);
			window.draw(textIntro9);
			window.draw(textIntro10);
			
			window.draw(textPlatform1);
			//if (puzzleDone[0])
				window.draw(textPlatform2);
			//if (puzzleDone[1])
				window.draw(textPlatform3);
			//if (puzzleDone[2])
				window.draw(textPlatform4);

			for (int i = 0; i < Utils.MaxLevel; i++)
			{
				for (int j = 0; j < numCollectiblesPerLevel; j++)
				{
					if (collectible[i][j].collected())
					{
						//System.out.println("output");
						window.draw(collectible[i][j].getPlatform());
					}
				}
			}
			//display player
			window.draw(menuPlayer.getSprite());
			
			// display what was drawn on the window
			window.display();
			
			if (Keyboard.isKeyPressed(Keyboard.Key.W) || Keyboard.isKeyPressed(Keyboard.Key.UP))
			{
				select.play();
				currentPos = currentPos - 2;
				if (currentPos <= 0)
					currentPos = 1;
				
				try        
				{
					Thread.sleep(100);
				} 
				catch (InterruptedException e) 
				{
					System.out.println(e);
				}

			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.A) || Keyboard.isKeyPressed(Keyboard.Key.LEFT))
			{
				select.play();
				currentPos = currentPos - 1;
				if (currentPos <= 0)
					currentPos = 1;
				
				try        
				{
					Thread.sleep(100);
				} 
				catch(InterruptedException e) 
				{
					System.out.println(e);
				}
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.S) || Keyboard.isKeyPressed(Keyboard.Key.DOWN))
			{
				select.play();
				currentPos = currentPos + 2;
				if (currentPos > levelsUnlocked)
					currentPos = levelsUnlocked;
				
				try        
				{
					Thread.sleep(100);
				} 
				catch(InterruptedException e) 
				{
					System.out.println(e);
				}
			}
			else if(Keyboard.isKeyPressed(Keyboard.Key.D) || Keyboard.isKeyPressed(Keyboard.Key.RIGHT))
			{
				select.play();
				currentPos = currentPos + 1;
				if (currentPos > levelsUnlocked)
					currentPos = levelsUnlocked;
				
				try        
				{
					Thread.sleep(100);
				} 
				catch(InterruptedException e) 
				{
					System.out.println(e);
				}
			}
			else if (Keyboard.isKeyPressed(Keyboard.Key.RETURN))
			{
				select.play();
				music.pause();
				levelmusic.play();
				if (currentPos == 1)
				{
					PlatformGame platGame = new PlatformGame();
					if ((platGame.run(0, collectible[0])) && (levelsUnlocked == 1))
					{
						Puzzle0 puzzle = new Puzzle0();
						if (puzzle.run())
						{
							puzzleDone[0] = true;
							level.incrementLevel();
							levelsUnlocked++;
						}
						puzzle = null;
						levelsUnlocked++;
					}
					music.play();
					levelmusic.pause();
					platGame = null;
					updatePosition();
				}
				else if (currentPos == 2)
				{
					Puzzle0 puzzle = new Puzzle0();
					if (puzzle.run() && levelsUnlocked == 2)
					{
						puzzleDone[0] = true;
						levelsUnlocked++;
					}
					puzzle = null;
					updatePosition();
					music.play();
					levelmusic.pause();
				}
				else if (currentPos == 3)
				{
					PlatformGame platGame = new PlatformGame();
					if (platGame.run(1, collectible[1]) && (levelsUnlocked == 3))
					{
						Puzzle1 puzzle = new Puzzle1();
						if (puzzle.run())
						{
							puzzleDone[1] = true;
							level.incrementLevel();
							levelsUnlocked++;
						}
						puzzle = null;
						levelsUnlocked++;
					}
					music.play();
					levelmusic.pause();
					platGame = null;
					updatePosition();
				}
				else if (currentPos == 4)
				{
					Puzzle1 puzzle = new Puzzle1();
					if (puzzle.run() && levelsUnlocked == 4)
					{
						puzzleDone[1] = true;
						levelsUnlocked++;
					}
					puzzle = null;
					updatePosition();
					music.play();
					levelmusic.pause();
				}
				else if (currentPos == 5)
				{
					PlatformGame platGame = new PlatformGame();
					if (platGame.run(2, collectible[2]) && (levelsUnlocked == 5))
					{
						Puzzle2 puzzle = new Puzzle2();
						if (puzzle.run())
						{
							puzzleDone[2] = true;
							level.incrementLevel();
							levelsUnlocked++;
						}
						puzzle = null;
						levelsUnlocked++;
					}
					music.play();
					levelmusic.pause();
					platGame = null;
					updatePosition();
				}
				else if (currentPos == 6)
				{
					Puzzle2 puzzle = new Puzzle2();
					if (puzzle.run() && levelsUnlocked == 6)
					{
						puzzleDone[2] = true;
						levelsUnlocked++;
					}
					puzzle = null;
					updatePosition();
					music.play();
					levelmusic.pause();
				}
				else if (currentPos == 7)
				{
					PlatformGame platGame = new PlatformGame();
					if (platGame.run(3, collectible[3]) && (levelsUnlocked == 7))
					{
						Puzzle3 puzzle = new Puzzle3();
						if (puzzle.run())
						{
							puzzleDone[3] = true;
							level.incrementLevel();
						}
						puzzle = null;
						levelsUnlocked++;
					}
					music.play();
					levelmusic.pause();
					platGame = null;
					updatePosition();
				}
				else if (currentPos == 8)
				{
					Puzzle3 puzzle = new Puzzle3();
					if (puzzle.run() && levelsUnlocked == 8)
					{
						puzzleDone[3] = true;
					}
					puzzle = null;
					updatePosition();

					music.play();
					levelmusic.pause();

				}
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
	
	// Updates the position of the selector
	public static void updatePosition()
	{
		currentPos = levelsUnlocked;
		if (levelsUnlocked >= 8)
		{
			currentPos = 1;
		}
	}
}
