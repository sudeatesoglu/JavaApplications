package threads;

import java.util.Arrays;

public class Operator {
	public static double sum(int[] arr, int numThreads) throws InterruptedException {	
		// initialize values that will be used
		int len = arr.length;
		int limit = len / numThreads;
		OperationThread[] threads = new OperationThread[numThreads];

		// for loop to start and join threads
		for (int i = 0; i < numThreads; i++) {
			int start = i * limit;
			int end = (i+1) * limit;
			threads[i] = new SumThread(arr, start, end);
			threads[i].start();
		}
		
		double result = 0;
		for (int i = 0; i < numThreads; i++) {
			threads[i].join();
			result += threads[i].getAnswer();
		}
		
		return result;
	}
	
	public static double average(int[] arr, int numThreads) throws InterruptedException {
		// not implemented threads again, average already calculates using sum method in itself
		double result = 0;
		result = sum(arr, numThreads) / arr.length;
		return result;
	}
	
	public static double multiply(int[] arr, int numThreads) throws InterruptedException {
		int len = arr.length;
		int limit = len / numThreads;
		OperationThread[] threads = new OperationThread[numThreads];

		for (int i = 0; i < numThreads; i++) {
			int start = i * limit;
			int end = (i+1) * limit;
			threads[i] = new MultiplyThread(arr, start, end);
			threads[i].start();
		}
		
		double result = 1;
		for (int i = 0; i < numThreads; i++) {
			threads[i].join();
			result *= threads[i].getAnswer();
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		int[] arr = {1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12, 13};
		int[] arr2 = {1, 2, 3, 4, 5, 6};
		try {
			double sumResult2 = Operator.sum(arr2, 2);
			double avgResult2 = Operator.average(arr2, 2);
			double multResult2 = Operator.multiply(arr2, 2);
			
			double sumResult = Operator.sum(arr, 3);
			double avgResult = Operator.average(arr, 3);
			double multResult = Operator.multiply(arr, 3);
			
			System.out.println("Array entered: " + Arrays.toString(arr2));
			System.out.println("Summation result: " + sumResult2);
			System.out.println("Average of the elements: " + avgResult2);
			System.out.println("Multiplication of the elements: " + multResult2);
			
			System.out.println();
			
			System.out.println("Array entered: " + Arrays.toString(arr));
			System.out.println("Summation result: " + sumResult);
			System.out.println("Average of the elements: " + avgResult);
			System.out.println("Multiplication of the elements: " + multResult);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
