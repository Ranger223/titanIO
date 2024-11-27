package titanIO;
/*Author: Steven Bates
 * Date: 2/23/16
 * Comments: agario
 */
import java.awt.Container;
import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TitanIOGUI {

	public static void main(String[] args) throws IOException, Exception {
		Clip clip;
		JFrame theGUI = new JFrame();
		theGUI.setTitle("TitanIO");
		theGUI.setSize(1920, 1080);
		theGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		theGUI.setUndecorated(false);
		ImageIcon image1 = new ImageIcon("images/agarioBack3.png");
		ImageIcon image2 = new ImageIcon("images/agarioSkin.jpg");
		
		
		
		
		SplashPanel splash = new SplashPanel(image2);
		
		Container pane = theGUI.getContentPane();
		
		theGUI.setVisible(true);
		pane.add(splash);
		

		
		theGUI.setFocusable(true);
		
		File file = new File("sounds/11Raggae.wav");
		 AudioInputStream sample = AudioSystem.getAudioInputStream(file);

         //create a sound buffer
         clip = AudioSystem.getClip();

         //load the audio file
         clip.open(sample);

         //play sample
         clip.start();
		
		
		while (true) {  // run until a picture is selected. 
			
			if (splash.getSel()) {
				clip.stop();
				pane.remove(splash);
				TitanIOPanel panel1 = new TitanIOPanel(image1, splash.getImage());
				pane.add(panel1);
				pane.revalidate();
				
				System.out.println("new panel");
				break;
			}
			
		}
		
		Audio.main("sounds/AgarioBack.wav");
		

	}

}