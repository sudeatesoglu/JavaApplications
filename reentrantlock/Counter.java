package reentrantlock;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Counter extends Thread {
	private String[] array;
	private HashMap<String, Integer> hMap;
	private ReentrantLock rtLock;

	// constructor to initialize
	public Counter(String[] array, HashMap<String, Integer> hMap, ReentrantLock rtLock) {
		// TODO Auto-generated constructor stub
		this.array = array;
		this.hMap = hMap;
		this.rtLock = rtLock;
	}
	
	public void run() {
		// traverse the array and increment the count of each element in the hashmap
		for (String element : array) {
			rtLock.lock(); 
			try {
				hMap.put(element, hMap.getOrDefault(element, 0) + 1);
			}
			finally {
				rtLock.unlock();  // release the lock after updated hashmap
			}
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		// create shared hashmap and reentrantlock to synchronize
		HashMap<String, Integer> hashMap = new HashMap<>();
		ReentrantLock rtLock = new ReentrantLock();
		
		// create two arrays that repeates the letter 400 times
		String[] array1 = new String[400];
		String[] array2 = new String[400];
		for (int i = 0; i < 400; i++) {
			array1[i] = "a";
	        array2[i] = "a";
		}
		
		// start and join the threads
		Counter counter = new Counter(array1, hashMap, rtLock);
		Counter counter2 = new Counter(array2, hashMap, rtLock);
		
		counter.start();
		counter2.start();
		
		counter.join();
		counter2.join();
		
		System.out.println(hashMap);  // print the counts
		// {a=800}
	}

}
