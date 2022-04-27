package screens;


public interface ScreenSwitcher {
	public static final int BEGINNING_SCREEN = 0;
	public static final int MENU_SCREEN = 1;
	public static final int LEVEL1 = 2;
	
	public void switchScreen(int i);
}
