package utility;

public class ColorDef {

	private Range r;
	private Range g;
	private Range b;
	private int c=0;
	
	public ColorDef(Range r, Range g, Range b, int c) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.c = c;
	}
	
	public int getColor(RGB rgb) {
		if (r.inRange(rgb.getR()) && g.inRange(rgb.getG()) && b.inRange(rgb.getB()) ) {
			return c;
		}
		
		return 0;
	}
}
