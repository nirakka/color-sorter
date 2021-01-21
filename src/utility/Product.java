package utility;

public class Product {
	private int weight=0;
	private RGB rgb;

	public Product(int w, RGB r) {
		weight = w;
		rgb = r;
	}

	public int getWeight() {
		return weight;
	}

	public RGB getRGB() {
		return rgb;
	}
	
	public String toString() {
		return weight + "";
	}
}
