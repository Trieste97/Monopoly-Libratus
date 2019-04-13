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
		dado1 = random.nextInt(6) + 1;
		dado2 = random.nextInt(6) + 1;
		if (dado1 == dado2) {
			this.setDoppioNumero(true);
		}
		return dado1 + dado2;
	}

	public boolean isDoppioNumero() {
		return doppioNumero;
	}

	public void setDoppioNumero(boolean doppioNumero) {
		this.doppioNumero = doppioNumero;
	}

	public void stampa() {
		System.out.println(this.dado1 + " + " + this.dado2);
	}
	
	@Override
	public String toString()  {
		return String.valueOf(dado1) + "+" + String.valueOf(dado2) + " = " + String.valueOf(dado1+dado2);
	}
}
