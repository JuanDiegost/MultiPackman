/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Server;

/**
 *
 * @author Yuliana, Pedro, Karol, Juan, Jhon Edison 
 */
public class ServerTest {
    public static void main(String[] args) {
        try {
            Server server=new Server();
        } catch (IOException ex) {
            Logger.getLogger(ServerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}