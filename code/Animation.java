/**
 * This class creates the animation for the player
 */
import java.nio.file.*;
import java.io.*;
import org.jsfml.graphics.*;

class Animation extends Thread
{
	private Player player;
	private Texture[] texture = new Texture[4];
	
	/**
	 * Animation constructor - create a set of textures (images) for this player
	 * @param p - player
	 * @param path - file path for texture (image); each texture has the same file name followed by a number starting from 1
	 */
	public Animation(Player p, String path)
	{
		player = p;
		
		for (int i = 0; i < 4; i++)
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
			for (int i = 0; i < 4; i++)
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