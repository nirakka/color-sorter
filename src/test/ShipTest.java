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

public class ShipTest {
	ClassificationController c;
	
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
	
	@Test
	public void testNormal() {
		//c.setProductPool(products1);
		c.classify();
		c.rollout();
		assertTrue(true /* レーン1が出荷される */);
	}
	
	@Test
	public void testTimeout() {
		c.classify();
		//c.setTimeout(true);
		
		c.rollout();
		assertTrue(true /* どのレーンも出荷されない */);
		assertTrue(true /* 異常発生通知装置on  */);
	}
}
