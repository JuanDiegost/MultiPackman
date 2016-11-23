/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import value.Global;
import view.WindowClientGame;

/**
 *
 * @author Juan Diego Molina
 */
public class Client extends controller.ControllerButtons implements Runnable {

    private Socket socket;
    private ObjectOutputStream tx;
    private ObjectInputStream rx;
    private String action;
    private WindowClientGame game;
    private Thread thread;
    private int score;
    
    private boolean work;

    public Client(String address) {
        try {
            this.socket = new Socket(address, Global.DEFAULT_PORT);
            work = true;
            action = "";
            score=0;
            thread=new Thread(this);
            configureConnection();
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void eatCookie(){
        try {
            sendString(Global.ACTION_EAT_COOKIE);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void move(){
        try {
            sendString(Global.ACTION_MOVE);
            sendObject(game.getPositionPacman());
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }      
    }

    private void configureConnection() throws IOException {
        rx = new ObjectInputStream(socket.getInputStream());
        tx = new ObjectOutputStream(socket.getOutputStream());
        tx.flush();
        thread.start();
    }

    @Override
    public void run() {
        while (work) {
            //if si se comio la galleta
            
            try {
                try {
                    action = receiveAction();
                } catch (IOException ex) {

                }
                switch (action) {
                    case Global.ACTION_IP:
                        //InetAddress address = InetAddress.getLocalHost();
                        //sendString(address.getHostAddress());
                        break;
                    case Global.ACTION_NEW_USER:
                        register();
                        break;
                    case Global.ACTION_CLOSE_CONNECTION:
                        String message = receiveAction();
                        rx.close();
                        tx.close();
                        socket.close();
                        work = false;
                        JOptionPane.showMessageDialog(null, message);
                        break;
                    case Global.ACTION_SPAWN_COOKIE:
                        //dar punro galleta ventana
                        receiveObject();
                        break;
                    case Global.ACTION_MOVE_RIVALS_PACKMAN:
                        int id=(int) receiveObject();
                        Point point=(Point) receiveObject();
                        game.moveRivals(point, id);
                        break;
                    case Global.ACTION_NEW_OTHER_USER:
                        //Optener id y nombre cliente
                        int idNewClient=(int) receiveObject();
                        String name=(String) receiveObject();
                        int score=(int)receiveObject();
                        //Point pointR=(Point) receiveObject();
                        Color color=(Color) receiveObject();
                        //agrgar rival envia el color
                        game.addRival(name, idNewClient,new Point(30, 50),score);
                        break;
                    case Global.ACTION_GET_POSITION:
                        sendObject(game.getPositionPacman());
                        break;
                    case Global.ACTION_GET_SCORE:
                        sendObject(this.score);
                        break;
                }
            } catch (IOException ex) {

            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
            //move();
        }
    }

    public void sendString(String string) throws IOException {
        tx.writeUTF(string);
        tx.flush();
    }

    public void sendObject(Object object) throws IOException {
        tx.writeObject(object);
        tx.flush();
    }

    public Object receiveObject() throws IOException, ClassNotFoundException {
        return rx.readObject();
    }

    public String receiveAction() throws IOException {
        return rx.readUTF();
    }

    private void register() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            String name = JOptionPane.showInputDialog("Por favor escriba Su nombre: ");
            sendString(Global.ACTION_REGISTER);
            sendObject(name);
            sendObject(address.getHostAddress());
            //sendObject();enviar color
            this.game=new WindowClientGame(address.getHostAddress(), name, new Point(30, 50));
            game.setController(this);
            setjPanelGame(game.getjPanelGame());
            game.setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
