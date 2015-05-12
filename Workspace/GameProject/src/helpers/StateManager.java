package helpers;

import data.Editor;
import data.Game;
import data.MainMenu;

public class StateManager {
	
	public static enum GameState{
		MAINMENU, GAME, EDITOR
	}
	
	public static GameState gameState = GameState.MAINMENU;
	public static MainMenu mainMenu;
	public static Game game;
	public static Editor editor;
	
	public static void update (){
		switch(gameState){
		case MAINMENU:
			if (mainMenu == null)
				mainMenu = new MainMenu();
			mainMenu.update();
			break;
		case GAME:
			if (game== null)
				game = new Game();
			System.out.println("Game mode not working, update needed!");
			//game.update();
			break;
		case EDITOR:
			if (editor== null)
				editor = new Editor();
			editor.update();
			
			break;
		}
	}
	public static void setState(GameState newState){
		gameState = newState;
	}
}
