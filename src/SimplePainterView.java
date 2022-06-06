import javax.swing.*;
import java.awt.*;
import java.awt.desktop.SystemSleepEvent;
import java.awt.event.*;

public class SimplePainterView extends JPanel {

	JPanel menuPanel, optionPanel, messagePanel;
	DrawingPanel drawPanel;
	JButton[] btnMenuArray;
	JLabel lblSize;
	JTextField txtSize;
	JCheckBox chkFill;
	JButton btnColor;
	JTextArea txtArea;
	String strMessage;

	int nSize;
	boolean bFill;
	Color chooserColor;
	
	String btnInfo;

	public SimplePainterView() {
		setPreferredSize(new Dimension(830, 640));
		setBackground(Color.white);
		setLayout(null);

		nSize = 1;
		bFill = false;
		chooserColor = Color.black;

		menuPanel = new JPanel();
		menuPanel.setBounds(10, 10, 400, 90);
		menuPanel.setBackground(Color.white);
		menuPanel.setBorder(BorderFactory.createTitledBorder("MENU"));
		menuPanel.setLayout(new GridLayout(2, 4)); 
		add(menuPanel);

		optionPanel = new JPanel();
		optionPanel.setBounds(420, 10, 400, 90);
		optionPanel.setBackground(Color.white);
		optionPanel.setBorder(BorderFactory.createTitledBorder("OPTION"));
		add(optionPanel);

		messagePanel = new JPanel();
		messagePanel.setBounds(10, 535, 800, 85);
		messagePanel.setBackground(Color.white);
		messagePanel.setBorder(BorderFactory.createTitledBorder("MESSAGE"));
		add(messagePanel);

		drawPanel = new DrawingPanel(this);
		drawPanel.setBounds(10, 110, 800, 420);
		add(drawPanel);

		btnMenuArray = new JButton[8]; 
		for (int i = 0; i < 8; i++) {
			btnMenuArray[i] = new JButton(SimplePainterConstants.MENU[i]);
			btnMenuArray[i].setBackground(SimplePainterConstants.MENU_COLOR[0]);
			btnMenuArray[i].setForeground(SimplePainterConstants.MENU_COLOR[1]);
			btnMenuArray[i].addMouseListener(new MenuEnter());
			btnMenuArray[i].addActionListener(new MenuClicked());
			menuPanel.add(btnMenuArray[i]);
		}

		lblSize = new JLabel("DOT SIZE: ");
		optionPanel.add(lblSize);

		txtSize = new JTextField(8);
		txtSize.setText(Integer.toString(nSize));
		optionPanel.add(txtSize);

		chkFill = new JCheckBox("FILL");
		chkFill.setBackground(Color.white);
		chkFill.setSelected(bFill);
		optionPanel.add(chkFill);

		btnColor = new JButton("COLOR");
		btnColor.setBackground(SimplePainterConstants.MENU_COLOR[0]);
		btnColor.setForeground(SimplePainterConstants.MENU_COLOR[1]);
		btnColor.addActionListener(new ColorButton());
		optionPanel.add(btnColor);

		setOptionInvisible();

		strMessage = "";
		txtArea = new JTextArea(3, 70);
		JScrollPane scrollPane = new JScrollPane(txtArea);
		txtArea.setEditable(false);
		messagePanel.add(scrollPane);
		
		// test
		txtArea.append(strMessage);
		txtArea.setCaretPosition(txtArea.getDocument().getLength());

	}

	private void setOptionInvisible() {
		lblSize.setVisible(false);
		txtSize.setVisible(false);
		chkFill.setVisible(false);
		btnColor.setVisible(false);
	}
	

	public void addMenuButtonListener(ActionListener listener) {
		for(int i=0;i<8;i++) {
			btnMenuArray[i].addActionListener(listener);
		} 
	}

	private class MenuEnter implements MouseListener {

		@Override
		public void mouseEntered(MouseEvent e) {
			JButton btn = (JButton) e.getSource(); 
			btn.setBackground(SimplePainterConstants.MENU_COLOR[2]);
			btn.setForeground(SimplePainterConstants.MENU_COLOR[3]);
		}

		@Override
		public void mouseExited(MouseEvent e) {
			JButton btn = (JButton) e.getSource(); 
			btn.setBackground(SimplePainterConstants.MENU_COLOR[0]);
			btn.setForeground(SimplePainterConstants.MENU_COLOR[1]);
		}

		@Override
		public void mouseClicked(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		}
	}

	private class MenuClicked implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			for (int i=0; i<8; i++) {
				if (obj == btnMenuArray[i]) {
					setOptionInvisible();
					lblSize.setVisible(true);
					txtSize.setVisible(true);
					if (i == SimplePainterConstants.DOT) {
						 lblSize.setText("DOT SIZE : ");
						 btnInfo = "Draw mode: " + SimplePainterConstants.MENU[i] + "\nSize: " + txtSize.getText() + "\nColor: " + chooserColor.getRGB() + "(RGB)";
					} else if (i == SimplePainterConstants.LINE) {
						lblSize.setText("LINE WIDTH : ");
						btnInfo = "Draw mode: " + SimplePainterConstants.MENU[i] + "\nSize: " + txtSize.getText() + "\nColor: " + chooserColor.getRGB() + "(RGB)";
					} else if (i == SimplePainterConstants.RECT || 
							   i == SimplePainterConstants.OVAL) {
						lblSize.setText("LINE WIDTH : ");
						chkFill.setVisible(true);
						btnInfo = "Draw mode: " + SimplePainterConstants.MENU[i] + "\nSize: " + txtSize.getText()+"\tFill: " + chkFill.isSelected() + "\nColor: " + chooserColor.getRGB() + "(RGB)";
					} else if(i == SimplePainterConstants.CLEAR) {
						setOptionInvisible();
						drawPanel.clearData();
						btnInfo = "Draw mode: " + SimplePainterConstants.MENU[i];
					} else if(i == SimplePainterConstants.UNDO) {
						setOptionInvisible();
						drawPanel.undoData();
						btnInfo = "Draw mode: " + SimplePainterConstants.MENU[i];
					} else if(i == SimplePainterConstants.REDO) {
						setOptionInvisible();
						drawPanel.redoData();
						btnInfo = "Draw mode: " + SimplePainterConstants.MENU[i];
					} else if(i == SimplePainterConstants.FREE) {
						lblSize.setText("PEN Size : ");
						btnInfo = "Draw mode: " + SimplePainterConstants.MENU[i] + "\nSize: " + txtSize.getText();
					}
					btnColor.setVisible(true);
					
					txtArea.setText(btnInfo);
				} 
			} 
		} 
	} 

	private class ColorButton implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			Object obj = e.getSource();
			if (obj == btnColor) {
				chooserColor = JColorChooser.showDialog(drawPanel, "COLOR CHOOSER", chooserColor);
				btnColor.setBackground(chooserColor);
				drawPanel.data.color = chooserColor;
			}
		}

	}
}
