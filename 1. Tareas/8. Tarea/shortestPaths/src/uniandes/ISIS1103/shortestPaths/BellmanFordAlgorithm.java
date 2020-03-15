package uniandes.ISIS1103.shortestPaths;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class BellmanFordAlgorithm implements ShortestPathAlgorithm{

	@Override
	public int[][] shortestPaths(String fileName) throws IOException {
		String line=null;
		int[][] matrix=null;
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);

		line=bufferedReader.readLine();
		String [] numbers=line.split("\t");
		matrix= new int[numbers.length][numbers.length];

		for (int i = 0; i < numbers.length; i++) {
			String string = numbers[i];
			matrix[0][i]=Integer.parseInt(string);			
		}
		int j=1;
		while((line = bufferedReader.readLine()) != null) {
			numbers=line.split("\t");
			for (int i = 0; i < numbers.length; i++) {
				String string = numbers[i];
				matrix[j][i]=Integer.parseInt(string);			
			}
			j++;
		}   
		bufferedReader.close(); 
		
		int[][] distance = new int[matrix.length][matrix.length];
		for (int i = 0; i < distance.length; i++) {
			distance[i] = calculateShortestPathFromSource(matrix, i);
			System.out.println(i);
		}	
		return distance;
		
	}
	
	public static int[] calculateShortestPathFromSource(int[][] matrix, int source) 
    { 
    	ArrayList<Edge> edges = new ArrayList<Edge>();
    	for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix.length; j++) {
				if (matrix[i][j] != -1) {
					Edge edge = new Edge(i, j, matrix[i][j]);
					edges.add(edge);
				}		
			}
		}
    	
    	int V = matrix.length; 
    	int E = edges.size(); 
        int dist[] = new int[V]; 
        
        for (int i=0; i<V; ++i) {
        	dist[i] = Integer.MAX_VALUE;
        }      
        dist[source] = 0; 
  

        for (int i=1; i<V; ++i) 
        { 
            for (int j=0; j<E; ++j) 
            { 
                int u = edges.get(j).src; 
                int v = edges.get(j).dest; 
                int weight = edges.get(j).weight; 
                if (dist[u]!=Integer.MAX_VALUE && 
                    dist[u]+weight<dist[v]) 
                    dist[v]=dist[u]+weight; 
            } 
        }
        return dist;
    }
}
