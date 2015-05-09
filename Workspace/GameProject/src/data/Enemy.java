package data;

import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;

import static helpers.Artist.*;
import static helpers.Clock.*;


public class Enemy {
	private int width, height, health, currentCheckpoint;
	private float speed, x, y;
	private Texture texture;
	private Tile startTile;
	private boolean first = true;
	private TileGrid grid;
	
	private ArrayList<Checkpoint> checkpoints;
	private int[] directions;

	
	public Enemy(Texture texture,Tile startTile, TileGrid grid , int width, int height, float speed){
		this.texture = texture;
		this.startTile = startTile;
		this.x = startTile.getX();
		this.y = startTile.getY();
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.grid = grid;
		this.checkpoints = new ArrayList<Checkpoint>();
		
		this.directions = new int[2];
		this.directions[0]= 0; //x direction
		this.directions[1]= 1;//y direction
		this.currentCheckpoint = 0;
		System.out.println("Calling for populate cp LIST");
		PopulateCheckpointList();
		
		//testing
		directions = FindNextD(startTile);
		System.out.println("startTile X:"+startTile.getXPlace()+" Y:"+startTile.getYPlace());
		
	}
	public void Update() {
		if (first)
			first = false;
		else {
			if (CheckPointReached()) {
				if (currentCheckpoint + 1 == checkpoints.size())
					System.out.println("Enemy died.");
				else
					currentCheckpoint++;
			} else {
				x += Delta() * checkpoints.get(currentCheckpoint).getxDirection() * speed;
				y += Delta() * checkpoints.get(currentCheckpoint).getyDirection() * speed;
			}
		}
	}
	
	private boolean CheckPointReached(){
		boolean reached = false;
		Tile t = checkpoints.get(currentCheckpoint).getTile();
		// check if position reached tile within 3px on variance
		if (x > t.getX()- 3 &&
				x < t.getX() + 3 &&
				y > t.getY() - 3 &&
				y < t.getY() + 3){
			reached = true;
			//set position to exact tile!
			x = t.getX();
			y = t.getY();
		}
		//System.out.println("currentCheckpoint: "+t.getXPlace());
	//	System.out.println("startTile: "+startTile);
		//System.out.println("X place: "+ getXPlace());
		return reached;
	}
	
	private void PopulateCheckpointList(){
		checkpoints.add(FindNextC(startTile, directions = FindNextD(startTile)));
		int counter = 0 ;
		boolean cont = true;
		System.out.println("Asun populeerima cp listi");
		while (cont){
			int[] currentD = FindNextD(checkpoints.get(counter).getTile());
			
			if(currentD[0] == 2 ||counter == 20 ){   // check if a next direction(checkpoint e
				cont = false;
			}else{
				checkpoints.add(FindNextC(checkpoints.get(counter).getTile(),
						directions = FindNextD(checkpoints.get(counter).getTile())));
			}
			counter++;
		}
		for(int i = 0; i<checkpoints.size();i++){
			System.out.println("CP LIST:"+checkpoints.get(i).getxDirection());
			System.out.println(checkpoints.get(i).getyDirection());
		}
		
	}
	
	private Checkpoint FindNextC(Tile s, int[] dir){
		Tile next = null;
		Checkpoint c = null;
		
		boolean found = false;
		int counter = 1;
		while(!found){
			if(s.getType() != grid.GetTile(s.getXPlace()+ dir[0]*counter, s.getYPlace() + dir[1]*counter).getType()){
				// boolean to find out if next checkpoint is true
				found = true;
				//Move counter back 1 before new tileType
				counter -= 1;
				next = grid.GetTile(s.getXPlace()+ dir[0]*counter,
						s.getYPlace() + dir[1]*counter);
				System.out.println("Next:"+next.getXPlace());
				System.out.println("Next:"+next.getYPlace());
				
			}
					counter ++;
			
		}
		c = new Checkpoint(next,dir[0], dir[1]);
		return c;
	}
	
	private int [] FindNextD(Tile s){//Tile start = s
		int[] dir = new int[2];
		Tile u = grid.GetTile(s.getXPlace(), s.getYPlace()-1);//up
		Tile r = grid.GetTile(s.getXPlace()+1, s.getYPlace());//right
		Tile d = grid.GetTile(s.getXPlace(), s.getYPlace()+1);//down
		Tile l = grid.GetTile(s.getXPlace()-1, s.getYPlace());//left
		System.out.println("Xplace:"+s.getXPlace());
		System.out.println("Yplace:"+s.getYPlace());
		
		if(s.getType() == u.getType()&& directions[1] !=1){
			dir[0] = 0;
			dir[1] = -1;
			System.out.println("Moveing Up!");
		}else if(s.getType() == r.getType()&& directions[0] !=-1){
			dir[0] = 1;
			dir[1] = 0;
			System.out.println("Moveing right!");
			
		}else if(s.getType() == d.getType()&& directions[1] !=-1){
			dir[0] = 0;
			dir[1] = 1;
			System.out.println("Moveing down!");
		}else if(s.getType() == l.getType()&& directions[0] !=1){
			dir[0] = -1;
			dir[1] = 0;
			System.out.println("Moveing left!");
		}else{
			dir[0] = 2;
			dir[1] = 2;
			System.out.println("No directions found!");
		}
	
		
		return dir;
	}
	
	private boolean pathContinues(){
		boolean answer = true;
		
		Tile myTile = grid.GetTile((int)(x/64), (int)(y/64));
		Tile nextTile = grid.GetTile((int)(x/64)+1 , (int)(y/64));
		if (myTile.getType()!=nextTile.getType())
			answer = false;
			
		
		return answer;
	}
	
	
	public void Draw(){
		DrawQuadTex(texture, x, y, width, height);
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public Texture getTexture() {
		return texture;
	}
	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	public Tile getStartTile() {
		return startTile;
	}
	public void setStartTile(Tile startTile) {
		this.startTile = startTile;
	}
	public boolean isFirst() {
		return first;
	}
	public void setFirst(boolean first) {
		this.first = first;
	}
	public TileGrid getTileGrid(){
		return grid;
	}
}
