package GUI;

import javax.swing.ImageIcon;

public class Pedina {
	
	int posizioneX;
	int posizioneY;
	int height;
	int width;
	ImageIcon immagine;
	
	public Pedina() {
		init();
		posizioneX = 0;
		posizioneY = 0;
		height = 50;
		width = 50;
	}
	
	public void init() {
		immagine = new ImageIcon("src\\main\\java\\GUI\\pedina.png");
	}

	public int getPosizioneX() {
		return posizioneX;
	}

	public void setPosizioneX(int posizioneX) {
		this.posizioneX = posizioneX;
	}

	public int getPosizioneY() {
		return posizioneY;
	}

	public void setPosizioneY(int posizioneY) {
		this.posizioneY = posizioneY;
	}

	public ImageIcon getImmagine() {
		return immagine;
	}

	public void setImmagine(ImageIcon immagine) {
		this.immagine = immagine;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	
}
