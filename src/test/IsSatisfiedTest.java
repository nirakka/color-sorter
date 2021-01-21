package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import boundary.Counter;
import controller.ClassificationController;
import utility.ColorDef;
import utility.Product;
import utility.RGB;
import utility.Range;

public class IsSatisfiedTest {

ClassificationController c;
Counter ct;
    @BeforeEach
	void beforeEachTest(TestInfo testInfo) {
		System.out.printf("[%s.%s]\n", 
			testInfo.getTestClass().orElse(getClass()),
			testInfo.getDisplayName()
		);
		
		setup();
	}

	@AfterEach
	public void after() {
		System.out.println();
	}

	private void setup() {
		c = new ClassificationController();
	}
	
	private Product[] products1 = new Product[] {
			new Product( 30, new RGB(100, 100, 100)),
			new Product( 50, new RGB(200, 200, 200)),
	};
	
	class TestCaseElem {
		Range[] r;
		ColorDef[] c;
		Product[] p;
		int[] l;
		TestCaseElem (){}
		public TestCaseElem(Range[] r, ColorDef[] c, Product[] p, int[] l) {
			this.r = r;
			this.c = c;
			this.p = p;
			this.l = l;
		}
		
	}
	
	

	@Test
	public void testClassify00() {
		setup();
		assertTrue(c.getSelector().getResult()==0);
	}
	
	@Test
	public void testClassify01() {
		setup();
		
		c.setProductPool(products1);
		c.classify();
		
		assertTrue(c.getSelector().getResult()==0);
	}
	
	@Test
	public void testClassify02() {
		setup();
		
		c.setProductPool(products1);
		c.classify();
		c.classify();
		
		assertTrue(c.getSelector().getResult()==0);
	}

	TestCaseElem elem1 = new TestCaseElem (
			new Range[] { new Range(50, 100) },
			new ColorDef[] { 
					new ColorDef(new Range(100, 200), new Range(100, 200), new Range(100, 200), 1),
					new ColorDef(new Range(200, 300), new Range(200, 300), new Range(200, 300), 2),
			},
			new Product[] {
					new Product( 70, new RGB(150, 150, 150)),
					new Product( 70, new RGB(250, 250, 250)),
					new Product( 30, new RGB(250, 250, 250)),
			},
			new int[] {1, 2, 0 }
		);
	
	@Test
	public void testClassify11() {
		setup();

		c.setExaminer(elem1.r, elem1.c);
		c.setProductPool(elem1.p);
		c.classify();
		assertTrue(c.getSelector().getResult()==elem1.l[0]);
		c.classify();
		assertTrue(c.getSelector().getResult()==elem1.l[1]);
		c.classify();
		assertTrue(c.getSelector().getResult()==elem1.l[2]);
		
	}
	

	@Test
	public void testClassify99() {
		setup();
		//...
		//weightSensor.setTimeout(t, true); t時間後にtimeout
		//c.classify()
		//assertTrue(c.getSelector().getResult()==0);
	}

}