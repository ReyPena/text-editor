/**
 * File: IconButton.java
 */
package icon;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconButton {
	 
	 private URL filename;
	 private int width;
	 private int height;
	 private ImageIcon imageIcon;
	 private JButton iconButton;
	 /**
	 * Constructor Icon
	 */
	public IconButton(URL filename, int width, int height) {
		this.filename = filename;
		this.width = width;
		this.height = height;

		imageIcon = rescaleImageIcon(this.filename, this.width, this.height);
		iconButton = new JButton();
		iconButton.setIcon(imageIcon);
	}
	
	private ImageIcon rescaleImageIcon(URL filename, int width, int height) {
		ImageIcon theIcon = new ImageIcon(filename);
		Image theImage = theIcon.getImage();
		Image theNewImage = theImage.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
		return new ImageIcon(theNewImage);
	}
	
	public JButton getIconButton() {
		return iconButton;
	}
	
	public ImageIcon getImageIcon() {
		return imageIcon;
	}
	
	//HOW TO USE ICON BUTTON
//	IconButton iconButton = new IconButton(this.getClass().getResource("saveIcon.png"), 15, 15);
//	JButton btnSave = iconButton.getIconButton();
//	toolBar.add(btnSave);
}
