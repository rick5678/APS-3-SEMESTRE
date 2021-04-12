package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Player implements ActionListener{
	private int x, y;
	private int dx, dy;
	private Image imagem;
	private int altura, largura;
	private boolean isVisivel;
	private Timer timer;

	private static final int LARGURA = 485;
	private static final int LARGURA2 = 1;
	
	public Player() {
		this.x = 100;
		this.y = 450;
		isVisivel = true;
	}


	public void Load() {
		ImageIcon referencia = new ImageIcon("res\\player.png");
		imagem = referencia.getImage();
		altura = imagem.getHeight(null);
		largura = imagem.getWidth(null);
	}

	public void Update() {
		x += dx;
		
		if (this.x > LARGURA) {
			this.x = 485;
		}
		if (this.x < LARGURA2) {
			this.x = 1;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public void keyPressed(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		if (codigo == KeyEvent.VK_LEFT) {
			dx = -5;
		}
		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 5;
		}

	}

	public void keyReleased(KeyEvent tecla) {
		int codigo = tecla.getKeyCode();
		if (codigo == KeyEvent.VK_LEFT) {
			dx = 0;
		}
		if (codigo == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImagem() {
		return imagem;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
