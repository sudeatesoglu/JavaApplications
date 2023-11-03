package animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AnimateObj extends JPanel implements ActionListener, KeyListener {

	private CreateObj obj;
	private Timer timer;
	private static final long serialVersionUID = 1L;

	public AnimateObj() {
		// TODO Auto-generated constructor stub

		// select color options for the shape
		Object[] colors = { "Red", "Black", "Yellow", "Green" };

		String colorOpt = (String) JOptionPane.showInputDialog(null,
				"Select the color for the object:",
				"Color Option",
				JOptionPane.INFORMATION_MESSAGE,
				null,
				colors,
				colors[1]);

		// switch case for the option results
		Color selectedColor;
		switch (colorOpt) {
			case "Red":
				selectedColor = Color.RED;
				break;
			case "Black":
				selectedColor = Color.BLACK;
				break;
			case "Yellow":
				selectedColor = Color.YELLOW;
				break;
			case "Green":
				selectedColor = Color.GREEN;
				break;
			default:
				selectedColor = Color.BLACK;
		}

		// get the initial speeds by user input
		int initialXSpeed = Integer.parseInt(JOptionPane.showInputDialog("Enter initial x-axis speed:"));
		int initialYSpeed = Integer.parseInt(JOptionPane.showInputDialog("Enter initial y-axis speed:"));

		// create the shape object by specified properties
		obj = new CreateObj(50, 50, 70, initialXSpeed, initialYSpeed, selectedColor);
		timer = new Timer(16, this);
		timer.start();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// create the frame
		JFrame frame = new JFrame();
		AnimateObj animateObj = new AnimateObj();
		frame.add(animateObj);
		frame.setSize(600, 600);
		frame.setLocation(350, 150);
		frame.setTitle("Animation");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		// information label for user attention, user can update the speeds by pressing
		// key S
		JLabel infoLabel = new JLabel("Press 'S' to update speeds.");
		infoLabel.setHorizontalAlignment(JLabel.CENTER);
		frame.add(infoLabel, BorderLayout.NORTH);
		Font font = new Font("Sans Serif Bold", Font.PLAIN, 20);
		infoLabel.setFont(font);

		// add key listener to be able to update the speeds by key event
		frame.addKeyListener(animateObj);
		frame.setFocusable(true);
		frame.setFocusTraversalKeysEnabled(false);
	}

	// paint the shape
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		obj.paint(g);
	}

	// animate the shape
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(timer)) {
			((CreateObj) obj).moveObj(getWidth(), getHeight());
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	// update the speeds by key event
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_S) {
			int newSpeedX = Integer.parseInt(JOptionPane.showInputDialog("Enter new x-axis speed:"));
			int newSpeedY = Integer.parseInt(JOptionPane.showInputDialog("Enter new y-axis speed:"));
			obj.updateSpeed(newSpeedX, newSpeedY);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
