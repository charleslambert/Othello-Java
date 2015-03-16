package javagame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class WindowTest {

	private Window window;

	@Before
	public void setUp() throws Exception {
		window = new Window("bob");
		window.current_turn = "Black";
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSwitch_turns() {
		window.switch_turns();
		assertEquals("White",Window.current_turn);
	}

	//I was not able to test most of the methods because they are based on state
}
