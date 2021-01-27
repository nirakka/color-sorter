package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import controller.ClassificationController;
import controller.EjectController;

public class RolloutTest {
	ClassificationController c;
	EjectController e;
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
		e = new EjectController(c);
		e.start();
	}
	
	@Test
	public void testNormal() {
		//c.setProductPool(products1);
		c.setTimeout(10000);
		e.setTimeout(0);
		c.setCounter(new int[] {0, 6, 0, 0});
		c.rollout();
		assertTrue(!c.getAlarmCond() /* レーン1が出荷される */);
	}
	
	@Test
	public void testTimeout() {
		c.setTimeout(10);
		e.setTimeout(10000000);
		c.setCounter(new int[] {0, 6, 0, 0});
		c.rollout();
		assertTrue(c.getAlarmCond() /* レーン1が出荷される */);
		c.setCond(false);
	}
}
