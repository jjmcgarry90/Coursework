//HW 7 Written by Jessie McGarry
//Collaboration Statement: I worked with Drew Norvell on this class.

import java.util.Random;


/**
 * This class contains various methods for sorting an array
 * of ints into a least to greatest order.
 * @author Jessie McGarry
 */
public class SortingAlgorithms {
	private int[] array;
	private Random gen;

	/**
	* Sorts the given array of ints from least to greatest
	* using selection sort.
	* @param array, the array to be sorted
	* @return int[] array, the sorted array
	*/
	public int[] selectionSort(int[] array) {
		int min;
		int temp;
		
		for(int i = 0; i < array.length - 1; i++) {
			min = i;
			for (int check = i+1; check < array.length; check++)
				if (array[check] < array[min])
					min = check;
	
		
			temp = array[min];
			array[min] = array[i];
			array[i] = temp;
		}
			
		return array;  
	}

	/**
	* Sorts an array of ints from least to greatest using
	* insertion sort. 
	* @param array the array to be sorted 
	* @return int[] array, the sorted array
	*/
	public int[] insertionSort(int[] array) {
		for (int i = 1; i < array.length; i++) {
			int check = array[i];
			int current = i;
			
			while ( current > 0 && check < array[current-1]) {
				array[current] = array[current-1];
				current--;
			}
			
			array[current] = check;
		}
		return array;  
	}
	
	/**
	* Sorts the given array of ints using radix sort. The array
	* must contains ints of the same length, and no zeroes.
	* The sort should sort the ints from least to greatest. 
	* @param array the array to be sorted 
	* @return int[] array, the sorted array
	*/
	public int[] radixSort(int[] array) {
		int[][] numbers = new int[10][array.length];
		
		//assuming the array contains ints of the same length
		int elementLength = String.valueOf(array[0]).length();
		
		//sorts each place in the integer(ones, tens, hundreds, etc.)
		for(int i = elementLength; i > 0; i--) { 	
			for(int num : array) {
				String stringVal = String.valueOf(num);
				int digit = Integer.parseInt(stringVal.substring(i-1,i));
				int check = 0;
				while(!(numbers[digit][check] == 0))
					check++;
				numbers[digit][check] = num;
			}
			
			//using arrays as opposed to lists makes this algorithm
			//stupidly inefficient, as demonstrated here.
			int place = 0;
			for(int j = 0; j < 10; j++) {       
				for(int k = 0; k < array.length; k++)
					if(!(numbers[j][k] == 0)) {
						array[place] = numbers[j][k];
						numbers[j][k] = 0;
						place++;
					}
			}	  //for			
		}		  //for
		return array; 
	}
	
	/**
	* Sorts the given array of ints using merge sort.
	* The sort should sort the ints from least to greatest.
	* @param array, the array to be sorted
	* @return int[] array, the sorted array
	*/
	public int[] mergeSort(int[] array) {
		this.array = array;
		helper(0, array.length - 1);
		return array;
	}
	
	/**
	 * Helper method for merge sort.
	 * @param low - the first element in the array.
	 * @param high - the last element in the array.
	 */
	private void helper(int low, int high) {
		if (low < high) {
			int mid = (low + high) / 2;
			helper(low, mid);
			helper(mid + 1, high);
			merge(low, mid, high);
		}
	}
	
	/**
	 * Helper method for merge sort
	 * @param low - the first element in the array
	 * @param mid - the middle element in the array.
	 * @param high - the last element in the array.
	 */
	private void merge(int low, int mid, int high) {
		int[] helper = new int[array.length];
		for (int i = low; i <= high; i++) {
			helper[i] = array[i];
		}
		int l = low;
		int m = mid + 1;
		int l2 = low;
		
		while (l <= mid && m <= high) {
			if (helper[l] <= helper[m]) 
				array[l2] = helper[l]; 
			else 
				array[l2] = helper[m];
			
			l2++;
			l++;
		}
		
		while (l <= mid) {
			array[l2] = helper[l];
			l2++;
			l++;
		}
		helper = null;

	}


	


	/**
	* Sorts the given array of ints using inplace quick sort.
	* The sort should sort the ints from least to greatest 
	* and always pick pivots randomly.
	* @param array the array to be sorted 
	* @return int[] array, the sorted array
	*/
	public int[] inPlaceQuickSort(int[] array) {
		gen = new Random();
		if (array.length <= 1)
			return array;
		this.array = array;
		quicksort(0, array.length-1);
		return array;
	}
	
	/**
	 * Helper method for inPlaceQuickSort.
	 * @param low - the first element in the array
	 * @param high - the last element in the array.
	 */
	private void quicksort(int low, int high) {
		int l = low, h = high;
		int pivotIndex = (int)gen.nextFloat() * (high-low) + low;
		int pivot = array[pivotIndex];
		while (l <= h) {
			while (array[l] < pivot) 
				l++;
			while (array[h] > pivot) 
				h--;
			if (l <= h) {
				int temp = array[l];
				array[l] = array[h];
				array[h] = temp;
				l++;
				h--;
			}
		}
		if (low < h)
			quicksort(low, h);
		if (l < high)
			quicksort(l, high);
	}

	
	
	/**
	 * Test main creates several arrays and tests 
	 * the sort methods.
	 * @param args - command line arguments
	 */
	public static void main(String[] args) {
		int[] selection = new int[] {63,21,13,3,42,54};
		int[] insertion = new int[] {60,20,30,10,50,40};
		int[] radix = new int[] {68,27,46,52,34,81,75,13};
		int[] merge = new int[] {2,6,4,9,1,7,10,3,5,8};
		int[] quick = new int[] {3,7,9,2,5,1,4,6,8,10};
		SortingAlgorithms sorter = new SortingAlgorithms();
		
		int[] selectionSort = sorter.selectionSort(selection);
		for(int i = 0; i < selection.length; i++)
			System.out.print(selectionSort[i] + " ");
		System.out.println();
		
		int[] insertionSort = sorter.insertionSort(insertion);
		for(int i = 0; i < insertion.length; i++)
			System.out.print(insertionSort[i] + " ");
		System.out.println();
		
		int[] radixSort = sorter.radixSort(radix);
		for(int i = 0; i < radix.length; i++)
			System.out.print(radixSort[i] + " ");
		System.out.println();
		
		int[] mergeSort = sorter.mergeSort(merge);
		for(int i = 0; i < mergeSort.length; i++)
			System.out.print(mergeSort[i] + " ");
		System.out.println();
		
		int[] quickSort = sorter.inPlaceQuickSort(quick);
		for(int i = 0; i < quickSort.length; i++)
			System.out.print(quickSort[i] + " ");
		
		
	}
}