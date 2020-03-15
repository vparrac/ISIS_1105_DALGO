package ProblemaC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Clase que soluciona el problema C
 * @author Valerie Parra Cortés
 * @author Cristina Isabel González Osorio
 */
public class ProblemaC {
	/**
	 * Métod main de la clase, toma la respuesta por consola y concatena en un String la respuesta
	 * @param args no utilizado
	 * @throws IOException si hay algún error de entrada salida leyendo de la consola
	 */

	public  ArrayList<Intervalo> intervalos;
	public static void main(String[] args) throws IOException {
		//Cola de prioridad para ordenar los intervalos a medida que se guardan
		Intervalo nuevo=null; //Intervalo para guarda la intersección de todos los intervalos si existe
		int numeroDeSensores,inicio; //Número de sensores y una variable inicio para reutilizar
		boolean existe=true; //Para saber si existe la intersección de todos los sensores
		StringBuilder rta= new StringBuilder(); //String donde se concatenará la respuesta
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Leer la rta
		String st; 	//Variable auxiiar

		while (!(st = br.readLine()).equals("0")) { //Lee hasta que se acaban los casos de prueba			
			PriorityQueue<Intervalo> a= new PriorityQueue<>();
			numeroDeSensores=Integer.parseInt(st); //Guarda el número de sensores
			st=br.readLine(); 
			String[] b=st.split(" ");			
			for (inicio = 0; inicio <= b.length-2; inicio+=2) {
				int init=Integer.parseInt(b[inicio]); //Va creando los intervalos
				int fin=Integer.parseInt(b[inicio+1]);
				Intervalo nue= new Intervalo(init, fin);
				a.add(nue);	//Y los añade a la PQ
				//A medida que cargamos miramos si existe la intersección entre todas, si es la primera iteración inicializamos nuevo en el intervalo actual
				if(nuevo==null&&existe) {
					nuevo= new Intervalo(nue.inicio, nue.fin);
				}
				//Si sigue existiendo la posibilidad de que se intersecten
				else if(existe) {
					Intervalo elQueTieneMayor= (nuevo.inicio>=nue.inicio)?nuevo:nue; //El que tiene el mayor de los iniciales
					Intervalo elQueTieneElMenor =(nuevo.fin<=nue.fin)?nuevo:nue; //EL que tienen el menor de los finales
					//Debe cumplirse que el que tiene el mayor entre los iniciales debe ser menor a el final del otro intervalo y que
					//EL que tiene el menor entre los valores finales del intervalo debe ser mayor al número inicial del otro intervalo
					boolean m1= (elQueTieneMayor.inicio<=((elQueTieneMayor==nue)?nuevo:nue).fin)&&(elQueTieneElMenor.fin>((elQueTieneElMenor==nue)?nuevo:nue).inicio);
					if(m1==true) {//Si esi si se cumple
						nuevo.setInicio(elQueTieneMayor.inicio); //Apretamos el intercalo
						nuevo.setFin(elQueTieneElMenor.fin);
					}
					else {//Si no, no existe intersección entre todas las observaciones
						existe=false;
						nuevo=null;
					}
				}				
			}			
			if(!(nuevo==null)) {//Si si existe la intersección, se retorna la voe
				rta.append(nuevo.darVoe()+"\n");
			}
			else {	//Si no toca explorar hasta encontrarlo, se explora en orden para que una vez se encuentre una respuesta esta sea óptima
				ProblemaC problema = new ProblemaC();
				problema.intervalos= new ArrayList<>(a.size()); // Se guardan los intervalos en orden sacandolos de la PQ 
				numeroDeSensores=a.size(); 
				for(inicio=0; inicio<numeroDeSensores; inicio++) {
					problema.intervalos.add(a.poll());					
				}
				StateProblemaC solution = problema.feasibleSolution();				
				rta.append(solution.intervaloSolucion.darVoe()+"\n");
			}
		}
		System.out.println(rta);
	}
	/**
	 * Método que encuentra la voe 
	 * @return intervalo del cual se saca la Voe
	 */
	public  StateProblemaC feasibleSolution() {
		//Llegado a este punto, no se ha encontrado una intersección para todos las observaciones, por lo que se empiezan a buscar intersección
		//Entre el número de observaciones -1 
		StateProblemaC inicial= new StateProblemaC(intervalos, intervalos.size()-1, intervalos.size());
		StateProblemaC solution=null;
		Queue<StateProblemaC> agenda = new LinkedList<>();
		HashSet<String> conjunto = new HashSet<>();//El conjuto se usa para optimizar, cada estado se codifica como un String 
		//Y se guarda en en el set, se implementa un set porque las búsquedas son más rápidas. Esto implica un mayor gasto en espacio pero se gana mucho en tiempo
		agenda.add(inicial);
		while(agenda.size()>0&&solution==null) {
			inicial=agenda.poll();
			if(isSolution(inicial)) {
				solution=inicial;
			}
			else {				
				List<StateProblemaC> sucesores= sucesores(inicial);
				if(conjunto.isEmpty()) {					
					for (StateProblemaC stateProblemaC : sucesores) {			
										
						conjunto.add(stateProblemaC.toStringBuilder());
					}
					agenda.addAll(sucesores);
				}
				else {//Parte de la optimización: Sólo se añaden a la agenda los estados si estos no han sido recorridos
					for (StateProblemaC stateProblemaC : sucesores) {						
						if(!conjunto.contains(stateProblemaC.toStringBuilder())) {
							agenda.add(stateProblemaC);
							conjunto.add(stateProblemaC.toStringBuilder());
						}						
					}
				}				
			}			
		}
		return solution;
	}
	/**
	 * Los sucesores se definen a partir de dos cosas: un  punto de partida que indica en que posición del arreglo se está y el número de arreglos permitidos  
	 * @return Lista de sucesores
	 */
	public List<StateProblemaC> sucesores(StateProblemaC estado){
		ArrayList<StateProblemaC> sucesores = new ArrayList<>();
		int i,ultimaPos=0;//Variable para reutilizar
		boolean fin =false;
		//Caso 1 : se siguen haciendo permutaciones con el mismo número de intervalos cambiando el punto de partida
		if((intervalos.size()-estado.puntoPartida>estado.cantidadIntervalos)&&estado.puntoPartida!=intervalos.size()-1) {//Se pueden hacer permutaciones aun
			int[] indicadores= new int[estado.cantidadIntervalos];
			ultimaPos=estado.puntoPartida+1; //Mirar apuntador aquí
			for ( i = 0; i < indicadores.length; i++,ultimaPos++) {
				indicadores[i]=ultimaPos;
			}
			ultimaPos=indicadores.length-2;	
			while(!fin) {
				ArrayList<Intervalo> subset= new ArrayList<>(indicadores.length);
				for ( i = 0; i < indicadores.length; i++) {					
					subset.add(intervalos.get(indicadores[i]));				
				}
				//Crear el sucesor y añadirlo
				StateProblemaC estadoSucesor= new StateProblemaC(subset, estado.puntoPartida+1, estado.cantidadIntervalos);
				sucesores.add(estadoSucesor);
				indicadores[indicadores.length-1]=indicadores[indicadores.length-1]+1;
				if(indicadores[indicadores.length-1]==intervalos.size()) {
					while(true) {		
						if(ultimaPos==0) {							
							fin=true;
							break;
						}
						if(indicadores[ultimaPos]+1==intervalos.size()) {
							ultimaPos--;						
						}
						else {
							indicadores[ultimaPos]=indicadores[ultimaPos]+1;
							for (i = ultimaPos+1; i < indicadores.length; i++) {
								indicadores[i]=indicadores[i-1]+1;
							}
							if(indicadores[indicadores.length-1]>=intervalos.size()) {
								ultimaPos--;
							}
							else {
								break;
							}
						}							
					}
				}	
			}
		}
		//Caso 2: Si no, se intenta busca una intersección con menos intervalos
		else {			
			int nuevoTamano=estado.cantidadIntervalos-1;			
			//Caso trivial: no hay conjuntos que se sobrelapen
			if(nuevoTamano==1) {				
				for(i=0;i<intervalos.size();i++) {
					ArrayList<Intervalo> intervaloEstado= new ArrayList<>();
					intervaloEstado.add(intervalos.get(i));
					StateProblemaC nuevo = new StateProblemaC(intervaloEstado, i, 1);
					sucesores.add(nuevo);
				}				
			}
			else { //Realizar las permutaciones
				int[] indicadores= new int[nuevoTamano];
				for(i=0;i<indicadores.length;i++) {
					indicadores[i]= i;
				}
				ultimaPos=indicadores.length-2;	
				fin=false;
				while(!fin) {
					ArrayList<Intervalo> subset= new ArrayList<>(indicadores.length);
					for ( i = 0; i < indicadores.length; i++) {				
						subset.add(intervalos.get(indicadores[i]));				
					}
					//Crear el sucesor y añadirlo
					StateProblemaC estadoSucesor= new StateProblemaC(subset, 0, nuevoTamano);
					indicadores[indicadores.length-1]=indicadores[indicadores.length-1]+1;
					sucesores.add(estadoSucesor);
					if(indicadores[indicadores.length-1]==intervalos.size()) {
						while(true) {		
							if(ultimaPos==0) {							
								fin=true;
								break;
							}
							if(indicadores[ultimaPos]+1==intervalos.size()) {
								ultimaPos--;						
							}
							else {
								indicadores[ultimaPos]=indicadores[ultimaPos]+1;
								for (i = ultimaPos+1; i < indicadores.length; i++) {
									indicadores[i]=indicadores[i-1]+1;
								}
								if(indicadores[indicadores.length-1]>=intervalos.size()) {
									ultimaPos--;
								}
								else {
									break;
								}
							}							
						}
					}
				}
			}
		}				
		return sucesores;
	}
	/**
	 * Método que permite saber si un estado es solución
	 * Un estado es solución si todos los conjutos que pertenecen al estado tienen intersección no vacia
	 * Para un estado que sólo tiene un intervalo, se dice que es solución
	 * @param estado al cual se le calcularan los sucesores
	 * @return true si el estado es solucion, false de lo contrario
	 */
	public boolean isSolution(StateProblemaC estado) {
		if(estado.intervalosEstado.size()==1) {
			estado.setIntervaloSolucion(estado.intervalosEstado.get(0));
			return true;
		}
		int inicio=0;
		Intervalo nuevo=null;
		boolean existe = true;
		for (inicio = 0; inicio < estado.intervalosEstado.size()&&existe; inicio++) {			
			Intervalo nue= estado.intervalosEstado.get(inicio);
			//A medida que cargamos miramos si existe la intersección entre todas, si es la primera iteración inicializamos nuevo en el intervalo actual
			if(nuevo==null&&existe) {
				nuevo= new Intervalo(nue.inicio, nue.fin);
			}
			//Si sigue existiendo la posibilidad de que se intersecten
			else if(existe) {
				Intervalo elQueTieneMayor= (nuevo.inicio>=nue.inicio)?nuevo:nue; //El que tiene el mayor de los iniciales
				Intervalo elQueTieneElMenor =(nuevo.fin<=nue.fin)?nuevo:nue; //EL que tienen el menor de los finales
				//Debe cumplirse que el que tiene el mayor entre los iniciales debe ser menor a el final del otro intervalo y que
				//EL que tiene el menor entre los valores finales del intervalo debe ser mayor al número inicial del otro intervalo
				boolean m1= (elQueTieneMayor.inicio<=((elQueTieneMayor==nue)?nuevo:nue).fin)&&(elQueTieneElMenor.fin>=((elQueTieneElMenor==nue)?nuevo:nue).inicio);
				if(m1==true) {//Si esi si se cumple
					nuevo.setInicio(elQueTieneMayor.inicio); //Apretamos el intercalo
					nuevo.setFin(elQueTieneElMenor.fin);
				}
				else {//Si no, no existe intersección entre todas las observaciones
					existe=false;
					nuevo=null;					
				}
			}				
		}
		estado.setIntervaloSolucion(nuevo);
		return existe;
	}
}	
/**
 * Clase que representa un estado del problema 
 * Un estado esta compuesto de un punto de partida, la cantidad e intervalos que lo componen, los intervalos del estado y el intervalo 
 * solución donde existe la intersección
 * @author Valerie Parra Cortés
 * @author Cristina Isabel Gonzalez Osorio
 */
