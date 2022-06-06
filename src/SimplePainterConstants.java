import java.awt.Color;

/*
 * FREE의 경우에는 펜으로 그리는 경우를 표현하기 위해 마우스 이동마다 각 포인트를 drawOval로 그리려 했지만
 * Oval의 한계 때문인지 마우스를 빠르게 움직일 경우 띄엄띄엄 나오게 되었습니다
 * 
 * UNDO와 REDO의 경우에는 DataModel과 PenModel이 따로 있기 때문에
 * FREE 데이터를 undo,redo 하기 위해서는 FREE버튼을 눌러준 뒤에 실행하여야 합니다.
 * 
 * PenModel을 DataModel을 상속받아서 한번에 undo,redo가 되도록 하려했지만
 * DrawingPanel repaint()시 savedData 인자 전달에서 막혀서 실패했습니다..
 */
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
	
} // SimplePainterConstants class
