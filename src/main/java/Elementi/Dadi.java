package Elementi;

import java.util.Random;

public class Dadi {
	
	private boolean doppioNumero;
	private int dado1;
	private int dado2;
	
	
	public Dadi() {
		this.setDoppioNumero(false);
	}

	public int tiraDadi() {
		
		this.setDoppioNumero(false);
		Random random = new Random();
		setDado1(random.nextInt(6) + 1);
		setDado2(random.nextInt(6) + 1);
		if (getDado1() == getDado2()) {
			this.setDoppioNumero(true);
		}
		return getDado1() + getDado2();
	}

	public boolean isDoppioNumero() {
		return doppioNumero;
	}

	public void setDoppioNumero(boolean doppioNumero) {
		this.doppioNumero = doppioNumero;
	}

	public void stampa() {
		System.out.println(this.getDado1() + " + " + this.getDado2());
	}
	
	@Override
	public String toString()  {
		return String.valueOf(getDado1()) + "+" + String.valueOf(getDado2()) + " = " + String.valueOf(getDado1()+getDado2());
	}

	public int getDado1() {
		return dado1;
	}

	public void setDado1(int dado1) {
		this.dado1 = dado1;
	}

	public int getDado2() {
		return dado2;
	}

	public void setDado2(int dado2) {
		this.dado2 = dado2;
	}
}
