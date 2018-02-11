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
	private static String Title   = "Puzzle 3";
	private static String square1   = "images\\trafficPuzzle\\square1.png";
	private static String square2   = "images\\trafficPuzzle\\square2.png";

	/**
	 * run - handle display and movement of the platform game for this level
	 */
	public static void run () 
	{
		// create the window
		RenderWindow window = new RenderWindow( );
		window.create(new VideoMode((7*100), (7*100)),
					Title,
					WindowStyle.CLOSE | WindowStyle.TITLEBAR);	// window can't be resized

		// limit the framerate
		window.setFramerateLimit(60);

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
		for(int i = 0; i < 2; i++)
		{
			tiles[player[i][0]][player[i][1]] = new MazeTile((player[i][0]*100),(player[i][1]*100),"images\\trafficPuzzle\\player.png",100,100,false);
			tiles[car2[i][0]][car2[i][1]] = new MazeTile((car2[i][0]*100),(car2[i][1]*100),"images\\trafficPuzzle\\car2.png",100,100,false);
			tiles[car4[i][0]][car4[i][1]] = new MazeTile((car4[i][0]*100),(car4[i][1]*100),"images\\trafficPuzzle\\car4.png",100,100,false);
			tiles[car6[i][0]][car6[i][1]] = new MazeTile((car6[i][0]*100),(car6[i][1]*100),"images\\trafficPuzzle\\car6.png",100,100,false);
		}
		for(int i = 0; i < 3; i++)
		{
			tiles[car3[i][0]][car3[i][1]] = new MazeTile((car3[i][0]*100),(car3[i][1]*100),"images\\trafficPuzzle\\car3.png",100,100,false);
			tiles[car5[i][0]][car5[i][1]] = new MazeTile((car5[i][0]*100),(car5[i][1]*100),"images\\trafficPuzzle\\car5.png",100,100,false);
			tiles[car7[i][0]][car7[i][1]] = new MazeTile((car7[i][0]*100),(car7[i][1]*100),"images\\trafficPuzzle\\car7.png",100,100,false);
			tiles[car8[i][0]][car8[i][1]] = new MazeTile((car8[i][0]*100),(car8[i][1]*100),"images\\trafficPuzzle\\car8.png",100,100,false);
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
		Text text1 = new Text("Can you move the reindeer to unblock santa's sleigh?", sansRegular, 18);
		Text text2 = new Text("Select the object with num keys, Use the arrow keys, or WASD, to move", sansRegular, 18);
		Text text3 = new Text("Well done, you completed the picture!", sansRegular, 18);
		text1.setColor(Color.RED);
		text2.setColor(Color.RED);
		text3.setColor(Color.WHITE);
		text2.setStyle(Text.ITALIC);
		text1.setPosition(50, Utils.PuzzleGameHeight+20);
		text2.setPosition(50, Utils.PuzzleGameHeight+40);
		text3.setPosition(50, Utils.PuzzleGameHeight+60);
		
		
		boolean finished = false;

		while (window.isOpen() && !finished) 
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

			// handle keyboard/mouse events (movement can be via WASD or arrow keys)
			for (Event event : window.pollEvents()) 
			{
				switch(event.type) 
				{
					case KEY_PRESSED:
						KeyEvent keyEvent = event.asKeyEvent();
						if ((keyEvent.key == Keyboard.Key.DOWN) || (keyEvent.key == Keyboard.Key.S))
						{
							if(dictionary[selected][0][1] < dictionary[selected][1][1])
							{
								//System.out.println(dictionary[selected].length);
								for(int i = dictionary[selected].length-1; i > -1; i--)
								{
									switchTile[1] = dictionary[selected][i][1] + 1;
									switchTile[0] = dictionary[selected][i][0];
									if (switchTile[1] < 6)
									{
										if(tiles[switchTile[0]][switchTile[1]].isPassable())
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
							if(dictionary[selected][0][1] < dictionary[selected][1][1])
							{
								System.out.println("1");
								for(int i = 0; i < dictionary[selected].length; i++)
								{
									System.out.println("original "+dictionary[selected][i][1]);
									switchTile[1] = dictionary[selected][i][1] -1;
									switchTile[0] = dictionary[selected][i][0];
									System.out.println(switchTile[1]);
									if (switchTile[1] >= 0)
									{
										System.out.println("3");
										System.out.println(tiles[switchTile[0]][switchTile[1]].getX()+" "+tiles[switchTile[0]][switchTile[1]].getY());
										if(tiles[switchTile[0]][switchTile[1]].isPassable())
										{
											System.out.println("4");
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
							if(dictionary[selected][0][0] < dictionary[selected][1][0])
							{
								System.out.println("1");
								for(int i = dictionary[selected].length-1; i > -1; i--)
								{
									System.out.println("original "+dictionary[selected][i][0]);
									switchTile[1] = dictionary[selected][i][1];
									switchTile[0] = dictionary[selected][i][0]+1;
									System.out.println(switchTile[1]);
									if (switchTile[0] < 6)
									{
										System.out.println("3");
										System.out.println(tiles[switchTile[0]][switchTile[1]].getX()+" "+tiles[switchTile[0]][switchTile[1]].getY());
										if(tiles[switchTile[0]][switchTile[1]].isPassable())
										{
											System.out.println("4");
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
							if(dictionary[selected][0][0] < dictionary[selected][1][0])
							{
								System.out.println("1");
								for(int i = 0; i < dictionary[selected].length; i++)
								{
									System.out.println("original "+dictionary[selected][i][0]);
									switchTile[1] = dictionary[selected][i][1];
									switchTile[0] = dictionary[selected][i][0]-1;
									System.out.println(switchTile[1]);
									if (switchTile[0] >= 0)
									{
										System.out.println("3");
										System.out.println(tiles[switchTile[0]][switchTile[1]].getX()+" "+tiles[switchTile[0]][switchTile[1]].getY());
										if(tiles[switchTile[0]][switchTile[1]].isPassable())
										{
											System.out.println("4");
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
						else if(keyEvent.key == Keyboard.Key.NUM1)
						{
							System.out.println("SELECTED: PLAYER");
							selected = 0;
						}
						else if(keyEvent.key == Keyboard.Key.NUM2)
						{
							System.out.println("SELECTED: 2");
							selected = 1;
						}
						else if(keyEvent.key == Keyboard.Key.NUM3)
						{
							System.out.println("SELECTED: 3");
							selected = 2;
						}
						else if(keyEvent.key == Keyboard.Key.NUM4)
						{
							System.out.println("SELECTED: 4");
							selected = 3;
						}
						else if(keyEvent.key == Keyboard.Key.NUM5)
						{
							System.out.println("SELECTED: 5");
							selected = 4;
						}
						else if(keyEvent.key == Keyboard.Key.NUM6)
						{
							System.out.println("SELECTED: 6");
							selected = 5;
						}
						else if(keyEvent.key == Keyboard.Key.NUM7)
						{
							System.out.println("SELECTED: 7");
							selected = 6;
						}
						else if(keyEvent.key == Keyboard.Key.NUM8)
						{
							System.out.println("SELECTED: 8");
							selected = 7;
						}
						break;
					case CLOSED:
						System.out.println("Close clicked");
						window.close();
						break;
				}
			}

			if (window.isOpen())
			{
				// add instructions
				window.draw(text1);
				window.draw(text2);
				
				

				// check whether images are all in the right place
				
				
				if (dictionary[0][1][0] == 5)
					finished = true;
				if (finished)
				{
					System.out.println("Well done, you completed the picture!");
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
	}

	public static void main (String args[ ])
	{
		Puzzle3 p = new Puzzle3( );
		p.run( );
		p = null;
	}
}	
