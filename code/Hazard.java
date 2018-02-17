/**
 * This class manages the display and movement of each platform that the player can stand on or run under/into
 */
import java.nio.file.*;
import java.io.*;

import org.jsfml.system.*;
import org.jsfml.graphics.*;

class Hazard extends Platform 
{
	private AnimatedHazard animation;

	/**
	 * Hazard constructor - creates a hazard. Can be run through, but inflicts damage when active
	 * @param xPosition - the left hand side of the hazard
	 * @param yPosition - the top of the hazard
	 * @param width - the width of the hazard
	 * @param height - the height of the hazard
	 * @param damageImage - the filename for the hazard image that inflicts damage
	 * @param noDamageImage - the filename for the hazard image that inflicts no damage
	 */
	public Hazard(int xPosition, int yPosition, int width, int height, String damageImage, String noDamageImage)
	{
		super(xPosition, yPosition, width, height, noDamageImage + "\\Image1.png", true);
	}
	
	/**
	 * Changes the current animation
	 * @param ani - the new animation
	 */
	public void setAnimation(AnimatedHazard ani)
	{
		if (animation != null)
		{
			animation.setActive(false);
		}
		animation = ani;
		animation.setPlatform(this);
		animation.setActive(true);
	}
}
 