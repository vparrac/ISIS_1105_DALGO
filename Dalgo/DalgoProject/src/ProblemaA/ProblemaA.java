package ProblemaA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProblemaA {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Leer entrada
		String st; 	//Variable auxiiar
		int numero;
		StringBuilder rta= new StringBuilder();		
		while (!(st = br.readLine()).equals("0")) {
			numero= Integer.parseInt(st);
			st=br.readLine();
			String[] b = st.split(" ");
			int longitudHastai=1;
			int longitudMaxima=1;
			int decrecei=0;
			int numeroi=0;
			int anterior=Integer.parseInt(b[0]);	
			int ultimoDecrecimiento=0;
			for (int i = 1; i < b.length; i++) {				
				numeroi=Integer.parseInt(b[i]);
				if(!(numeroi>=anterior)) {
					decrecei++;
					if(decrecei==1) {
						ultimoDecrecimiento=i;
					}
				}		
				if(decrecei<=1) { //Sigue existiendo un arreglo casi creciente
					longitudHastai++;
				}
				else {
					longitudHastai=1;
					decrecei=0;				
					numeroi=anterior;	
					i=ultimoDecrecimiento;					
					anterior=Integer.parseInt(b[ultimoDecrecimiento]);
					ultimoDecrecimiento=-1;
				
				}
				if(longitudHastai>=longitudMaxima) {
					longitudMaxima=longitudHastai;
				}
				if(ultimoDecrecimiento!=-1) {
				anterior=numeroi;
				}
			}
			rta.append(longitudMaxima+"\n");
		}
		System.out.println(rta);
	}
}
