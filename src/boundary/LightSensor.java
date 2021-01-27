package boundary;

import utility.Product;
import utility.RGB;

public class LightSensor {
	private boolean timeout=false;
	public boolean isTimeout() {return timeout;}
	public void setTimeout(boolean f) {timeout=f;}
	public LightSensor() {}

	public RGB getRGB(Product p) {
		RGB rgb = p.getRGB();
		System.out.print("rgb:"+rgb+" ");
		return rgb;
	}
}
