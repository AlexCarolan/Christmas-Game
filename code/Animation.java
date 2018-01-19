import java.nio.file.*;
import java.io.*;
import org.jsfml.graphics.*;

class Animation extends Thread
{
	private static Player player;
	private static final Texture[] texture = new Texture[4];
	
	
	public Animation(Player p, String path)
	{
		player = p;
		
		for(int i=0; i<4; i++)
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
	
	public void run()
  {
		while(true)
		{
			for(int i=0; i<4; i++)
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