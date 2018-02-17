/**
 * This class animates a hazard in the game
 */
import java.nio.file.*;
import java.io.*;
import org.jsfml.graphics.*;
import java.util.ArrayList;

class AnimatedHazard extends Thread
{
	private Platform platform;	// the hazard item
	private Texture[] texture;
	private int numNoDamageFrames;
	private int numDamageFrames;
	private int interval;
	private boolean active = false;
	private boolean alive = true;
	private boolean damaging = false;
	
	/**
	 * AnimatedHazard constructor - creates the images for the animation
	 * @param noDamagePath - the file path to the images for no damage
	 * @param noDamageFrames - the number of frames in the no damage animation
	 * @param damagePath - the file path to the images for damage
	 * @param damageFrames - the number of frames in the damage animation
	 * @param delay - the delay between each frame of the animation in milliseconds
	 */
	public AnimatedHazard(String noDamagePath, int noDamageFrames, String damagePath, int damageFrames, int delay)
	{
		numNoDamageFrames = noDamageFrames;
		numDamageFrames = damageFrames;
		texture = new Texture[noDamageFrames + damageFrames];
		interval = delay;
		
		for (int i = 0; i < noDamageFrames; i++)
		{
			texture[i] = new Texture();
			try {
				// try to load the texture from file
				texture[i].loadFromFile(Paths.get(noDamagePath + "\\Image" + (i+1) + ".png"));
				texture[i].setSmooth(true);
			} catch (IOException ex) {
				System.out.println("Unable to open image file");
			}
		}
		for (int i = 0; i < noDamageFrames; i++)
		{
			texture[noDamageFrames + i] = new Texture();
			try {
				// try to load the texture from file
				texture[noDamageFrames + i].loadFromFile(Paths.get(damagePath + "\\Image" + (i+1) + ".png"));
				texture[noDamageFrames + i].setSmooth(true);
			} catch (IOException ex) {
				System.out.println("Unable to open image file");
			}
		}
		this.start();
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
				platform.setSprite(texture[i]);
				//System.out.println("ALIVE");
				if (++i == texture.length)
				{
					damaging = false;
					i = 0;
				}
				else if (i >= numNoDamageFrames)
					damaging = true;
			}
		}
	}
	
	/**
	 * Damaging - returns true if the animation is currently damaging
	 * @return boolean - true if hazard is currently damaging
	 */
	public boolean Damaging()
	{
		return damaging;
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
	
	/**
	* sets the hazard to be animated
	*/
	public void setPlatform(Platform p)
	{
		platform = p;
	}
}
