package javagame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PlayerTest {

	private Player player;

	@Before
	public void setUp() throws Exception {
		player = new Player("Black");
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSet_player_name() {
		player.set_player_name("bob");
		assertEquals("bob",player.name);
	}
	
	//I was not able to test most of the methods because they are based on state
}
