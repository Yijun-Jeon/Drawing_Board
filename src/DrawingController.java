import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingController implements MouseListener, MouseMotionListener {
	DrawingPanel drawPanel;
	SimplePainterView view;
	
	
	public void setDrawingPanel(DrawingPanel panel) {
		drawPanel = panel;
	}
	
	public void setView(SimplePainterView sView) {
		view  = sView;
	}

	public void mouseDragged(MouseEvent event) {
		if(drawPanel.data.nDrawMode == SimplePainterConstants.LINE ||
				drawPanel.data.nDrawMode == SimplePainterConstants.RECT ||
				drawPanel.data.nDrawMode == SimplePainterConstants.OVAL) {
			drawPanel.data.ptTwo = event.getPoint();
			drawPanel.repaint();
		} else if (drawPanel.data.nDrawMode == SimplePainterConstants.FREE) {
			drawPanel.data.ptOne = event.getPoint();
			drawPanel.penData.addPenData(new DataModel(drawPanel.data));
			drawPanel.repaint();
		}
		
	}
	public void mouseClicked(MouseEvent event) {
		if (drawPanel.data.nDrawMode == SimplePainterConstants.DOT) {
			drawPanel.bPen = false;
			drawPanel.data.ptOne = event.getPoint();
			drawPanel.data.nSize = Integer.parseInt(view.txtSize.getText());
			drawPanel.dataList.add(new DataModel(drawPanel.data)); 
			drawPanel.repaint();
		} 
	}
	public void mousePressed(MouseEvent event) {
		if(drawPanel.data.nDrawMode == SimplePainterConstants.LINE ||
				drawPanel.data.nDrawMode == SimplePainterConstants.RECT ||
				drawPanel.data.nDrawMode == SimplePainterConstants.OVAL){
			drawPanel.bDrag = true;
			drawPanel.data.ptOne = event.getPoint();
			drawPanel.data.nSize = Integer.parseInt(view.txtSize.getText());
			drawPanel.data.bFill = view.chkFill.isSelected();
		}
		else if(drawPanel.data.nDrawMode == SimplePainterConstants.FREE) {
			drawPanel.bDrag = true;
			drawPanel.data.ptOne = event.getPoint();
			drawPanel.data.nSize = Integer.parseInt(view.txtSize.getText());
			drawPanel.penData.setSize(drawPanel.data.nSize);
			drawPanel.penData.setColor(drawPanel.data.color);
			drawPanel.penData.addPenData(new DataModel(drawPanel.data));
			drawPanel.repaint();
		}
	}

	public void mouseReleased(MouseEvent event) {
		if(drawPanel.data.nDrawMode == SimplePainterConstants.LINE ||
				drawPanel.data.nDrawMode == SimplePainterConstants.RECT ||
				drawPanel.data.nDrawMode == SimplePainterConstants.OVAL) {
			drawPanel.data.ptTwo = event.getPoint();
			drawPanel.dataList.add(new DataModel(drawPanel.data));
			drawPanel.bDrag = false;
			drawPanel.repaint();
		}else if(drawPanel.data.nDrawMode == SimplePainterConstants.FREE) {
			drawPanel.penList.add(new PenModel(drawPanel.penData));
			drawPanel.penData.clear();
			drawPanel.bDrag = false;
		}
	}
	
	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}
}
