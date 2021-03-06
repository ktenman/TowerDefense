package ee.tlu.klassid;

import static org.lwjgl.opengl.GL11.*;
import java.io.IOException;
import java.io.InputStream;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Artist {
	
	public static final int WIDTH = 1280, HEIGHT = 960; // final seega muutujad on suurte t�htedega
	// 1280/64=20 ja 960/64=15 tilesi
	public static void BeginSession(){
		Display.setTitle("M�ng"); //anname pealkirja
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT)); //peaaknale m��dud ning loomine
			Display.create(); 
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		glMatrixMode(GL_PROJECTION);
		
		/*
		The projection matrix is used to create your viewing volume. 
		As for the modelview matrix, it is used to make various transformations to the models (objects) in your world.
 		Like this you only have to define your object once and then translate it or rotate it or scale it. 
		You would use the projection matrix before drawing the objects in your scene to set the view volume.
		Then you draw your object and change the modelview matrix accordingly. Of course you can change your
		matrix midway of drawing your models if for example you want to draw a scene and then draw some text
		(which with some methods you can work easier in orthographic projection) then change back to modelview matrix.
		 */
		glLoadIdentity();
		/*
		 * glLoadIdentity replaces the current matrix with the identity matrix.
		 * It is semantically equivalent to calling glLoadMatrix with the identity matrix
		 * 1 0 0 0 0 1 0 0 0 0 1 0 0 0 0 1
		 * but in some cases it is more efficient.
		 */
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		/*
		 * left, right
		 * Specify the coordinates for the left and right vertical clipping planes.
		 * 
		 * bottom, top
		 * Specify the coordinates for the bottom and top horizontal clipping planes.
		 * 
		 * nearVal, farVal
		 * Specify the distances to the nearer and farther depth clipping planes.
		 * These values are negative if the plane is to be behind the viewer.
		 */
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D); // lubab meil joonistada tekstuure ekraanile
		glEnable(GL_BLEND); // et enemy pilt ei oleks kandiline
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA); // et enemy pilt ei oleks kandiline	
	}

	public static void DrawQuad(float x, float y, float width, float height){
		glBegin(GL_QUADS); // teema ruudukujulise kastikese
		glVertex2f(x, y); // vasak yleval
		glVertex2f(x + width, y); // parem yleval
		glVertex2f(x + width, y + height); // parem all
		glVertex2f(x, y + height); // vasak all
		glEnd();
	}
	
	public static void DrawQuadTex(Texture tex, float x, float y , float width, float height){
		tex.bind();
		glTranslatef(x, y, 0);  // 2d m�ngul pole z koordinaati vaja -> z=0
		// x ja y muutsime 0-deks
		//System.out.println(x+" "+y);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0); // yleval vasak
		glVertex2f(0, 0);
		glTexCoord2f(1, 0); // yleval parem
		glVertex2f(width, 0); 
		glTexCoord2f(1, 1);  // all parem
		glVertex2f(width, height);
		glTexCoord2f(0, 1); // all vasak
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
	}
	
	public static Texture LoadTexture(String path, String filetype){
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(filetype, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;	
	}
	
	public static Texture QuickLoad(String name){
		Texture tex = null;
		tex = LoadTexture("ee/tlu/hoidla/" + name + ".png", "PNG");
		return tex;
	}
	
}
