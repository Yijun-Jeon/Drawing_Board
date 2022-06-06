import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class SimplePainterController implements ActionListener {

	DrawingPanel drawPanel;
	
	public void setDrawingPanel(DrawingPanel panel) {
		drawPanel = panel;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		JButton btnMenu = (JButton)event.getSource();

		for (int i=0; i<8; i++) {
			if (SimplePainterConstants.MENU[i].equals(btnMenu.getText())) {
				drawPanel.data.nDrawMode = i;
				switch(i) {
				case SimplePainterConstants.FREE:
					drawPanel.bPen = true;
					break;
				case SimplePainterConstants.DOT:
				case SimplePainterConstants.LINE:
				case SimplePainterConstants.RECT:
				case SimplePainterConstants.OVAL:
					drawPanel.bPen = false;
				default:
					break;
				}
				break;
			} 
		}
	} // actionPerformed()
}