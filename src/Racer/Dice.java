package Racer;

<<<<<<< HEAD
public class Dice {
	private int valorObtenido;
	final int cantDados = 2;

	public int getValorObtenido() {
		return valorObtenido;
	}

	public void setValorObtenido(int valorObtenido) {
		this.valorObtenido = valorObtenido;
	}
=======
import java.util.Random;

public class Dice {
	
	private int value;

	public Dice() {
		
	}
	
	public void diceRoll() {
		Random ran = new Random();
		this.setValue(ran.nextInt(6) + 1);
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

>>>>>>> 2e4ee7abc1ac8744898df979e0e9e4fac3379315
}
