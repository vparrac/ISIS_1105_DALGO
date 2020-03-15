package uniandes.algorithms.sorting;

/**
 * Implements the quick sort algorithm for number arrays
 */
public class QuickNumbersArraySorter implements NumbersArraySorter {
	@Override
	public void sort(double[] numbers) {
		quickSort(numbers, 0, numbers.length-1);
	}
	static void swap(double[] arr, int i, int j) {
		double temp = arr[i];
		arr[i]=arr[j];
		arr[j] = temp;
	}
	static int partir(double [] arr, int l, int h) {
		double pivot = arr[h];
		int i = l;
		for(int j = l; j<h; j++) {
			if(arr[j]<pivot) {
				swap(arr, i,j);
				i++;				
			}
		}
		swap(arr, i ,h);
		return i;
	}
	
	static void quickSort( double arr[], int l, int h) {
		if(l<h) {
			int pivot = partir(arr, l, h);
			quickSort(arr, l, pivot-1);
			quickSort(arr, pivot+1, h);
		}
	}
}
