import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;

//enemy jet
public class Bogey extends GameObject {
    private static final ImageIcon image = new ImageIcon(Assets.BOGEY); //import image for bogey
    private Random random = new Random(); //used for random generation 

    //constructor for the bogey jets
    public Bogey(int xPos, int yPos){
        super(xPos, yPos);
    }

    //randomize position - used for start
    public void spawn(){
        int randomInt = random.nextInt(600); //random x cordinate
        super.setxPos(randomInt);
    }

    //draw bogey jet
    public void draw(Graphics g, JPanel panel){
        int imageWidth = image.getImage().getWidth(null) / 15; //scale image down by factor
        int imageHeight = image.getImage().getHeight(null) / 15; 

        g.drawImage(image.getImage(), super.getxPos(), super.getyPos(), imageWidth, imageHeight, panel);
    }

    //move bogey forwards
    public void move(){
        super.setyPos(getyPos() + 1);
    }
}
