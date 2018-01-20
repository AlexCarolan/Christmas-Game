/**
 * This class animates a player in the game
 */
import java.nio.file.*;
import java.io.*;
import org.jsfml.graphics.*;
import java.util.ArrayList;

class Animation extends Thread
{
	private static Player player;
	private Texture[] texture;
	
	/**
	 * Platform constructor - creates a platform (for the sprite to stand on)
	 * @param p - the player instance to be animated
	 * @param path - the file path to the sprites
	 * @param frames - the number of frames in the animation
	 */
	public Animation(Player p, String path, int frames)
	{
		player = p;
		texture = new Texture[frames];
		
		for(int i=0; i<frames; i++)
		{
			texture[i] = new Texture();
			try {
				// try to load the texture from file
				texture[i].loadFromFile(Paths.get(path + (i+1) + ".png"));
			} catch(IOException ex) {
				System.out.println("Unable to open image file");
			}
		}
	}
	
	/**
	* Starts the thread causing the frames to cycle at regular intervals
	*/
	public void run()
  {
		while(true)
		{
			for(int i=0; i<texture.length; i++)
			{
				player.setSprite(texture[i]);
				
				try{
					this.sleep(125);
				}catch(InterruptedException e){
					System.out.println(e);
				}
			}
		}
  }
	
	
}