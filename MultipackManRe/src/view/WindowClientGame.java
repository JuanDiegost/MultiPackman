/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerButtons;
import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Point;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import model.Client;
import value.Global;
import value.GlobalActionsAnimation;

/**
 *
 * @author KAROL ALFONSO
 */
public class WindowClientGame extends JFrame {

    private JPanelButtons jPanelButtons;
    private JPanelGame jPanelGame;
    private ControllerButtons controllerButtons;
    private Point positionPacman;
    private String ip;
    private String name;

    public WindowClientGame(int id,String ip, String name, Point positionPacman, Point positionCookie, Color color) {
        this.ip = ip;
        this.name = name;
        this.positionPacman = positionPacman;
        jPanelGame = new JPanelGame(ip,id, name, positionPacman, positionCookie, color);
        this.add(jPanelGame);
        //this.setTitle("GAME PACMAN");
        this.setSize(900, 550);
        this.setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKey();
    }

    private void addKey() {
        KeyboardFocusManager ky = KeyboardFocusManager.getCurrentKeyboardFocusManager();

        ky.addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && (e.getKeyCode() == KeyEvent.VK_UP)) {
                    controllerButtons.actionPerformed(GlobalActionsAnimation.ACTION_UP);
                } else if (e.getID() == KeyEvent.KEY_PRESSED && (e.getKeyCode() == KeyEvent.VK_DOWN)) {
                    controllerButtons.actionPerformed(GlobalActionsAnimation.ACTION_DOWN);
                } else if (e.getID() == KeyEvent.KEY_PRESSED && (e.getKeyCode() == KeyEvent.VK_LEFT)) {
                    controllerButtons.actionPerformed(GlobalActionsAnimation.ACTION_LEFT);
                }else if (e.getID() == KeyEvent.KEY_PRESSED&& (e.getKeyCode() == KeyEvent.VK_RIGHT)) {
                    controllerButtons.actionPerformed(GlobalActionsAnimation.ACTION_RIGHT);
                }
                return true;
            }
        });
    }

    public void setController(ControllerButtons controllerButtons) {
        this.controllerButtons = controllerButtons;
        jPanelButtons = new JPanelButtons(controllerButtons);
        this.add(jPanelButtons);
    }

    public void init() {

    }

    public void moveRivals(Point point, int id) {
        jPanelGame.movePackmanRival(point, id);
    }

    public void setScore(int score) {
        jPanelGame.setScore(score);
    }

    public void setScoreRival(int id, int score) {
        jPanelGame.setScoreRival(id, score);
    }

    public void addRival(String name, int id, Point point, int score, Color color) {
        jPanelGame.addRival(id, name, point, score, color);
    }

    public void removeRival(int id) {
        jPanelGame.removePacman(id);
    }

    public void drawnCookie(Point point) {
        jPanelGame.setPositionCookie(point);
    }

    public JPanelButtons getjPanelButtons() {
        return jPanelButtons;
    }

    public void setjPanelButtons(JPanelButtons jPanelButtons) {
        this.jPanelButtons = jPanelButtons;
    }

    public JPanelGame getjPanelGame() {
        return jPanelGame;
    }

    public void setjPanelGame(JPanelGame jPanelGame) {
        this.jPanelGame = jPanelGame;
    }

    public ControllerButtons getControllerButtons() {
        return controllerButtons;
    }

    public void setControllerButtons(ControllerButtons controllerButtons) {
        this.controllerButtons = controllerButtons;
    }

    public Point getPositionPacman() {
        return jPanelGame.getPositionPackman();
    }

    public void setPositionPacman(Point positionPacman) {
        this.positionPacman = positionPacman;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

}
