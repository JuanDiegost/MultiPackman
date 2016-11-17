package model;


import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import value.Global;
import view.MainWindowServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Juan Diego Molina
 */
public class Server extends Thread{
    private ServerSocket server;
    private MainWindowServer console;
    private ArrayList<Connection> connections;

    public Server() throws IOException {
        this.server = new ServerSocket(Global.DEFAULT_PORT);
        console=new MainWindowServer();
        connections=new ArrayList<>();
        console.setVisible(true);
        start();
    }

    @Override
    public void run() {
        super.run();
        messageInit();
        while (true) {            
            Socket socket=null;
            try {
                socket=server.accept();
                System.out.println("model.Server.run()");
                Connection connection=new Connection(socket,console,connections);
                connections.add(connection);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void messageInit(){
        System.out.println("Servidor List");
        System.out.println("Servicio de hacer sumas");
        try {
            InetAddress address=InetAddress.getLocalHost();
            System.out.println("IP SERVIDOR: "+address.getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}