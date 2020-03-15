package uniandes.ISIS1103.shortestPaths;

import java.io.IOException;

/**
 * Interfaz que representa un algoritmo que es capaz de encontrar una matriz
 * de costos minimos
 * @author Valerie Parra Cortés
 */
public interface ShortestPathAlgorithm {
	/**
	 * Método que dada una matriz de entrada, devuelve una con los costos minimos de 
	 * cualquier nodo a cualquier otro
	 * @param matrix que representa el grafo dirijido, es la posición i,j dela matriz esta
	 * el costo de ir del nodo i al nodo j
	 * @return matriz de costos minímos, en cada posición i,j de a matriz esta el camino más corto
	 * para ir de i a j, si el número almacenado es infinito, no existe manera de llegar del nodo i al
	 * nodo j
	 * @throws IOException 
	 */
	public int[][] shortestPaths(String fileName) throws IOException;

}
