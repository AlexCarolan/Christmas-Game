import java.lang.*;
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
import org.jsfml.window.*;
import org.jsfml.window.event.*;
import org.jsfml.window.Keyboard.*;
import org.jsfml.graphics.*;

class Puzzle3
{
	private static String Title   = "Sleigh Puzzle, Level 4";
	private static String square1   = "images\\trafficPuzzle\\Ice_Tile.png";

	/**
	 * run - handle display and movement of the platform game for this level
	 * @return boolean - success indicator (true if puzzle completed)
	 */
	public boolean run()
	{
		// create the window
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode((7*100), Utils.PlatformGameHeight),
					Title,
					WindowStyle.CLOSE | WindowStyle.TITLEBAR);	// window can't be resized

		// limit the framerate
		window.setFramerateLimit(60);
		
		// add load screen
		Texture loadImg = new Texture();
 		
 		try {
 		loadImg.loadFromFile(Paths.get("images\\load\\puzzle3.png"));
 		} catch(IOException ex) {
 			System.out.println(ex);
 		}
  
 		Sprite loadBkg = new Sprite(loadImg);
		loadBkg.setOrigin(0,0);
 		loadBkg.setPosition(0,0);

		window.draw(loadBkg);
		window.display();

		// create all object
		//PuzzleTile t = new PuzzleTile(0,0,"T0.png");
		int[] blankTile = new int[2];
		int[] switchTile = new int[2];
		blankTile[0] = 1;
		blankTile[1] = 0;
		switchTile[0] = 1;
		switchTile[1] = 1;
		MazeTile[][] tiles = new MazeTile[6][6];
		//PuzzleTile[][] endTiles = new PuzzleTile[3][3];

		// tiles at start of puzzle game
		for (int i = 0; i < 6; i++)
		{
			for (int j = 0; j < 6; j++)
			{
				tiles[i][j] = new MazeTile((i*100),(j*100),square1,100,100,true);
			}
		}
		int player[][] = {{1,2},{2,2}};
		int car2[][] = {{0,0},{1,0}};
		int car4[][] = {{0,4},{0,5}};
		int car6[][] = {{4,4},{5,4}};
		int car3[][] = {{0,1},{0,2},{0,3}};
		int car5[][] = {{3,1},{3,2},{3,3}};
		int car7[][] = {{2,5},{3,5},{4,5}};
		int car8[][] = {{5,0},{5,1},{5,2}};
		int[][][] dictionary = {player,car2,car3,car4,car5,car6,car7,car8};
		int selected = 1;
		
		//System.out.println(dictionary[0].length);
		//System.out.println(car8.length+""+""+car2.length);
		tiles[player[0][0]][player[0][1]] = new MazeTile((player[0][0]*100),(player[0][1]*100),"images\\trafficPuzzle\\Sleigh_H_Left.png",100,100,false);
		tiles[player[1][0]][player[1][1]] = new MazeTile((player[1][0]*100),(player[1][1]*100),"images\\trafficPuzzle\\Sleigh_H_Right.png",100,100,false);
		
		tiles[car2[0][0]][car2[0][1]] = new MazeTile((car2[0][0]*100),(car2[0][1]*100),"images\\trafficPuzzle\\Logs_H_Left.png",100,100,false);
		tiles[car2[1][0]][car2[1][1]] = new MazeTile((car2[1][0]*100),(car2[1][1]*100),"images\\trafficPuzzle\\Logs_H_Right.png",100,100,false);
		
		tiles[car4[0][0]][car4[0][1]] = new MazeTile((car4[0][0]*100),(car4[0][1]*100),"images\\trafficPuzzle\\Logs_V_Top.png",100,100,false);
		tiles[car4[1][0]][car4[1][1]] = new MazeTile((car4[1][0]*100),(car4[1][1]*100),"images\\trafficPuzzle\\Logs_V_Bottom.png",100,100,false);
		
		tiles[car6[0][0]][car6[0][1]] = new MazeTile((car6[0][0]*100),(car6[0][1]*100),"images\\trafficPuzzle\\Tree_H_Left.png",100,100,false);
		tiles[car6[1][0]][car6[1][1]] = new MazeTile((car6[1][0]*100),(car6[1][1]*100),"images\\trafficPuzzle\\Tree_H_Right.png",100,100,false);
		
