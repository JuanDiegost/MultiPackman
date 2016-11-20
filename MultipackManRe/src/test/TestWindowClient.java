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
        
        WindowClientGame wc=new WindowClientGame("10.4.85.93","KaYu",new Point(30, 50));
        wc.setVisible(true);
        
    }

}
