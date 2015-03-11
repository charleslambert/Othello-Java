package javagame;

import java.util.Hashtable;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Space {

	private Hashtable<String,Image> images;
	private String current_state;
	private Image image;
	
	public Space() throws SlickException{
		images = new Hashtable<String,Image>();
			images.put("White",new_image("res/White_Circle.png"));
			images.put("Black",new_image("res/Black_Circle.png"));
			images.put("Empty",new_image("res/Empty_Space.png"));
		current_state = "Empty";
		image = images.get(current_state);
	}
	
	public Image new_image(String filename) throws SlickException{
		return new Image(filename);
	}
	
	public String get_contents(){
		return current_state;
	}
	
	public void set_contents(String state){
		current_state = state;
		image = images.get(state);
	}
	
	public void draw_space(int x,int y){
		image.draw(x*50,(1+y)*50);
	}
}