		tiles[car3[0][0]][car3[0][1]] = new MazeTile((car3[0][0]*100),(car3[0][1]*100),"images\\trafficPuzzle\\Tree_V_Top.png",100,100,false);
		tiles[car3[1][0]][car3[1][1]] = new MazeTile((car3[1][0]*100),(car3[1][1]*100),"images\\trafficPuzzle\\Tree_V_Middle.png",100,100,false);
		tiles[car3[2][0]][car3[2][1]] = new MazeTile((car3[2][0]*100),(car3[2][1]*100),"images\\trafficPuzzle\\Tree_V_Bottom.png",100,100,false);
		
		tiles[car5[0][0]][car5[0][1]] = new MazeTile((car5[0][0]*100),(car5[0][1]*100),"images\\trafficPuzzle\\Reindeer_V_Top.png",100,100,false);
		tiles[car5[1][0]][car5[1][1]] = new MazeTile((car5[1][0]*100),(car5[1][1]*100),"images\\trafficPuzzle\\Reindeer_V_Middle.png",100,100,false);
		tiles[car5[2][0]][car5[2][1]] = new MazeTile((car5[2][0]*100),(car5[2][1]*100),"images\\trafficPuzzle\\Reindeer_V_Bottom.png",100,100,false);
		
		tiles[car7[0][0]][car7[0][1]] = new MazeTile((car7[0][0]*100),(car7[0][1]*100),"images\\trafficPuzzle\\Ice_H_Left.png",100,100,false);
		tiles[car7[1][0]][car7[1][1]] = new MazeTile((car7[1][0]*100),(car7[1][1]*100),"images\\trafficPuzzle\\Ice_H_Middle.png",100,100,false);
		tiles[car7[2][0]][car7[2][1]] = new MazeTile((car7[2][0]*100),(car7[2][1]*100),"images\\trafficPuzzle\\Ice_H_Right.png",100,100,false);
		
		tiles[car8[0][0]][car8[0][1]] = new MazeTile((car8[0][0]*100),(car8[0][1]*100),"images\\trafficPuzzle\\Ice_V_Top.png",100,100,false);
		tiles[car8[1][0]][car8[1][1]] = new MazeTile((car8[1][0]*100),(car8[1][1]*100),"images\\trafficPuzzle\\Ice_V_Middle.png",100,100,false);
		tiles[car8[2][0]][car8[2][1]] = new MazeTile((car8[2][0]*100),(car8[2][1]*100),"images\\trafficPuzzle\\Ice_V_Bottom.png",100,100,false);

		for (int i = 0; i < 2; i++)
		{
			//tiles[player[i][0]][player[i][1]] = new MazeTile((player[i][0]*100),(player[i][1]*100),"images\\trafficPuzzle\\player.png",100,100,false);
			//tiles[car2[i][0]][car2[i][1]] = new MazeTile((car2[i][0]*100),(car2[i][1]*100),"images\\trafficPuzzle\\car2.png",100,100,false);
			//tiles[car4[i][0]][car4[i][1]] = new MazeTile((car4[i][0]*100),(car4[i][1]*100),"images\\trafficPuzzle\\car4.png",100,100,false);
			//tiles[car6[i][0]][car6[i][1]] = new MazeTile((car6[i][0]*100),(car6[i][1]*100),"images\\trafficPuzzle\\car6.png",100,100,false);
		}
		for (int i = 0; i < 3; i++)
		{
			//tiles[car3[i][0]][car3[i][1]] = new MazeTile((car3[i][0]*100),(car3[i][1]*100),"images\\trafficPuzzle\\car3.png",100,100,false);
			//tiles[car5[i][0]][car5[i][1]] = new MazeTile((car5[i][0]*100),(car5[i][1]*100),"images\\trafficPuzzle\\car5.png",100,100,false);
			//tiles[car7[i][0]][car7[i][1]] = new MazeTile((car7[i][0]*100),(car7[i][1]*100),"images\\trafficPuzzle\\car7.png",100,100,false);
			//tiles[car8[i][0]][car8[i][1]] = new MazeTile((car8[i][0]*100),(car8[i][1]*100),"images\\trafficPuzzle\\car8.png",100,100,false);
		}
		
