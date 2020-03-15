package uniandes.algorithms.sorting;
/**
 * Implements the bubble sort algorithm for number arrays
 */
public class BubbleNumbersArraySorter implements NumbersArraySorter {
	@Override
	public void sort(double[] numbers) {
		int n = numbers.length;  
		double temp = 0;  
		for(int i=0; i < n; i++){  
			for(int j=1; j < (n-i); j++){  
				if(numbers[j-1] > numbers[j]){				  
					temp = numbers[j-1];  
					numbers[j-1] = numbers[j];  
					numbers[j] = temp;  
				}  
			}  
		} 
	}
}
