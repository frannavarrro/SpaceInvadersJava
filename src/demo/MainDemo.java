package demo;

import javax.swing.*;

public class MainDemo {

	public static void main(String[] args) {

		JFrame ventana=new JFrame();
		panel=new PanelJuego();
		ventana.add(panel);
		ventana.pack();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setResizable(false);
		ventana.setTitle("Space Invaders");
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
	
	public static PanelJuego panel;

}
