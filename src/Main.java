import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Iterator;

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
        Bogey bogey = new Bogey(0, 0);
        bogey.spawn();
        ArrayList<Missle> missiles = new ArrayList<>();

        //Animated Panel layered on top of background
        JPanel gamePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) { //paint component to be called in animaiton loop
                super.paintComponent(g);

                Iterator<Missle> iterator = missiles.iterator(); // Create iterator
                while(iterator.hasNext()){
                    Missle missle = iterator.next();
                    missle.move(); // Move the missile
                    if(missle.getyPos() < -100){
                        iterator.remove();
                    }
                    else{
                        missle.draw(g, this);
                    }

                    //System.out.println(bogey.getxPos());
                    if((missle.getxPos() >= bogey.getxPos()) && (missle.getxPos() <= bogey.getxPos() + 128)){ //x collision
                        if(missle.getyPos() <= bogey.getyPos()){ //y collision
                            System.exit(0);
                        }
                    }
                }

                player.move(); //update the player position
                player.draw(g, this); //draw player at updated position

                bogey.draw(g, this); //draw bogey
                bogey.move(); //move bogey forward

                if(bogey.getyPos() > 700){
                    System.exit(0);
                }
            }
        };
        gamePanel.setOpaque(false); //ensure transparency
        gamePanel.setBounds(0, 0, 700, 700); //set size

        //Add keystroke listener for game input
        gamePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) { //Listen for key pressed
                if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == 65) { //left arrow or "A"
                    player.setvelocity(-1);
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == 68) { //right arrow or "D"
                    player.setvelocity(1);
                }
                
                if(e.getKeyCode() == 32){ //space bar pressed
                    Missle playerMissle = new Missle(player.getxPos() + 50, player.getyPos() + 60); //create new missle at player location
                    missiles.add(playerMissle); //keep track of all active missiles
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if ((e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == 65) && player.getVelocity() == -1) { //left arrow or "A" - check that player is moving left before stopping
                    player.setvelocity(0); //stop movmenent
                }
                else if((e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == 68) && player.getVelocity() == 1){ //right arrow or "D" - check that player is moving right before stopping
                    player.setvelocity(0); //stop movmenent
                }
            }
        });
        

        // Use JLayeredPane to stack panels
        JLayeredPane layeredPane = new JLayeredPane(); //create organizer for background and animation panel
        layeredPane.setBounds(0, 0, 700, 700); //set size
        layeredPane.add(backgroundPanel, Integer.valueOf(0)); //set background behind animation panel
        layeredPane.add(gamePanel, Integer.valueOf(1)); //set animaiton panel in from of background panel

        //make game panel focused in order to pick up key strokes
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();

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