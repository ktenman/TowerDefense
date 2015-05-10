package ee.tlu;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*; // impordime staatilise meetodi, et saaks koodi lühemalt kirjutada

public class Main {

	public Main(){
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
		while(!Display.isCloseRequested()){ //true if the user or operating system has asked the window to close
			
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
