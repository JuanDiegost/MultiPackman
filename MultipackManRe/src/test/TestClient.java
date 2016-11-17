/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Client;
import value.Global;

/**
 *
 * @author Juan Diego Molina
 */
public class TestClient {
    
    
    public static void main(String[] args) {
        try {
            Scanner scanner=new Scanner(System.in);
            String ipServer="";
            System.out.println("Por favor escriba la ip del servidor: ");
            ipServer=scanner.nextLine();
            Client client=new Client(ipServer);
            System.out.println("Por favor escriba Su nombre: ");
            client.sendString(Global.ACTION_REGISTER);
            String name="";
            name=scanner.nextLine();
            InetAddress address=InetAddress.getLocalHost();
            client.sendObject(address.getHostAddress());
            client.sendObject(name);
        } catch (IOException ex) {
            Logger.getLogger(TestClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}