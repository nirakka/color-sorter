package boundary;

import java.util.Arrays;

public class Counter {
	private int[] value=new int[4];
	
	private static final int LIMIT=6;
	public Counter() {}
	
	public void reset() {
		Arrays.fill(value, 0);
	}
	
	public void inc(int k) {
		if (k<value.length) value[k]++;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("bad="+value[0]);
		sb.append(" r="+value[1]);
		sb.append(" g="+value[2]);
		sb.append(" b="+value[3]);
		
		return sb.toString();
	}

	public int isSatisfied() {
		for (int i=1; i<value.length; i++) {
			if (value[i] == LIMIT ) {
				return i;
			}
		}
		return 0;
	}
}
