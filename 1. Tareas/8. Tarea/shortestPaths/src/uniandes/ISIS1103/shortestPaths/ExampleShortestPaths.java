package uniandes.ISIS1103.shortestPaths;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
/**
 * Clase para probar los algoritmos implementados
 * @param args Matriz con los argumentos necesarios
 * args[0] Es el algoritmo a utilizar, los algoritmos disponibles son
 * BellmanFord, Dijkstra y FloydWarschall
 * args[1] Es el nombre del archivo con la matriz que se cargará para probar
 * @throws ClassNotFoundException Si se introduce una clase equivocada 
 * @throws IllegalAccessException Si se introduce una clase equivocada
 * @throws InstantiationException Si se introduce una clase equivocada
 * @throws IOException Si hay algún problema leyendo el archivo
 * @author Valerie Parra Cortés
 */
public class ExampleShortestPaths {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IOException {
		String algorithmClassName = ExampleShortestPaths.class.getPackage().getName()+"."+args[0]+"Algorithm";
		ShortestPathAlgorithm algorithm = (ShortestPathAlgorithm) Class.forName(algorithmClassName).newInstance();
		long startTime = System.currentTimeMillis();
		int[][] mi= algorithm.shortestPaths(args[1]);
		long endTime = System.currentTimeMillis();

		System.out.println("La matriz de caminos de costos minimos es \n");
		for (int i = 0; i < mi.length; i++) {
			for (int k = 0; k < mi.length; k++) {
				String caracterFinal=(k==(mi.length-1))?"":", \t";
				System.out.print(mi[i][k]+caracterFinal);;
			}
			System.out.println("\n");
		}
		System.out.println("Total time spent (milliseconds): "+(endTime-startTime));
	}
}
