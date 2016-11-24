/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author Yuliana, Pedro, Karol, Juan, Jhon Edison 
 */
public class SinglePacman extends JButton implements KeyListener {

    private String ip;
    private String name;
    private int id;
    private Thread me;
    private boolean isOfMe;
    public static final int DEFAULT_SISE = 50;
    private JLabel labelMyName;
    private char orientation;// n ,s ,e, w
    private int x;
    private int y;
    private int z;
    boolean inverse;
    private Color myPacmanColor;
    private int score;

    @SuppressWarnings({"OverridableMethodCallInConstructor", "LeakingThisInConstructor"})
    public SinglePacman(String ip, String name, Point position, boolean isOfMe, Color myColor,int id,int score) {
        this.score = score;
        this.id=id;
        inverse = true;
        this.setBorder(null);
        this.setBorderPainted(false);
        this.setOpaque(true);
        this.ip = ip;
        this.name = name;
        this.setSize(DEFAULT_SISE, DEFAULT_SISE);
        this.isOfMe = isOfMe;
        this.myPacmanColor = myColor;
        labelMyName = new JLabel();
        labelMyName.setFont(new Font("arial", 2, 20));
        labelMyName.setHorizontalAlignment(CENTER);
        if (isOfMe) {
            labelMyName.setForeground(Color.DARK_GRAY);
        } else {
            labelMyName.setForeground(Color.red);
        }
        orientation = 'r';
        placeTriangle(orientation);
        @SuppressWarnings("Convert2Lambda")
        Runnable runnableLabelName = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    labelMyName.setText(name + " score : " + score);
                    labelMyName.setBounds(getX() - 225, getY() - 60, 500, 30);
                    labelMyName.setVisible(true);
                    labelMyName.setIgnoreRepaint(true);
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
                    try {
                        Thread.sleep(7);
                    } catch (InterruptedException ex) {
                    }
                    repaint();
                }
            }
        };
        me = new Thread(runnableLabelName);
        if (isOfMe) {
            this.addKeyListener(this);
        }
        this.setIgnoreRepaint(false);
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void meStart() {
        getParent().add(labelMyName);
        me.start();
        this.setBackground(getParent().getBackground());

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

    public void setMyLocation(Point point, char orientation) {
        this.orientation = orientation;
    }

    public Point getMyLocation() {
        return this.getLocation();
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("view.SinglePacman.keyPressed()");
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: {
                if (getX() >= 0 && getX() <= getParent().getWidth()) {
                    if (getX() > 7) {
                    }
                    orientation = 'l';
                }
            }
            break;
            case KeyEvent.VK_RIGHT: {
                if (getX() >= 0 && getX() <= getParent().getWidth()) {
                    if (getX() < getParent().getWidth() - DEFAULT_SISE - 7) {
                    }
                    orientation = 'r';
                }
            }
            break;
            case KeyEvent.VK_UP: {
                if (getY() >= 0 && getY() <= getParent().getHeight()) {
                    if (getY() > 7) {
                    }
                    orientation = 'n';
                }
            }
            break;
            case KeyEvent.VK_DOWN: {
                if (getY() >= 0 && getY() <= getParent().getHeight()) {
                    if (getY() < getParent().getHeight() - DEFAULT_SISE - 7) {
                    }
                    orientation = 's';
                }
            }
            break;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.setColor(myPacmanColor);
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

    }

    public int getId() {
        return id;
    }
    
    @Override
    public int getX() {
        return x; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int getY() {
        return y; //To change body of generated methods, choose Tools | Templates.
    }

    public int getZ() {
        return z;
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    
}