class StateProblemaC {	
	/**
	 * Punto de partida donde se hicieron las permutaciones
	 */
	public int puntoPartida;
	/**
	 * Cantidad de intervalos  del estado
	 */
	public int cantidadIntervalos;
	/**
	 * Intervalos del estado
	 */
	ArrayList<Intervalo> intervalosEstado;
	/**
	 * Intervalo donde se intersectan intervalos del problema
	 */
	Intervalo intervaloSolucion;
	/**
	 *Crea un nuevo estado con las observaciones, el punto de partida y la cantidad de intervalos dada por parametro 
	 * @param estados arreglo de intervalos del problema
	 * @param puntoPartida punto inicial por donde se hicieron las permutaciones
	 * @param cantidadIntervalos cantidad de intervalos que tiene el estados
	 */
	public StateProblemaC(ArrayList<Intervalo> estados, int puntoPartida, int cantidadIntervalos) {		
		intervalosEstado=estados;		
		this.puntoPartida=puntoPartida;
		this.cantidadIntervalos=cantidadIntervalos;
	}
	/**
	 * Método que modifica el intervalo solución
	 * @param intervaloSolucion nuevo intervalo solución
	 */
	public void setIntervaloSolucion(Intervalo intervaloSolucion) {
		this.intervaloSolucion = intervaloSolucion;
	}
	/**
	 * Método para codificar los estados y meterlos al set
	 * @return
	 */
	public String toStringBuilder() {
		StringBuilder intervalos= new StringBuilder();
		for (int i=0;i<intervalosEstado.size();i++) {
			String fin=(i==intervalosEstado.size()-1)?"":",";
			intervalos.append(intervalosEstado.get(i).toString()+fin);
		}
		StringBuilder string = new StringBuilder("StateProblemaC [puntoPartida=" + puntoPartida + ", cantidadIntervalos=" + cantidadIntervalos
				+ ", intervalosEstado= [" + intervalos + "]]");				
		return string.toString();
	}
}
/**
 * Clase que representa un intervalo, implementa la interfaz Comparable
 * @author Valerie Parra Cortés
 * @author Cristina Isabel Gonzalez Osorio
 */
