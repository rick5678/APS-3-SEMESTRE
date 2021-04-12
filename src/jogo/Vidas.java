package jogo;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Vidas {
	private Image imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	public Vidas(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;

	}
	public void LoadVida() {
		ImageIcon enemy = new ImageIcon("res\\vidas.png");
		imagem = enemy.getImage();
		this.largura = imagem.getWidth(null);
		this.altura = imagem.getHeight(null);
	}
	public void remove() {
		ImageIcon enemy = new ImageIcon("");
		imagem = enemy.getImage();

	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return imagem;
	}

	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getAltura() {
		return altura;
	}
	
}