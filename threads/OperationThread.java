package threads;

public abstract class OperationThread extends Thread {
	private int min, max;
	private int[] arr;
	private double answer = 0;
	public abstract double operation(int x); // Must be implemented
	
	public OperationThread(int[] arr, int min, int max) {
		this.min = min;
		this.max = max;
		this.arr = arr;
	}
	
	@Override
	public void run() {
		for (int i = min; i < max; i++) {
			answer = operation(arr[i]);
		}
	}

	public double getAnswer() {
		return answer;
	}

	public void setAnswer(double answer) {
		this.answer = answer;
	}
	
	
}
