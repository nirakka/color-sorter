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
		c.setCounter(new int[] {0, 6, 0, 0});
	}
	
	class DelayParam{
		public DelayParam(int i, int j) {
			cl = i;
			sh = j;
		}
		int cl;
		int sh;
		
	};
	
	
	
	DelayParam[] param00 = new DelayParam[] {
			new DelayParam(0,0),
			new DelayParam(10000, 0),
			new DelayParam(1000000, 0),
			new DelayParam(10,   1000000),
			new DelayParam(100000, 100000),
	};
	
	boolean[] results00 = new boolean[] {
			false,
			false,
			false,
			true,
			false,
	};
	
	private void testrolloutX(int k) {
		c.setTimeout(param00[k].cl);
		e.setTimeout(param00[k].sh);
		
		c.rollout();
		assertTrue(c.getAlarmCond() == results00[k]);
	}
	
	@Test
	public void testrollout01() {
		testrolloutX(1);
	}
	
	@Test
	public void testrollout02() {
		testrolloutX(2);
	}
	
	@Test
	public void testrollout03() {
		testrolloutX(3);
	}
	
	@Test
	public void testrollout04() {
		testrolloutX(4);
	}
}
