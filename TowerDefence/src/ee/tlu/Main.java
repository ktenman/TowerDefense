package ee.tlu;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import ee.tlu.klassid.Enemy;
import ee.tlu.objektid.Tile;
import ee.tlu.objektid.TileType;
import static ee.tlu.klassid.Artist.*;

public class Main {

	public Main(){
		
		BeginSession();

		//Texture t = LoadTexture("ee/tlu/hoidla/dirt64.png", "PNG");
		//Texture t2 = LoadTexture("ee/tlu/hoidla/grass64.png", "PNG");
		//Texture t = QuickLoad("dirt64");
		//Texture t2 = QuickLoad("grass64");
		Tile tile = new Tile(0, 8, 64, 64, TileType.Water);
		//Tile tile2 = new Tile(0, 128, 64, 64, TileType.Dirt);
		int[][] map = {
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},	
		};

		
		TileGrid grid = new TileGrid(map);
		/*
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.println(i+" "+j+" "+grid.GetTile(i, j));
			}
		}
		grid.SetTile(4, 4, TileType.Water);
		*/
		//System.out.println(grid.GetTile(4, 4));
		
		//grid.SetTile(1, 4, TileType.Water);
		//grid.SetTile(2, 4, TileType.Water);
		//grid.SetTile(0, 0, TileType.Water);
		//System.out.println(grid.GetTile(0, 0).getType());
		//grid.SetTile(5, 5, grid.GetTile(0, 0).getType());
		/*
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				System.out.println(map[i][j]);		
			}
		}
		*/
	//Enemy e = new Enemy(QuickLoad("ufo64"), grid.GetTile(6, 5), 64, 64, 2);
		Enemy e = new Enemy(QuickLoad("ufo64"), grid.GetTile(5, 5), 64, 64, 4);
		while(!Display.isCloseRequested()){ //true if the user or operating system has asked the window to close	
			DrawQuadTex(tile.getTexture(),tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
			//DrawQuadTex(tile2.getTexture(),tile2.getX(), tile2.getY(), tile2.getWidth(), tile2.getHeight());
			//DrawQuadTex(t, 0, 0, 64, 64);
			//DrawQuadTex(t2, 64, 0, 64, 64);
			//tile.Draw();
			//tile2.Draw();
			DrawQuadTex(e.getTexture(), e.getX(), e.getY(), e.getWidth(), e.getHeight());
			grid.Draw();
			e.Draw();
			//e.Draw();
			//DrawQuad(50, 50, 100, 100);
			//DrawQuad(150, 150, 100, 100);
			
			/*
			glBegin(GL_LINES); // joone joonistamiseks
			glVertex2f(10, 10); // alguskoordinaadid
			glVertex2f(100, 50); // lõppkoordinaadid
			glEnd();
			*/ 
		
			// ekraani refreshimine + saime lahti veast NOT RESPONDING
			Display.update();
			Display.sync(60); //frames per second
			//
		}
		Display.destroy(); //akna sulgemine mängu lõpus
	}

	public static void main(String[] args) {
		new Main();
	}

}
