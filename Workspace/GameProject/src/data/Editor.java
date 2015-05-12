package data;

import java.sql.SQLException;

import helpers.Clock;
import static helpers.Artist.HEIGHT;
import static helpers.Leveler.*;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class Editor {
	
	private TileGrid grid;
	private int index;
	private TileType[] types;
	
	public Editor(){

		this.grid = new TileGrid();
		this.index = 0;
	
		this.types = new TileType[3];
		this.types[0] = TileType.Grass;
		this.types[1] = TileType.Dirt;
		this.types[2] = TileType.Water;
		
	}
	public void update(){
		grid.Draw();
		
	//Handeling mouse input
	if(Mouse.isButtonDown(0) ){
		SetTile();
	}

	//Handle Keyboard
		while(Keyboard.next()){
			if(Keyboard.getEventKey() == Keyboard.KEY_RIGHT && Keyboard.getEventKeyState()){
				MoveIndex();
			}
			if(Keyboard.getEventKey()==Keyboard.KEY_S && Keyboard.getEventKeyState()){
				try {
					saveMap("MapTest5", grid);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	
	}
	public void SetTile(){
		grid.SetTile((int) Math.floor(Mouse.getX()/ 64),
				(int)(Math.floor((Mouse.getY()*-1)/64)+14 ),types[index]);
		}
	private void MoveIndex(){
		index++;
		if(index > types.length-1 ){
			index = 0;
			}
		}
	
}
