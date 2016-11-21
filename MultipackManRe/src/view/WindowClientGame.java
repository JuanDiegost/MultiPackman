/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerButtons;
import java.awt.Point;
import javax.swing.JFrame;

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
    public WindowClientGame(String ip, String name, Point positionPacman) {
        this.ip=ip;
        this.name=name;
        this.positionPacman=positionPacman;
      jPanelGame=new JPanelGame(ip,name,positionPacman);
        this.add(jPanelGame);
        this.controllerButtons=new ControllerButtons(jPanelGame);
       jPanelButtons= new JPanelButtons(controllerButtons);
        this.add(jPanelButtons);
        this.setTitle("GAME PACMAN");
        this.setSize(900,550);
        this.setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
    }
public void init(){
     
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
        return positionPacman;
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
