package tz.g.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public JPanel cp;
	
	public GFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(860, 480);
		this.setLocationRelativeTo(null);
		
		this.cp = new JPanel();
		this.cp.setLayout(null);
		this.setContentPane(this.cp);
	}
	
}
