package animation;

import java.awt.*;

public class CreateObj {
	
	private int xPos, yPos;
	private int size;
	private int xSpeed, ySpeed;
	private Color color;

	public CreateObj(int xPos, int yPos, int size, int xSpeed, int ySpeed, Color color) {
		// TODO Auto-generated constructor stub
		// initialize
		this.xPos = xPos;
	    this.yPos = yPos;
		this.size = size;
	    this.xSpeed = xSpeed;
	    this.ySpeed = ySpeed;
	    this.color = color;
	}
	
	// paint the object (shape)
	public void paint(Graphics g) {
		g.setColor(color);
		g.fillOval(xPos, yPos, size, size);
	}
	
	// animate the object (shape)
	public void moveObj(int frameWidth, int frameHeight) {
		if (xPos < 0 || xPos > frameWidth - size) xSpeed = -xSpeed;
	    if (yPos < 0 || yPos > frameHeight - size) ySpeed = -ySpeed;
	    xPos += xSpeed;
	    yPos += ySpeed;
	}

	// update the speed of the object (shape)
	public void updateSpeed(int updatedXSpeed, int updatedYSpeed) {
		this.xSpeed = updatedXSpeed;
	    this.ySpeed = updatedYSpeed;
	}
}
