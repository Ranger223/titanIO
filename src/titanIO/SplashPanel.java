package titanIO;
/*This class created by Mattson
 * as a splash screen to come up prior to the game.
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SplashPanel extends JPanel {
	
	
	private ImageIcon image;
	private javax.swing.Timer timer;
	private boolean sel = false;
	private Image pic1, pic2, pic3, pic4, pic5, pic6, pic7, pic8, selectedPic;
	

	public SplashPanel(ImageIcon im) {
		
		image = im;
		addMouseListener(new PanelListener());
		addMouseMotionListener(new PanelMotionListener());
		timer = new javax.swing.Timer(40, new MoveListener());
		timer.start();
		
		pic1 = new ImageIcon("images/Doge.png").getImage(); 
		pic2 = new ImageIcon("images/raccoon.gif").getImage(); 
		pic3 = new ImageIcon("images/Trump1.jpg").getImage(); 
		pic4 = new ImageIcon("images/MLGF.gif").getImage(); 
		pic5 = new ImageIcon("images/Ill.png").getImage(); 
		pic6 = new ImageIcon("images/Harambe.png").getImage(); 
		pic7 = new ImageIcon("images/dude.gif").getImage(); 
		pic8 = new ImageIcon("images/tri.gif").getImage(); 
		
		
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Font f1 = new Font("Comic Sans MS", Font.BOLD, 80);
		g.setFont(f1);
		g.setColor(Color.red);
		
		image.paintIcon(this, g, 0, 0);
		
		g.drawString("Titan IO", 755, 120);
		
		Font f2 = new Font("Comic Sans MS", Font.BOLD, 60);
		g.setFont(f2);
		g.setColor(Color.blue);
		
		g.drawImage(pic1, 250, 200, 200, 200, null);
		g.drawImage(pic2, 650, 200, 200, 200, null);
		g.drawImage(pic3, 1050, 200, 200, 200, null);
		g.drawImage(pic4, 250, 500, 200, 200, null);
		g.drawImage(pic5, 650, 500, 200, 200, null);
		g.drawImage(pic6, 1050, 500, 200, 200, null);
		g.drawImage(pic7, 1450, 500, 200, 200, null);
		g.drawImage(pic8, 1450, 200, 200, 200, null);
		
		g.drawString("Click on Picture to Select", 585, 800);
	}
	
	public boolean getSel(){
		System.out.println("");
		return sel;
	}
	
	public Image getImage(){
		return selectedPic;
	}
	
	
	private class MoveListener implements ActionListener {  //timer
		
		public void actionPerformed(ActionEvent e) {
			//System.out.println("timer");
			
			repaint();
		}
	}
	
	
	public class PanelMotionListener extends MouseMotionAdapter {
		
		public void mouseMoved(MouseEvent e) {
			
			
		}
	}
	

	public class PanelListener implements MouseListener {

		public void mouseClicked(MouseEvent e) {
			System.out.println("clicked");
			
			
			// make rectangles of the pics to check mouse contains point
			Rectangle pic1Rect, pic2Rect, pic3Rect, pic4Rect, pic5Rect, pic6Rect, pic7Rect, pic8Rect;
			pic1Rect = new Rectangle(250, 200, 200, 200);
			pic2Rect = new Rectangle(650, 200, 200, 200);
			pic3Rect = new Rectangle(1050, 200, 200, 200);
			pic4Rect = new Rectangle(250, 500, 200, 200);
			pic5Rect = new Rectangle(650, 500, 200, 200);
			pic6Rect = new Rectangle(1050, 500, 200, 200);
			pic7Rect = new Rectangle(1450, 500, 200, 200);
			pic8Rect = new Rectangle(1450, 200, 200, 200);
			
			int x = e.getX();
			int y = e.getY();
			
			// every click see if the mouse is in the pics area. If so, it is selected
			if (pic1Rect.contains(x, y)) {
				selectedPic = pic1;
				sel = true;
			}
			else if (pic2Rect.contains(x, y)) {
				selectedPic = pic2;
				sel = true;
			}
			else if (pic3Rect.contains(x, y)) {
				selectedPic = pic3;
				sel = true;
			}
			else if (pic4Rect.contains(x, y)) {
				selectedPic = pic4;
				sel = true;
			}
			else if (pic5Rect.contains(x, y)) {
				selectedPic = pic5;
				sel = true;
			}
			else if (pic6Rect.contains(x, y)) {
				selectedPic = pic6;
				sel = true;
			}
			else if (pic7Rect.contains(x, y)) {
				selectedPic = pic7;
				sel = true;
			}
			else if (pic8Rect.contains(x, y)) {
				selectedPic = pic8;
				sel = true;
			}
			else{
				
			}
			
		}

		public void mouseEntered(MouseEvent e) {
		
		}

		public void mouseExited(MouseEvent e) {
		
		}

		public void mousePressed(MouseEvent e) {
		
		}

		public void mouseReleased(MouseEvent e) {
		
		}
	
	

	} // closes PanelListener
} //closes the main class