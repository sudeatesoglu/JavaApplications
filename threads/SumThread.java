package threads;

public class SumThread extends OperationThread {
	
	public SumThread(int[] arr, int min, int max) {
		super(arr, min, max);
	}
	
	@Override
	public double operation(int x) {
		return this.getAnswer() + x;
	}
}
