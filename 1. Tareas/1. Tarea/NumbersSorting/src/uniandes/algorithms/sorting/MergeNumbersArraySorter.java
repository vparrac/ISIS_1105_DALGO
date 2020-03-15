package uniandes.algorithms.sorting;
/**
 * Implements the merge sort algorithm for number arrays
 */
public class MergeNumbersArraySorter implements NumbersArraySorter {
	@Override
	public void sort(double[] numbers) {
		mergeSort(numbers, 0, numbers.length-1);
	}
	private void mergeSort(double[] numbers, int l, int r) {		
		if (l < r) { 
			int m = l+(r-l)/2;	       
			mergeSort(numbers, l, m); 
			mergeSort(numbers, m+1, r);	  
			merge(numbers, l, m, r); 
		} 
	}
	private void merge(double[]numbers, int l,int m, int r) {
		int i, j, k; 
		int n1 = m - l + 1; 
		int n2 =  r - m; 	  
		/* create temp arrays */
		double L[]= new double[n1];
		double R[]= new double[n2];	  
		/* Copy data to temp arrays L[] and R[] */
		for (i = 0; i < n1; i++) 
			L[i] = numbers[l + i]; 
		for (j = 0; j < n2; j++) 
			R[j] = numbers[m + 1+ j];	  
		/* Merge the temp arrays back into arr[l..r]*/
		i = 0; // Initial index of first subarray 
		j = 0; // Initial index of second subarray 
		k = l; // Initial index of merged subarray 
		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) { 
				numbers[k] = L[i]; 
				i++; 
			} 
			else{ 
				numbers[k] = R[j]; 
				j++; 
			} 
			k++; 
		} 
		while (i < n1){ 
			numbers[k] = L[i]; 
			i++; 
			k++; 
		} 
		while (j < n2){ 
			numbers[k] = R[j]; 
			j++; 
			k++; 
		}
	}
}