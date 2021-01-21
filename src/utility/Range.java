package utility;

public class Range {
	private int lower=0;
	private int upper=0;
	
	public Range(int l, int u) {
		lower = l;
		upper = u;
	}
	
	public boolean inRange(int w) {
		return  ( w >= lower && w <= upper);
	}

}
