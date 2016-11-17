/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import value.Global;
import view.MainWindowServer;

/**
 *
 * @author Juan Diego Molina
 */
public class Connection extends Thread{

    private Socket socket;
    private ObjectOutputStream tx;
    private ObjectInputStream rx;
    private String action;
    private MainWindowServer mainWindowServer;
    private ArrayList<Connection> connections;
    private String ip;
    private String name;

    public Connection(Socket socket,MainWindowServer mainWindowServer,ArrayList<Connection> connections) {
        this.socket=socket;
        this.mainWindowServer=mainWindowServer;
        this.connections=connections;
        action="";
        try {
            tx=new ObjectOutputStream(socket.getOutputStream());
            tx.flush();
            rx=new ObjectInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        start();
    }
     
    private void sendAll(String ip,String name){
        for (Connection connection : connections) {
            try {
                connection.sendString(name);
                connection.sendString(ip);
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    @Override
    public void run() {
        super.run();
        while (true) {            
            try {            
                try {
                    action = receiveAction();
                } catch (IOException ex) {
                    mainWindowServer.remove(name, ip);
                }
                switch (action){
                    case Global.ACTION_REGISTER:
                         ip=(String) receiveObject();
                         name=(String) receiveObject();
                        mainWindowServer.addClient(ip, name);
                        sendAll(name, ip);
                        sendList();
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void sendList(){
        try {
            sendString(Global.ACTION_SHOW_WINDOW);
            sendObject(mainWindowServer.getModelName());
            sendObject(mainWindowServer.getModelIp());
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendString(String string) throws IOException{
        tx.writeUTF(string);
        tx.flush();
    }
    
    public void sendObject(Object object) throws IOException{
        tx.writeObject(object);
        tx.flush();
    }
    
    public Object receiveObject() throws IOException, ClassNotFoundException{
        return rx.readObject();
    }
    
    public String receiveAction() throws IOException{
        return rx.readUTF();
    }
    
}