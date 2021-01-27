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

Counter c = new Counter();
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

	private void setup() {	}
	

	@Test
	public void testClassify00() {
		c.setCounter(new int[] {0, 0, 0, 0});
		assertTrue(c.isSatisfied() == 0);
	}
	
	@Test
	public void testClassify01() {
		c.setCounter(new int[] {0, 1, 0, 0});
		assertTrue(c.isSatisfied() == 0);
	}
	
	@Test
	public void testClassify02() {
		c.setCounter(new int[] {0, 5, 0, 0});
		assertTrue(c.isSatisfied() == 0);
	}
	
	@Test
	public void testClassify03() {
		c.setCounter(new int[] {0, 6, 0, 0});
		assertTrue(c.isSatisfied() == 1);
	}

}