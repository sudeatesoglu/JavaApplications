package reentrantlock;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class Counter extends Thread {
	private String[] array;
	private HashMap<String, Integer> hMap;
	private ReentrantLock rtLock;

	// constructor for initializing
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
		
		// create two arrays that repeates the same letter 400 times
		String[] array1 = new String[400];
		String[] array2 = new String[400];
		for (int i = 0; i < 400; i++) {
			array1[i] = "a";
			array2[i] = "a";
		}
		
		// create another array mix of different letters
		String[] array3 = {"x", "y", "z", "x", "y", "z", "x", "x", "x"};

		// start and join the threads
		Counter counter1 = new Counter(array1, hashMap, rtLock);
		Counter counter2 = new Counter(array2, hashMap, rtLock);
		Counter counter3 = new Counter(array3, hashMap, rtLock);
		
		counter1.start();
		counter2.start();
		counter3.start();
		
		counter1.join();
		counter2.join();
		counter3.join();
		
		// print the counts
		for (String key : hashMap.keySet()) {
			System.out.println(key + ": " +hashMap.get(key));  
		}
		
		/* a: 800
		   x: 5
		   y: 2
		   z: 2 */
	}
}
