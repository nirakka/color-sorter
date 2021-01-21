package utility;

public class RGB {
	private int r=0;
	private int g=0;
	private int b=0;

	public RGB() {}
	public RGB(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public String toString() {
		return "(" + r + "," + g + "," + b + ")";
	}
	
	public int getR() {
		return this.r;
	}

	public int getG() {
		return this.g;
	}
	
	public int getB() {
		return this.b;
	}


}
