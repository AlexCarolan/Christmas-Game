/**
 * This class animates a player in the game
 */
import java.nio.file.*;
import java.io.*;
import org.jsfml.graphics.*;

import java.util.ArrayList;

class Animation extends Thread
{
	private Player player;
	private Texture[] texture;
	
	/**
	 * Animation constructor - create a set of textures (images) for this player
	 * @param p - the player instance to be animated
	 * @param path - file path for texture (image); each texture has the same file name followed by a number starting from 1
	 * @param frames - the number of frames in the animation
	 */
	public Animation(Player p, String path, int frames)
	{
		player = p;
		texture = new Texture[frames];
		
		for (int i = 0; i < frames; i++)
		{
			texture[i] = new Texture();
			try {
				// try to load the texture from file
				texture[i].loadFromFile(Paths.get(path + (i+1) + ".png"));
			} catch(IOException ex) {
				System.out.println("Unable to open image file " + (path + (i+1)));
			}
		}
		
	}

	/**
	 * run - handle animation of player image using textures
	 */
	public void run()
	{
		while (true)
		{
			for (int i = 0; i < texture.length; i++)
			{
				player.setSprite(texture[i]);
				
				try {
					this.sleep(125);
				} catch(InterruptedException e){
					System.out.println(e);
				}
			}
		}
	}

}