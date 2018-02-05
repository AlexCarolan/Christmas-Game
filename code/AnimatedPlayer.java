/**
 * This class animates a player in the game
 */
import java.nio.file.*;
import java.io.*;
import org.jsfml.graphics.*;
import java.util.ArrayList;

class AnimatedPlayer extends Thread
{
	private static Player player;
	private Texture[] texture;
	private int interval;
	private boolean active = false;
	private boolean alive = true;
	
	/**
	 * Platform constructor - creates a platform (for the sprite to stand on)
	 * @param p - the player instance to be animated
	 * @param path - the file path to the sprites
	 * @param frames - the number of frames in the animation
	 * @param delay - the delay between each frame of the animation in milliseconds
	 */
	public AnimatedPlayer(Player p, String path, int frames, int delay)
	{
		player = p;
		texture = new Texture[frames];
		interval = delay;
		
		for (int i = 0; i<frames; i++)
		{
			texture[i] = new Texture();
			try {
				// try to load the texture from file
				texture[i].loadFromFile(Paths.get(path + (i+1) + ".png"));
				texture[i].setSmooth(true);
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
				player.setSprite(texture[i]);
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

