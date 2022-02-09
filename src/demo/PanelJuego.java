package demo;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PanelJuego extends JPanel{
	
	public PanelJuego() {
		
		running=true;
		setBackground(Color.BLACK);
		Dimension area=new Dimension(ANCHO,ALTO);
		setPreferredSize(area);
		setFocusable(true);
		addKeyListener(new Inputs());
		barra=new BarraEspaciadora();
		addKeyListener(barra);
		Box cajaH=Box.createHorizontalBox();
		puntaje=new JLabel();
		puntaje.setForeground(Color.white);
		puntaje.setFont(new Font("Franklin Gothic Heavy",Font.PLAIN,30));
		cajaH.add(puntaje);
		cajaH.add(Box.createHorizontalStrut(400));
		add(cajaH);

		hilo_juego=new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				while(true) {
					
					repaint();
					
					try {
						
						Thread.sleep(16);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
					}
				}
			}
			
		});
		
		iniciar(0,true);
		hilo_juego.start();

	}
	
	@Override
	public void paintComponent(Graphics g) {
					
			super.paintComponent(g);
			Graphics2D g2=(Graphics2D)g;
			
			if(running && marcianos_muertos<55) {
				
				try {
					
					if(Jugador.vidas==3)
					imagen_vidas=ImageIO.read(getClass().getResourceAsStream("/sprites/3_vidas.gif"));
					else if(Jugador.vidas==2)
					imagen_vidas=ImageIO.read(getClass().getResourceAsStream("/sprites/2_vidas.gif"));
					else if(Jugador.vidas==1)
				    imagen_vidas=ImageIO.read(getClass().getResourceAsStream("/sprites/1_vidas.gif"));
					
					g2.drawImage(imagen_vidas, 400, 10, null);
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
				
				for(Escudos i:escudos) {
					
					if(i.getWidth()>0)
					g2.drawImage(i.getSprite(), i.x, i.y, null);
				}
				
				g2.drawImage(jugador.getSprite(),jugador.x,jugador.y,null);
				
				for(Enemigos i: enemigos) {
					
					if(i.getWidth()!=0) {
						
						g2.drawImage(i.getSprite(),i.x,i.y, null);
					}
				}
				
				try {
					
					g2.setPaint(Color.green);
					g2.fill(misil);
					
				}catch(Exception e) {}
				
				try {
					
					g2.setColor(Color.red);
					
					for(MisilEnemigo i: misiles_enemigo) {
						
						g2.fill(i);
					}
					
				}catch(Exception e) {}
				
			}else {
									
					if(marcianos_muertos<55) {
						
						try {
							
							hilo_enemigos.stop();
							imagen_vidas=ImageIO.read(getClass().getResourceAsStream("/sprites/0_vidas.gif"));
							g2.drawImage(imagen_vidas, 400, 10, null);
							g2.setColor(Color.RED);
							g2.setFont(new Font("Franklin Gothic Heavy",Font.PLAIN,70));
							g2.drawString("GAME OVER",160, ALTO/2);

						} catch (IOException e) {}
					}else {
						
						hilo_enemigos.stop();
						iniciar(puntos,false);
					}
				
			}
	}
	
	public static void iniciar(int puntos_actuales, boolean escudos_nuevos) {
		
		marcianos_muertos=0;
		
		if(escudos_nuevos) {
			
			escudos=new Escudos[] {
					
					new Escudos(86),
					new Escudos(112),
					new Escudos(138),
					new Escudos(164),
					
					new Escudos(296),
					new Escudos(322),
					new Escudos(348),
					new Escudos(374),
					
					new Escudos(506),
					new Escudos(532),
					new Escudos(558),
					new Escudos(584)
				};
		}
		
		misiles_enemigo=new LinkedList<>();
		puntos=puntos_actuales;
		puntaje.setText("Puntaje: "+puntos);
		jugador=new Jugador();
		int fila=80;
		int columna=120;
		
		for(int i=0; i<11; i++) {
			
			enemigos[i]=new Enemigos("/sprites/alien3.png",fila,columna,3);
			fila+=50;
		}
		
		columna+=50;
		fila=80;
		
		for(int i=11; i<22; i++) {
			
			enemigos[i]=new Enemigos("/sprites/alien2.png",fila,columna,2);
			fila+=50;
		}
		
		columna+=50;
		fila=80;
		
		for(int i=22; i<33; i++) {
			
			enemigos[i]=new Enemigos("/sprites/alien2.png",fila,columna,2);
			fila+=50;
		}
		
		columna+=50;
		fila=80;
		
		for(int i=33; i<44; i++) {
			
			enemigos[i]=new Enemigos("/sprites/alien.png",fila,columna,1);
			fila+=50;
		}
		
		columna+=50;
		fila=80;
		
		for(int i=44; i<55; i++) {
			
			enemigos[i]=new Enemigos("/sprites/alien.png",fila,columna,1);
			fila+=50;
		}
		
		Enemigos.delanteros=new Enemigos[] {
				
				enemigos[44],
				enemigos[45],
				enemigos[46],
				enemigos[47],
				enemigos[48],
				enemigos[49],
				enemigos[50],
				enemigos[51],
				enemigos[52],
				enemigos[53],
				enemigos[54],
		};
		
		Enemigos.animarEnemigos();
	}
	
	private class Inputs extends KeyAdapter{
		
		@Override
		public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_LEFT && jugador.x>0) {
					
					jugador.translate(-14,0);
					
				}else if(e.getKeyCode()==KeyEvent.VK_RIGHT && jugador.x<ANCHO-jugador.getAncho()) {
					
					jugador.translate(14,0);
				}
		}
	}
	
	private class BarraEspaciadora extends KeyAdapter{
		
		@Override
		public void keyReleased(KeyEvent e) {
			
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {
				
				if(running) {
					
					misil=new Misil(jugador.getAncho(),(int)jugador.getX(),(int)jugador.getY());
					Thread hilo_misil=new Thread(misil);
					hilo_misil.start();
					
				}else {
					
					running=true;
					iniciar(0,true);
				}
				
			}
		}
	}
		
	public final static int ANCHO=700; //700
	public final static int ALTO=800;  //800
	private Misil misil;
	public static Thread hilo_enemigos;
	public static LinkedList<MisilEnemigo> misiles_enemigo;
	public static Jugador jugador;
	public static BarraEspaciadora barra;
	public static Enemigos[] enemigos=new Enemigos[55];
	public static JLabel puntaje;
	private BufferedImage imagen_vidas;
	public static int puntos;
	public static Thread hilo_juego;
    public static Escudos[] escudos;
    public static boolean running;
    public static int marcianos_muertos;
}
