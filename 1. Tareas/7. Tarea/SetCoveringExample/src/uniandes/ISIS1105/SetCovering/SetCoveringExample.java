package uniandes.ISIS1105.SetCovering;

import java.util.ArrayList;
import java.util.Arrays;

import uniandes.ISIS1105.VerificationProblem.SetCoveringVerificationProblem;
/**
 * Class to prove the Graph Exploration Algorithm
 * @author Valerie Parra Cortés 
 */
public class SetCoveringExample {
	public static void main(String[] args) {
		//A specify example where greedy doesn't select the minimum subset
		Integer[] subset1= {1,1,1,1,0,0};
		Integer[] subset2= {1,0,0,1,0,1};
		Integer[] subset3= {0,1,1,0,1,0};
		Integer[] subset4= {0,0,0,1,0,1};
		Integer[] subset5= {1,1,1,0,1,0};
	
		ArrayList<Integer[]> subsets= new ArrayList<>();
		subsets.add(subset1);
		subsets.add(subset2);
		subsets.add(subset3);
		subsets.add(subset4);
		subsets.add(subset5);
		
		ArrayList<Integer[]> subsetsCopy= new ArrayList<>();
		subsetsCopy.addAll(subsets);
		
		GraphExplorationSetCoveringAlgorithm gesca= new GraphExplorationSetCoveringAlgorithm();
		ArrayList<Integer[]> ans= gesca.getSetCovering(subsets);
		System.out.println("The number of days is "+ans.size()+" and the days are: ");		
		for (int i = 0; i < ans.size(); i++) {
			System.out.println(Arrays.toString(ans.get(i)));
		}		
		
		//It is viable?
		GreedySetCoveringAlgorithm gsca= new GreedySetCoveringAlgorithm();
		ArrayList<Integer[]> greedySolution=gsca.setCovering(subsets);
		
		SetCoveringVerificationProblem verifier = new SetCoveringVerificationProblem();
		boolean verify = verifier.verify(ans, subsetsCopy, greedySolution.size());
		String sucess= (verify==true)?"Viable":"Non-viable";
		System.out.println("The solution is "+sucess);
	}
}
