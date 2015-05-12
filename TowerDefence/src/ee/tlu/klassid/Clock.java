package ee.tlu.klassid;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

import org.lwjgl.Sys;

public class Clock {
	private static boolean paused = false;
	public static long LastFrame, totalTime;
	public static float d = 0, multiplier = 1;
	public Timer t = new Timer();
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();

	public Clock(){
		System.out.println(dateFormat.format(date));
	}
	
	// To get milliseconds from Sys.getTime(), the 'ticks' are multiplied by 1000
	//so that the end result will be in milliseconds and then divide by the number of ticks in a second.
	
	public static long getTime(){
		return Sys.getTime() * 1000 / Sys.getTimerResolution(); // 60 fps
	}
	
	public static float getDelta(){
		long currentTime = getTime();
		int delta = (int) (currentTime - LastFrame);
		LastFrame = getTime();
		return delta * 0.01f; // float v‰‰rtuse tagastamiselt kirjutame numbri lıppu F
	}
	/*
	public static float Delta(){
		if (paused){
			return 0;
		} else {
			
		}
	}
	*/
	
	public static void main(String[] args){
		new Clock();
		System.out.println(Sys.getTime());
		System.out.println(getTime());
		System.out.println(Sys.getTimerResolution());
		
	}
}
