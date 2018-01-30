/**
 * This class animates a player in the game
 */
import java.nio.file.*;
import java.io.*;
import org.jsfml.graphics.*;
import java.util.ArrayList;

class AnimatedCollectible extends Thread
{
	private static Collectible col;
	private Texture[] texture;
	private int interval;
	private boolean active = false;
	private boolean alive = true;
	
	/**
	 * Platform constructor - creates a platform (for the sprite to stand on)
	 * @param c - the Collectible instance to be animated
	 * @param path - the file path to the sprites
	 * @param frames - the number of frames in the animation
	 * @param delay - the delay between each frame of the animation in milliseconds
	 */
	public AnimatedCollectible(Collectible c, String path, int frames, int delay)
	{
		col = c;
		texture = new Texture[frames];
		interval = delay;
		
		for (int i = 0; i<frames; i++)
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
		int i = 0;
		while (alive)
		{
			try {
				this.sleep(interval);
			} catch(InterruptedException e){
				System.out.println(e);
			}
			if (active)
			{
				col.setSprite(texture[i]);
				//System.out.println("ALIVE");
				if (++i == texture.length)
					i = 0;
			}
		}
	}
	
	/**
	 * Allows the animation to be activated or halted
	 * @param state - the true/false value that states if the animation is active
	 */
	public void setActive(boolean state)
	{
		active = state;
	}

	public boolean getActive()
	{
		return active;
	}
	
	/**
	* Ends the thread
	*/
	public void kill()
	{
		alive = false;
	}
}
