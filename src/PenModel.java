import java.awt.Color;
import java.util.ArrayList;

public class PenModel {
	ArrayList<DataModel> pens;
	public int nSize;
	public Color color;
	
	public PenModel() {
		pens = new ArrayList<DataModel>();
		nSize = 5;
		color = Color.black;
	}
	
	public PenModel(PenModel data) {
		pens = new ArrayList<DataModel>();
		for(DataModel d: data.pens)
			pens.add(new DataModel(d));
		nSize = data.nSize;
		color = data.color;
	}
	
	public void setSize(int size) {nSize = size;}
	public void setColor(Color c) {color = c;}
	
	public void addPenData(DataModel data) {
		pens.add(new DataModel(data));
	}
	
	public void clear() {
		pens.clear();
	}
}
