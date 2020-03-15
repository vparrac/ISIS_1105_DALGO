package uniandes.algorithms.coinchange;
public class DynamicProgrammingCoinChangeAlgorithm implements CoinChangeAlgorithm{
	int[] optimalChange;
	@Override
	public int[] calculateOptimalChange(int totalValue, int[] denominations) {
		calculateOptimalChangeNumber(totalValue, denominations);
		return optimalChange;
	}
	/**
	 * Este método, en primera instancia cálcula el grafo de necesidades M y es capaz de recordar
	 * para cada iteración cuantas veces se necesito la moneda para la solución optima
	 * @param totalValue el valor total de vueltas que se quiere dar
	 * @param denominations arreglo con la denominación de las monedas 
	 * @return el minimo número de monedas necesarias para dar las vuelts 
	 */
	public void calculateOptimalChangeNumber(int totalValue, int[] denominations) {
		//Creación de los arreglos auxiliares: Se necesitan 2 arreglos: M que representa el
		//grafo de prioridad y remeberK que va recordando las k minimas
		int[][] M = new int[denominations.length][totalValue+1];
		int[][] rememberk= new int[denominations.length][totalValue+1];
		//Ciclo para recorrer toda la matriz
		for (int j = 0; j < M[0].length; j++) { //Columnas
			for (int i = 0; i < M.length; i++) { //Filas		
				//Caso base 1: Si solo tengo una moneda, esta por defecto es 1. Entonces el número de monedas
				//Será la cantidad de dinero que quiero lograr
				if(i==0&j!=0) {
					M[i][j]=j;
					rememberk[i][j]=j;
				}
				//Caso base 2 : Este caso realmente no es necesario [ya que la matriz por defecto se inicializa en 0
				//Pero se coloca por claridad, este caso representa el caso en que necesito devolver cero dinero, 
				//entonces necesito 0 monedas
				else if(j==0) {
					M[i][j]=0;
					rememberk[i][j]=0;
				}
				//Este caso es el que simboliza el caso recursivo. Tengo  que hallar el minimo entre no llevarme
				//llevarme 1 vez
				//llevarme 2 veces..
				//llevarme n veces
				else {
					int minValue=(int) Double.POSITIVE_INFINITY;
					int maxValueOfk=j/denominations[i];					
					for (int k = 0; k <= maxValueOfk; k++) {
						int aux = M[i-1][j-k*denominations[i]]+k;
						if(aux<=minValue) {
							minValue=aux;
							rememberk[i][j]=k;
						}
					}
					//El óptimo esta al final
					M[i][j]=minValue;			
				}				
			}			
		}		
		//Me devuelvo para encontrar las denominaciones
		rollBack(rememberk, totalValue, denominations);		
	}
	/**
	 * Método para obtener el número de monedas usado en las vueltas óptima. Le entra las decisiones k en un arreglo
	 * el dinero total que se quiere formar y un arreglo con las denominaciones de las monedas
	 * @param rememberk arreglo  con los k minimos
	 * @param totalValue el dinero total que se quiere formar
	 * @param denominations las denominaciones de las monedas
	 */
	public void rollBack(int[][] rememberk, int totalValue, int[] denominations) {		
		optimalChange=new int[rememberk.length]; //Arreglo donde guardare el cambio óptimo
		int valorTotal=totalValue;
		for (int i = rememberk[0].length-1; i >= 0&valorTotal!=0; i--) {  //Columnas
			for (int j = rememberk.length-1; j>=0&valorTotal!=0; j--) { //Filas
				int aux2 =rememberk[j][i]; 
				if(aux2!=0) { //Use la moneda en la solución óptima?
					optimalChange[j]=aux2; //El número de veces que use la moneda					
					i= valorTotal-aux2*denominations[j]+1; //El nuevo número a buscar + 1 [A penas se ejecute el break, se disminuirá en 1
					valorTotal=valorTotal-aux2*denominations[j];  //El dinero que falta completar
					j= rememberk.length-1; //Todas las monedas
					break;
				}
			}
		}		
	}	
}