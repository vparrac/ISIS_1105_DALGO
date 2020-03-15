package SetCovering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphExplorationSetCoveringAlgorithm {
	/**
	 * Array with de subsets, the input
	 */
	private ArrayList<Integer[]> subsets;
	/**
	 * Total number of people in universe (number of rows in the input)
	 */
	private int totalNumberOfPeople;
	/**
	 * Method that solves the optimization problem
	 * @param subsets 
	 * @return minimum set covering
	 */
	public 	ArrayList<Integer[]> getSetCovering(ArrayList<Integer[]> subsets){
		this.subsets=subsets;
		totalNumberOfPeople=subsets.get(0).length;
		//To limit the number of solutions that will be compared in the cycle below
		//the suboptimal solution provided the greedy algorithm would be used as upper bound		
		ArrayList<Integer[]> copy = new ArrayList<>();
		copy.addAll(subsets); //Because Greedy algorithm changes the original array
		GreedySetCoveringAlgorithm scva= new GreedySetCoveringAlgorithm();
		ArrayList<Integer[]> greedySolution=scva.setCovering(copy);
		SetCoveringState opt= new SetCoveringState(greedySolution);//BC: Greedy is optimal		
		int maxSubsets= opt.getSize();
		int minSubsets=0;		
		while(minSubsets<maxSubsets) {
			int bound= (maxSubsets+minSubsets)/2;
			//Call of the graph exploration algorithm to find feasible solutions
			SetCoveringState solution= findFeasibleSolution(bound);			
			if(solution==null) {
				minSubsets=bound+1;
			}
			else {
				opt=solution;
				maxSubsets = bound-1;
			}			
		}
		return opt.subsetsOfState;
	}
	/**
	 * This method explores the Graph looking a solution  
	 * @param bound Max number of subsets allowed
	 * @return the Feasible solution or null in the solution is not attainable
	 */
	private SetCoveringState findFeasibleSolution(int bound) {
		SetCoveringState ans=null;
		//Initial state
		SetCoveringState initial = new SetCoveringState();
		//Agenda
		Queue<SetCoveringState> agenda = new LinkedList<>();
		agenda.add(initial);		
		//All states are feasible
		while(agenda.size()>0&&ans==null) {			 
			if(isSolution(initial, bound)) {
				ans=initial;
			}
			else {
				List<SetCoveringState> successors= getSuccessors(initial);
				agenda.addAll(successors);
			}
		}
		return ans;	
	}
		
	/**
	 * Calculates the successors of the given state. Successors are all states formed adding
	 * other subset
	 * @param state source state to define successors
	 * @return List<SetCoveringState> successors of the given state
	 */
	private ArrayList<SetCoveringState> getSuccessors(SetCoveringState state){
		ArrayList<SetCoveringState> successors = new ArrayList<>();		
		ArrayList<Integer[]> subsetsOfState= state.getSubsets();		
		for (int i = 0; i < this.subsets.size(); i++) {
			if(!(subsetsOfState.contains(this.subsets.get(i)))) {
				ArrayList<Integer[]> newState= new ArrayList<>();
				newState.addAll(subsetsOfState);
				newState.add(subsets.get(i));
				SetCoveringState newSuccessor = new SetCoveringState(newState);
				successors.add(newSuccessor);
				subsetsOfState.remove(subsets.get(i));
			}
		}
		return successors;	
	}
	
	/**
	 * Determines if the given state is a solution. Implements the satisfiability predicate of the
	 * graph exploration algorithm
	 * @param state that will be checked
	 * @param bound Maximum number of subsets allowed
	 * @return boolean true if the total value of the given state is solution of set covering problem
	 */
	private boolean isSolution(SetCoveringState state, int bound) {
		return (state.getTotalNumberOfPeople()==totalNumberOfPeople&&state.subsetsOfState.size()<=bound);
	}
			
	
	/**
	 * An specific state for the graph exploration defined for a list of subsets represented by
	 * an array of boolean
	 */

	class SetCoveringState {
		/**
		 * Array with subsets, the input a
		 */
		private ArrayList<Integer[]> subsetsOfState;
		/**
		 * Total number of people that can be served in this state
		 */
		private int totalNumberOfPeople;

		/**
		 * Constructor of the state class
		 * @param subsets the subsets of the state
		 */
		public SetCoveringState(ArrayList<Integer[]> subsets) {
			Integer[] peopleServerState= new Integer[subsets.get(0).length];
			Arrays.fill(peopleServerState, 0);
			this.subsetsOfState=subsets;
			
			for (int i = 0; i < subsetsOfState.size(); i++) {
				Integer[] subset=subsetsOfState.get(i);			
				for (int j = 0; j < subset.length; j++) {		
					if((subset[j]==1)) {
						peopleServerState[j]=1;
					}					
				}
			}
				for (int i = 0; i < peopleServerState.length; i++) {
				totalNumberOfPeople+=peopleServerState[i];
			}
			
		}
		
		/**
		 * Empty constructor
		 */		
		public SetCoveringState() {
			subsetsOfState= new ArrayList<>();
			totalNumberOfPeople=0;
		}		
		/**
		 * Returns the subsets of the state
		 * @return
		 */
		public ArrayList<Integer[]> getSubsets() {
			return subsetsOfState;
		}
		
		/**
		 * Returns the number of people who is served in the state
		 * @return
		 */
		public int getTotalNumberOfPeople() {
			return totalNumberOfPeople;
		}		
		
		/**
		 * Returns the number of subsets in the state
		 * @return
		 */
		public int getSize() {
			return subsetsOfState.size();
		}
	}		
	
}