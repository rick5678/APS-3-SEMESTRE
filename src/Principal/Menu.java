package Principal;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;



public class Menu extends JFrame {
	private Image inicio;
	private static Container algo;
	
	public Menu() {
		setTitle("Save the planet");
		setSize(560, 580);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		ImageIcon icone = new ImageIcon("res\\iconee.png");
		setIconImage(icone.getImage());
		ImageIcon play = new ImageIcon("res\\menu.png");
		inicio = play.getImage();
		this.setResizable(false);
		setVisible(true);
		addKeyListener(new TecladoAdapter());
	}

	public void paint(Graphics g) {
		Graphics2D graficos = (Graphics2D) g;
		graficos.drawImage(inicio, 0, 0, null);
		g.dispose();
	}

	public static void main(String[] args) {
		 algo = new Container();
	}

	private class TecladoAdapter extends KeyAdapter {
		@Override

		public void keyPressed(KeyEvent tecla) {
			int codigo = tecla.getKeyCode();
			if (codigo == KeyEvent.VK_ENTER) {
				setVisible(false);
				algo.abrir();
				// dispose();

			}
		}
	}
}
