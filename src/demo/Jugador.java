package demo;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Jugador extends Rectangle{
	
	public Jugador() {
		
		ANCHO=PanelJuego.ANCHO/14;
		ALTO=ANCHO-26;
		setSize(ANCHO,ALTO);
		setLocation(PanelJuego.ANCHO/2-ANCHO/2,PanelJuego.ALTO-ALTO);
		vidas=3;
		
		try {
			
			sprite=ImageIO.read(getClass().getResourceAsStream("/sprites/jugador.gif"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	public int getAncho() {
		
		return ANCHO;
		
	}
	
	public int getAlto() {
		
		return ALTO;
		
	}
	
	public BufferedImage getSprite() {
		
		return sprite;
	}
	
	private final int ANCHO,ALTO;
	private BufferedImage sprite;
	public static int vidas;
}
