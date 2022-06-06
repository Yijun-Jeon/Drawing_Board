import javax.swing.JFrame;

public class SimplePainterEx {
	public static void main(String[] args) {
		JFrame frame = new JFrame("SIMPLE PAINTER");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		
		SimplePainterView view = new SimplePainterView();
		SimplePainterController controller = new SimplePainterController();
		view.addMenuButtonListener(controller);
		controller.setDrawingPanel(view.drawPanel);
		
		DrawingController drawController = new DrawingController();
		drawController.setDrawingPanel(view.drawPanel);
		drawController.setView(view);
		view.drawPanel.addDrawingMouseListener(drawController);
		
		frame.getContentPane().add(view);
		
		frame.pack();
		frame.setVisible(true);
		
	}

}
