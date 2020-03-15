package ProblemaB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * Clase que soluciona el problema B
 * @author Valerie Parra Cortés
 * @author Cristina Isabel Gonzalez Osorio
 */
public class ProblemaBv2 {
	/**
	 * Método main que soluciona el problema B
	 * @param args 
	 * @throws IOException si se produce un error de entrada salida
	 */
	
	public static void main(String[] args) throws IOException{	
		int c, n, j; 
		int[] a=new int[100]; //Variables auxiliares
		int[] b=new int[100]; //Variables auxiliares
		int i=0;
		int k=0;
		int max=0;
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

			int[][] m=new int[n+1][3];

			//Recurrencia
			for (i = 0; i < m.length; i++) {
				for (k = 0; k < m[0].length; k++) {
					if(i==0) {
						m[i][k]=c;
					}
					if(i!=0&&k==0) {
						m[i][k]=Math.max(m[i-1][1]+m[i-1][1]*a[i-1]/100, m[i-1][2]+m[i-1][2]*a[i-1]/100);
					}
					if(i!=0&&k==1) {
						max=((m[i-1][0]+m[i-1][0]*b[i-1]/100)>(m[i-1][1]+m[i-1][1]*b[i-1]/100))?(m[i-1][0]+m[i-1][0]*b[i-1]/100):m[i-1][1]+m[i-1][1]*b[i-1]/100;
						max=(max>(m[2][i-1]+m[2][i-1]*b[i-1]/100))?max:(m[2][i-1]+m[2][i-1]*b[i-1]/100);
						m[i][k]=max;
					}
					if(i!=0&&k==2){
						max=(m[i-1][0]>m[i-1][1])?m[i-1][0]:m[i-1][1];
						max=(max>m[i-1][2])?max:m[i-1][2];
						m[i][k]=max;
					}
				}
			}
			k=m.length-1;
			n=(m[k][0]>m[k][1])?m[k][0]:m[k][1];
			n=(n>m[k][2])?n:m[k][2];
			rta.append(n+"\n");	
		}		
		System.out.println(rta);		
	} 
}
