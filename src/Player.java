import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;

public class Player extends GameObject {
    private static final ImageIcon image = new ImageIcon(Assets.PLAYER_ICON); //import image for player

    private int velocity = 0;

    //constructor for the player jet
    public Player(int xPos, int yPos){
        super(xPos, yPos);
    }

    //move the player according to velocity (velocity is changed by player)
    public void move(){

        if(super.getxPos() < -30){ //left boundry 
            super.setxPos(-30);
        }
        else if(super.getxPos() > 550){ //right boundry
            super.setxPos(550);
        }

        super.setxPos(super.getxPos() + (velocity * 2)); //adjust for speed
    }

    //draw the image for the player at its cordinates
    public void draw(Graphics g, JPanel panel){
        int imageWidth = image.getImage().getWidth(null) / 4; //scale image down by factor
        int imageHeight = image.getImage().getHeight(null) / 4; 

        g.drawImage(image.getImage(), super.getxPos(), super.getyPos(), imageWidth, imageHeight, panel);
    }

    //setter for velocity
    public void setvelocity(int dirrection){
        velocity = dirrection;
    }

    //getter for velocity
    public int getVelocity() {
        return velocity;
    }
}
