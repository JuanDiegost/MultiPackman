package model;


import java.awt.Point;
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
public class Server extends Thread {

    private ServerSocket server;
    private MainWindowServer console;
    public static ArrayList<Connection> listConnections;
    public static Point pointCookie;
    public ArrayList<IpData> datas;
    public static final int CONNECTION_MAX_SERVER = 5;
    public static final int CONNECTION_MAX_USER = 3;

    public Server() throws IOException {
        this.server = new ServerSocket(Global.DEFAULT_PORT);
        console = new MainWindowServer();
        listConnections = new ArrayList<>();
        datas  = new ArrayList<>();
        console.setVisible(true);
        start();
    }

    @Override
    public void run() {
        super.run();
        messageInit();
        int countConneccion = 0;
        while (true) {
            Socket socket = null;
            try {
                socket = server.accept();
                Connection connection = new Connection(socket, console);
                //connection.sendString(Global.ACTION_IP);
                if (datas.isEmpty()) {
                    datas.add(new IpData(connection.getIp()));
                }else{
                    for(IpData data : datas) {
                        if (data.existsHere(connection.getIp())) {
                            if (data.getNumberOfConettion()< CONNECTION_MAX_USER) {
                                data.compareHere();                                
                            }else{
                                connection.maxNumberConnectionByUser();
                            }
                        }
                    }
                }
                if (countConneccion < CONNECTION_MAX_SERVER) {
                    connection.connectionAccepted();
                    countConneccion++;
                } else {
                    connection.maxNumberUserConnect();
                }
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void messageInit() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            System.out.println("IP SERVIDOR: " + address.getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
