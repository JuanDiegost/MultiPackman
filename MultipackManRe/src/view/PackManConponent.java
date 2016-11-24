/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author Yuliana, Pedro, Karol, Juan, Jhon Edison 
 */
public class PackManConponent extends JButton implements Runnable {

    private String name;
    private int id;
    private int score;
    private Color color;

    private Thread thread;
    private char orientation;// n ,s ,e, w
    private int x;
    private int y;
    private int z;
    boolean inverse;

    public PackManConponent(String name, int id, int score, Color color) {
        this.name = name;
        this.id = id;
        this.score = score;
        this.color = color;
        this.inverse = true;
        orientation = 'l';
        this.setEnabled(false);
        thread = new Thread(this);
        thread.start();
    }

    public void setOrientation(char orientation) {
        this.orientation = orientation;
        revalidate();
        repaint();
    }

    public void placeTriangle(char orientation) {
        switch (orientation) {
            case 'n': {
                x = 0;
                y = getWidth();
            }
            break;
            case 's': {
                x = 0;
                y = getWidth();
            }
            break;
            case 'l': {
                x = 0;
                y = getHeight();
            }
            break;
            case 'r': {
                x = 0;
                y = getHeight();
            }
            break;
        }
    }

    public int getId() {
        return id;
    }
    
    public void addScore(){
        score++;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void moveUp() {
        orientation = 'n';
        revalidate();
        repaint();
    }

    public void moveDown() {
        orientation = 's';
        revalidate();
        repaint();
    }

    public void moveRight() {
        orientation = 'r';
        revalidate();
        repaint();
    }

    public void moveLeft() {
        orientation = 'l';
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(color);
        g.fillOval(0, 0, getWidth(), getHeight());
        g.setColor(getParent().getBackground());
        switch (orientation) {
            case 'n': {
                int coordX[] = {x, getWidth() / 2, y};
                int coordY[] = {0, getHeight() / 2, 0};
                g.fillPolygon(coordX, coordY, 3);
            }
            break;
            case 's': {
                int coordX[] = {x, getWidth() / 2, y};
                int coordY[] = {getHeight(), getHeight() / 2, getHeight()};
                g.fillPolygon(coordX, coordY, 3);
            }
            break;
            case 'r': {
                int coordX[] = {getWidth(), getWidth() / 2, getWidth()};
                int coordY[] = {x, getHeight() / 2, y};
                g.fillPolygon(coordX, coordY, 3);
            }
            break;
            case 'l': {
                int coordX[] = {0, getWidth() / 2, 0};
                int coordY[] = {x, getHeight() / 2, y};
                g.fillPolygon(coordX, coordY, 3);
            }
            break;
        }
        g.setColor(Color.BLACK);
        g.drawString(name, 5, getHeight()/2);
        g.drawString(score+"",(getWidth()/2)-10,(getHeight()/2)+10);
    }

    private void animate() {
        if (inverse) {
            if (y >= 0 && x <= getWidth()) {
                y--;
                x++;
            } else {
                inverse = false;
            }
        } else if (x >= 0 && y <= getWidth()) {
            y++;
            x--;
        } else {
            inverse = true;
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
            placeTriangle(orientation);
            animate();
        }

    }
}
