/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import value.GlobalActionsAnimation;
import view.JPanelGame;

/**
 *
 * @author KAROL ALFONSO
 */
public abstract class ControllerButtons implements ActionListener {
private JPanelGame jPanelGame;

    public void setjPanelGame(JPanelGame jPanelGame) {
        this.jPanelGame = jPanelGame;
    }


    public abstract void move();
    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case GlobalActionsAnimation.ACTION_UP:
                jPanelGame.moveUp();
                move();
                               break;
            case GlobalActionsAnimation.ACTION_DOWN:
                jPanelGame.moveDown();
                move();
                break;
            case GlobalActionsAnimation.ACTION_LEFT:
                jPanelGame.moveLeft();
                move();
                break;
            case GlobalActionsAnimation.ACTION_RIGHT:
                jPanelGame.moveRight();
                move();
                break;
            case GlobalActionsAnimation.ACTION_DIAG_UP_LEFT:
                jPanelGame.moveDia_Up_Left();
                move();
                break;
            case GlobalActionsAnimation.ACTION_DIAG_UP_RIGHT:
                jPanelGame.moveDia_Up_Right();
                move();
                break;
            case GlobalActionsAnimation.ACTION_DIAG_DOWN_LEFT:
                jPanelGame.moveDia_Down_Left();
                move();
                break;
            case GlobalActionsAnimation.ACTION_DIAG_DOWN_RIGHT:
                jPanelGame.moveDia_Down_Right();
                move();
                break;
        }
        if (jPanelGame.getAbstractPacman().eatCookie(jPanelGame.getJbuCookie())) {
            jPanelGame.remove(jPanelGame.getJbuCookie());
//              jPanelGame.repaint();
            jPanelGame.revalidate();
            System.out.println("borre galleta");
            //aqui deben enviar la nueva posicion de la galleta, lo descomentan y funciona
//                a la perfeccion
//               jPanelGame.drawButtonCookie("posicion aleatoria");
            jPanelGame.repaint();

        }
    }
}
