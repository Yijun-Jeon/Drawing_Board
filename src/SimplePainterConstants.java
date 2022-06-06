import java.awt.Color;

public class SimplePainterConstants {

	public static final int DOT = 0;
	public static final int LINE = 1;
	public static final int RECT = 2;
	public static final int OVAL = 3;
	public static final int FREE = 4;
	public static final int UNDO = 5;
	public static final int REDO = 6;
	public static final int CLEAR = 7;
	public static final int NONE = 8;
	
	
	public static final String[] MENU = {
			"DOT", "LINE", "RECT", "OVAL",
			"FREE", "UNDO", "REDO", "CLEAR" }; 
	
	public static final Color[] MENU_COLOR = {
			Color.white, Color.blue,
			Color.yellow, Color.red
	};
	
} 
