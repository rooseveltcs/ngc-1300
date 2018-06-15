import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import javax.swing.ImageIcon; 
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NGC1300 extends JFrame {
   
   // creates and sizes the panel
   public NGC1300() {
      this.setTitle("NGC 1300");
      this.setDefaultCloseOperation(EXIT_ON_CLOSE);
      this.setSize(new Dimension(1280, 750));
      this.setLocationRelativeTo(null);
      this.getContentPane().setLayout(new BorderLayout());
      this.getContentPane().add(new GamePanel("titleScreen.jpg")); // this is where it starts
      this.setVisible(true);
   }
   
   // creates a new NGC1300
   public static void main(String[] args) {
      new NGC1300();
   }
   
   // contains a lot of the important stuff
   public class GamePanel extends JPanel {
      private Image screen;
      private int xCoordinate = 0;
      private int yCoordinate = 0;
      private int downs = 0; // how many times down has been pressed
      private int ups = 0; // how many times up has been pressed
      private int rights = 0; // how many times right has been pressed
      private int lefts = 0; // how many times left has been pressed
      private int keysPressed = 0; // keeps track of what screen is shown
      boolean key_right, key_left, key_down, key_up, key_r, key_space;
      URL urlForImage;
      ImageIcon space;
      
      // initial game panel display
      public GamePanel(String filename) {
         loadImageFromFile(filename);
         this.setFocusable(true);
         addKeyListener(new GameInput());
      }
      
      // loads an image from a file 
      public void loadImageFromFile(String filename) {
         urlForImage = getClass().getResource(filename);
         space = new ImageIcon(urlForImage);
         screen = space.getImage();
      }
      
      // repaints the panel based on the key pressed
      public void paintComponent(Graphics g) {
         super.paintComponent(g); // repaints with the parent class
         g.drawImage(screen, xCoordinate, yCoordinate, this);
         if (key_down) {downs++; keysPressed++;
            key_down = false; // forces you to press another key to continue through screens
            if (downs == 1 && keysPressed == 3) {nextScreen("loseScreenBook.jpg");} //eventOne option
            if (downs == 1 && keysPressed == 4) {nextScreen("eventThree.jpg");} //eventTwo option
            if (downs == 2 && keysPressed == 5) {nextScreen("loseScreenRam.jpg");} //eventThree option
         }
         if (key_up) {ups++; keysPressed++;
            key_up = false;
            if (ups == 1 && keysPressed == 1) {nextScreen("characterScreen.jpg");}
            if (ups == 2 && keysPressed == 2) {nextScreen("eventOne.jpg");}
            if (ups == 3 && keysPressed == 3) {nextScreen("loseScreenWhaleLanguage.jpg");} //eventOne option
            if (ups == 3 && keysPressed == 4) {nextScreen("loseScreenBlackhole.jpg");} //eventTwo option
            if (ups == 3 && keysPressed == 5) {nextScreen("loseScreenRaid.jpg");} //eventThree option
         }
         if (key_right) {rights++; keysPressed++;
            key_right = false;
            if (rights == 1 && keysPressed == 3) {nextScreen("eventTwo.jpg");} //eventOne option
            if (rights == 2 && keysPressed == 4) {nextScreen("loseScreenCrew.jpg");} //eventTwo option
            if (rights == 2 && keysPressed == 5) {nextScreen("winScreen.jpg");} //eventThree option
         }
         if (key_left) {lefts++; keysPressed++;
            key_left = false;
            if (lefts == 1 && keysPressed == 3) {nextScreen("loseScreenBomb.jpg");} //eventOne option
            if (lefts == 1 && keysPressed == 4) {nextScreen("loseScreenBomb.jpg");} //eventTwo option
            if (lefts == 1 && keysPressed == 5) {nextScreen("loseScreenBomb.jpg");} //eventThree option
         }
         if (key_r) {key_r = false; restart();} // restarts the game
         if (key_space) {key_space = false; nextScreen("howToPlay.jpg");}    
         repaint();
      }
      
      // like GamePanel method but better because it works for everything
      public void nextScreen(String filename) {
         Graphics g = this.getGraphics();
         loadImageFromFile(filename);
         this.setFocusable(true);
         paintComponent(g);
      }
      
      // restarts the game
      public void restart() {
         downs = 0; // resets all values so you can play again
         ups = 0;
         rights = 0;
         lefts = 0;
         keysPressed = 0;
         Graphics g = this.getGraphics();
         loadImageFromFile("titleScreen.jpg");
         this.setFocusable(true);
         paintComponent(g);
      }   
      
      // tracks which keys are pressed
      private class GameInput implements KeyListener {
         public void keyTyped(KeyEvent e) {}
         // once a key is released   
         public void keyReleased(KeyEvent e) {
            if (e.getKeyCode() == e.VK_DOWN) {key_down = true;}
            if (e.getKeyCode() == e.VK_UP) {key_up = true;}
            if (e.getKeyCode() == e.VK_RIGHT) {key_right = true;}
            if (e.getKeyCode() == e.VK_LEFT) {key_left = true;} 
            if (e.getKeyCode() == e.VK_R) {key_r = true;}
            if (e.getKeyCode() == e.VK_SPACE) {key_space = true;}
         }
         // KeyListener hates it when I try to take this method out so...
         public void keyPressed(KeyEvent e) { 
         }
      }
   }
}