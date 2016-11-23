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

    public abstract void move(char d);
    public abstract void eat();

    @Override
    public void actionPerformed(ActionEvent ae) {
        switch (ae.getActionCommand()) {
            case GlobalActionsAnimation.ACTION_UP:
                jPanelGame.moveUp();
                move('n');
                break;
            case GlobalActionsAnimation.ACTION_DOWN:
                jPanelGame.moveDown();
                move('s');
                break;
            case GlobalActionsAnimation.ACTION_LEFT:
                jPanelGame.moveLeft();
                move('l');
                break;
            case GlobalActionsAnimation.ACTION_RIGHT:
                jPanelGame.moveRight();
                move('r');
                break;
            case GlobalActionsAnimation.ACTION_DIAG_UP_LEFT:
                jPanelGame.moveDia_Up_Left();
                move('l');
                break;
            case GlobalActionsAnimation.ACTION_DIAG_UP_RIGHT:
                jPanelGame.moveDia_Up_Right();
                move('r');
                break;
            case GlobalActionsAnimation.ACTION_DIAG_DOWN_LEFT:
                jPanelGame.moveDia_Down_Left();
                move('l');
                break;
            case GlobalActionsAnimation.ACTION_DIAG_DOWN_RIGHT:
                jPanelGame.moveDia_Down_Right();
                move('r');
                break;
        }
        if (jPanelGame.getAbstractPacman().isEatCookie(jPanelGame.getJbuCookie())) {
            eat();
            jPanelGame.addScore();
        }
    }

    public void actionPerformed(String action) {
        switch (action) {
            case GlobalActionsAnimation.ACTION_UP:
                jPanelGame.moveUp();
                move('n');
                break;
            case GlobalActionsAnimation.ACTION_DOWN:
                jPanelGame.moveDown();
                move('s');
                break;
            case GlobalActionsAnimation.ACTION_LEFT:
                jPanelGame.moveLeft();
                move('l');
                break;
            case GlobalActionsAnimation.ACTION_RIGHT:
                jPanelGame.moveRight();
                move('r');
                break;
            case GlobalActionsAnimation.ACTION_DIAG_UP_LEFT:
                jPanelGame.moveDia_Up_Left();
                move('l');
                break;
            case GlobalActionsAnimation.ACTION_DIAG_UP_RIGHT:
                jPanelGame.moveDia_Up_Right();
                move('r');
                break;
            case GlobalActionsAnimation.ACTION_DIAG_DOWN_LEFT:
                jPanelGame.moveDia_Down_Left();
                move('l');
                break;
            case GlobalActionsAnimation.ACTION_DIAG_DOWN_RIGHT:
                jPanelGame.moveDia_Down_Right();
                move('r');
                break;
        }
       
        if (jPanelGame.getAbstractPacman().isEatCookie(jPanelGame.getJbuCookie())) {
            eat();
            jPanelGame.addScore();
        }
    }
}