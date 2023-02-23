/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package server;

import object.TxRxData;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author xavi
 */
public class Server {

    // Atibuts
    static int port = 5555;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private InputStreamReader in2;


    private ObjectInputStream obIn;

    private TxRxData paquet;


    public void start(int port) throws IOException, ClassNotFoundException {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();

        //out = new PrintWriter(clientSocket.getOutputStream(), true);
        //in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        obIn = new ObjectInputStream(clientSocket.getInputStream());

        paquet = (TxRxData) obIn.readObject();

        System.out.println(paquet.getUser().getNom());


        // Depenent del que reb, tornar
//        String greeting = in.readLine();
//        System.out.println(greeting);


//            if ("hello server".equals(greeting)) {
//                out.println("hello client");
//            }
//            else {
//                out.println("unrecognised greeting");
//            }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }
    
    public static void main(String[] args) throws IOException {
        Server server = new Server();
        try {
            server.start(port);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    
}
