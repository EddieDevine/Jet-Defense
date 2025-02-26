//imports
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.*;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Player extends GameObject {
    private static final ImageIcon image = new ImageIcon(Assets.PLAYER_ICON); //import image for player

    //constructor for the player jet
    public Player(int xPos, int yPos){
        super(xPos, yPos);
    }

    //draw the image for the player at its cordinates
    public void draw(Graphics g, JPanel panel){
        int imageWidth = image.getImage().getWidth(null) / 4; //scale image down by factor
        int imageHeight = image.getImage().getHeight(null) / 4; 

        g.drawImage(image.getImage(), super.getxPos(), super.getyPos(), imageWidth, imageHeight, panel);
    }
}
