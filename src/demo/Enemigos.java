package demo;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Enemigos extends Rectangle{
	
	public Enemigos(String ruta, int x, int y,int id) {
		
		final int ANCHO=PanelJuego.ANCHO/20;
		final int ALTO=ANCHO;
        setLocation(x,y);
        setSize(ANCHO,ALTO);
        this.id=id;
        
		try {
			
			sprite=ImageIO.read(getClass().getResourceAsStream(ruta));
			
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
	}
	
	public static void animarEnemigos() {
		
		PanelJuego.hilo_enemigos=new Thread(new Runnable() {

			@Override
			public void run() {
				
				int direccion=10;
				int delay=750;
				int delay_misil=650;
				
				while(true) {
					
					try {
						Thread.sleep(delay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					Random random=new Random();
					
					int numero=random.nextInt(delay_misil+1);
					
					if(numero<=75) {
						
						if(numero<=7) {
							
							delanteros[0].disparar();
							
						}else if(numero<=14) {
							
							delanteros[1].disparar();
							
						}else if(numero<=21) {
							
							delanteros[2].disparar();
							
						}else if(numero<=28) {
							
							delanteros[3].disparar();
							
						}else if(numero<=35) {
							
							delanteros[4].disparar();
							
						}else if(numero<=42) {
							
							delanteros[5].disparar();
							
						}else if(numero<=49) {
							
							delanteros[6].disparar();
							
						}else if(numero<=56) {
							
							delanteros[7].disparar();
							
						}else if(numero<=63) {
							
							delanteros[8].disparar();
							
						}else if(numero<=70) {
							
							delanteros[9].disparar();
							
						}else if(numero<=75) {
							
							delanteros[10].disparar();
							
						}
					}

					if(PanelJuego.enemigos[10].x==660 && direccion==10) {
						
						direccion=-10;
						if(delay>100)delay-=45; //50
						if(delay_misil>250)delay_misil-=50;
						
						for(Enemigos i: PanelJuego.enemigos) {
							
							i.translate(0, 25);
						}
						
					}else if(PanelJuego.enemigos[0].x==0 && direccion==-10) {
						
						direccion=10;
						if(delay>100)delay-=45; //50
						if(delay_misil>250)delay_misil-=50;
						
						for(Enemigos i: PanelJuego.enemigos) {
							
							i.translate(0, 25);
						}
						
					}else {
						
						for(Enemigos i:PanelJuego.enemigos) {
							
							i.translate(direccion, 0);
						}
					}
					
					for(Enemigos i: PanelJuego.enemigos) {
						
						if(i.getY()>=PanelJuego.jugador.getY() && i.getWidth()>0) {
							
							PanelJuego.running=false;
						}
					}
										
				}
				
			}
			
			
		});
		
		PanelJuego.hilo_enemigos.start();
	}
	
	public BufferedImage getSprite() {
		
		return sprite;
	}
	
	public int getId() {
		
		return id;
	}
	
	private void disparar() {
		
		MisilEnemigo misil_malo=new MisilEnemigo(this.width,this.x,this.y);
		PanelJuego.misiles_enemigo.offer(misil_malo);
        Thread hilo_misil_enemigo=new Thread(misil_malo);
        hilo_misil_enemigo.start();
	}

	private BufferedImage sprite;
	private int id;
	public static Enemigos[] delanteros;

}
