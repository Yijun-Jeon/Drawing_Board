import java.awt.Point;
import java.util.ArrayList;

public class PenModel extends DataModel{
	ArrayList<Point> pens;
	
	public PenModel() {
		super();
		pens = new ArrayList<Point>();
	}
	
	public PenModel(PenModel data) {
		super(data);
		pens = new ArrayList<Point>();
		for(Point pt: data.pens)
			pens.add(new Point(pt));
	}
	
	
	public void addPenData(Point data) {
		pens.add(new Point(data));
	}
	
	public void clear() {
		pens.clear();
	}
}
