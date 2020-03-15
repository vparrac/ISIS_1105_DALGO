package uniandes.ISIS1103.shortestPaths;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DijkstraAlgorithm implements ShortestPathAlgorithm{

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

public static int[] calculateShortestPathFromSource(int[][] matrix, int source) {
		
		int[] distance = new int[matrix.length]; 
        int[] visited = new int[matrix.length]; 
        
        for (int i=0; i<distance.length; ++i) {
        	distance[i] = Integer.MAX_VALUE; 
        }     
        distance[source] = 0; 
  
        for (int counter = 0; counter < matrix.length; counter++) { 
            int min = Integer.MAX_VALUE;
            int nextNode = 0;
            for (int i = 0; i < matrix.length; i++) { 
                if (min > distance[i] && visited[i]!=1) 
                { 
                    min = distance[i]; 
                    nextNode = i; 
                } 
            } 
  
            visited[nextNode] = 1; 
            for (int i = 0; i < matrix.length; i++) { 
                if (visited[i]!=1) { 
                    if (matrix[nextNode][i] != -1 && min+matrix[nextNode][i] < distance[i]) { 
                        distance[i] = min+matrix[nextNode][i]; 
                    } 
                } 
            } 
        } 
		return distance;
	}

}
