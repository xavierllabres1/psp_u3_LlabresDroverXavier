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
    static int port = 9595;
    private static ServerSocket serverSocket;
    private static Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private InputStreamReader in2;


    private static ObjectInputStream obIn;
    private static ObjectOutputStream obOut;

    private TxRxData paquet;

//
//    public void start(int port) throws IOException, ClassNotFoundException {
//        serverSocket = new ServerSocket(port);
//        clientSocket = serverSocket.accept();
//
//        //out = new PrintWriter(clientSocket.getOutputStream(), true);
//        //in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//
//        obOut = new ObjectOutputStream(clientSocket.getOutputStream());
//        obIn = new ObjectInputStream(clientSocket.getInputStream());
//        boolean lectura = true;
//
//            while (lectura) {
//                try {
//                    Object obj = obIn.readObject();
//                    System.out.println("Received object: " + obj);
//
//                    obOut.writeObject("Hello, Client!");
//                    obOut.flush();
//                } catch (EOFException e) {
//                    System.out.println("all dades");
//                    break;
//                }
//            }
//            System.out.println("out while lectura");
//
//    }



    // Inici del servidor
    public static void main(String[] args) throws IOException {
//        Server server = new Server();
//        try {
//            server.start(port);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
        boolean serverOn = true;

        // Iniciar el servidor
        serverSocket = new ServerSocket(port);
        while(serverOn) {

            // Definic socket i fluxes
            clientSocket = serverSocket.accept();
            obOut = new ObjectOutputStream(clientSocket.getOutputStream());
            obIn = new ObjectInputStream(clientSocket.getInputStream());

            // Crear un fil i transfeir el socket i fluxe per poder seguir escoltants
            new ServerFil(clientSocket, obIn, obOut).start();
        }
    }

    // Per aturar fluxes
    public void stop() throws IOException {
        if (obIn != null) {obIn.close();}
        if (obOut != null) {obOut.close();}
        if (clientSocket != null) {clientSocket.close();}
        if (serverSocket != null) {serverSocket.close();}
    }

}