package titanIO;
/*Author: Steven Bates
 * Date: 2/16/17
 * Comments: Circles	
 */
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;


public class Circle{
		private int centerX, centerY, radius, velocity, direction;
		private Color color;
		
	public Circle(int x, int y, int r, Color c){
		centerX = x;
		centerY = y;
		radius = r;
		color = c;
		}
		
	public void draw(Graphics g){
		Color oldColor = g.getColor();
		g.setColor(color);
		g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
		g.setColor(oldColor);		
		}
	
	public void fill(Graphics g){
		Color oldColor = g.getColor();
		g.setColor(color);
		g.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);
		g.setColor(oldColor);
		}
	
	public boolean containsPoint(int x, int y){
		int xSquared = (x - centerX) * (x - centerX);
		int ySquared = (y - centerY) * (y - centerY);
		int radiusSquared = radius * radius;
		return xSquared + ySquared - radiusSquared <= 0;
		}
	
	public Rectangle getRect(){
		Rectangle rect = new Rectangle(getX() - radius, getY() - radius, radius * 2, radius * 2);
		return rect;
	}
	
	public boolean hasCollision(Circle circle){
		double xDiff = centerX - circle.getX();
		double yDiff = centerY - circle.getY();
		double distance = Math.sqrt((Math.pow(xDiff, 2) + Math.pow(yDiff, 2)));
		
		return distance < (radius + circle.getRadius());
	}
	
	
	
	
	public void move(int xAmount, int yAmount){
		centerX = centerX + xAmount;
		centerY = centerY + yAmount;
		}
	public void setVelocity(int v){
		velocity = v;
		}
	
	public void setDirection(int degrees){
		direction = (direction + degrees);
		}
	
	public void turn(int degrees){
		direction = (direction + degrees) % 360;
		}
	
	public void move(){
		move((int)(velocity * Math.cos(Math.toRadians(direction))),
			(int)(velocity * Math.sin(Math.toRadians(direction))));
		
		}

	public void setFilled(boolean b) {
		b = true;
		
	}

	public int getRadius() {
	
		return radius;
	}
	public void setRadius(int r){
		radius = r;
	}

	public int getX() {
		
		return centerX;
	}

	public int getY() {
		
		return centerY;
	}
}