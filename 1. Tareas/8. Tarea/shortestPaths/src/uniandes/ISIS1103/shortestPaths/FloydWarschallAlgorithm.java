package uniandes.ISIS1103.shortestPaths;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase que implementa el algoritmo de Floy-Warschall
 * @author Valerie Parra Cortés
 *
 */
public class FloydWarschallAlgorithm implements ShortestPathAlgorithm{
	@Override
	public int[][] shortestPaths(String fileName) throws IOException {
		String line=null;
		int[][] arrayOfnumbers=null;
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		//Caso base
		line=bufferedReader.readLine();
		String [] numbers=line.split("\t");
		arrayOfnumbers= new int[numbers.length][numbers.length];

		for (int i = 0; i < numbers.length; i++) {
			String string = numbers[i];
			arrayOfnumbers[0][i]=Integer.parseInt(string);			
		}
		int j=1;
		while((line = bufferedReader.readLine()) != null) {
			numbers=line.split("\t");
			for (int i = 0; i < numbers.length; i++) {
				String string = numbers[i];
				arrayOfnumbers[j][i]=Integer.parseInt(string);			
			}
			j++;
		}   
		bufferedReader.close();     
		int[][] ans= new int[arrayOfnumbers.length][arrayOfnumbers.length];
		int [][][] graph = new int[arrayOfnumbers.length][arrayOfnumbers.length][arrayOfnumbers.length];
		//Base case
		for (int i = 0; i < graph.length; i++) {
			for (int j1 = 0; j1 < graph.length; j1++) {
				graph[i][j1][0]= (int) ((arrayOfnumbers[i][j1]==-1)?100000000:arrayOfnumbers[i][j1]);
			}			
		}		
		int k=1;
		//Recursive case
		for (int i = 0; i < graph.length; i++) {
			for (int j1 = 0; j1 < graph.length; j1++) {
				int met2=(graph[i][k][k-1]+graph[k][j1][k-1]);
				graph[i][j1][k]=Math.min(graph[i][j1][k-1], (graph[i][k][k-1]+graph[k][j1][k-1]));
				if(j1==(graph.length-1)&&(i==(graph.length-1))) {
					k++;
					if(k<graph.length) {
						i=0;
						j1=0;
					}
				}
			}				
		}
		//Ans
		for (int i = 0; i < graph.length; i++) {
			for (int j1 = 0; j1 < graph.length; j1++) {
				int minimo=100000001;
				for (int l = 0; l < graph.length; l++) {
					int aux= graph[i][j1][l];
					if(aux<minimo) {
						ans[i][j1]=aux;
					}
				}
			}
		}				
		return ans;
	}
}
