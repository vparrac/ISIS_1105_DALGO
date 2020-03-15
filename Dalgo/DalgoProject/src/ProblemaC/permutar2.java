package ProblemaC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;


public class permutar2 {
	public static void main(String[] args) {
		ArrayList<Integer> numeros = new ArrayList<>();
		numeros.add(1);
		numeros.add(2);
		numeros.add(3);
		numeros.add(4);
		numeros.add(5);
		numeros.add(6);
		numeros.add(7);
		numeros.add(9);
		int numero=4;
		
		
	//	subArreglo.addAll(numeros);
		int inicioCantidadARemover=1;
		if(numero<4) {
			permutaciones(numeros, numero);
		}
//		else {
//			while(subArreglo.size()>numero) {
//
//			}	
//		}

	}
	public static void permutaciones(ArrayList<Integer> numeros, int numero) {		

		ArrayList<ArrayList<Integer>> permutaciones = new ArrayList<>();		
		Queue<Integer> cola = new LinkedList<>();	

		ArrayList<Integer> subset = new ArrayList<>();
		int i;
		for( i=0; i<numero-1; i++) {			
			subset.add(numeros.get(i));			
		}		

		int contador=subset.size()-1;
		for (int h=i;h<numeros.size();h++) {
			cola.add(numeros.get(h));
		}

		Queue<Integer> cola2 = new LinkedList<>();
		cola2.addAll(cola);


		boolean fin=true;
		int cantidadNumeroAExtraer=numeros.size()-numero+1;
		int cantidadNumeroAExtraerCopia=cantidadNumeroAExtraer--;
		while(fin) {
			if(numero==numeros.size()) {
				permutaciones.add(numeros);
				break;
			}

			int[] numerosExtraidos=  new int[cantidadNumeroAExtraer];
			int j=0;

			for (int k = numeros.size()-cantidadNumeroAExtraer; k < numeros.size(); k++) {
				numerosExtraidos[j]=numeros.get(k);
				j++;
			}

			for (int k = 0; k < numerosExtraidos.length; k++) {
				ArrayList<Integer> permutacionActual = new ArrayList<>();
				permutacionActual.addAll(subset);
				permutacionActual.add(numerosExtraidos[k]);
				permutaciones.add(permutacionActual);
			}
			cantidadNumeroAExtraer--;
			if(!cola.isEmpty()&&cantidadNumeroAExtraer!=0&&contador!=0) {				
				subset.set(subset.size()-1, cola.poll());				
			}
			else{			
				
				fin=false;
			}		

		}
		for (int k = 0; k<permutaciones.size(); k++) {
			ArrayList<Integer> permutacionActual = permutaciones.get(k);
			System.out.println("------------------------------------");
			String rta = "";
			for (int j = 0; j < permutacionActual.size(); j++) {
				System.out.println(permutacionActual.get(j));
			}
			System.out.println("--------------------------------");
		}

	}
}

