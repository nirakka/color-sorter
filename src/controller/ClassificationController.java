package controller;

import boundary.Alarm;
import boundary.Counter;
import boundary.LightSensor;
import boundary.Selector;
import boundary.Timer;
import boundary.WeightSensor;
import entity.ProductPool;
import utility.ColorDef;
import utility.Product;
import utility.RGB;
import utility.Range;
import net.jini.space.JavaSpace;
public class ClassificationController extends Thread {
	// クラス図に出現するオブジェクトをインスタンス化
	private WeightSensor weightSensor;
	private LightSensor lightSensor;
	private Examiner examiner;
	private Selector selector;
	private ProductPool pool;
	private Counter counter;
	private Timer timer;
	private Alarm alarm;
	
	private boolean received=false;
	
	public  int t1 = 10;
	private Range[] WRange = new Range[] { new Range(50, 100) };
	private ColorDef[] ColorDefs = new ColorDef[] { 
            new ColorDef(new Range(200, 300), new Range(0, 50), new Range(0, 50), 1), // red
            new ColorDef(new Range(0, 50), new Range(200, 300), new Range(0, 50), 2), // green
            new ColorDef(new Range(0, 50), new Range(0, 50), new Range(200, 300), 3), // blue
	};
	
	private Product[] products1 = new Product[] {
			new Product( 30, new RGB(100, 100, 100)),
			new Product( 50, new RGB(200, 200, 200)),
			new Product( 50, new RGB(200, 0, 0)),
			new Product( 50, new RGB(200, 0, 0)),
			new Product( 50, new RGB(200, 0, 0)),
			new Product( 50, new RGB(200, 0, 0)),
			new Product( 50, new RGB(200, 0, 0)),
			new Product( 50, new RGB(200, 0, 0)),
	};

	public ClassificationController() {
		examiner = new Examiner(WRange, ColorDefs); 
		selector = new Selector();
		weightSensor = new WeightSensor();
		lightSensor = new LightSensor();
		pool = new ProductPool(products1);
		counter = new Counter();
		timer = new Timer();
		alarm = new Alarm();
	}
	
	public void run() {
		while (!pool.isEmpty()) {
			classify(); //UC:[製品を分類する]に対応
			int b = counter.isSatisfied(); //事前条件

			System.out.println("rollout:" + b);
			if (b>0) {
				System.out.println("rollout");
				rollout();     //UC:[製品を出荷する]に対応
			}
		}
	}
		
	public void rollout() {  
		timer.setTimeout(t1);
		//addReq() 出荷要求送信
		addReq();
		
		while(true) {
			//出荷完了通知を受け取るまで
			timer.inc();
			if (timer.isTimeout()) {
				System.out.println("timer: "+timer.counter);
				alarm.on();
				break;
			} else if (received) {
				System.out.println("timer: "+timer.counter);
				timer.reset();
				break;
			}
			System.out.println("why this can't loop");
		}

		removeReq();
		
		System.out.println("finish");
		System.out.println("Product: " + pool.getProduct());

	}
	
	public void setTimeout(int t) {this.t1 = t;}
	public boolean getAlarmCond() {return alarm.getCond();}
	public void setCounter(int [] cnt) {counter.setCounter(cnt);}
	public void setCond(boolean b) {alarm.setCond(b);}

	private void removeReq() {
		System.out.println("removereq called");
		Lookup finder = new Lookup(JavaSpace.class);
		JavaSpace space = (JavaSpace) finder.getService();
		if( space == null )
		{
		    System.err.println("No JavaSpace found.");
		    System.exit( 1 );
		}

		Message template = new Message();
		Message result;
		try
		{
		    result = (Message)space.take(template, null, Long.MAX_VALUE);
		    System.out.println("HelloTaker: took ["+result.content+"]");
		    
		}
		catch( Exception e )
		{
		    System.err.println("JavaSpace read error "+e.getMessage());
		    e.printStackTrace();
		    System.exit( -1 );
		}
	}

	private void addReq() {
		Lookup finder = new Lookup(JavaSpace.class);
		JavaSpace space = (JavaSpace) finder.getService();
		if( space == null )
		{
		    System.err.println("No JavaSpace found.");
		    System.exit( 1 );
		}
		Message msg = new Message();
		msg.content = "Eject" ;
		try
		{
			space.write(msg, null, net.jini.core.lease.Lease.FOREVER);
		    System.out.println("EjectRequested: wrote["+msg.content+"]");
		}
		catch( Exception e )
		{
		    System.err.println("JavaSpace write error "+e.getMessage());
		    e.printStackTrace();
		    System.exit( -1 );
		}
	}

	public void classify() {
		Product p = pool.getProduct();
		int w = weightSensor.getWeight(p);
		RGB rgb = lightSensor.getRGB(p);
		int lane = examiner.examine(w, rgb);
		selector.select(lane);
		counter.inc(lane);
		System.out.println("counter:" + counter);
	}

	public void setExaminer(Range[] r, ColorDef[] c) {
		examiner = new Examiner(r, c);
	}

	public Selector getSelector() {
		return selector;
	}

	public void setProductPool(Product[] products) {
		pool.setProducts(products);
	}

	public void response() {
		received = true;
	}
	
}