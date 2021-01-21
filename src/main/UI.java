package main;

import controller.ClassificationController;
import controller.EjectController;

public class UI {

	public static void main(String[] args) {
		
		ClassificationController c = new ClassificationController();
		EjectController e = new EjectController(c);
		c.start();
		e.start();
	}
}