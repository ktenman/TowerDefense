package ee.tlu;
import org.lwjgl.opengl.Display;
import static ee.tlu.klassid.Artist.*;

public class Main {

	public Main(){
		
		BeginSession();
		
		while(!Display.isCloseRequested()){ //true if the user or operating system has asked the window to close
			
			DrawQuad(50, 50, 100, 100);
			DrawQuad(150, 150, 100, 100);
			
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
