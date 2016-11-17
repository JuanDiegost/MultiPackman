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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import value.Global;
import view.MainWindowServer;

/**
 *
 * @author Juan Diego Molina
 */
public class Client extends Thread{

    private Socket socket;
    private ObjectOutputStream tx;
    private ObjectInputStream rx;
    private String action;
    private MainWindowServer windows;

    public Client(String address) {
        try {
            this.socket=new Socket(address, Global.DEFAULT_PORT);
            windows=new MainWindowServer();
            action="";
            try {
                rx=new ObjectInputStream(socket.getInputStream());
                tx=new ObjectOutputStream(socket.getOutputStream());
                tx.flush();
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            start();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
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
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
                switch (action){
                    case Global.ACTION_SHOW_WINDOW:
                        windows.setModelName((DefaultListModel<String>) receiveObject());
                        windows.setModelIp((DefaultListModel<String>) receiveObject());
                        windows.setTitle("user");
                        //windowServer.setVisible(true);
                        break;
                    case Global.ACTION_NEW_USER:
                        windows.addClient(receiveAction(), receiveAction());
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
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