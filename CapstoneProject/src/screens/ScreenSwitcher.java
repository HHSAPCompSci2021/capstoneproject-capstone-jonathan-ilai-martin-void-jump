package screens;


public interface ScreenSwitcher {
	public static final int BEGINNING_SCREEN = 0;
	public static final int MENU_SCREEN = 1;
	public static final int LEVEL1 = 2;
	public static final int LEVEL2 = 3;
	public static final int LEVEL3 = 4;
	public static final int LEVEL4 = 5;
	public static final int LEVEL5 = 6;

	public static final int INSTRUCTIONS = 7;
	public static final int LEVEL6 = 8;
	public static final int LEVEL7 = 9;
	public static final int LEVEL8 = 10;
	public static final int LEVEL9 = 11;
	public static final int LEVEL10 = 12;

	public void switchScreen(int i);
}
