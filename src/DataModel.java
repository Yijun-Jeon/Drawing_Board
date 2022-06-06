import java.awt.Color;
import java.awt.Point;

public class DataModel { 
	public int nDrawMode;
	public Point ptOne, ptTwo;
	public int nSize;
	public boolean bFill;
	public Color color;
	
	public DataModel() {
		nDrawMode = SimplePainterConstants.NONE;
		ptOne = new Point();
		ptTwo = new Point();
		nSize = 5;
		bFill = false;
		color = Color.black;
	}
	public DataModel(DataModel data) {
		nDrawMode = data.nDrawMode;
		ptOne = data.ptOne;
		ptTwo = data.ptTwo;
		nSize = data.nSize;
		bFill = data.bFill;
		color = data.color;
	}
}