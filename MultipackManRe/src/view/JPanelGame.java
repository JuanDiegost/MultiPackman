/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.awt.Color;
import java.awt.Image;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import model.AbstractPacman;
import model.Cookie;
import model.Pacman;

/**
 *
 * @author Yuliana, Pedro, Karol, Juan, Jhon Edison 
 */
public class JPanelGame extends JPanel {

    private AbstractPacman abstractPacman;
    private PackManConponent jBuPacman;
    private Cookie cookie;

    private JButton jbuCookie;
    private ArrayList<PackManConponent> arrayButtonsPacman;
//------------------------------Constructor------------------------------//

    public JPanelGame(String ip, int id,String name, Point pointPacman, Point pointCookie, Color color) {
        abstractPacman = new Pacman(ip, name, pointPacman);
        cookie = new Cookie(pointCookie);
        this.setLayout(null);
        Border bordejpanel = new TitledBorder(new EtchedBorder(), "Game");
        this.setBorder(bordejpanel);
//        
        arrayButtonsPacman = new ArrayList<>();
        drawButtonPacman(id,name, color);
        drawButtonCookie(pointCookie);
//        this.setBackground(Color.blue);

    }

    /**
     * Este metodo agrega los rivales con sus caracteristicas.
     *
     * @param id
     * @param name
     * @param point
     * @param score
     * @param color
     */
    public void addRival(int id, String name, Point point, int score, Color color) {
        PackManConponent newPackman = new PackManConponent(name, id, score, color);
        newPackman.setBounds(point.x, point.y, (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        newPackman.setBorder(null);
        newPackman.setContentAreaFilled(false);
//        jBuPacman.setEnabled(false);
        this.add(newPackman);
        arrayButtonsPacman.add(newPackman);
        repaint();
    }

    public void movePackmanRival(Point point, int id,char d) {
        for (PackManConponent packManConponent : arrayButtonsPacman) {
            if (packManConponent.getId() == id) {
                packManConponent.setBounds(point.x, point.y, (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
                packManConponent.setOrientation(d);
                return;
            }
        }
        repaint();
    }

    public Point getPositionPackman() {
        return new Point((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos());
    }

    public void setScore(int score) {
        jBuPacman.setScore(score);
    }
    
    public void addScore(){
        jBuPacman.addScore();
    }

    public void drawButtonPacman(int id,String name, Color color) {
        jBuPacman = new PackManConponent(name, id, 0, color);
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(), (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        jBuPacman.setBorder(null);
        jBuPacman.setContentAreaFilled(false);
//        jBuPacman.setEnabled(false);
        this.add(jBuPacman);
    }

    public void setPositionCookie(Point positionCookie) {
        jbuCookie.setBounds((int) positionCookie.getX(), (int) positionCookie.getY(), (int) cookie.getWidth(), (int) cookie.getHeight());

    }

    public void drawButtonCookie(Point positionCookie) {
        jbuCookie = new JButton();
        jbuCookie.setBounds((int) positionCookie.getX(), (int) positionCookie.getY(), (int) cookie.getWidth(), (int) cookie.getHeight());
        jbuCookie.setBorder(null);
        jbuCookie.setContentAreaFilled(false);
        jBuPacman.setEnabled(false);
        ImageIcon imageIcon = new ImageIcon("src/iconProyect/galleta.png");
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance((int) cookie.getWidth(), (int) cookie.getHeight(), Image.SCALE_DEFAULT));
        this.repaint();
        jbuCookie.setIcon(icon);
        jbuCookie.setBackground(this.getBackground());
        //jbuCookie.setContentAreaFilled(false);
        this.add(jbuCookie);
    }

    public void removePacman(int idPacman) {
        for (PackManConponent pacman : arrayButtonsPacman) {
            if (pacman.getId() == idPacman) {
                arrayButtonsPacman.remove(pacman);
                this.remove(pacman);
                this.repaint();
            }
        }
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
        jBuPacman.moveLeft();
        repaint();
    }

    public void moveRight() {
        abstractPacman.makeMovementRight(5);
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(),
                (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        jBuPacman.moveRight();
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

    public AbstractPacman getAbstractPacman() {
        return abstractPacman;
    }

    public Cookie getCookie() {
        return cookie;
    }

    public JButton getJbuCookie() {
        return jbuCookie;
    }

    void setScoreRival(int id, int score) {
        for (PackManConponent packManConponent : arrayButtonsPacman) {
            if (packManConponent.getId() == id) {
                packManConponent.setScore(score);
            }
        }
    }
}