		MazeTile exit = new MazeTile((6*100),(2*100),"images\\trafficPuzzle\\exit.png",100,100,true);
		
		// set up instructions
										
		// Load the font for the instructions
		Font sansRegular = new Font( );
		try {
			sansRegular.loadFromFile(
					Paths.get("fonts\\LucidaSansRegular.ttf"));
		} catch (IOException ex) {
			ex.printStackTrace( );
		}
		Text text1 = new Text("Can you move the obstacles and get Santa's sleigh to the exit?", sansRegular, 18);
		Text text2a = new Text("Select the obstacle using the number keys", sansRegular, 18);
		Text text2b = new Text("Use the arrow keys, or WASD, to move the selected object", sansRegular, 18);
		Text text3 = new Text("Well done, you unblocked the sleigh!", sansRegular, 18);
		text1.setColor(Color.RED);
		text2a.setColor(Color.RED);
		text2b.setColor(Color.RED);
		text3.setColor(Color.WHITE);
		text2a.setStyle(Text.ITALIC);
		text2b.setStyle(Text.ITALIC);
		text1.setPosition(20, Utils.PuzzleGameHeight+20);
		text2a.setPosition(20, Utils.PuzzleGameHeight+40);
		text2b.setPosition(20, Utils.PuzzleGameHeight+60);
		text3.setPosition(20, Utils.PuzzleGameHeight+80);
		
		
		boolean finished = false;
		boolean paused = false;
		while (window.isOpen() && !finished) 
		{
			// handle keyboard/mouse events (movement can be via WASD or arrow keys)
			for (Event event : window.pollEvents()) 
			{
				switch(event.type) 
				{
					case KEY_PRESSED:
						if (!paused)
						{
							KeyEvent keyEvent = event.asKeyEvent();
							if ((keyEvent.key == Keyboard.Key.DOWN) || (keyEvent.key == Keyboard.Key.S))
							{
								if (dictionary[selected][0][1] < dictionary[selected][1][1])
								{
									//System.out.println(dictionary[selected].length);
									for (int i = dictionary[selected].length-1; i > -1; i--)
									{
										switchTile[1] = dictionary[selected][i][1] + 1;
										switchTile[0] = dictionary[selected][i][0];
										if (switchTile[1] < 6)
										{
											if (tiles[switchTile[0]][switchTile[1]].isPassable())
											{
												MazeTile temp = new MazeTile(tiles[switchTile[0]][switchTile[1]].getX(),tiles[switchTile[0]][switchTile[1]].getY(),tiles[switchTile[0]][switchTile[1]].getPicture(),100,100,tiles[switchTile[0]][switchTile[1]].isPassable());
												//tiles[switchTile[0]][switchTile[1]] = new MazeTile(tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getX(), tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getY(), tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getPicture(),100,100,tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].isPassable());
												tiles[switchTile[0]][switchTile[1]] = new MazeTile(tiles[switchTile[0]][switchTile[1]].getX(), tiles[switchTile[0]][switchTile[1]].getY(), tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getPicture(),100,100,tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].isPassable());
												//tiles[dictionary[selected][i][0]][dictionary[selected][i][1]] = new MazeTile(temp.getX(),temp.getY(),temp.getPicture(),100,100,temp.isPassable());
												tiles[dictionary[selected][i][0]][dictionary[selected][i][1]] = new MazeTile(tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getX(),tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getY(),temp.getPicture(),100,100,temp.isPassable());
												//dictionary[selected][i][1] = switchTile[1];
												dictionary[selected][i][1]+=1;
											}
										}
									}
								}
							}
							else if ((keyEvent.key == Keyboard.Key.UP) || (keyEvent.key == Keyboard.Key.W))
							{
								if (dictionary[selected][0][1] < dictionary[selected][1][1])
								{
									//System.out.println("1");
									for (int i = 0; i < dictionary[selected].length; i++)
									{
										//System.out.println("original "+dictionary[selected][i][1]);
										switchTile[1] = dictionary[selected][i][1] -1;
										switchTile[0] = dictionary[selected][i][0];
										//System.out.println(switchTile[1]);
										if (switchTile[1] >= 0)
										{
											//System.out.println("3");
											//System.out.println(tiles[switchTile[0]][switchTile[1]].getX()+" "+tiles[switchTile[0]][switchTile[1]].getY());
											if (tiles[switchTile[0]][switchTile[1]].isPassable())
											{
												//System.out.println("4");
												MazeTile temp = new MazeTile(tiles[switchTile[0]][switchTile[1]].getX(),tiles[switchTile[0]][switchTile[1]].getY(),tiles[switchTile[0]][switchTile[1]].getPicture(),100,100,tiles[switchTile[0]][switchTile[1]].isPassable());
												//tiles[switchTile[0]][switchTile[1]] = new MazeTile(tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getX(), tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getY(), tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getPicture(),100,100,tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].isPassable());
												tiles[switchTile[0]][switchTile[1]] = new MazeTile(tiles[switchTile[0]][switchTile[1]].getX(), tiles[switchTile[0]][switchTile[1]].getY(), tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getPicture(),100,100,tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].isPassable());
												//tiles[dictionary[selected][i][0]][dictionary[selected][i][1]] = new MazeTile(temp.getX(),temp.getY(),temp.getPicture(),100,100,temp.isPassable());
												tiles[dictionary[selected][i][0]][dictionary[selected][i][1]] = new MazeTile(tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getX(),tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getY(),temp.getPicture(),100,100,temp.isPassable());
												//dictionary[selected][i][1] = switchTile[1];
												dictionary[selected][i][1]-=1;
											}
										}
									}
								}
							}
							else if ((keyEvent.key == Keyboard.Key.RIGHT) || (keyEvent.key == Keyboard.Key.D))
							{
								if (dictionary[selected][0][0] < dictionary[selected][1][0])
								{
									//System.out.println("1");
									for (int i = dictionary[selected].length-1; i > -1; i--)
									{
										//System.out.println("original "+dictionary[selected][i][0]);
										switchTile[1] = dictionary[selected][i][1];
										switchTile[0] = dictionary[selected][i][0]+1;
										//System.out.println(switchTile[1]);
										if (switchTile[0] < 6)
										{
											//System.out.println("3");
											//System.out.println(tiles[switchTile[0]][switchTile[1]].getX()+" "+tiles[switchTile[0]][switchTile[1]].getY());
											if (tiles[switchTile[0]][switchTile[1]].isPassable())
											{
												//System.out.println("4");
												MazeTile temp = new MazeTile(tiles[switchTile[0]][switchTile[1]].getX(),tiles[switchTile[0]][switchTile[1]].getY(),tiles[switchTile[0]][switchTile[1]].getPicture(),100,100,tiles[switchTile[0]][switchTile[1]].isPassable());
												//tiles[switchTile[0]][switchTile[1]] = new MazeTile(tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getX(), tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getY(), tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getPicture(),100,100,tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].isPassable());
												tiles[switchTile[0]][switchTile[1]] = new MazeTile(tiles[switchTile[0]][switchTile[1]].getX(), tiles[switchTile[0]][switchTile[1]].getY(), tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getPicture(),100,100,tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].isPassable());
												//tiles[dictionary[selected][i][0]][dictionary[selected][i][1]] = new MazeTile(temp.getX(),temp.getY(),temp.getPicture(),100,100,temp.isPassable());
												tiles[dictionary[selected][i][0]][dictionary[selected][i][1]] = new MazeTile(tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getX(),tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getY(),temp.getPicture(),100,100,temp.isPassable());
												//dictionary[selected][i][1] = switchTile[1];
												dictionary[selected][i][0]+=1;
											}
										}
									}
								}
							}
							else if ((keyEvent.key == Keyboard.Key.LEFT) || (keyEvent.key == Keyboard.Key.A))
							{
								if (dictionary[selected][0][0] < dictionary[selected][1][0])
								{
									//System.out.println("1");
									for (int i = 0; i < dictionary[selected].length; i++)
									{
										//System.out.println("original "+dictionary[selected][i][0]);
										switchTile[1] = dictionary[selected][i][1];
										switchTile[0] = dictionary[selected][i][0]-1;
										//System.out.println(switchTile[1]);
										if (switchTile[0] >= 0)
										{
											//System.out.println("3");
											//System.out.println(tiles[switchTile[0]][switchTile[1]].getX()+" "+tiles[switchTile[0]][switchTile[1]].getY());
											if (tiles[switchTile[0]][switchTile[1]].isPassable())
											{
												//System.out.println("4");
												MazeTile temp = new MazeTile(tiles[switchTile[0]][switchTile[1]].getX(),tiles[switchTile[0]][switchTile[1]].getY(),tiles[switchTile[0]][switchTile[1]].getPicture(),100,100,tiles[switchTile[0]][switchTile[1]].isPassable());
												//tiles[switchTile[0]][switchTile[1]] = new MazeTile(tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getX(), tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getY(), tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getPicture(),100,100,tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].isPassable());
												tiles[switchTile[0]][switchTile[1]] = new MazeTile(tiles[switchTile[0]][switchTile[1]].getX(), tiles[switchTile[0]][switchTile[1]].getY(), tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getPicture(),100,100,tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].isPassable());
												//tiles[dictionary[selected][i][0]][dictionary[selected][i][1]] = new MazeTile(temp.getX(),temp.getY(),temp.getPicture(),100,100,temp.isPassable());
												tiles[dictionary[selected][i][0]][dictionary[selected][i][1]] = new MazeTile(tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getX(),tiles[dictionary[selected][i][0]][dictionary[selected][i][1]].getY(),temp.getPicture(),100,100,temp.isPassable());
												//dictionary[selected][i][1] = switchTile[1];
												dictionary[selected][i][0]-=1;
											}
										}
									}
								}
							}
							else if ((keyEvent.key == Keyboard.Key.NUM1) || (keyEvent.key == Keyboard.Key.NUMPAD1))
							{
								//System.out.println("SELECTED: PLAYER");
								selected = 0;
							}
							else if ((keyEvent.key == Keyboard.Key.NUM2) || (keyEvent.key == Keyboard.Key.NUMPAD2))
							{
								//System.out.println("SELECTED: 2");
								selected = 1;
							}
							else if ((keyEvent.key == Keyboard.Key.NUM3) ||(keyEvent.key == Keyboard.Key.NUMPAD3))
							{
								//System.out.println("SELECTED: 3");
								selected = 2;
							}
							else if ((keyEvent.key == Keyboard.Key.NUM4) || (keyEvent.key == Keyboard.Key.NUMPAD4))
							{
								//System.out.println("SELECTED: 4");
								selected = 3;
							}
							else if ((keyEvent.key == Keyboard.Key.NUM5) || (keyEvent.key == Keyboard.Key.NUMPAD5))
							{
								//System.out.println("SELECTED: 5");
								selected = 4;
							}
							else if ((keyEvent.key == Keyboard.Key.NUM6) || (keyEvent.key == Keyboard.Key.NUMPAD6))
							{
								//System.out.println("SELECTED: 6");
								selected = 5;
							}
							else if ((keyEvent.key == Keyboard.Key.NUM7) || (keyEvent.key == Keyboard.Key.NUMPAD7))
							{
								//System.out.println("SELECTED: 7");
								selected = 6;
							}
							else if ((keyEvent.key == Keyboard.Key.NUM8) || (keyEvent.key == Keyboard.Key.NUMPAD8))
							{
								//System.out.println("SELECTED: 8");
								selected = 7;
							}
							break;
						}
					case CLOSED:
						//System.out.println("Close clicked");
						window.close();
						break;
					case LOST_FOCUS:
						paused = true;
						break;
					case GAINED_FOCUS:
						paused = false;
						break;
				}
			}

			if (window.isOpen())
			{
				// fill the window with black
				window.clear(Color.BLACK);

				// add all objects onto the window
				for (int i = 0; i < 6; i++)
				{
					for (int j = 0; j < 6; j++)
					{
						window.draw(tiles[i][j].getTile());
					}
				}
				window.draw(exit.getTile());

				// add instructions
				window.draw(text1);
				window.draw(text2a);
				window.draw(text2b);

				// check whether images are all in the right place
				if (dictionary[0][1][0] == 5)
					finished = true;

				if (finished)
				{
					System.out.println("Well done, you unblocked the sleigh!");
					window.draw(text3);
				}
				
				// display what was drawn on the window
				window.display();
				
				if (finished)
				{
					try {					// pause so player can see success message
						Thread.sleep(1000);
					} catch (Exception e) {
						System.out.println();
					}
					window.close();
				}
			}
		}
		if (window.isOpen())
			window.close();
		return finished;
	}
}	
