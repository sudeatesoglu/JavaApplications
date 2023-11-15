package animation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

public class Animation extends JFrame implements Runnable, ActionListener, KeyListener {
	private static final long serialVersionUID = 1L;
	private List<Shape> shapes;  // list to store shapes
    private JButton startButton, stopButton;
    private JPanel buttonPanel, animationPanel;
    private boolean animationRunning;  // flag to track animation state
    
	public Animation() {
		// TODO Auto-generated constructor stub
		shapes = new ArrayList<>();  // crate set of shapes 
		
		// button settings
		startButton = new JButton("START");
        stopButton = new JButton("STOP");

        startButton.addActionListener(this);
        stopButton.addActionListener(this);
        
        JLabel infoLabel = new JLabel("Press start after stop, to change the number of shapes.");
		infoLabel.setHorizontalAlignment(JLabel.CENTER);
		add(infoLabel, BorderLayout.NORTH);
		Font font = new Font("Sans Serif Bold", Font.PLAIN, 20);
		infoLabel.setFont(font);

        // panel settings
        buttonPanel = new JPanel();
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);

        animationPanel = new AnimationPanel();
        add(animationPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        add(buttonPanel, BorderLayout.SOUTH);

        setSize(550,550);
        setLocation(350, 100);
		setTitle("Animation of Shapes");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
	}
	
	private void startAnimation() {
        animationRunning = true;
        int numShapes = getShapesNum();

        // create and start threads for each shape
        for (int i = 0; i < numShapes; i++) {
            Shape shape = new Shape();
            shapes.add(shape);
            Thread shapeThread = new Thread(shape);
            shapeThread.start();
        }

        // create and start the animation thread
        Thread animationThread = new Thread(new Runnable() {
            public void run() {
                while (animationRunning) {
                    for (Shape shape : shapes) {
                        shape.moveShape(550, 550);
                    }
                    animationPanel.repaint();
                    try {
                        Thread.sleep(20);  // delay for smoother animation
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
        });
        animationThread.start();
    }

	 public void stopAnimation() {
		 animationRunning = false;
	     animationPanel.repaint();
	 }

	private int getShapesNum() {
		Object[] numbers = { "1", "2", "3", "4", "5", "6", "7", "8", "9" };

		// input dialog for user to select number of shapes to animate
    	String numberOpt = (String) JOptionPane.showInputDialog(null,
    			"Select the number of shapes: ",
    			"Number of Shapes",
    			JOptionPane.INFORMATION_MESSAGE,
    			null,
    			numbers,
    			numbers[3]);
    	
    	try {
    		return Integer.parseInt(numberOpt);
    	}
    	catch (NumberFormatException e) {
    		return 0;
    	}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_U) {
			int newNumShapes = getShapesNum();
	        if (newNumShapes > 0) {
	        	stopAnimation();
	        	startAnimation();
	        }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("START")) {
            if (!animationRunning) startAnimation();
        }
		else if (e.getActionCommand().equals("STOP")) {
            stopAnimation();
        }
	}
	
	public static void main(String[] args) {
		// run the animation on the event dispatch thread
		SwingUtilities.invokeLater(() -> new Animation());
	}

	// paint each shape on the animation panel
	private class AnimationPanel extends JPanel {
		public AnimationPanel() {
			setFocusable(true);
		}
		@Override
	    protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.setColor(getBackground());
		    g.fillRect(0, 0, getWidth(), getHeight());
	        for (Shape shape : shapes) {
	        	shape.paint(g);
	        }
	    }
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}
}
