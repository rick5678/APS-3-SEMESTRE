package jogo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Principal.Container;

public class Fase extends JPanel implements ActionListener {
	private Image fundo;
	private Player player;
	private Timer timer;
	private Timer time;
	private List<Enemy1> enemy1;
	private boolean emJogo;
	private List<Stars> stars;
	private List<Lampada> lampada;
	private Image fim;
	private Vidas vidas;
	private Vidas vida2;
	private List<Lata> lata;
	private int pontuacao;
	
	
	JLabel placar = new JLabel("Pontos: "+pontuacao);
	
	public Fase() {
		setFocusable(true);
		setDoubleBuffered(true);

		ImageIcon logo = new ImageIcon("res\\beco2.jpg");
		fundo = logo.getImage();
		
		pontuacao = 0;
		
		player = new Player();
		player.Load();
		vidas = new Vidas(10, 10);
		vidas.LoadVida();
		vida2 = new Vidas(30, 10);
		vida2.LoadVida();
		addKeyListener(new TecladoAdapter());

		timer = new Timer(5, this);
		timer.start();

		inicializaInimigos();
		inicializaStars();
		inicializaLampada();
		inicializaLata();
		

		emJogo = true;
	}

	public void inicializaLata() {
		int coordenadas[] = new int[40];
		lata = new ArrayList<Lata>();

		for (int m = 0; m < coordenadas.length; m++) {
			int x = (int) (Math.random() * 480 + 30);
			int y = (int) (Math.random() * -10000);
			lata.add(new Lata(x, y));
			
		}
	}

	public void inicializaInimigos() {
		int coordenadas[] = new int[30];
		enemy1 = new ArrayList<Enemy1>();

		for (int m = 0; m < coordenadas.length; m++) {
			int x = (int) (Math.random() * 480 + 30);
			int y = (int) (Math.random() * -10000);
			enemy1.add(new Enemy1(x, y));
		}
	}

	public void inicializaLampada() {
		int coordenadas[] = new int[40];
		lampada = new ArrayList<Lampada>();

		for (int m = 0; m < coordenadas.length; m++) {
			int x = (int) (Math.random() * 480 + 30);
			int y = (int) (Math.random() * -10000);
			lampada.add(new Lampada(x, y));
		}
	}

	public void inicializaStars() {
		int coordenadas[] = new int[50];
		stars = new ArrayList<Stars>();

		for (int i = 0; i < coordenadas.length; i++) {
			int x = (int) (Math.random() * 480 + 30);
			int y = (int) (Math.random() * -10000);
			stars.add(new Stars(x, y));
		}
	}

	public void paint(Graphics g) {
	
		Graphics2D graficos = (Graphics2D) g;
		if (emJogo == true) {
			graficos.drawImage(fundo, 0, 0, null);
			for (int p = 0; p < stars.size(); p++) {
				Stars q = stars.get(p);
				q.Load();
				graficos.drawImage(q.getImagem(), q.getX(), q.getY(), this);
			}
			graficos.drawImage(player.getImagem(), player.getX(), player.getY(), this);
			
			
			for (int j = 0; j < enemy1.size(); j++) {
				Enemy1 in = enemy1.get(j);
				in.Load();
				graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
			}
			for (int y = 0; y < lampada.size(); y++) {
				Lampada l = lampada.get(y);
				l.Load();
				graficos.drawImage(l.getImagem(), l.getX(), l.getY(), this);
			}
			for (int h = 0; h < lata.size(); h++) {
				Lata b = lata.get(h);
				b.Load();
				graficos.drawImage(b.getImagem(), b.getX(), b.getY(), this);
			}
			graficos.drawImage(vidas.getImagem(), vidas.getX(), vidas.getY(), this);
			graficos.drawImage(vida2.getImagem(), vida2.getX(), vida2.getY(), this);
			
		}
		
		if (emJogo == false) {
			ImageIcon referencia = new ImageIcon("res\\gameover.png");
			fim = referencia.getImage();
			graficos.drawImage(fim, 0, 0, null);
			
		}

		g.dispose();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player.Update();
		for (int j = 0; j < enemy1.size(); j++) {
			Enemy1 in = enemy1.get(j);
			if (in.isVisivel()) {
				in.update();
				if(in.isVisivel()==false) {
					enemy1.remove(j);
				}
			} else {
				enemy1.remove(j);
			}

		}
		for (int j = 0; j < lata.size(); j++) {
			Lata refri = lata.get(j);
			if (refri.isVisivel()) {
				refri.update();
				if(refri.isVisivel()==false) {
					lata.remove(j);
				}
			} else {
				lata.remove(j);
			}

		}
		for (int z = 0; z < lampada.size(); z++) {
			Lampada in = lampada.get(z);
			if (in.isVisivel()) {
				in.update();
				if(in.isVisivel()==false) {
					lampada.remove(z);
				}
			} else {
				lampada.remove(z);
			}

		}
		for (int m = 0; m < stars.size(); m++) {
			Stars in = stars.get(m);
			if (in.isVisivel()) {
				in.update();
				if(in.isVisivel()==false) {
					stars.remove(m);
				}
			} else {
				stars.remove(m);
			}

		}
		checarColisoes();
		repaint();
		if (emJogo ==false) {
			timer.stop();
		JOptionPane.showMessageDialog(null, "Sua pontuação foi:"+ pontuacao);
		}
	}

	public void checarColisoes() {
		Rectangle formaNave = player.getBounds();
		Rectangle formaEnemy1;
		Rectangle formaStars;
		Rectangle formaLampada;
		Rectangle formaLata;
		for (int i = 0; i < enemy1.size(); i++) {
			Enemy1 tempEnemy1 = enemy1.get(i);
			formaEnemy1 = tempEnemy1.getBounds();
			if (formaNave.intersects(formaEnemy1)) {
				tempEnemy1.setVisivel(false);
				pontuacao = pontuacao +10;
			}
		}
		for (int a = 0; a < stars.size(); a++) {
			Stars tempStars = stars.get(a);
			formaStars = tempStars.getBounds();
			if (formaNave.intersects(formaStars)) {
				tempStars.setVisivel(false);
				pontuacao = pontuacao +10;
			}
		}
		for (int p = 0; p < lampada.size(); p++) {
			Lampada tempLampada = lampada.get(p);
			formaLampada = tempLampada.getBounds();
			if (formaNave.intersects(formaLampada)) {
				if (vida2.isVisivel() == true) {
					tempLampada.setVisivel(false);
					vida2.remove();
					vida2.setVisivel(false);
				} else if (vidas.isVisivel() == true) {
					vidas.setVisivel(false);
					vidas.remove();
				} else {
					emJogo = false;

				}
			}
		}
		for (int a = 0; a < lata.size(); a++) {
			Lata tempLata = lata.get(a);
			formaLata = tempLata.getBounds();
			if (formaNave.intersects(formaLata)) {
				tempLata.setVisivel(false);
				pontuacao = pontuacao +10;
			}
		}
	}
	

	private class TecladoAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			player.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			player.keyReleased(e);
		}
	}
}
