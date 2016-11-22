/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import model.AbstractPacman;
import model.Pacman;

/**
 *
 * @author KAROL ALFONSO
 */
public class JPanelGame extends JPanel {

    private AbstractPacman abstractPacman;
    private PackManConponent jBuPacman;
    private ArrayList<PackManConponent> arrayButtonsPacman;

    public JPanelGame(String ip, String name, Point pointPacman) {
        abstractPacman = new Pacman(ip, name, pointPacman);
        this.setLayout(null);
        Border bordejpanel = new TitledBorder(new EtchedBorder(), "game");
        this.setBorder(bordejpanel);
        this.setSize(500, 550);
        arrayButtonsPacman = new ArrayList<>();
        drawButtonPacman(name);
//        this.setBackground(Color.blue);

    }

    public void addRival(int id, String name,Point point,int score) {
        PackManConponent newPackman = new PackManConponent(name, id, score, Color.yellow);
        newPackman.setBounds(point.x, point.y, (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        newPackman.setBorder(null);
        newPackman.setContentAreaFilled(false);
//        jBuPacman.setEnabled(false);
        this.add(newPackman);
        arrayButtonsPacman.add(newPackman);
        repaint();
    }

    public void movePackmanRival(Point point, int id) {
        for (PackManConponent packManConponent : arrayButtonsPacman) {
            if (packManConponent.getId()==id) {
                packManConponent.setBounds(point.x, point.y,(int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
                return;
            }
        }
        repaint();
    }

    public Point getPositionPackman() {
        return new Point((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos());
    }
    
    public void setScore(int score){
        jBuPacman.setScore(score);
    }

    public void drawButtonPacman(String name) {
        jBuPacman = new PackManConponent(name, 0, 0, Color.yellow);
        
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(), (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());

        jBuPacman.setBorder(null);
        jBuPacman.setContentAreaFilled(false);
//        jBuPacman.setEnabled(false);
        this.add(jBuPacman);
    }

    public void moveUp() {
        abstractPacman.makeMovementUP(10);
        jBuPacman.moveUp();
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(),
                (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        repaint();
    }

    public void moveDown() {
        abstractPacman.makeMovementDown(5);
        jBuPacman.moveDown();
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(),
                (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        repaint();
    }

    public void moveLeft() {
        abstractPacman.makeMovementLeft(5);
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(),
                (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        repaint();
    }

    public void moveRight() {
        abstractPacman.makeMovementRight(5);
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(),
                (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        repaint();

    }

    public void moveDia_Up_Left() {
        abstractPacman.makeMovementDia_Up_Left(5);
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(),
                (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        repaint();
    }

    public void moveDia_Up_Right() {
        abstractPacman.makeMovementDia_Up_Right(5);
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(),
                (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        repaint();
    }

    public void moveDia_Down_Left() {
        abstractPacman.makeMovementDia_Down_Left(5);
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(),
                (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        repaint();
    }

    public void moveDia_Down_Right() {
        abstractPacman.makeMovementDia_Down_Right(5);
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(),
                (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        repaint();
    }

}
