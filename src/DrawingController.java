import java.awt.Point;
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
			drawPanel.data.ptOne.setLocation(drawPanel.data.ptTwo);
			drawPanel.data.ptTwo = event.getPoint();
			drawPanel.data.addPenData(new Point(drawPanel.data.ptOne));
			drawPanel.repaint();
		}
		
	}
	public void mouseClicked(MouseEvent event) {
		if (drawPanel.data.nDrawMode == SimplePainterConstants.DOT) {
			drawPanel.data.ptOne = event.getPoint();
			drawPanel.data.nSize = Integer.parseInt(view.txtSize.getText());
			drawPanel.dataList.add(new PenModel(drawPanel.data)); 
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
			drawPanel.data.ptTwo = event.getPoint();
			drawPanel.data.nSize = Integer.parseInt(view.txtSize.getText());
			drawPanel.data.nSize = Integer.parseInt(view.txtSize.getText());
			drawPanel.data.addPenData(new Point(drawPanel.data.ptOne));
			drawPanel.repaint();
		}
	}

	public void mouseReleased(MouseEvent event) {
		if(drawPanel.data.nDrawMode == SimplePainterConstants.LINE ||
				drawPanel.data.nDrawMode == SimplePainterConstants.RECT ||
				drawPanel.data.nDrawMode == SimplePainterConstants.OVAL) {
			drawPanel.data.ptTwo = event.getPoint();
			drawPanel.dataList.add(new PenModel(drawPanel.data));
			drawPanel.bDrag = false;
			drawPanel.repaint();
		}else if(drawPanel.data.nDrawMode == SimplePainterConstants.FREE) {
			drawPanel.dataList.add(new PenModel(drawPanel.data));
			drawPanel.data.clear();
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
