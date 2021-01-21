package controller;

import net.jini.space.JavaSpace;
public class EjectController extends Thread {
	//private Ejector[] ejectors= new Ejector[4];
	private ClassificationController c;
	public EjectController(ClassificationController c) {
//		for(Ejector e: ejectors) {
//			e = new Ejector();
//		}
		this.c = c;
	};

	public void run() {
		while(true) {
			System.out.println("ejector running");
			String lane = readReq();
			System.out.println("lane:" + lane);
			if (lane != null) {
				eject();
			}
		}
		
	}
	
	public void eject() {	
//		int lane = 1; /* req.getLane() */
//		ejectors[lane].eject();
		System.out.println("Ejected!");
		
		try {  
			Thread.sleep(500);
		}catch (InterruptedException e){}
		c.response();
	}

	public String readReq() {
		Lookup finder = new Lookup(JavaSpace.class);
		JavaSpace space = (JavaSpace) finder.getService();
		if( space == null )
		{
		    System.err.println("No JavaSpace found.");
		    return "0";
		}

		Message template = new Message();
		Message result;
		try
		{
		    result = (Message)space.read(template, null, Long.MAX_VALUE);
		    System.out.println("HelloReader: read ["+result.content+"]");
		    
		    return result.content;
		}
		catch( Exception e )
		{
		    System.err.println("JavaSpace read error "+e.getMessage());
		    e.printStackTrace();
		}
		
		return "0";
	}
}