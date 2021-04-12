package jogo;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Lata {

	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	private static final int LARGURA = 500;
	private static int VELOCIDADE = 2;
	
	public Lata(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
	}
	public void Load() {
		ImageIcon enemy = new ImageIcon("res\\lata.png");
		imagem = enemy.getImage();
		
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	public void update() {
		
		this.y += VELOCIDADE;
		if (this.y > LARGURA) {
			isVisivel = false;
		}
	
	}
	public Rectangle getBounds() {
		return new Rectangle (x,y,largura,altura);
	}
	public boolean isVisivel() {
		return isVisivel;
	}
	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}
	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}
	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
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
	
}