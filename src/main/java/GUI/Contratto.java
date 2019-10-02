package GUI;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import Gioco.Main;

public class Contratto {
	
	int posizioneX;
	int posizioneY;
	int height;
	int width;
	boolean ipotecato;
	
	Image image;
	
	public Contratto(String nome)  {
		init(nome);
		posizioneX = 0;
		posizioneY = 0;
		height = 39;
		width = 82;
	}
	
	private void init(String nome)  {
		String path = "/images/" + nome + ".png";
		try {
			URL url = Main.class.getResource(path);
			if (url == null)  {
				return;
			}
			image = ImageIO.read(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getPosizioneX() {
		return posizioneX;
	}

	public void setPosizioneX(int posizioneX) {
		this.posizioneX = posizioneX;
	}
	
	public void setPosizioneXY(int x, int y)  {
		this.posizioneX = x;
		this.posizioneY = y;
	}

	public int getPosizioneY() {
		return posizioneY;
	}

	public void setPosizioneY(int posizioneY) {
		this.posizioneY = posizioneY;
	}

	public Image getImg() {
		return this.image;
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
