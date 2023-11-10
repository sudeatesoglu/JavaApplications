package threads;

public class MultiplyThread extends OperationThread {
	
	public MultiplyThread(int[] arr, int min, int max) {
		super(arr, min, max);
		setAnswer(1);  // set initial value of answer as 1 to  avoid wrong calculation in multiplication
					   // otherwise multiplication was going to return always 0 
	}
	
	@Override
	public double operation(int x) {
		return this.getAnswer() * x;  
	}
}
