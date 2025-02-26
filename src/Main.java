import javax.swing.*;
import java.awt.*;

class Main {
    public static void main(String[] args){
        //create GUI
        JFrame frame = new JFrame("Jet Defense"); //create frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when user exits
        frame.setSize(700, 700); //size
        frame.setLocationRelativeTo(null); //center on screen
        frame.setLayout(null); //used for layering objects on top of background
        ImageIcon windowIcon = new ImageIcon(Assets.WINDOW_ICON); //import window icon from assets
        frame.setIconImage(windowIcon.getImage()); //set window icon


        //Panel for background image
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) { //paint component to paint background
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon(Assets.BACKGROUND); //import image from assets
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this); //draw background
            }
        };
        backgroundPanel.setBounds(0, 0, 1000, 700); //set size of background panel

        Player player = new Player(270, 480); //create a player object before starting game

        //Animated Panel layered on top of background
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) { //paint component to be called in animaiton loop
                super.paintComponent(g);
                player.draw(g, this);
            }
        };
        gamePanel.setOpaque(false); //ensure transparency
        gamePanel.setBounds(0, 0, 700, 700); //set size

        // Use JLayeredPane to stack panels
        JLayeredPane layeredPane = new JLayeredPane(); //create organizer for background and animation panel
        layeredPane.setBounds(0, 0, 700, 700); //set size
        layeredPane.add(backgroundPanel, Integer.valueOf(0)); //set background behind animation panel
        layeredPane.add(gamePanel, Integer.valueOf(1)); //set animaiton panel in from of background panel

        frame.setContentPane(layeredPane); //set content of frame to the panels organized in the layeredPane
        frame.setVisible(true); //make it visible

        //loop animation paint component on its own thread
        new Thread(() -> {
            while (true) {
                gamePanel.repaint(); //re call all methods in paint component
                try {
                    Thread.sleep(10); // 100 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}