/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import javax.swing.JOptionPane;
import model.Client;

/**
 *
 * @author Juan Diego Molina
 */
public class TestClient {
    
    
    public static void main(String[] args) {
        Client client=new Client(JOptionPane.showInputDialog("Por favor escriba la ip del servidor: "));
    }
}