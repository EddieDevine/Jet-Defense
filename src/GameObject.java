//imports
import javax.swing.*;
import java.awt.*;

//Class for all game objects - abstract means it cannot be constructred on its own
public abstract class GameObject {
    //cordinates of game object
    private int xPos = 0;
    private int yPos = 0;

    //public constructor for all game objects
    protected GameObject(int x, int y){
        this.xPos = x;
        this.yPos = y;
    }

    //getter for x position
    protected int getxPos() {
        return xPos;
    }

    //getter for y position
    protected int getyPos() {
        return yPos;
    }

    //setter for x position
    protected void setxPos(int xPos) {
        this.xPos = xPos;
    }

    //setter for x position
    protected void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
