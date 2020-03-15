package uniandes.ISIS1103.shortestPaths;

import java.io.IOException;

/**
 * Interfaz que representa un algoritmo que es capaz de encontrar una matriz
 * de costos minimos
 * @author Valerie Parra Cort�s
 */
public interface ShortestPathAlgorithm {
	/**
	 * M�todo que dada una matriz de entrada, devuelve una con los costos minimos de 
	 * cualquier nodo a cualquier otro
	 * @param matrix que representa el grafo dirijido, es la posici�n i,j dela matriz esta
	 * el costo de ir del nodo i al nodo j
	 * @return matriz de costos min�mos, en cada posici�n i,j de a matriz esta el camino m�s corto
	 * para ir de i a j, si el n�mero almacenado es infinito, no existe manera de llegar del nodo i al
	 * nodo j
	 * @throws IOException 
	 */
	public int[][] shortestPaths(String fileName) throws IOException;

}
