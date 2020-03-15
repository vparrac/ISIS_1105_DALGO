package CoinChange;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
public class GeneradorDenominaciones {
	public static void main(String[] args) {
		PrintStream out;
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			String numeros="";
			out = new PrintStream("data/paraProbar1000.txt");
			for (int i =0; i<5;i++) {
				int numero = (int) (Math.random()*1000);
				if(!list.contains(numero) & numero != 0) {
					list.add(numero);
					String caracter = (i==4)?"":",";
					numeros+=numero+caracter;
					System.out.println(i);
				}
				else {
					i--;
				}
			}
			System.out.print("Aquí");
			out.println(numeros);
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		}
	}
}


