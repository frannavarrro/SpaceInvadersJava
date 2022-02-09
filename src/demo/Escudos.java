package demo;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Escudos extends Rectangle{
	
	public Escudos(int x) {
		
		setSize(26,20);
		setLocation(x,700);
		durabilidad=4;
		
		try {
			
			sprite=ImageIO.read(getClass().getResourceAsStream("/sprites/escudo.png"));
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
	public BufferedImage getSprite() {
		
		return sprite;
	}
	
	public void romper() {
		
		try {
			
		durabilidad--;
			
		if(durabilidad==3) {
				
					
			sprite=ImageIO.read(getClass().getResourceAsStream("/sprites/escudo2.png"));		
			
		 }else if(durabilidad==2) {
			
			
		   sprite=ImageIO.read(getClass().getResourceAsStream("/sprites/escudo3.png"));					
	     }else if(durabilidad==1) {
	    	 
			   sprite=ImageIO.read(getClass().getResourceAsStream("/sprites/escudo4.png"));					
	     }else {
	    	 
	    	 setSize(0,0);
	     }
			
		}catch(Exception e) {}

	}

	private BufferedImage sprite;
	private int durabilidad;
}
