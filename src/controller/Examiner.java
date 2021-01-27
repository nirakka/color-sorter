package controller;

import java.util.Arrays;

import utility.ColorDef;
import utility.RGB;
import utility.Range;

public class Examiner {
	private Range[] weightRange;
	private ColorDef[] colorDef;
	
	public Examiner() {}
	
	public Examiner(Range[] w, ColorDef[] c) {
		weightRange = Arrays.copyOf(w, w.length);
		colorDef    = Arrays.copyOf(c, c.length);
	}
	
	//w: Product weight rgb: Product rgb
	public int examine(int w, RGB rgb) {
		for (int i=0; i<weightRange.length; i++) {
			if (weightRange[i].inRange(w)) {
				for (int j=0; j<colorDef.length; j++) {
					int color =  colorDef[j].getColor(rgb);
					if (color > 0) {
						return color;
					}
				}
			}
		}
		
		return 0;
	}
	
}
