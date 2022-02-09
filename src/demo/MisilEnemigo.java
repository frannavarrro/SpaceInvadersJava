package demo;
  
import java.awt.Rectangle;

import javax.swing.JOptionPane;

public class MisilEnemigo extends Rectangle implements Runnable{
	
	public MisilEnemigo(int ancho_enemigo, int x_enemigo,int y_enemigo) {
		
		ANCHO=ancho_enemigo/8;
		ALTO=ANCHO*3;
		setSize(ANCHO,ALTO);
		setLocation(x_enemigo+ancho_enemigo/2-ANCHO/2,y_enemigo+ALTO);

	}

	@Override
	public void run() {
		
		while(getY()<PanelJuego.ALTO-ALTO && getY()>0) {
			
			try {
				
				Thread.sleep(17);
				
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
			
			if(this.intersects(PanelJuego.jugador)) {
				
				PanelJuego.misiles_enemigo.poll();
				Jugador.vidas--;
				
                   if(Jugador.vidas==0){
                	   
                	   PanelJuego.running=false;
				}
				
				PanelJuego.jugador.setLocation(PanelJuego.ANCHO/2-PanelJuego.jugador.getAncho()/2,PanelJuego.ALTO-PanelJuego.jugador.getAlto());
				return;
				
			}
			
			for(Escudos i:PanelJuego.escudos) {
				
				if(this.intersects(i)) {
					
					i.romper();
					PanelJuego.misiles_enemigo.poll();
					return;
				}
			}
			
			this.translate(0,7);
		}
		
		PanelJuego.misiles_enemigo.poll();
	}
	
	public int getAncho(){
		
		return ANCHO;
	}
	
    public int getAlto(){
    	
    	return ALTO;
    }
	
	private final int ANCHO;
	private final int ALTO;

}
