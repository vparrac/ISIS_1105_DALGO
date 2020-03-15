package ProblemaB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Clase que soluciona el problema de inversión en la bolsa
 * @author Valerie Parra Cortés
 * @author Cristina Isabel Gonzalez Osorio
 */
public class ProblemaB {
	public static void main(String[] args) throws IOException{	
		int c, n; 
		String[] a; //Variables auxiliares
		String[] b; //Variables auxiliares
		int i=0;		
		int max=0;
		StringBuilder rta= new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String st; 	
		while (!(st = br.readLine()).equals("0 0")) {
			String[] array=st.split(" ");
			n=Integer.parseInt(array[0]);
			c=Integer.parseInt(array[1]);			
			a=br.readLine().split(" ");			
			b=br.readLine().split(" ");
			int[] m=new int[3];
			int bolsaA=0;
			int bolsaB=0;
			int bolsaC=0;
			//Recurrencia
			m[0]=m[1]=m[2]=c;
			for (i = 0; i < n; i++) {
				bolsaA=m[0];
				bolsaB=m[1];
				bolsaC=m[2];
				//Para la bolsa A
				m[0]=(bolsaB+bolsaB*Integer.parseInt(a[i])/100>bolsaC+bolsaC*Integer.parseInt(a[i])/100)?bolsaB+bolsaB*Integer.parseInt(a[i])/100:bolsaC+bolsaC*Integer.parseInt(a[i])/100;
				//Para la bolsa B
				max=(bolsaA+bolsaA*Integer.parseInt(b[i])/100>bolsaB+bolsaB*Integer.parseInt(b[i])/100)?bolsaA+bolsaA*Integer.parseInt(b[i])/100:bolsaB+bolsaB*Integer.parseInt(b[i])/100;
				max=(max>bolsaC+bolsaC*Integer.parseInt(b[i])/100)?max:bolsaC+bolsaC*Integer.parseInt(b[i])/100;
				m[1]=max;
				//Para la bolsa C
				max=(bolsaA>bolsaB)?bolsaA:bolsaB;
				max=(max>bolsaC)?max:bolsaC;
				m[2]=max;
			}
			max=(m[0]>m[1])?m[0]:m[1];
			max=(max>m[2])?max:m[2];
			rta.append(max+"\n");
		}		
		System.out.println(rta);		
	} 
}
