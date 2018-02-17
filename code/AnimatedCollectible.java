/**
 * This class animates a platform (or collectible) in the game
 */
import java.nio.file.*;
import java.io.*;
import org.jsfml.graphics.*;
import java.util.ArrayList;

class AnimatedCollectible extends Thread
{
	private Platform collectible;
	private Texture[] texture;
	private int interval;
	private boolean active = false;
	private boolean alive = true;
	
	/**
	 * AnimatedCollectible constructor - creates the images for the animation
	 * @param path - the file path to the images
	 * @param frames - the number of frames in the animation
	 * @param delay - the delay between each frame of the animation in milliseconds
	 */
	public AnimatedCollectible(String path, int frames, int delay)
	{
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
		this.start();
	}
	
	/**
	* run - starts the thread causing the frames to cycle at regular intervals
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
				collectible.setSprite(texture[i]);
				//System.out.println("ALIVE");
				if (++i == texture.length)
					i = 0;
			}
		}
	}
	
	/**
	 * setActive - allows the animation to be activated or halted
	 * @param state - the true/false value that states if the animation is active
	 */
	public void setActive(boolean state)
	{
		active = state;
	}

	/**
	 * getActive - returns whether the animation is active
	 * @return boolean, true if the animation is currently active
	 */
	public boolean getActive()
	{
		return active;
	}
	
	/**
	* kill - ends the thread
	*/
	public void kill()
	{
		alive = false;
	}
	
	/**
	* setCollectible - sets the platform/collectible to be animated
	*/
	public void setCollectible(Platform col)
	{
		collectible = col;
	}
}
