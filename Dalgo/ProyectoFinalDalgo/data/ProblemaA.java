package ProblemaA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Clase que busca linealmente el arreglo casi ascendente más largo
 * @author Valerie Parra Cortés
 * @author Cristina Isabel Gonzalez Osorio
 */
public class ProblemaA {
	/**
	 * Lee las entradas y escribe las salidas 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Leer entrada
		String st; 	//Variable auxiiar
		StringBuilder rta= new StringBuilder();		
		while (!(st = br.readLine()).equals("0")) {			
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
					longitudHastai=i-ultimoDecrecimiento+1;
					decrecei=1;
					anterior=numeroi;
					ultimoDecrecimiento=i;
					if(longitudHastai>=longitudMaxima) {
						longitudMaxima=longitudHastai;
					}
					continue;
				}
				if(longitudHastai>=longitudMaxima) {
					longitudMaxima=longitudHastai;
				}
				
				anterior=numeroi;
			}
			rta.append(longitudMaxima+"\n");
		}
		System.out.println(rta);
	}
}