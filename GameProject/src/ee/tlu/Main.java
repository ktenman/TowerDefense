package ee.tlu;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Main {

	public Main(){
		Display.setTitle("Mäng");
		try {
			Display.setDisplayMode(new DisplayMode(600, 400));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new Main();
		

	}

}
