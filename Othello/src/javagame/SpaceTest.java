package javagame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class SpaceTest {

	public Window window;
	private Space space;
	
	@Before
	public void setUp() throws Exception {
		//Window game = new Window("Othello");
		//game.new_game();
		//space = game.board.grid[0][0];
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGet_contnets() {
		//assertEquals("Empty",space.get_contents());
	}
	// I wasn't able to do any test here because I could not figure out the error
}
