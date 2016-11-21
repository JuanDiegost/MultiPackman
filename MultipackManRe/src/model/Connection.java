/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.awt.Point;
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
public class Connection extends Thread {

    public static int ID_COUNT = 0;
    private Socket socket;
    private ObjectOutputStream tx;
    private ObjectInputStream rx;
    private String action;
    private MainWindowServer mainWindowServer;
    private ArrayList<Connection> listConnections;
    private int id;
    private String ip;
    private String name;
    private int score;
    private boolean working;

    public Connection(Socket socket, MainWindowServer mainWindowServer, ArrayList<Connection> connections) {
        this.socket = socket;
        ID_COUNT++;
        this.score = 0;
        this.id = ID_COUNT;
        this.working = true;
        this.mainWindowServer = mainWindowServer;
        this.listConnections = connections;
        action = "";
        try {
            tx = new ObjectOutputStream(socket.getOutputStream());
            tx.flush();
            rx = new ObjectInputStream(socket.getInputStream());

        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        start();
    }

    private void addScore() {
        score++;
        for (Connection listConnection : listConnections) {
            try {
                listConnection.sendString(Global.ACTION_SCORE);
                listConnection.sendObject(score);
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void newUser() {
        for (Connection listConnection : listConnections) {
            if (listConnection.getIdUser() != getIdUser()) {
                try {
                    listConnection.sendString(Global.ACTION_NEW_OTHER_USER);
                    listConnection.sendObject(id);
                    listConnection.sendObject(name);
                } catch (IOException ex) {
                    Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        listConnections.add(this);
    }

    public void maxNumberUserConnect() {
        try {
            sendString(Global.ACTION_CLOSE_CONNECTION);
            sendString(Global.TEXT_USER_MAX_CONNECT);
            closeConnection();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void closeConnection() throws IOException {
        rx.close();
        tx.close();
        socket.close();
        working = false;
    }

    public void connectionAccepted() {
        try {
            sendString(Global.ACTION_NEW_USER);
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void sendAll(String ip, String name) {
        for (Connection connection : listConnections) {
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
        while (working) {
            try {
                try {
                    action = receiveAction();
                } catch (IOException ex) {
                    mainWindowServer.remove(id + "." + name);
                    socket.close();
                }
                switch (action) {
                    case Global.ACTION_REGISTER:
                        System.out.println(id);
                        name = (String) receiveObject();
                        ip = (String) receiveObject();
                        mainWindowServer.addClient(ip, id + "." + name);
                        newUser();
                        //sendAll(name, ip);
                        //sendList();
                        break;
                    case Global.ACTION_MOVE:
                        moveUser();
                        break;
                    case Global.ACTION_EAT_COOKIE:
                        userEatCookie();
                        addScore();
                        break;
                }
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void sendList() {
        try {
            sendString(Global.ACTION_SHOW_WINDOW);
            sendObject(mainWindowServer.getModelName());
            sendObject(mainWindowServer.getModelIp());
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean validConnectionUser() throws IOException {
        int i = 0;
        for (Connection connection : listConnections) {
            if (ip.equals(connection.getIp())) {
                i++;
            }
        }
        return i < Server.CONNECTION_MAX_USER;
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

    public String getIp() {
        return ip;
    }
    
    public int getIdUser(){
        return id;
    }

    public void maxNumberConnectionByUser() {
        try {
            sendString(Global.ACTION_CLOSE_CONNECTION);
            sendString(Global.TEXT_MAX_CONNECTION_BY_USER);
            closeConnection();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void moveUser() {
        try {
            Point point = (Point) receiveObject();
            for (Connection connection : listConnections) {
                if (connection.getIdUser() != getIdUser()) {
                    try {
                        System.out.println("model.Connection.moveUser()------------");
                        connection.sendString(Global.ACTION_MOVE_RIVALS_PACKMAN);
                        connection.sendObject(id);
                        connection.sendObject(point);
                    } catch (IOException ex) {
                        Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Point generateCookie() {
        return new Point((int) (Math.random() * Global.DIMENCION.getWidth()), (int) (Math.random() * Global.DIMENCION.getHeight()));
    }

    private void userEatCookie() {
        Point point = generateCookie();
        for (Connection connection : listConnections) {
            try {
                connection.sendString(Global.ACTION_SPAWN_COOKIE);
                connection.sendObject(point);
            } catch (IOException ex) {
                Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
