package uniandes.algorithms.sorting;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class generadorNumeros {
	public static void main(String[] args) {
		PrintStream out;
		try {
			out = new PrintStream("data/paraOrdenar10000.txt");
			for (int i =0; i<10000;i++) {
			Double numero = Math.random()*2000000;
			out.println(numero);
			}
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		}
	}
}


