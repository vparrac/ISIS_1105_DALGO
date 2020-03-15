package ProblemaC;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


public class permutar {
	public static void main(String[] args) {
		ArrayList<Integer> numeros = new ArrayList<>();
		numeros.add(1);
		numeros.add(2);
		numeros.add(4);
		int numero=2;		
		
		ArrayList<ArrayList<Integer>> permutaciones = new ArrayList<>();
		Queue<Integer> cola = new LinkedList<>();
		
		int numeroAExtraer=numeros.size()-numero+1;
		ArrayList<Integer> subset = new ArrayList<>();
		int i;
		for( i=0; i<=numeros.size()-numero; i++) {
			cola.add(numeros.get(i));
			subset.add(numeros.get(i));			
		}
		
		subset.remove(subset.size()-1);
		Queue<Integer> cola2 = new LinkedList<>();
		cola2.addAll(cola);
		
		
		

		while(numero>0) {
			
			int[] numerosExtraidos=  new int[numero];
			int j=0;

			for (int k = numeros.size()-numero; k < numeros.size(); k++) {
				numerosExtraidos[j]=numeros.get(k);
				j++;
			}
			
			for (int k = 0; k < numerosExtraidos.length; k++) {
				ArrayList<Integer> permutacionActual = new ArrayList<>();
				permutacionActual.addAll(subset);
				permutacionActual.add(numerosExtraidos[k]);
				permutaciones.add(permutacionActual);
			}
			
			if(!cola.isEmpty()) {
			numeros.set(numeroAExtraer, cola.poll());
			}
			else {		
				cola=cola2;
				cola2.addAll(cola);				
			}			
		}
		for (int k = 0; i<permutaciones.size(); i++) {
			ArrayList<Integer> permutacionActual = permutaciones.get(i);
			System.out.println("------------------------------------");
			String rta = "";
			for (int j = 0; j < permutacionActual.size(); j++) {
				rta+=permutacionActual.get(i)+"*";
			}
			System.out.println("--------------------------------");
		}
	}
}
