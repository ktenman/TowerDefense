package ee.tlu;
import org.lwjgl.opengl.Display;
import org.newdawn.slick.opengl.Texture;

import ee.tlu.objektid.Tile;
import ee.tlu.objektid.TileType;
import static ee.tlu.klassid.Artist.*;

public class Main {

	public Main(){
		
		BeginSession();
		
		Tile tile = new Tile(0, 0, 64, 64, TileType.Grass);
		Tile tile2 = new Tile(0, 128, 64, 64, TileType.Dirt);
		
		//Texture t = LoadTexture("ee/tlu/hoidla/dirt64.png", "PNG");
		//Texture t = QuickLoad("dirt64");
		//Texture t2 = LoadTexture("ee/tlu/hoidla/grass64.png", "PNG");
		//Texture t2 = QuickLoad("grass64");
		
		while(!Display.isCloseRequested()){ //true if the user or operating system has asked the window to close
			
			
			tile.Draw();
			tile2.Draw();
			//DrawQuadTex(tile.getTexture(),tile.getX(), tile.getY(), tile.getWidth(), tile.getHeight());
			//DrawQuadTex(tile2.getTexture(),tile2.getX(), tile2.getY(), tile2.getWidth(), tile2.getHeight());
			//DrawQuadTex(t, 0, 0, 64, 64);
			//DrawQuadTex(t2, 64, 0, 64, 64);
			
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
