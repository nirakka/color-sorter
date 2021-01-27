package boundary;

public class Alarm {
	private boolean cond = false;
	
	public void on() {
		System.out.println("alarm is on!");
		this.cond = true;
	}
	
	public boolean getCond() {
		return this.cond;
	}
	
	public void setCond(boolean b) {this.cond=b;}
}
