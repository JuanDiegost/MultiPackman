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
public class ControllerButtons implements ActionListener {
private JPanelGame jPanelGame;

    public ControllerButtons(JPanelGame jPanelGame) {
        this.jPanelGame=jPanelGame;
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case GlobalActionsAnimation.ACTION_UP:
                jPanelGame.moveUp();
                               break;
            case GlobalActionsAnimation.ACTION_DOWN:
                jPanelGame.moveDown();
                break;
            case GlobalActionsAnimation.ACTION_LEFT:
                jPanelGame.moveLeft();
                break;
            case GlobalActionsAnimation.ACTION_RIGHT:
                jPanelGame.moveRight();
                break;
            case GlobalActionsAnimation.ACTION_DIAG_UP_LEFT:
                jPanelGame.moveDia_Up_Left();
                break;
            case GlobalActionsAnimation.ACTION_DIAG_UP_RIGHT:
                jPanelGame.moveDia_Up_Right();
                break;
            case GlobalActionsAnimation.ACTION_DIAG_DOWN_LEFT:
                jPanelGame.moveDia_Down_Left();
                break;
            case GlobalActionsAnimation.ACTION_DIAG_DOWN_RIGHT:
                jPanelGame.moveDia_Down_Right();
                break;

        }

    }
}
