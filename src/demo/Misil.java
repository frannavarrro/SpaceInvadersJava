package demo;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Misil extends Rectangle implements Runnable{
	
	public Misil(int ancho_jugador, int x_jugador,int y_jugador) {
		
		ANCHO=ancho_jugador/13;
		ALTO=ANCHO*4;
		setSize(ANCHO,ALTO);
		setLocation(x_jugador+ancho_jugador/2-ANCHO/2,y_jugador-ALTO);

	}

	@Override
	public void run() {
		
		MainDemo.panel.removeKeyListener(PanelJuego.barra);

		while(getY()<PanelJuego.ALTO-ALTO && getY()>0) {
			
			try {
				
				Thread.sleep(15);
				
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			
			for(int i=0; i<PanelJuego.enemigos.length; i++) {
				
				if(PanelJuego.enemigos[i].intersects(this)) {
					
					PanelJuego.enemigos[i].setSize(0, 0);
					setSize(0,0);
					MainDemo.panel.addKeyListener(PanelJuego.barra);
						
					if(PanelJuego.enemigos[i].getId()==1) {
						
						PanelJuego.puntos+=10;
						PanelJuego.puntaje.setText("Puntaje: "+PanelJuego.puntos);
					}else if(PanelJuego.enemigos[i].getId()==2) {
						
						PanelJuego.puntos+=20;
						PanelJuego.puntaje.setText("Puntaje: "+PanelJuego.puntos);
					}else {
						
						PanelJuego.puntos+=30;
						PanelJuego.puntaje.setText("Puntaje: "+PanelJuego.puntos);
					}
					
					for(int j=0; j<Enemigos.delanteros.length; j++) {
						
						if(PanelJuego.enemigos[i].equals(Enemigos.delanteros[j]) && i>=11) {
							
							if(PanelJuego.enemigos[i-11].getWidth()>0)
							Enemigos.delanteros[j]=PanelJuego.enemigos[i-11];
							else if(i>=22 && PanelJuego.enemigos[i-22].getWidth()>0)
							Enemigos.delanteros[j]=PanelJuego.enemigos[i-22];
							else if(i>=33 && PanelJuego.enemigos[i-33].getWidth()>0)
							Enemigos.delanteros[j]=PanelJuego.enemigos[i-33];
							else if(i>=44 && PanelJuego.enemigos[i-44].getWidth()>0)
							Enemigos.delanteros[j]=PanelJuego.enemigos[i-44];

						}
					}
					
					PanelJuego.marcianos_muertos++;
					return;
				}
			}
			
			for(Escudos i:PanelJuego.escudos) {
				
				if(this.intersects(i)) {
					
					i.romper();
					setSize(0,0);
					MainDemo.panel.addKeyListener(PanelJuego.barra);
					return;
				}
			}
			
			this.translate(0,-7);
		}
		
		MainDemo.panel.addKeyListener(PanelJuego.barra);
		setSize(0,0);
		
	}
	
	private final int ANCHO;
	private final int ALTO;
	
}
