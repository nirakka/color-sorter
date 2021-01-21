package boundary;

public class Timer {

	public int counter=0;
	private int limit=0;
	
	public void setTimeout(int t) {this.limit = t;}
	public void inc() {counter++;}
	public boolean isTimeout() {return counter > limit;}
	public void reset() {counter = 0 ;}
}
