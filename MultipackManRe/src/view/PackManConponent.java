/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.border.Border;
import sun.reflect.generics.tree.Tree;

/**
 *
 * @author Juan Diego Molina
 */
public class PackManConponent extends JButton implements Runnable {

    private String name;
    private int id;
    private int score;
    private Color color;

    private int angle;
    private boolean open;
    private Thread thread;

    public PackManConponent(String name, int id, int score, Color color) {
        this.name = name;
        this.id = id;
        this.score = score;
        this.color = color;
        this.angle = 0;
        this.open = true;
        this.setEnabled(false);
        thread = new Thread(this);
        thread.start();
    }

    public int getId() {
        return id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void moveUp(){
        angle=-8;
        repaint();
    }
    
    public void moveDown(){
        angle=8;
    }
    
    public void moveRight(){
        
    }
    
    public void moveLeft(){
        
    }
    
    public void moveUpLeft(){
        
    }

    public void moveUpRight(){
        
    }
    
    public void moveDownLeft(){
        
    }
    
    public void moveDownRight(){
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setColor(getParent().getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.fillArc(0, 0, getWidth(), getHeight(), angle - 2, 360 - 2 * angle + 4);
        g.setColor(color);
        g.fillArc(1, 1, getWidth() - 1, getHeight() - 1, angle, 360 - 2 * angle);
        g.setColor(Color.BLACK);
        g.drawString(name, 5, getHeight() / 2);
        g.drawString(score + "", (getWidth() / 2), (getHeight() / 2) + 10);
    }

    private void animate() {
        if (open) {
            if (angle >= 45) {
                open = false;
            } else {
                angle += 4;
            }
        } else if (angle <= 0) {
            open = true;
        } else {
            angle -= 4;
        }
        revalidate();
        repaint();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(PackManConponent.class.getName()).log(Level.SEVERE, null, ex);
            }
            animate();
        }

    }
}
