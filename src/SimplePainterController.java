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
			if (SimplePainterConstants.MENU[i].equals(btnMenu.getText()))
				drawPanel.data.nDrawMode = i;
		}
	} // actionPerformed()
}