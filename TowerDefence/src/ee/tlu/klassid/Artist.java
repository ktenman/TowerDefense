package ee.tlu.klassid;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2f;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Artist {
	
	public static final int WIDTH = 600, HEIGHT = 400; // final seega muutujad on suurte tähtedega
	
	public static void BeginSession(){
		Display.setTitle("Mäng"); //anname pealkirja
		
		try {
			Display.setDisplayMode(new DisplayMode(600, 400)); //peaaknale mõõdud ning loomine
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
		glOrtho(0, 600, 400, 0, 1, -1);
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
	}
	
	public static void DrawQuad(float x, float y, float width, float height){
		glBegin(GL_QUADS); // teema ruudukujulise kastikese
		glVertex2f(x, y); // vasak yleval
		glVertex2f(x + width, y); // parem yleval
		glVertex2f(x + width, y + height); // parem all
		glVertex2f(x, y + height); // vasak all
		glEnd();
	}

	
}
