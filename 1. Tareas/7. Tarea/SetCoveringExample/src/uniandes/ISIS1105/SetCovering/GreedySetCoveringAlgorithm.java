package uniandes.ISIS1105.SetCovering;
import java.util.ArrayList;
import java.util.Arrays;
public class GreedySetCoveringAlgorithm {
	/**
	 * Greedy solution of Set covering problem
	 * @param subsets 
	 * @return Subsets of minimum covering 	 
	 */
	public 	ArrayList<Integer[]> setCovering(ArrayList<Integer[]> subsets){
		ArrayList<Integer[]> ans = new ArrayList<>();		
		//Find the biggest subset
		int maxSum =0;
		int posBiggestSubSet=0;


		for (int i = 0; i < subsets.size(); i++) {
			Integer[] sub= subsets.get(i);
			int aux=0;

			for (int j = 0; j < sub.length; j++) {
				aux+=sub[j];
				if(aux>maxSum) {
					maxSum=aux;
					posBiggestSubSet=i;
				}
			}

		}		
		//Add it to the list
		ans.add(subsets.get(posBiggestSubSet));
		//Remove of the original list
		subsets.remove(posBiggestSubSet);
		//List of the people served
		Integer[] people  = Arrays.copyOf(ans.get(0), ans.get(0).length);

		//Greedy part: in every step the algorithm select the subset that contains the 
		//maximum number of people who have not been served yet

		int maxNumber=0;
		int pos=0;
		while(sum(people)!=people.length) {			
			for (int i = 0; i < subsets.size(); i++) {
				Integer[] sub= subsets.get(i);
				int numberNewPeople=0;
				for (int j = 0; j < sub.length; j++) {

					if(people[j]==0&&sub[j]==1) {
						numberNewPeople+=1;
					}

				}

				if(numberNewPeople>maxNumber) {
					maxNumber=numberNewPeople;
					pos=i;
				}
			}
			ans.add(subsets.get(pos));
			for (int i = 0; i < subsets.get(pos).length; i++) {
				people[i]=(people[i]==0&&subsets.get(pos)[i]==1)?1:people[i];
			}
			subsets.remove(pos);
		}
		return ans;	
	}

	/**
	 * @param people
	 * @return Sum of all elements
	 */
	public int sum(Integer[] people) {
		int ans=0;
		for (int i = 0; i < people.length; i++) {
			ans+=people[i];
		}
		return ans;
	}	
}