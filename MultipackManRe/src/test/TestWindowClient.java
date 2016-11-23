/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import controller.ControllerButtons;
import java.awt.Point;
import view.WindowClientGame;

/**
 *
 * @author KAROL ALFONSO
 */
public class TestWindowClient {
    
    public static void main(String args[]) {
        
        WindowClientGame wc=new WindowClientGame("localhost", "name", new Point(30, 50), new Point(100, 200));
        wc.setVisible(true);
        
    }

}
