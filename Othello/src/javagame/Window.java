package javagame;

import java.awt.Font;
import java.util.Hashtable;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Input;
import org.newdawn.slick.gui.TextField;

public class Window extends BasicGame 
{
	public Hashtable<String, Integer> thing;
	public static Player player1;
	public static Player player2;
	public static Board board;
	public static String current_turn;
	public Input input = new Input(450);
	public static TextField text;
	public static Boolean get_text;
	public static Boolean show_score;
	public static Boolean show_win;
	public static Boolean show_turn;
	public static Boolean show_invalid_move;
	public static Boolean show_close_game;
	public static Boolean show_reset_game;
	public static Boolean show_player_info;
	public static Boolean show_player;
	private static String win;
	public String p_one_info;
	public String p_two_info;
	public static String reset_game;
	public static String close_game;
	public static String invalid_move;
	private static boolean end_of_game;
	public int window_width;
	public int window_height;
	public Font font;
	public TrueTypeFont ttf;
	private String score;
	
	public Window(String title) throws SlickException 
	{
		super(title);
	}

	public static void main(String[] args) throws SlickException 
	{
		AppGameContainer appgc;
		Window game = new Window("Othello");
		try
		{
			appgc = new AppGameContainer(game, 400, 450, false);
			appgc.setShowFPS(false);
			appgc.start();
			
		}
		catch(SlickException e)
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void init(GameContainer gc) throws SlickException 
	{
		font = new Font("Times New Roman", 0, 20);
		ttf = new TrueTypeFont(font, true);
		window_height = gc.getHeight();
		window_width = gc.getWidth();
		text = new TextField(gc, gc.getDefaultFont(),0,0, window_width, window_height);
		new_game();
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException 
	{
		skip_turn(player1,player2,current_turn);
	}
	
	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException 
	{
		board.draw_board();
		draw_player_info();
		draw_player(player1);
		draw_player(player2);
		draw_current_turn();
		draw_score();
		draw_win();
		draw_invalid_move();
		draw_close_game();
		draw_reset_game();
	}
	
	public void skip_turn(Player player1, Player player2, String current_turn)
	{
		if (player1.color == current_turn && board.no_moves_left(player1.color))
		{
			switch_turns();
		}
		else if (player2.color == current_turn && board.no_moves_left(player2.color))
		{
			switch_turns();
		}
	}
	
	@Override
	public void mousePressed(int button, int x, int y)
	{
		int msleft = 0;
		if (button == msleft && get_text == false && end_of_game == false)
		{
			player_turn(player1,player2,current_turn);
			is_it_end_game(player1,player2);
		}
	}
	
	public void keyPressed(int key, char c) 
	{
		int kbC = Input.KEY_C;
		int kbR = Input.KEY_R;
		int kbReturn = Input.KEY_RETURN;
		
		if (key == kbC && get_text == false)
		{
				System.exit(0);
		}
		else if (key == kbR && get_text == false)
		{
				try {
					reset();
				} catch (SlickException e) {
					e.printStackTrace();
				}
		}
		else if (key == kbReturn && get_text == true)
		{
			if (current_turn == player1.color)
			{
			player1.set_player_name(text.getText());
			text.setText("");
			current_turn = "White";
			}
			else
			{
				player2.set_player_name(text.getText());
				show_player_info = false;
				show_player = true;
				show_turn = true;
				current_turn = "Black";
				text.setFocus(false);
				text.setText("");
				get_text = false;
			}
		}
	}	
	
	public void player_turn(Player player1,Player player2,String current_turn)
	{
		if (player1.color == current_turn)
		{
			select_space(player1.get_color());
		} 
		else 
		{
			select_space(player2.get_color());
		}
	}
	
	public void select_space(String color)
	{
		int x = (int) Math.floor((input.getMouseX() / 50));
		int y = (int) Math.floor((input.getMouseY() / 50) -1);
		
		if (board.is_valid_move(color,x,y) == true)
		{
			valid_move(color,x,y);
		}
		else
		{
			invalid_move();
		}
	}
	
	public void valid_move(String color,int mouse_x,int mouse_y)
	{
		board.make_move(color, mouse_x, mouse_y);
		show_invalid_move = false;
		switch_turns();
	}
	
	public void invalid_move()
	{
		show_invalid_move = true;
	}
	
	public void switch_turns()
	{
		if (current_turn == "Black")
		{
			current_turn = "White";
		}
		else 
		{
			current_turn = "Black";
		}
	}

	
	public void is_it_end_game(Player player1,Player player2)
	{
		if (board.no_moves_left(player1.color) && board.no_moves_left(player2.color))
		{
			end_game(player1,player2);
		}
	}
	
	public void end_game(Player player1, Player player2)
	{
		final_score();
		check_win(player1,player2);
		show_close_game = true;
		show_reset_game = true;
		show_player = false;
		end_of_game = true;
	}
	
	public void final_score()
	{
		board.count_pieces();
		show_score = true;
		score = "Black "+board.black_count+" White "+board.white_count;
	}
	
	public void check_win(Player player1, Player player2)
	{
		if (board.black_count > board.white_count)
		{
			win = player1.name+" Wins";
		}
		else if (board.black_count == board.white_count)
		{
			win = "Tie";
		}
		else
		{
			win = player2.name+" Wins";
		}
		
		show_win = true;
		show_turn = false;
	}
	
	public void reset() throws SlickException
	{
		new_game();
	}
	
	public static void new_game() throws SlickException
	{
		player1 = new Player("Black");
		player2 = new Player("White");
		board = new Board();
		current_turn = "Black";
		win = "";
		end_of_game = false;
		create_text_images();
		show_only_player_info();
		text.setText("");
		text.setFocus(get_text = true);
		text.setConsumeEvents(false);
	}

	public static void create_text_images()
	{
		reset_game = "Press R \nto reset game";
		close_game = "Press C \nto close game";
		invalid_move = "Sorry Invalid Move";
	}
	
	public static void show_only_player_info()
	{
		show_score = false;
		show_win = false;
		show_turn = false;
		show_invalid_move = false;
		show_close_game = false;
		show_reset_game = false;
		show_player_info = true;
		show_player = false;
	}
	
	public int middle_of_screen(String text)
	{
		return ((window_width/2)-(ttf.getWidth(text)/2));
	}

	public void draw_player(Player player)
	{
		if (show_player == true)
		{
			
			if (player.color == "Black")
			{
				 ttf.drawString(0,0,player.tag);
			}
			else if (player.color == "White")
			{
				ttf.drawString(window_width-ttf.getWidth(player.tag),0,player.tag);
			}
		}
	}
	
	public void new_line_wrap(String text,int x,int y)
	{
		for (String line : text.split("\n"))
		{
			ttf.drawString(x, y, line);
			y += ttf.getLineHeight();
		}
	}
	
	public void draw_player_info()
	{
		p_one_info = "Your color is "+player1.color+". \nName:"+text.getText();
		p_two_info ="Your color is "+player2.color+". \nName:"+text.getText();
		
		if (show_player_info == true)
		{
			if (current_turn == player1.color)
			{
				new_line_wrap(p_one_info,120,0);
			}
			else
			{
				new_line_wrap(p_two_info,120,0);
			}
				
		}
	}
	
	public void draw_reset_game()
	{
		if (show_reset_game == true)
		{
			new_line_wrap(reset_game, 0,0);
		}
	}
	
	public void draw_close_game()
	{
		if (show_close_game == true)
		{
			new_line_wrap(close_game, window_width-110, 0);
		}
	}
	
	public void draw_invalid_move()
	{
		if (show_invalid_move == true)
		{
			ttf.drawString(middle_of_screen(invalid_move), ttf.getHeight(), invalid_move);
		}
	}
	
	public void draw_win()
	{
		if (show_win == true)
		{
			ttf.drawString(middle_of_screen(win),0,win);
		}
	}
	
	public void draw_score()
	{
		if (show_score == true)
		{
			ttf.drawString(middle_of_screen(score), ttf.getHeight(), score);
		}
	}
	
	public void draw_current_turn()
	{
		if (show_turn == true)
		{
			ttf.drawString(middle_of_screen(current_turn), 0, current_turn);
		}
	}
}
