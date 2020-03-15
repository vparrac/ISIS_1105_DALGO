package uniandes.ISIS1105.VerificationProblem;

import java.util.ArrayList;

public class SetCoveringVerificationProblem {
	/**
	 * Verify the set covering problem
	 * @param answer of
	 * @param allSubsets
	 * @param bound
	 * @return
	 */
	@SuppressWarnings("unlikely-arg-type")
	public boolean verify(ArrayList<Integer[]> answer, ArrayList<Integer[]> allSubsets, int bound) {
		//Contains
		for (int i = 0; i < answer.size(); i++) {
			if(!allSubsets.contains(answer.get(i))) {
				return false;
			}
		}
		//Size
		if(answer.size()>bound) {
			return false;
		}
		
		int[] people= new int[answer.get(0).length];
		//Universe
		for (int i = 0; i <answer.size(); i++) {
			Integer[] subset = answer.get(i);
			for (int j = 0; j < subset.length; j++) {
				if(subset[j]==1) {
					people[j]=1;
				}
			}
		}
		return true;
	}

}