class Intervalo implements Comparable<Intervalo>{
	/**
	 * Inicio del intervalo
	 */
	public int inicio; 
	/**
	 * Fin dle intervalo
	 */
	public int fin;
	/**
	 * Método que crea un nuevo intervalo con los valores de inicio y fin dados por parametro
	 * @param inicio del intervalo
	 * @param fin del intervalo
	 */
	public Intervalo(int inicio, int fin) {
		this.inicio=inicio;
		this.fin=fin;
	}
	/**
	 * Método que compara los intervalos
	 * Se da prioridad al número en donde empieza el intervalo, luego al número donde termina
	 */
	@Override
	public int compareTo(Intervalo o) {			
		return (inicio>o.inicio)?1:(inicio<o.inicio)?-1:(fin<o.fin)?-1:(o.fin<fin)?1:0;
	}
	/**
	 * Método que modifica el inicio del intervalo por uno dado por parametro
	 * @param inicio dle intervalo
	 */
	public void setInicio(int inicio) {
		this.inicio = inicio;
	}
	/**
	 * Método que modifica el fin del intervalo por uno dado por parametro
	 * @param fin del intervalo
	 */
	public void setFin(int fin) {
		this.fin = fin;
	}
	/**
	 * La voe de un intervalo se define como el inicio más el fin medios
	 */
	public int darVoe() {
		return (inicio+fin)/2;
	}
}