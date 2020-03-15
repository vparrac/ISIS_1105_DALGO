package ProblemaC;

import java.util.ArrayList;


public class permutaciones3 {
	public static void main(String[] args) {
		ArrayList<Integer> numeros = new ArrayList<>();
		ArrayList<ArrayList<Integer>> subsets= new ArrayList<>();
		
		
		numeros.add(10);		
		numeros.add(12);
		numeros.add(14);
		numeros.add(16);
		numeros.add(27);
		int numero = 5;

		int[] indicadores= new int[numero];
		for (int i = 0; i < indicadores.length; i++) {
			indicadores[i]=i;
		}
		int ultimaPos=indicadores.length-2;	
		boolean fin =false;
		
		while(!fin) {	
			
			ArrayList<Integer> subset= new ArrayList<>(indicadores.length);
			for (int i = 0; i < indicadores.length; i++) {				
				subset.add(numeros.get(indicadores[i]));				
			}
			subsets.add(subset);
			
			indicadores[indicadores.length-1]=indicadores[indicadores.length-1]+1;
			
			if(indicadores[indicadores.length-1]==numeros.size()) {
				while(true) {		
					if(ultimaPos==-0) {							
						fin=true;
						break;
					}
					if(indicadores[ultimaPos]+1==numeros.size()) {
						ultimaPos--;						
					}
					else {
						indicadores[ultimaPos]=indicadores[ultimaPos]+1;
						for (int i = ultimaPos+1; i < indicadores.length; i++) {
							indicadores[i]=indicadores[i-1]+1;
						}
						if(indicadores[indicadores.length-1]>=numeros.size()) {
							ultimaPos--;
						}
						else {
							break;
						}
						
					}
				}
			}
		}
		for (int i = 0; i < subsets.size(); i++) {
			System.out.println("---------------------------");
			ArrayList<Integer> subact= subsets.get(i);
			for (int j = 0; j < subact.size(); j++) {
				System.out.println(subact.get(j));
			}
		}
	}

}
