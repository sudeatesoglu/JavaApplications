package animation;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;


public class Shape extends Thread {
	private int size;
    private int xPos, yPos;
    private int xSpeed, ySpeed;
    private Color color;
    private Random rand;

	public Shape() {
		// TODO Auto-generated constructor stub,
		// initialize the variables
		rand = new Random();
        size = rand.nextInt(15, 50);
        xPos = rand.nextInt(540);
        yPos = rand.nextInt(500);
        xSpeed = rand.nextInt(20) + 1;
        ySpeed = rand.nextInt(20) + 1;
        color = new Color(rand.nextInt(255),
                rand.nextInt(255),
                rand.nextInt(255));
	}
	
	// paint the shape
	public void paint(Graphics g) {
        g.setColor(color);
        g.fillOval(xPos, yPos, size, size);
    }

	// move the shape with bouncing back from the frame boundaries
    public void moveShape(int frameWidth, int frameHeight) {
        if (xPos < 0 || xPos > frameWidth - size)
            xSpeed = -xSpeed;
        if (yPos < 0 || yPos > frameHeight - size)
            ySpeed = -ySpeed;
        xPos += xSpeed;
        yPos += ySpeed;
    }

    // run method for thread interface
    public void run() {
        while (true) {
            moveShape(550, 550);
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) {
            	Thread.currentThread().interrupt();
            }
        }
    }
}
