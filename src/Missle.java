import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Missle extends GameObject {
    private static final ImageIcon image = new ImageIcon(Assets.MISSLE); //import image for missle

    //constructor for missle
    public Missle(int xPos, int yPos){
        super(xPos, yPos);
    }
 
    //draw missle
    public void draw(Graphics g, JPanel panel){
        int imageWidth = image.getImage().getWidth(null) / 12; //scale image down by factor
        int imageHeight = image.getImage().getHeight(null) / 12; 

        g.drawImage(image.getImage(), super.getxPos(), super.getyPos(), imageWidth, imageHeight, panel);
    }

    //move missle forward
    public void move(){
        super.setyPos(super.getyPos() - 1); //adjust for speed
    }
}
