//Collaboration Statement: This is solely my work.

import java.util.Random;

/**
 * This class test several sorting algorithms and diplays
 * their running time on integer arrays of various sizes.
 * @author Jessie McGarry
 *
 */
public class Driver {
	/**
	 * main method - (see above)
	 * @param args - command line arguments
	 */
	public static void main (String[] args) {
		SortingAlgorithms sorters = new SortingAlgorithms();
		long timeStart = 0, time = 0;
		
		System.out.println("Selection Sort");
		int[] selection1 = makeArray(100);
		int[] selection2 = makeArray(200);
		int[] selection3 = makeArray(500);
		int[] selection4 = makeArray(1000);
		int[] selection5 = makeArray(5000);
		
		timeStart = System.nanoTime();
		sorters.selectionSort(selection1);
		time = (System.nanoTime() - timeStart);
		System.out.println("100 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.selectionSort(selection2);
		time = (System.nanoTime() - timeStart);
		System.out.println("200 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.selectionSort(selection3);
		time = (System.nanoTime() - timeStart);
		System.out.println("500 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.selectionSort(selection4);
		time = (System.nanoTime() - timeStart);
		System.out.println("1000 Elements: " + time + " nanoseconds");
		
		timeStart = System.currentTimeMillis();
		sorters.selectionSort(selection5);
		time = (System.currentTimeMillis() - timeStart);
		System.out.println("5000 Elements: " + time + " milliseconds");
		
		System.out.println();
		
		System.out.println("Insertion Sort");
		int[] insertion1 = makeArray(100);
		int[] insertion2 = makeArray(200);
		int[] insertion3 = makeArray(500);
		int[] insertion4 = makeArray(1000);
		int[] insertion5 = makeArray(5000);
		
		timeStart = System.nanoTime();
		sorters.insertionSort(insertion1);
		time = (System.nanoTime() - timeStart);
		System.out.println("100 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.insertionSort(insertion2);
		time = (System.nanoTime() - timeStart);
		System.out.println("200 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.insertionSort(insertion3);
		time = (System.nanoTime() - timeStart);
		System.out.println("500 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.insertionSort(insertion4);
		time = (System.nanoTime() - timeStart);
		System.out.println("1000 Elements: " + time + " nanoseconds");
		
		timeStart = System.currentTimeMillis();
		sorters.insertionSort(insertion5);
		time = (System.currentTimeMillis() - timeStart);
		System.out.println("5000 Elements: " + time + " milliseconds");
		
		System.out.println();
		
		System.out.println("Radix Sort");
		int[] radix1 = makeRadixArray(100);
		int[] radix2 = makeRadixArray(200);
		int[] radix3 = makeRadixArray(500);
		int[] radix4 = makeRadixArray(1000);
		int[] radix5 = makeRadixArray(5000);
		
		timeStart = System.nanoTime();
		sorters.radixSort(radix1);
		time = (System.nanoTime() - timeStart);
		System.out.println("100 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.radixSort(radix2);
		time = (System.nanoTime() - timeStart);
		System.out.println("200 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.radixSort(radix3);
		time = (System.nanoTime() - timeStart);
		System.out.println("500 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.radixSort(radix4);
		time = (System.nanoTime() - timeStart);
		System.out.println("1000 Elements: " + time + " nanoseconds");
		
		timeStart = System.currentTimeMillis();
		sorters.radixSort(radix5);
		time = (System.currentTimeMillis() - timeStart);
		System.out.println("5000 Elements: " + time + " milliseconds");
		
		
		System.out.println();
		
		System.out.println("Merge Sort");
		int[] merge1 = makeArray(100);
		int[] merge2 = makeArray(200);
		int[] merge3 = makeArray(500);
		int[] merge4 = makeArray(1000);
		int[] merge5 = makeArray(5000);
		
		timeStart = System.nanoTime();
		sorters.mergeSort(merge1);
		time = (System.nanoTime() - timeStart);
		System.out.println("100 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.mergeSort(merge2);
		time = (System.nanoTime() - timeStart);
		System.out.println("200 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.mergeSort(merge3);
		time = (System.nanoTime() - timeStart);
		System.out.println("500 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.mergeSort(merge4);
		time = (System.nanoTime() - timeStart);
		System.out.println("1000 Elements: " + time + " nanoseconds");
		
		timeStart = System.currentTimeMillis();
		sorters.mergeSort(merge5);
		time = (System.currentTimeMillis() - timeStart);
		System.out.println("5000 Elements: " + time + " milliseconds");
		
		System.out.println();
		
		System.out.println("Quick Sort");
		int[] quick1 = makeArray(100);
		int[] quick2 = makeArray(200);
		int[] quick3 = makeArray(500);
		int[] quick4 = makeArray(1000);
		int[] quick5 = makeArray(5000);
		
		timeStart = System.nanoTime();
		sorters.inPlaceQuickSort(quick1);
		time = (System.nanoTime() - timeStart);
		System.out.println("100 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.inPlaceQuickSort(quick2);
		time = (System.nanoTime() - timeStart);
		System.out.println("200 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.inPlaceQuickSort(quick3);
		time = (System.nanoTime() - timeStart);
		System.out.println("500 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.inPlaceQuickSort(quick4);
		time = (System.nanoTime() - timeStart);
		System.out.println("1000 Elements: " + time + " nanoseconds");
		
		timeStart = System.nanoTime();
		sorters.inPlaceQuickSort(quick5);
		time = (System.nanoTime() - timeStart);
		System.out.println("5000 Elements: " + time + " nanoseconds");	
		}
	
	public static int[] makeArray(int size) {
		Random gen = new Random();
		int[] array = new int[size];
		for(int i = 0; i < size; i++)
			array[i] = gen.nextInt(1000) + 1;
		return array;
		
	}
	
	public static int[] makeRadixArray(int size) {
		Random gen = new Random();
		int[] array = new int[size];
		for(int i = 0; i < size; i++)
			array[i] = gen.nextInt(9000) + 1000;
		return array;
		
	}
	
	
}
