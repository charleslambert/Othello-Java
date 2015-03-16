package javagame;

import java.util.ArrayList;

import org.newdawn.slick.SlickException;

public class Board {
	
	public Integer white_count;
	public Integer black_count;
	public Space[][] grid = new Space[8][8];
	
	public Board() throws SlickException{
		white_count = 0;
		black_count = 0;
		make_grid();
		othello_board_start();
	}

	public void make_grid() throws SlickException{
		for (int x=0; x<8; x++)
			for (int y=0; y<8; y++)
				grid[x][y] = new Space();
	}
	
	public ArrayList<Space> direction_array(int delta_x,int delta_y,int pos_x,int pos_y){
		pos_x = pos_x + delta_x;
		pos_y = pos_y + delta_y;
		ArrayList<Space> direction_array = new ArrayList<Space>();
		while (pos_x <= 7 && pos_x >= 0 && pos_y <= 7 && pos_y >= 0){
			direction_array.add(grid[pos_x][pos_y]);
			pos_x += delta_x;
			pos_y += delta_y;
		
		}
		direction_array.trimToSize();
		return direction_array;
		
	}
	
	public  ArrayList<ArrayList<Space>> all_direction_arrays(int pos_x,int pos_y){
		ArrayList<ArrayList<Space>> all_direction_arrays = new ArrayList<ArrayList<Space>>();
		for (int dx=-1; dx<2; dx++)
			for (int dy=-1; dy<2; dy++)
				if (!(dx == 0 && dy == 0)){
					all_direction_arrays.add(direction_array(dx,dy,pos_x,pos_y));
				}
		all_direction_arrays.trimToSize();
		return all_direction_arrays;
	}
	
	public boolean valid_directions(int pos_x,int pos_y,String player_color){
		for (ArrayList<Space> direction_array : all_direction_arrays(pos_x,pos_y)){
			if (true == is_valid_direction(player_color,direction_array)){
				return true;
			}
		}
		return false;
	}
	
	
	public Boolean is_valid_direction(String player_color, ArrayList<Space> direction_array){
		Boolean seen_opp_color = false;
		for (Space space : direction_array){
			String content = space.get_contents();
			if (content == "Empty"){
				return false;
			} else if (content == player_color){
				if (seen_opp_color == true){
					return true;
				} else{
					return false;
				}
			}else {
				seen_opp_color = true;
			}
		}
		return false;
	}
	
	
	public void change_pieces(String player_color,ArrayList<Space> direction_array){
		String opp_color = player_color == "White" ? "Black" : "White";
		for (Space space : direction_array){
			String content = space.get_contents();
			if (content == "Empty"){
				break;
			}
			else if (content == opp_color){
				space.set_contents(player_color);
			}
			else {
				break;
			}
		}
	}
	
	public void change_all_pieces(int pos_x, int pos_y, String player_color){
		for (ArrayList<Space> direction_array : all_direction_arrays(pos_x,pos_y)){
			if (true == is_valid_direction(player_color,direction_array)){
				change_pieces(player_color,direction_array);
			}
		}
		
	}
	
	public void count_pieces()
	{
		for(Space[] column : grid)
		{
			for(Space space : column)
			{
				count(space);
			}
		}
			
	}
	
	public void count(Space space)
	{
		if (space.get_contents() == "White")
		{
			white_count += 1;
		}
		else if (space.get_contents() == "Black")
		{
			black_count += 1;
		}
	}
	
	public void othello_board_start()
	{
		grid[3][3].set_contents("White");
		grid[4][4].set_contents("White");
		grid[3][4].set_contents("Black");
		grid[4][3].set_contents("Black");
		//grid[0][0].set_contents("white");
		//grid[1][0].set_contents("Black");
	}
	
	public void draw_board(){
		for (int x=0; x<8; x++){
			for (int y=0; y<8; y++){
				grid[x][y].draw_space(x,y);
			}
		}
	}
	
	public boolean is_valid_move(String color,int x,int y)
	{
		if (y < 0)
		{
			return false;
		}
		if (grid[x][y].get_contents() == "Empty" && valid_directions(x,y,color)){
			return true;
		} 
		else {
			return false;
		}
	}
	
	public void make_move(String color,int x,int y){
		grid[x][y].set_contents(color);
		change_all_pieces(x,y,color);
	}
	
	public boolean possible_move(int x,int y,String player_color)
	{
		if (grid[x][y].get_contents() == "Empty" && valid_directions(x,y,player_color))
		{
			return true;
		}
		return false;
	}
	
	public boolean possible_moves(String player_color)
	{
		for (int x = 0;x<8;++x)
		{
			for (int y=0;y<8;++y)
				if (possible_move(x,y,player_color) == true)
				{
					return true;
				}
		}
		return false;
	}
	
	public boolean no_moves_left(String player_color)
	{
		if (possible_moves(player_color) == true)
		{
			return false;
		}
		else 
		{
			return true;
		}
	}
	
	
}

