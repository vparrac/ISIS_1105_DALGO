package ProblemaB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * Clase que soluciona el problema de inversión en la bolsa
 * @author Valerie Parra Cortés
 * @author Cristina Isabel Gonzalez Osorio
 */
public class ProblemaB {
	public static void main(String[] args) throws Exception{	
		int c, n, j;
		int[] a=new int[100];
		int[] b=new int[100];
		j=0;		
		StringBuilder rta= new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		String st; 	
		while (!(st = br.readLine()).equals("0 0")) {			
			String[] array=st.split(" ");
				n=Integer.parseInt(array[0]);
				c=Integer.parseInt(array[1]);			
			st=br.readLine();		
			array=st.split(" ");
				for (j = 0; j < array.length; j++) {
					a[j]=Integer.parseInt(array[j]);
				}			
			st=br.readLine();
			array=st.split(" ");
				for (j = 0; j < array.length; j++) {
					b[j]=Integer.parseInt(array[j]);
				}			
			for (j = 0; j <n; j++) {
				int q=(a[j]>b[j])?a[j]:b[j];
				q=(q>0)?q:0;
				c=c+c*q/100;			
			}
			rta.append(c+"\n");	
		}		
		System.out.println(rta);		
	} 
}