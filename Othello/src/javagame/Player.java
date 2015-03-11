package javagame;

import org.newdawn.slick.Graphics;

public class Player {

	public String color;
	public String name;
	public String tag;
	
	public Player(String player_color){
		color = player_color;
		name = "";
	}
	
	public void set_player_name(String text)
	{
		name = text;
		tag =  name + ":" + color;
	}
	
	public String get_color(){
		return color;
	}
	
	public void draw_tag(Graphics g, int x, int y){
		g.drawString("hello",0,0);
	}
}


