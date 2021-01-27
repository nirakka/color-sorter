package boundary;

import utility.Product;

public class WeightSensor {
	
	private boolean timeout=false;
	public boolean isTimeout() {return timeout;}
	public void setTimeout(boolean f) {timeout=f;}
	public WeightSensor() {}
	
	public int getWeight(Product p) {
		int w = p.getWeight();
		System.out.print("w:"+w+" ");
		return w;
	}
}
