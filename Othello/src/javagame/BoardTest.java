package javagame;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	private Board boards;
	private Window game;

	@Before
	public void setUp() throws Exception {
		game = new Window("bob");
		game.new_game();
		boards = game.board;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDirection_array() {
		// Integer test_direction_array_length = 7
		// assertEquals(7,board.direction_array(1,0,1,0).length
		// assertEquals(0,board.direction_array(0,-1,0,0).length
	}

	@Test
	public void testAll_direction_arrays() {
		// assertEquals(8, board.all_direction_arrays(5,5).length
	}

	@Test
	public void testCount_pieces() {
		// board.count_pieces
		// assertEquals(2, board.white_count)
		// assertEquals(2, board.black_count)
	}

	@Test
	public void testCount() {
		// Space space = new Space
		// space.set_contents("Black")
		// board.count(space)
		// assertEquals(1,board.black_count)
	}

	@Test
	public void testIs_valid_move() {
		// assertEquals(true, board.is_valid_move("Black",2,3)
	}

	@Test
	public void testPossible_move() {
		// assertEquals(true, board.possible_move(2,3,"Black")
	}

	@Test
	public void testPossible_moves() {
		// assertEquals(true, board.possible_moves("Black")
	}

	@Test
	public void testNo_moves_left() {
		// assertEquals(false, board.no_moves_left("Black")
	}

	// I was not able to test most of the methods because they are based on state and the rest 
	// I was having issues with junit
}
