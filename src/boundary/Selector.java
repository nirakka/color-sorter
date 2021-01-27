package boundary;

public class Selector {
	
	public Selector() {};
	
	private int result=0;
	public void select(int n) {
		result = n;
		System.out.format("lane:%d ", n);
		System.out.println();		
	}
	
	public int getResult() {
		return result;
	}
	
}
