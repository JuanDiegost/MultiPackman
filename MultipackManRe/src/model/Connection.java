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
import java.net.Socket;
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
    private int id;
    private String ip;
    private String name;
    private int score;
    private Point point;
    private Color color;
    private boolean working;

    public Connection(Socket socket, MainWindowServer mainWindowServer) {
        this.socket = socket;
        ID_COUNT++;
        this.score = 0;
        this.id = ID_COUNT;
        this.working = true;
        this.mainWindowServer = mainWindowServer;
        action = "";
        try {
            tx = new ObjectOutputStream(socket.getOutputStream());
            tx.flush();
            rx = new ObjectInputStream(socket.getInputStream());

        } catch (IOException ex) {
        }
        this.ip = socket.getInetAddress().getHostAddress();
        start();
    }

    public int getScore() {
        return score;
    }

    public Point getPoint() {
        return point;
    }

    public Color getColor() {
        return color;
    }

    private void newUser() {
        for (Connection connection : Server.listConnections) {
            if (connection.getIdUser() != getIdUser()) {
                try {
                    System.out.println(id + "  " + connection.getIdUser());
                    connection.sendString(Global.ACTION_NEW_OTHER_USER);
                    connection.sendObject(id);
                    connection.sendObject(name);
                    connection.sendObject(score);
                    connection.sendObject(point);
                    connection.sendObject(color);
                    sendString(Global.ACTION_NEW_OTHER_USER);
                    sendObject(connection.getIdUser());
                    sendObject(connection.getNameUser());
                    sendObject(connection.getScore());
                    sendObject(connection.getPoint());
                    sendObject(connection.getColor());//enviar color del listConnection
                } catch (IOException ex) {
                }
            }
        }
        Server.listConnections.add(this);
    }

    public void maxNumberUserConnect() {
        try {
            sendString(Global.ACTION_CLOSE_CONNECTION);
            sendString(Global.TEXT_USER_MAX_CONNECT);
            closeConnection();
        } catch (IOException ex) {
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
        }
    }

    private void closeConnectionByUser() {
        for (Connection connection : Server.listConnections) {
            try {
                connection.sendString(Global.ACTION_CLOSE_CONNECTION_BY_USER);
                connection.sendObject(getIdUser());
            } catch (IOException ex) {
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
                    Server.listConnections.remove(this);
                    closeConnectionByUser();
                    socket.close();
                }
                switch (action) {
                    case Global.ACTION_REGISTER:
                        name = (String) receiveObject();
                        ip = (String) receiveObject();
                        point = (Point) receiveObject();
                        color = (Color) receiveObject();
                        if (Server.pointCookie == null) {
                            Server.pointCookie = generateCookie();
                        }
                        sendObject(Server.pointCookie);
                        sendObject(getIdUser());
                        mainWindowServer.addClient(ip, id + "." + name);
                        newUser();
                        //sendAll(name, ip);
                        //sendList();
                        break;
                    case Global.ACTION_MOVE:
                        moveUser();
                        break;
                    case Global.ACTION_EAT_COOKIE:
                        System.out.println(id + " Accion comi");
                        userEatCookie();
                        break;
                }
            } catch (IOException | ClassNotFoundException ex) {
            }
        }
    }

    public void sendList() {
        try {
            sendString(Global.ACTION_SHOW_WINDOW);
            sendObject(mainWindowServer.getModelName());
            sendObject(mainWindowServer.getModelIp());
        } catch (IOException ex) {
        }
    }

    public boolean validConnectionUser() throws IOException {
        int i = 0;
        for (Connection connection : Server.listConnections) {
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

    public int getIdUser() {
        return id;
    }

    public void maxNumberConnectionByUser() {
        try {
            sendString(Global.ACTION_CLOSE_CONNECTION);
            sendString(Global.TEXT_MAX_CONNECTION_BY_USER);
            closeConnection();
        } catch (IOException ex) {
        }
    }

    private void moveUser() {
        try {
            point = (Point) receiveObject();
            char d = (char) receiveObject();
            for (Connection connection : Server.listConnections) {
                try {
                    connection.sendString(Global.ACTION_MOVE_RIVALS_PACKMAN);
                    connection.sendObject(getIdUser());
                    connection.sendObject(point);
                    connection.sendObject(d);
                } catch (IOException ex) {
                }
            }
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
        }

    }

    public String getNameUser() {
        return name;
    }

    public Point generateCookie() {
        return new Point((int) (Math.random() * Global.DIMENSION.getWidth()), (int) (Math.random() * Global.DIMENSION.getHeight()));
    }

    private void userEatCookie() {        
        score++;      
        synchronized (this) {
            Server.pointCookie = generateCookie();
        }
        for (Connection connection : Server.listConnections) {
            try {
                connection.sendString(Global.ACTION_SPAWN_COOKIE);
                connection.sendObject(Server.pointCookie);
                connection.sendString(Global.ACTION_SCORE);
                connection.sendObject(getIdUser());
                connection.sendObject(score);
            } catch (IOException ex) {
            }
        }
    }
}