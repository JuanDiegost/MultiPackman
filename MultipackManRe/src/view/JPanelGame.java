/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

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
import model.Pacman;

/**
 *
 * @author KAROL ALFONSO
 */
public class JPanelGame extends JPanel {

    private AbstractPacman abstractPacman;
    private JButton jBuPacman;
    private ArrayList<JButton> arrayButtonsPacman;

    public JPanelGame(String ip, String name, Point pointPacman) {
        abstractPacman = new Pacman(ip, name, pointPacman);
        this.setLayout(null);
        Border bordejpanel = new TitledBorder(new EtchedBorder(), "game");
        this.setBorder(bordejpanel);
        this.setSize(500, 550);
        arrayButtonsPacman = new ArrayList<>();
        drawButtonPacman();
//        this.setBackground(Color.blue);

    }

    public void drawButtonPacman() {
        ImageIcon imageIcon = new ImageIcon("iconProyect/Pacman.png");
        Icon icon = new ImageIcon(imageIcon.getImage().getScaledInstance(jBuPacman.getWidth(), jBuPacman.getHeight(), Image.SCALE_DEFAULT));
        jBuPacman=new JButton(icon);
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(), (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());

        this.repaint();
//        Image img = new ImageIcon("iconProyect/Pacman.png").getImage().getScaledInstance( 200, -12, java.awt.Image.SCALE_AREA_AVERAGING);
//	JButton btn = new JButton(new ImageIcon(img));
        jBuPacman.setBorder(null);
        jBuPacman.setContentAreaFilled(false);
//        jBuPacman.setEnabled(false);
        this.add(jBuPacman);
    }

    public void moveUp() {
        abstractPacman.makeMovementUP(10);
        jBuPacman.setBounds((int) abstractPacman.getX_Pos(), (int) abstractPacman.getY_Pos(),
                (int) abstractPacman.getWidth(), (int) abstractPacman.getHeight());
        System.out.println("aquiiii");
        repaint();
    }

    public void moveDown() {
        abstractPacman.makeMovementDown(5);
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
