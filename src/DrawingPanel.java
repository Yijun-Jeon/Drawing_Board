import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Stack;

public class DrawingPanel extends JPanel {

	ArrayList<PenModel> dataList;
	PenModel data;
	boolean bDrag;
	SimplePainterView view;
	Stack<PenModel> undoList;
	
	public DrawingPanel(SimplePainterView v) {
		setBackground(Color.white);
		setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
		
		view = v;
		
		dataList = new ArrayList<PenModel>();
		data = new PenModel();
		undoList = new Stack<PenModel>();
	
		bDrag = false;
	}
	
	@Override
	public void paintComponent(Graphics page) {
		super.paintComponent(page);
		
		Graphics2D page2 = (Graphics2D) page;
		
		if (bDrag) { 
			page.setColor(data.color);
			page2.setStroke(new BasicStroke(data.nSize));
			
			if (data.nDrawMode == SimplePainterConstants.LINE) {
				page.drawLine(data.ptOne.x, data.ptOne.y, data.ptTwo.x, data.ptTwo.y);
			} else if (data.nDrawMode == SimplePainterConstants.RECT) {
				draw4Rect(page, data.ptOne, data.ptTwo, data.bFill);
			} else if (data.nDrawMode == SimplePainterConstants.OVAL) {
				draw4Oval(page,data.ptOne,data.ptTwo,data.bFill);
			} else if (data.nDrawMode == SimplePainterConstants.FREE) {
				drawPen(page,data);
			}
		} 
		
		for(PenModel savedData:dataList) {
			page.setColor(savedData.color);
			page2.setStroke(new BasicStroke(savedData.nSize));
			if (savedData.nDrawMode == SimplePainterConstants.DOT) {
				page.fillOval(	savedData.ptOne.x-savedData.nSize/2, 
								savedData.ptOne.y-savedData.nSize/2, 
								savedData.nSize, 
								savedData.nSize);
			} else if (savedData.nDrawMode == SimplePainterConstants.LINE) {
				page.drawLine(	savedData.ptOne.x, savedData.ptOne.y,
								savedData.ptTwo.x, savedData.ptTwo.y);
			} else if (savedData.nDrawMode == SimplePainterConstants.RECT) {
				draw4Rect(page, savedData.ptOne, savedData.ptTwo, savedData.bFill);
			} else if (savedData.nDrawMode == SimplePainterConstants.OVAL) {
				draw4Oval(page,savedData.ptOne,savedData.ptTwo,savedData.bFill);
			} else if (savedData.nDrawMode == SimplePainterConstants.FREE) {
				drawPen(page,savedData);
			}
		} 
	}
	
	private void drawPen(Graphics page,PenModel savedData) {
		for(Point pt: savedData.pens)
			page.fillOval(pt.x - savedData.nSize/2, pt.y - savedData.nSize/2, savedData.nSize, savedData.nSize);
	}
	
	private void draw4Rect(Graphics page, Point pt1, Point pt2, boolean fill) {
		int x, y, w, h;
		x=y=w=h=0;
		
		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			x = pt1.x;
			y = pt1.y;
			w = pt2.x - pt1.x;
			h = pt2.y - pt1.y;
		} else if(pt1.x < pt2.x && pt1.y > pt2.y) {
			x = pt1.x;
			y = pt2.y;
			w = pt2.x - pt1.x;
			h = pt1.y - pt2.y;
		} else if(pt1.x > pt2.x && pt1.y < pt2.y) {
			x = pt2.x;
			y = pt1.y;
			w = pt1.x - pt2.x;
			h = pt2.y - pt1.y;
		} else if(pt1.x > pt2.x && pt1.y >pt2.y) {
			x = pt2.x;
			y = pt2.y;
			w = pt1.x - pt2.x;
			h = pt1.y - pt2.y;
		}
		
		if (fill) page.fillRect(x, y, w, h);
		else 	  page.drawRect(x, y, w, h);
	}
	
	private void draw4Oval(Graphics page, Point pt1, Point pt2, boolean fill) {
		int x, y, w, h;
		x=y=w=h=0;
		
		if (pt1.x < pt2.x && pt1.y < pt2.y) {
			x = pt1.x;
			y = pt1.y;
			w = pt2.x - pt1.x;
			h = pt2.y - pt1.y;
		} else if(pt1.x < pt2.x && pt1.y > pt2.y) {
			x = pt1.x;
			y = pt2.y;
			w = pt2.x - pt1.x;
			h = pt1.y - pt2.y;
		} else if(pt1.x > pt2.x && pt1.y < pt2.y) {
			x = pt2.x;
			y = pt1.y;
			w = pt1.x - pt2.x;
			h = pt2.y - pt1.y;
		} else if(pt1.x > pt2.x && pt1.y >pt2.y) {
			x = pt2.x;
			y = pt2.y;
			w = pt1.x - pt2.x;
			h = pt1.y - pt2.y;
		}
		if(fill)
			page.fillOval(x, y, w, h);
		else
			page.drawOval(x, y, w, h);
	}
	
	public void clearData() {
		dataList.clear();
		undoList.clear();
		repaint();
	}
	
	public void undoData() {
		if(!dataList.isEmpty())
			undoList.push(dataList.remove(dataList.size()-1));
		repaint();
	}
	
	public void redoData() {
		if(!undoList.isEmpty())
			dataList.add(undoList.pop());
		repaint();
	}
	
	public void addDrawingMouseListener(DrawingController listener) {
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
	}
}
